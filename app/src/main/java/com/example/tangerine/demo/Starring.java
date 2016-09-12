package com.example.tangerine.demo;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Tangerine on 16/7/31.
 */
public class Starring {

    public static void ListStargazers(final Context context, String user,String repo, final OnSuccessGetEvent onSuccessGetEvent ) {

        String uri = new String("https://api.github.com/repos/"+user+"/"+repo+"/stargazers");
        HttpClient.clearHeader();
        HttpClient.addOneHeader("Accept"," application/vnd.github.v3.star+json");
        HttpClient.get(context, uri, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(context, "网络请求失败" + statusCode + responseString, Toast.LENGTH_SHORT);
            }

        });
    }

    public static void ListRepoBeingStarByUser(final Context context, String user, String repo, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = new String("https://api.github.com/users/"+user+"/starred");
        HttpClient.get(context, uri, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(context, "网络请求失败" + statusCode + responseString, Toast.LENGTH_SHORT);
            }

        });
    }



//    public static void ListRepoBeingStarByUser(final Context context, String user, String repo, final OnSuccessGetJson onSuccessGetJson){
//        String uri = new String("https://api.github.com/users/"+user+"/starred");
//        HttpClient.get(context, uri, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                onSuccessGetJson.successGet(response);
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//                Toast.makeText(context, "网络请求失败" + statusCode + responseString, Toast.LENGTH_SHORT);
//            }
//
//        });
//    }



    interface OnSuccessGetEvent{
        void successGet(JSONObject json);
    }
}
