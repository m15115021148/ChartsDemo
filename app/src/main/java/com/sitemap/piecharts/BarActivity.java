package com.sitemap.piecharts;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * 柱形图
 */
public class BarActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout back;
    private TextView title;
    private BarChart mChart;
    private Typeface mTf;//字体样式
    private int[] barColor = new int[]{
            Color.rgb(0, 255, 0), Color.rgb(253, 203, 76), Color.rgb(255, 0, 0),
            Color.rgb(255, 208, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, 157)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        back = (LinearLayout) findViewById(R.id.include_title).findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.include_title).findViewById(R.id.title);
        title.setText("柱形图");
        mChart = (BarChart) findViewById(R.id.bar);
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        initBarChartsData();
    }

    @Override
    public void onClick(View v) {
        if (v == back)
            finish();
    }

    private void initBarChartsData() {
        // apply styling
        mChart.setDescription("");
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextColor(Color.WHITE);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5);
        leftAxis.setSpaceTop(20f);
        leftAxis.setTextColor(Color.WHITE);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5);
        rightAxis.setSpaceTop(20f);
        rightAxis.setTextColor(Color.WHITE);
        // set data
        mChart.setData(generateDataBar());

        // do not forget to refresh the chart
        mChart.animateY(700);

        Legend l = mChart.getLegend();
        l.setTextColor(Color.WHITE);
        l.setTextSize(11f);
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        mChart.invalidate();
    }

    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 6; i++) {
            entries.add(new BarEntry((int) (Math.random() * 50) + 20, i));
        }

        BarDataSet d = new BarDataSet(entries, "数据");
        d.setBarSpacePercent(16f);
        d.setValueTextColor(Color.WHITE);
        d.setColors(barColor);
        d.setHighLightAlpha(Color.rgb(255, 247, 140));//点击选中的时候颜色

        BarData cd = new BarData(getMonths(), d);
        return cd;
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("一月");
        m.add("三月");
        m.add("五月");
        m.add("七月");
        m.add("九月");
        m.add("十一月");
        return m;
    }
}
