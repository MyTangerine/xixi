package com.example.tangerine.demo;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

/**
 * Created by Tangerine on 16/7/29.
 */
public class HttpClient {
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     *
     * @param header
     * @param value
     */
    public static void addOneHeader(String header ,String value){
        client.addHeader(header,value);
    }

    /**
     *
     */
    public static void clearHeader(){

        client.removeAllHeaders();
    }

    /**
     *
     * @param url
     * @param responseHandler
     */
    public static void get(String url, ResponseHandlerInterface responseHandler) {
        client.get(url,responseHandler);
    }

    /**
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.get(url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param responseHandler
     */
    public static void get(Context context, String url, ResponseHandlerInterface responseHandler){
        client.get(context,url,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.get(context,url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param headers
     * @param params
     * @param responseHandler
     */
    public static void get(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler){
        client.get(context,url,headers,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void get(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.get(context,url,entity,contentType,responseHandler);
    }


    /**
     *
     * @param url
     * @param responseHandler
     */
    public static void post(String url, ResponseHandlerInterface responseHandler){
        client.post(url,responseHandler);
    }

    /**
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.post(url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.post(context,url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void post(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.post(context,url,entity,contentType,responseHandler);
    }

    /**
     *
     * @param context
     * @param url†
     * @param headers
     * @param params
     * @param contentType
     * @param responseHandler
     */
    public static void post(Context context, String url, Header[] headers, RequestParams params, String contentType,
                            ResponseHandlerInterface responseHandler){
        client.post(context,url,headers,params,contentType,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param headers
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void post(Context context, String url, Header[] headers, HttpEntity entity, String contentType,
                            ResponseHandlerInterface responseHandler){
        client.post(context,url,headers,entity,contentType,responseHandler);
    }

    /**
     *
     * @param url
     * @param responseHandler
     */
    public static void put(String url, ResponseHandlerInterface responseHandler){
        client.put(url,responseHandler);
    }

    /**
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void put(String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.put(url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void put(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.put(context,url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void put(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.put(context,url,entity,contentType,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param headers
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void put(Context context, String url, Header[] headers, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.put(context,url,headers,entity,contentType,responseHandler);
    }

    /**
     *
     * @param url
     * @param responseHandler
     */
    public static void patch(String url, ResponseHandlerInterface responseHandler){
        client.patch(url,responseHandler);
    }

    /**
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void patch(String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.patch(url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void patch(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler){
        client.patch(context,url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void patch(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.patch(context,url ,entity,contentType,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param headers
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void patch(Context context, String url, Header[] headers, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.patch(context,url,headers,entity,contentType,responseHandler);
    }

    /**
     *
     * @param url
     * @param responseHandler
     */
    public static void delete(String url, ResponseHandlerInterface responseHandler){
        client.delete(url,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param responseHandler
     */
    public static void delete(Context context, String url, ResponseHandlerInterface responseHandler){
        client.delete(context,url,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param headers
     * @param responseHandler
     */
    public static void delete(Context context, String url, Header[] headers, ResponseHandlerInterface responseHandler){
        client.delete(context,url,headers,responseHandler);
    }

    /**
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.delete(url,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param headers
     * @param params
     * @param responseHandler
     */
    public static void delete(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler){
        client.delete(context,url,headers,params,responseHandler);
    }

    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void delete(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler){
        client.delete(context,url,entity,contentType,responseHandler);
    }
}
