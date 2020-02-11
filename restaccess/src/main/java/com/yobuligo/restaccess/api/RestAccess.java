package com.yobuligo.restaccess.api;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import com.yobuligo.restaccess.internal.DataContext;
import com.yobuligo.restaccess.internal.IDataContext;
import com.yobuligo.restaccess.internal.Login;
import com.yobuligo.restaccess.internal.Logout;
import com.yobuligo.restaccess.internal.SendRequest;

import net.openid.appauth.AuthState;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RestAccess<T> implements IRestAccess {
    private IAuthorizationRequestConfig authorizationRequestConfig;
    private IWebserviceRequestConfig webserviceRequestConfig;
    private IDataContext dataContext;
    private Activity activity;

    public RestAccess(IAuthorizationRequestConfig authorizationRequestConfig, IWebserviceRequestConfig webserviceRequestConfig, Activity activity) {
        this.authorizationRequestConfig = authorizationRequestConfig;
        this.webserviceRequestConfig = webserviceRequestConfig;
        this.activity = activity;
    }

    @Override
    public void login() {
        Login login = new Login(getDataContext());
        registerLoginAsBroadcastReceiver(login);
        login.execute();
    }

    @Override
    public Boolean isLoggedIn() {
        AuthState authState = dataContext.restoreAuthState();
        return authState.isAuthorized();
    }

    @Override
    public void logout() {
        Logout logout = new Logout(getDataContext());
        logout.execute();
    }

    @Override
    public void sendRequest(String path) {
        SendRequest sendRequest = new SendRequest(getDataContext());
        sendRequest.execute(path);
    }

    @Override
    public void setOnLoginListener(ILoginListener loginListener) {
        getDataContext().setOnLoginListener(loginListener);
    }

    @Override
    public void setOnLogoutListener(ILogoutListener logoutListener) {
        getDataContext().setOnLogoutListener(logoutListener);
    }

    private IDataContext getDataContext() {
        if (dataContext == null) {
            dataContext = new DataContext(authorizationRequestConfig, webserviceRequestConfig, activity);
        }

        return dataContext;
    }

    private void registerLoginAsBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        Context context = getDataContext().getContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IDataContext.HANDLE_AUTHORIZATION_RESPONSE);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }
}