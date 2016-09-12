package com.example.tangerine.demo;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tangerine on 16/8/8.
 */
public class DataPattern {
    public static String getPreByteSentNum(String string) {
        String s = new String();
        Pattern pattern = Pattern.compile("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()){
            s = matcher.group();
        }
        return s;
    }

    public static String getPreBytrsSent(String string){
        String s = new String();
        Pattern pattern = Pattern.compile("[B,K,M,G,T]");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find())
            if (matcher.group() == "B")
                s = "B";
            else if (matcher.group() == "K")
                s = "K";
            else if (matcher.group() == "M")
                s = "M";
            else if (matcher.group() == "G")
                s = "G";
            else if (matcher.group() == "T")
                s = "T";
        return s;

    }
    public static String getInteger(String string){
        String s = new String();

        return s;
    }




}
