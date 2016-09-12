package com.example.tangerine.demo;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;


public class NetSentSpeedActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private static String  TAG = "123";
    private static LineChart netSentSpeed;
    private static float value;
    private static Boolean beginFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_sent_speed);
        netSentSpeed = (LineChart) findViewById(R.id.speed_sent_chart);
        Intent intent = getIntent();
        value = 0.0f;
        beginFlag = intent.getBooleanExtra("sent_get",true);

        Log.e(TAG, "onCreate: SSSSSSSSSSSSSSS" );
        setLineChart(netSentSpeed);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    private void setLineChart(LineChart mChart) {
        LineDataSet lineSet = new LineDataSet(null,null);
        lineSet.setDrawCircles(false);
        lineSet.setDrawValues(false);
        LineData data = new LineData(lineSet);
        data.setValueTextColor(Color.WHITE);
        netSentSpeed.setData(data);

        mChart.setNoDataText("目前还没有接收到数据");
        mChart.setTouchEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.setDragEnabled(false);
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setPinchZoom(true);
//        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.rgb(126,134,149));

        mChart.setOnChartValueSelectedListener(this);
        mChart.animateX(1000);
        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(true);
    }



    public static  void invalidateSentData(float toTranslationValue,int duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(value,toTranslationValue).setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                if (beginFlag) {
                    LineData lineData = netSentSpeed.getLineData();
                    if (lineData != null) {
                        ILineDataSet set = lineData.getDataSetByIndex(0);
                        set.setDrawFilled(true);
                        if (set == null) {
                            set = new LineDataSet(null,null);
                            set.setDrawFilled(true);
                            set.setDrawValues(false);
                            lineData.addDataSet(set);
                        }

                        lineData.addEntry(new Entry(set.getEntryCount(), value), 0);
                        lineData.notifyDataChanged();
                        netSentSpeed.notifyDataSetChanged();
                        netSentSpeed.invalidate();
                        netSentSpeed.setVisibleXRangeMaximum(15);
                        netSentSpeed.moveViewToX(lineData.getEntryCount());
                    }
                }
            }
        });
        animator.start();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        beginFlag = false;
    }
}














