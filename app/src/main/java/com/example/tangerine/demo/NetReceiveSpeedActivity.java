package com.example.tangerine.demo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class NetReceiveSpeedActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private String TAG = "123";
    private Toolbar toolBar;
    private static LineChart netReceiveSpeed;
    private static Boolean beginFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_receive_speed);
        netReceiveSpeed = (LineChart) findViewById(R.id.speed_receive_chart);
        Intent intent = getIntent();
        beginFlag = intent.getBooleanExtra("receive_get",true);
        setLineChart(netReceiveSpeed);
        Log.e(TAG, "onCreate: RRRRRRRRRRRRRRRR" );
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    private void setLineChart(LineChart mChart) {
//        ILineDataSet ilineSet = new LineDataSet(null,null);
        LineDataSet lin = new LineDataSet(null,null);
        lin.setDrawCircles(false);
        lin.setDrawValues(false);
        LineData data = new LineData(lin);

        data.setValueTextColor(Color.WHITE);
        netReceiveSpeed.setData(data);

        mChart.setNoDataText("目前还没有接收到数据");
        mChart.setTouchEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.setDragEnabled(false);
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setPinchZoom(true);

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

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



    public static  void invalidateReceiveData(float speedValue) {
        if (beginFlag) {
            LineData lineData = netReceiveSpeed.getLineData();
            if (lineData != null) {
                ILineDataSet set = lineData.getDataSetByIndex(0);
                set.setDrawFilled(true);
                if (set == null) {
                    set = new LineDataSet(null,null);
                    set.setDrawFilled(true);
                    set.setDrawValues(false);
                    lineData.addDataSet(set);
                }

                lineData.addEntry(new Entry(set.getEntryCount(), speedValue), 0);
                lineData.notifyDataChanged();
                netReceiveSpeed.notifyDataSetChanged();
                netReceiveSpeed.invalidate();
                netReceiveSpeed.setVisibleXRangeMaximum(15);
                netReceiveSpeed.moveViewToX(lineData.getEntryCount());
            }
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }








}
