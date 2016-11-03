package com.sitemap.piecharts;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.AnimationEasing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

/**
 * 饼形图
 */
public class PieActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout back;
    private TextView title;
    private PieChart mChart;
    private Typeface mTf;
    private String[] mParties = new String[] {
            "正常", "故障", "告警"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        back = (LinearLayout) findViewById(R.id.include_title).findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.include_title).findViewById(R.id.title);
        title.setText("饼形图");
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setUsePercentValues(true);

        // change the color of the center-hole
         mChart.setHoleColor(Color.rgb(235, 235, 235));
        mChart.setHoleColorTransparent(true);
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        mChart.setDescription("");
        mChart.setHoleRadius(60f);
        mChart.setTransparentCircleRadius(65f);
        mChart.setDrawCenterText(true);
        mChart.setDrawHoleEnabled(true);
//        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setCenterText("饼形图");
        mChart.setCenterTextColor(Color.WHITE);
        setData(3, 100);
        mChart.animateXY(1500, 1500, AnimationEasing.EasingOption.EaseOutBack);
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setFormSize(10f);
        l.setTextSize(10f);
        l.setTextColor(Color.WHITE);
        l.setLabels(mParties);
    }

    private void setData(int count, float range) {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            yVals1.add(new Entry((float) (Math.random() * range) + range / 5, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTf);
        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    @Override
    public void onClick(View v) {
        if (v == back)
            finish();
    }
}
