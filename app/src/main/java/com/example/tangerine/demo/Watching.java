package com.example.tangerine.demo;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Tangerine on 16/8/2.
 */
public class Watching {


    public static void MarkAsReadInRepo(Context context, String user, String repo, String last_read_at, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/repos/+"+user+"/"+repo+"/subscribers";
        HttpClient.put(uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(statusCode);
            }
        });
    }

    public static void ListRepoBeingWatched(Context context, String user, String repo, String last_read_at, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/repos/+"+user+"/"+repo+"/subscribers";
        HttpClient.put(uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(statusCode);
            }
        });
    }

    interface OnSuccessGetEvent{
        void successGet(JSONObject json);
        void successGet(int code);
    }
}
