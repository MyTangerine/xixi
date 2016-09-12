package com.example.tangerine.demo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Tangerine on 16/7/31.
 */
public class UserInfo{
    private static String TAG = "123";
    private static final String clientId =  "c4359c7c5f978696895c";
    private static final String clientSecret = "647305ba8e1afb7224bdc326be92862064fd7581";



    private interface getUserInfo{

    }
    public static void getUserInfo(final Context context, String user, final OnSuccessGetJson onSuccessGetJson ) {

        String uri = new String("https://api.github.com/users/"+user);
        HttpClient.clearHeader();
        HttpClient.addOneHeader("User-Agent", user);
        HttpClient.get(context, uri, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetJson.successGet(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(context, "网络请求失败" + statusCode + responseString, Toast.LENGTH_SHORT);
            }

        });
    }
    interface OnSuccessGetJson{
        void successGet(JSONObject json);
    }
}
