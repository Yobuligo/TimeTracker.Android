package com.yobuligo.restaccess.internal;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yobuligo.restaccess.api.ILoginListener;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenResponse;

import org.json.JSONException;

import java.util.ArrayList;

public class Login extends BroadcastReceiver implements ILogin {
    private IDataContext dataContext;

    public Login(IDataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        checkIntent(intent);
    }

    @Override
    public void execute() {
        AuthorizationServiceConfiguration serviceConfiguration = new AuthorizationServiceConfiguration(
                Uri.parse(dataContext.getAuthorizationRequestConfig().getAuthEndpointUri()),
                Uri.parse(dataContext.getAuthorizationRequestConfig().getTokenEndpointUri())
        );

        String clientId = dataContext.getAuthorizationRequestConfig().getClientId();
        Uri redirectUri = Uri.parse("com.yobuligo.restaccess.internal:/oauth2callback");
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(
                serviceConfiguration,
                clientId,
                AuthorizationRequest.RESPONSE_TYPE_CODE,
                redirectUri
        );

        builder.setScopes("openid profile email");
        AuthorizationRequest request = builder.build();

        AuthorizationService authorizationService = new AuthorizationService(dataContext.getContext());
        Intent postAuthorizationIntent = new Intent(IDataContext.HANDLE_AUTHORIZATION_RESPONSE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(dataContext.getContext(), request.hashCode(), postAuthorizationIntent, 0);
        authorizationService.performAuthorizationRequest(request, pendingIntent);
    }

    private void handleAuthorizationResponse(@NonNull Intent intent) {
        AuthorizationResponse response = AuthorizationResponse.fromIntent(intent);
        AuthorizationException error = AuthorizationException.fromIntent(intent);
        final AuthState authState = new AuthState(response, error);

        if (response != null) {
            Log.i(IDataContext.LOG_TAG, String.format("Handled Authorization Response %s ", authState.toString()));
            AuthorizationService service = new AuthorizationService(dataContext.getContext());

            service.performTokenRequest(response.createTokenExchangeRequest(), new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse tokenResponse, @Nullable AuthorizationException exception) {
                    if (exception != null) {
                        Log.w(IDataContext.LOG_TAG, "Token Exchange failed", exception);
                    } else {
                        if (tokenResponse != null) {
                            authState.update(tokenResponse, exception);
                            persistAuthState(authState);
                            Log.i(IDataContext.LOG_TAG, String.format("Token Response [ Access Token: %s, ID Token: %s ]", tokenResponse.accessToken, tokenResponse.idToken));
                            returnToPreviousActivity();
                            onLoginCompleted();
                        }
                    }
                }
            });
        }
    }

    private void returnToPreviousActivity() {
        Intent intent = new Intent(dataContext.getContext(), dataContext.getActivityClass());
        dataContext.getContext().startActivity(intent);
    }

    private void checkIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case IDataContext.HANDLE_AUTHORIZATION_RESPONSE:
                    if (!intent.hasExtra(IDataContext.USED_INTENT)) {
                        handleAuthorizationResponse(intent);
                        intent.putExtra(IDataContext.USED_INTENT, true);
                    }
                    break;
                default:
                    // do nothing
            }
        }
    }

    private void persistAuthState(@NonNull AuthState authState) {
        Context context = dataContext.getContext();
        context.getSharedPreferences(IDataContext.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
                .putString(IDataContext.AUTH_STATE, authState.toJsonString())
                .commit();
        enablePostAuthorizationFlows();
    }

    private void enablePostAuthorizationFlows() {
        AuthState authState = dataContext.restoreAuthState();
        dataContext.setAuthState(authState);
    }

    private void onLoginCompleted() {
        ArrayList<ILoginListener> loginListeners = dataContext.getOnLoginListeners();
        for (ILoginListener loginListener : loginListeners) {
            loginListener.onLogin();
        }
    }
}