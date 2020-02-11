package com.yobuligo.restaccess.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.yobuligo.restaccess.api.IAuthorizationRequestConfig;
import com.yobuligo.restaccess.api.ILoginListener;
import com.yobuligo.restaccess.api.ILogoutListener;
import com.yobuligo.restaccess.api.IWebserviceRequestConfig;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationService;

import org.json.JSONException;

import java.util.ArrayList;

public class DataContext<T> implements IDataContext {
    private IAuthorizationRequestConfig authorizationRequestConfig;
    private IWebserviceRequestConfig webserviceRequestConfig;
    private AuthorizationService authorizationService;
    private AuthState authState;
    private ArrayList<ILoginListener> loginListeners = new ArrayList<>();
    private ArrayList<ILogoutListener> logoutListeners = new ArrayList<>();
    private Activity activity;

    public DataContext(IAuthorizationRequestConfig authorizationRequestConfig, IWebserviceRequestConfig webserviceRequestConfig, Activity activity) {
        this.authorizationRequestConfig = authorizationRequestConfig;
        this.webserviceRequestConfig = webserviceRequestConfig;
        this.activity = activity;
    }

    @Override
    public IAuthorizationRequestConfig getAuthorizationRequestConfig() {
        return authorizationRequestConfig;
    }

    @Override
    public IWebserviceRequestConfig getWebserverRequestConfig() {
        return webserviceRequestConfig;
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public AuthorizationService getAuthorizationService() {
        if (authorizationService == null) {
            authorizationService = new AuthorizationService(getContext());
        }

        return authorizationService;
    }

    @Override
    public AuthState restoreAuthState() {
        if (authState != null) {
            return authState;
        }

        Context context = getContext();
        String jsonString = context.getSharedPreferences(IDataContext.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(IDataContext.AUTH_STATE, null);
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                return AuthState.fromJson(jsonString);
            } catch (JSONException jsonException) {
                // should never happen
            }
        }
        return null;
    }

    @Override
    public void setAuthState(AuthState authState) {
        this.authState = authState;
    }

    @Override
    public void setOnLoginListener(ILoginListener loginListener) {
        loginListeners.add(loginListener);
    }

    @Override
    public void setOnLogoutListener(ILogoutListener logoutListener) {
        logoutListeners.add(logoutListener);
    }

    @Override
    public ArrayList<ILoginListener> getOnLoginListeners() {
        return loginListeners;
    }

    @Override
    public ArrayList<ILogoutListener> getOnLogoutListeners() {
        return logoutListeners;
    }

    @Override
    public Class getActivityClass() {
        return activity.getClass();
    }
}