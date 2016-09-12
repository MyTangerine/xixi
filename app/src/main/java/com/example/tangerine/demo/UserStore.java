package com.example.tangerine.demo;

/**
 * Created by Tangerine on 16/7/30.
 */
public class UserStore {
    public static final String  clientId =  "c4359c7c5f978696895c";
    public static final String  clientSecret = "647305ba8e1afb7224bdc326be92862064fd7581";
    public static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserStore.token = token;
    }


}
