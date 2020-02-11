package com.yobuligo.restaccess.api;

import android.content.Context;

/**
 * IRestAccess is a facade to access all possible functions of this api
 */
public interface IRestAccess {
    /**
     * Method to display a login screen and login the user by user credentials
     */
    void login();

    /**
     * Returns if a user is logged in
     * @return
     */
    Boolean isLoggedIn();

    /**
     * Method to logout the current user
     */
    void logout();

    /**
     * Method to send a rest request with the credentials of the logged in user
     */
    void sendRequest(String path);

    /**
     * Sets a login listener that will be informed when a login was completed
     */
    void setOnLoginListener(ILoginListener loginListener);

    /**
     * Sets a logout lister that will be informed when a logout was completed
     */
    void setOnLogoutListener(ILogoutListener logoutListener);

}