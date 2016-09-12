package com.example.tangerine.demo;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Tangerine on 16/7/31.
 */
public class Event {
    public static void ListEvent(Context context, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com"+"/events";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }


    public static void ListPublicEvent(Context context, String user, String repo, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/repos/"+user+"/"+repo+"/events";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }

    public static void ListNetWorkPublicEvent(Context context, String user, String repo, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/networks/"+user+"/"+repo+"/events";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }
    public static void ListOrgPublicEvent(Context context,String org, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/orgs/"+org+"/events";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }
    public static void ListReceivedPublicEvent(Context context,String user, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/users/"+user+"/received_events"+"/public";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }
    public static void ListReceivedPrivateEvent(Context context,String user,String token, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/users/"+user+"/received_events";
        HttpClient.clearHeader();
        HttpClient.addOneHeader("Authorization"," token "+token);

        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }
    public static void ListPublicEvent(Context context, String user, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/users/"+user+"/events";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }
    public static void ListPrivateEvent(Context context, String user,String token, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/users/"+user+"/events";
        HttpClient.clearHeader();
        HttpClient.addOneHeader("Authorization"," token "+token);
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }

    private interface OnSuccessGetEvent{
        void successGet(JSONArray jsonArray);
    }
}
