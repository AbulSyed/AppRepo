package com.syed.authservice.utility;

import javax.servlet.http.Cookie;

public class AuthServiceUtility {

    /**
     * extracts access token from access_token=ACCESS_TOKEN&scope=&token_type=bearer
     * @param str the access token with extra params
     * @return the access token
     */
    public static String extractAccessToken(String str) {
        String[] parts = str.split("&");

        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue[0].equals("access_token")) {
                return keyValue[1];
            }
        }
        return null;
    }

    /**
     * create cookie to be sent to client
     * @param cookieName the name of the cookie
     * @param name the name to add to cookie
     * @param age the time of cookie life
     * @param httpOnly the HttpOnly
     * @param domain the domain
     * @param path the endpoint
     * @return the cookie
     */
    public static Cookie createCookie(String cookieName, String name, int age, boolean httpOnly, String domain,
                                      String path) {
        Cookie cookie = new Cookie(cookieName, name);
        cookie.setMaxAge(age);
        cookie.setHttpOnly(httpOnly);
        cookie.setDomain(domain);
        cookie.setPath(path);
        return cookie;
    }
}
