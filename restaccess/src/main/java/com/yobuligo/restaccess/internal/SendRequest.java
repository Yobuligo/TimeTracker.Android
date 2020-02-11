package com.yobuligo.restaccess.internal;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yobuligo.restaccess.api.IWebserviceRequestConfig;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationService;

import org.json.JSONObject;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.yobuligo.restaccess.internal.IDataContext.LOG_TAG;

public class SendRequest implements ISendRequest {
    private IDataContext dataContext;

    public SendRequest(IDataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public void execute(final String path) {
        AuthorizationService authorizationService = dataContext.getAuthorizationService();
        AuthState authstate = dataContext.restoreAuthState();
        if (authstate == null){
            //needs to login
            return;
        }

        authstate.performActionWithFreshTokens(authorizationService, new AuthState.AuthStateAction() {
            @Override
            public void execute(
                    String accessToken,
                    String idToken,
                    AuthorizationException ex) {
                if (ex != null) {
                    // negotiation for fresh tokens failed, check ex for more details
                    return;
                }

                // use the access token to do something ...
                Log.i(LOG_TAG, String.format("TODO: make an API call with [Access Token: %s, ID Token: %s]", accessToken, idToken));

                AuthState authState = dataContext.restoreAuthState();
                AuthorizationService authorizationService = dataContext.getAuthorizationService();

                authState.performActionWithFreshTokens(authorizationService, new AuthState.AuthStateAction() {
                    @Override
                    public void execute(@Nullable String accessToken, @Nullable String idToken, @Nullable AuthorizationException exception) {
                        new AsyncTask<String, Void, JSONObject>() {
                            @Override
                            protected JSONObject doInBackground(String... tokens) {
                                OkHttpClient client = new OkHttpClient();
                                String webserverUri = getWebserverUri();
                                Request request = new Request.Builder()
                                        .url(webserverUri + path)
                                        .addHeader("Authorization", String.format("Bearer %s", tokens[0]))
                                        .build();
                                try {
                                    Response response = client.newCall(request).execute();
                                    String jsonBody = response.body().string();
                                    Log.i(LOG_TAG, String.format("User Info Response %s", jsonBody));
                                    return new JSONObject(jsonBody);
                                } catch (Exception exception) {
                                    Log.w(LOG_TAG, exception);
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(JSONObject userInfo) {
                                Log.i(LOG_TAG, "onPostExecute");
                                    /*if (userInfo != null) {
                                        String fullName = userInfo.optString("name", null);
                                        String givenName = userInfo.optString("given_name", null);
                                        String familyName = userInfo.optString("family_name", null);
                                        String imageUrl = userInfo.optString("picture", null);
                                        if (!TextUtils.isEmpty(imageUrl)) {
                                            Picasso.with(mMainActivity)
                                                    .load(imageUrl)
                                                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                                                    .into(mMainActivity.mProfileView);
                                        }
                                        if (!TextUtils.isEmpty(fullName)) {
                                            mMainActivity.mFullName.setText(fullName);
                                        }
                                        if (!TextUtils.isEmpty(givenName)) {
                                            mMainActivity.mGivenName.setText(givenName);
                                        }
                                        if (!TextUtils.isEmpty(familyName)) {
                                            mMainActivity.mFamilyName.setText(familyName);
                                        }

                                        String message;
                                        if (userInfo.has("error")) {
                                            message = String.format("%s [%s]", mMainActivity.getString(R.string.request_failed), userInfo.optString("error_description", "No description"));
                                        } else {
                                            message = mMainActivity.getString(R.string.request_complete);
                                        }
                                        Snackbar.make(mMainActivity.mProfileView, message, Snackbar.LENGTH_SHORT)
                                                .show();
                                    }*/
                            }
                        }.execute(accessToken);
                    }
                });


            }
        });
    }

    private String getWebserverUri() {
        IWebserviceRequestConfig webserviceRequestConfig = dataContext.getWebserverRequestConfig();
        return webserviceRequestConfig.getWebserverUri() + ":" + webserviceRequestConfig.getWebserverPort();
    }
}
