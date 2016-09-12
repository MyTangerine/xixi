package com.example.tangerine.demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String minS = new String();
    private   JSONObject jsonObject = null;
    private static String TAG = "123";
    private Toolbar toolBar;
    private CustomPieChart pieChartOne ;
    private CustomPieChart pieChartTwo ;
    private CustomPieChart pieChartThree;

    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private TextView tv_four;

    private com.github.nkzawa.socketio.client.Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://119.29.161.242:5100/test");
        } catch (URISyntaxException e) {

        }
    }


    private  Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {




                    jsonObject = (JSONObject) args[0];
                    try {
                        NetSentSpeedActivity.invalidateSentData(Float.parseFloat(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("value")),1400);

                        tv_one.setText("当前发送网速:                                         "+(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("value"))+"    "+jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("s")+"/s");
                        tv_two.setText("当前接受网速:                                         "+(jsonObject.getJSONObject("eth0").getJSONObject("bytes-recv-PER-SEC").getString("value"))+"    "+jsonObject.getJSONObject("eth0").getJSONObject("bytes-recv-PER-SEC").getString("s")+"/s");
                        tv_three.setText("总发送数据量:                                         "+(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-TOTAL").getString("value"))+"        "+jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-TOTAL").getString("s"));

                        pieChartOne.valueChange(Float.parseFloat(jsonObject.getString("cpu_state")), 1000,CustomPieChart.CPU);
                        pieChartTwo.valueChange(Float.parseFloat(jsonObject.getJSONObject("memory_state").getString("percent")), 1000,CustomPieChart.MEMORRY);

                        String min = jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("s");

                        NetReceiveSpeedActivity.invalidateReceiveData(Float.parseFloat(jsonObject.getJSONObject("eth0").getJSONObject("bytes-recv-PER-SEC").getString("value")));


                        if (min.equals("B")){

                            if (minS.equals("M")||minS.isEmpty())
                                minS = "B";

                            if (minS.equals("B"))
                            pieChartThree.valueChange(Float.parseFloat(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("value")),1000,CustomPieChart.NETSPEED_B);
                            else if (minS.equals("K"))
                                pieChartThree.valueChange(Float.parseFloat(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("value"))/1024,1000,CustomPieChart.NETSPEED_K);

                        }else if (min.equals("K")){
                                if (minS.equals("M")||minS.equals("B")||minS.isEmpty())
                                minS = "K";

                            pieChartThree.valueChange(Float.parseFloat(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("value")),1000,CustomPieChart.NETSPEED_K);
                        }else if (min.equals("M")){
                                if (!minS.equals("M")||minS.isEmpty())
                                    minS = "M";
                            pieChartThree.valueChange(Float.parseFloat(jsonObject.getJSONObject("eth0").getJSONObject("bytes-sent-PER-SEC").getString("value")),1000,CustomPieChart.NETSPEED_M);
                        }

                    } catch (JSONException e) {
                        return;
                    }

                    }
                });
            }
        };




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            final Context context = this;
            Log.e(TAG, "onCreate: start" );
            pieChartOne = (CustomPieChart) findViewById(R.id.circle_one);
            pieChartOne.setPaintCircleColor(Color.parseColor("#FF4081"));
            pieChartTwo = (CustomPieChart) findViewById(R.id.circle_two);
            pieChartTwo.setPaintCircleColor(Color.parseColor("#ffd130"));
            pieChartThree = (CustomPieChart) findViewById(R.id.circle_three);
            pieChartThree.setPaintCircleColor(Color.parseColor("#54D8C6"));


            tv_one = (TextView) findViewById(R.id.tv_one);
            tv_two = (TextView) findViewById(R.id.tv_two);
            tv_three = (TextView) findViewById(R.id.tv_three);
            tv_four = (TextView) findViewById(R.id.tv_four);


            tv_one.setOnClickListener(this);
            tv_two.setOnClickListener(this);
            tv_three.setOnClickListener(this);
            tv_four.setOnClickListener(this);
            toolBar = (Toolbar) findViewById(R.id.tool_bar);
            toolBar.setTitle("");
            toolBar.setNavigationIcon(R.drawable.ic_menu_black_24);
            setSupportActionBar(toolBar);
            mSocket.on("send_llq_info", onNewMessage);
            mSocket.connect();
        }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_one:
                Intent intentSent = new Intent(MainActivity.this,NetSentSpeedActivity.class);
                intentSent.putExtra("sent_get",true);
                startActivity(intentSent);
                break;
            case R.id.tv_two:
                Intent intentReceive = new Intent(MainActivity.this,NetReceiveSpeedActivity.class);
                intentReceive.putExtra("receive_get",true);
                startActivity(intentReceive);
                break;
            case R.id.tv_three:

            case R.id.tv_four:
        }
    }



}



