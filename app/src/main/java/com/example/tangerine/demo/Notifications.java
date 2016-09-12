package com.example.tangerine.demo;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Tangerine on 16/7/31.
 */
public class Notifications {

    public static void ListNotifications(Context context, Boolean all, Boolean participating, String since,String before,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications";
        RequestParams params  = new RequestParams();
        params.put("all",all);
        params.put("participating",participating);
        params.put("since",since);
        params.put("before",before);

        HttpClient.get(context,uri,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }



    public static void ListUserNotifications(Context context, String user,String repo,Boolean all, Boolean participating, String since,String before,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/repos/"+user+"/"+repo+"/notifications";
        RequestParams params  = new RequestParams();
        params.put("all",all);
        params.put("participating",participating);
        params.put("since",since);
        params.put("before",before);

        HttpClient.get(context,uri,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }



    public static void MarkAsRead(Context context,String last_read_at,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications";
        RequestParams params  = new RequestParams();
        params.put("last_read_at",last_read_at);
        HttpClient.put(uri,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(statusCode);
            }
        });
    }



    public static void MarkAsReadInRepo(Context context,String user,String repo,String last_read_at,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/repos/+"+user+"/"+repo+"/notifications";
        RequestParams params  = new RequestParams();
        params.put("last_read_at",last_read_at);
        HttpClient.put(uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(statusCode);
            }
        });
    }



    public static void ViewSingleThread(Context context,String id,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications/threads/"+id;
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }



    public static void MarkThreadAsRead(Context context,String id,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications/threads/"+id+"/subscription";
        HttpClient.patch(uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(statusCode);
            }
        });
    }



    public static void GetThreadSubscription(Context context,String id, final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications/threads/"+id+"/subscription";
        HttpClient.get(context,uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }



    public static void SetThreadSubscription(Context context,String id,Boolean subscribed,Boolean ignored,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications/threads/"+id+"/subscription";
        RequestParams params = new RequestParams();
        params.put("ignored",ignored);
        params.put("subscribed",subscribed);
        HttpClient.put(uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(response);
            }
        });
    }



    public static void DelThreadSubscription(Context context,String id,final OnSuccessGetEvent onSuccessGetEvent){
        String uri = "https://api.github.com/notifications/threads/"+id+"/subscription";
        HttpClient.delete(uri,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                onSuccessGetEvent.successGet(statusCode);
            }
        });
    }

    private interface OnSuccessGetEvent{
        void successGet(JSONArray jsonArray);
        void successGet(int code);
    }
}
