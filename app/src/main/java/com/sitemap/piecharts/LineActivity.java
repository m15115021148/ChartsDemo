package com.sitemap.piecharts;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.sitemap.piecharts.custom.MyMarkerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * 线形图
 */
public class LineActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout back;
    private TextView title;
    private LineChart mLineChart;
    private Typeface mTf;//字体样式
    private String[] mParties = new String[] {
            "正常", "故障", "报警"
    };
    private int[] line_color = new int[]{
            Color.rgb(71, 204, 255), Color.rgb(253, 203, 76), Color.rgb(255, 0, 0),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        back = (LinearLayout) findViewById(R.id.include_title).findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.include_title).findViewById(R.id.title);
        title.setText("线形图");
        mLineChart = (LineChart) findViewById(R.id.line);
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        initLineChartData();
    }

    @Override
    public void onClick(View v) {
        if (v == back)
            finish();
    }

    /**
     * 初始化线形图的数据
     * @param
     */
    private void initLineChartData(){
        // apply styling
        mLineChart.setDescription("");
        mLineChart.setDrawGridBackground(false);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        // set the marker to the chart
        mLineChart.setMarkerView(mv);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextColor(Color.WHITE);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5);
        leftAxis.setStartAtZero(true);
        leftAxis.setTextColor(Color.WHITE);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5);
//        rightAxis.setDrawLabels(false);
//        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextColor(Color.WHITE);
        rightAxis.setStartAtZero(true);
        // set data
        mLineChart.setData(setLineData(mParties.length+3));
        mLineChart.animateX(750);
        Legend l = mLineChart.getLegend();
        l.setTextColor(Color.WHITE);
        l.setTextSize(10f);
        // do not forget to refresh the chart
        mLineChart.invalidate();

    }

    /**
     *设置线性图的数据
     * @return
     */
    private LineData setLineData(int num) {
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        for (int i = 0; i < num; i++) {
            e1.add(new Entry((int) (Math.random() * 30) + 10, i));
        }
        LineDataSet d1 = new LineDataSet(e1,mParties[0]);
        d1.setLineWidth(2f);
        d1.setCircleSize(2f);
        d1.setColor(line_color[0]);
        d1.setHighLightColor(line_color[0]);
        d1.setDrawValues(false);

        ArrayList<Entry> e2 = new ArrayList<Entry>();
        for (int i = 0; i < num; i++) {
            e2.add(new Entry((int) (Math.random() * 10) + 20, i));
        }
        LineDataSet d2 = new LineDataSet(e2, mParties[1]);
        d2.setLineWidth(2f);
        d2.setCircleSize(2f);
//        d2.setDrawCircles(false);
        d2.setCircleColor(line_color[1]);
        d2.setColor(line_color[1]);
        d2.setHighLightColor(line_color[1]);
        d2.setDrawValues(false);

        ArrayList<Entry> e3 = new ArrayList<Entry>();
        for (int i = 0; i < num; i++) {
            e3.add(new Entry((int) (Math.random() * 5) + 10, i));
        }
        LineDataSet d3 = new LineDataSet(e3, mParties[2]);
        d3.setLineWidth(2f);
        d3.setCircleSize(2f);
//        d3.setDrawCircles(false);
        d3.setCircleColor(line_color[2]);
        d3.setColor(line_color[2]);
        d3.setHighLightColor(line_color[2]);
        d3.setDrawValues(false);

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);
        sets.add(d2);
        sets.add(d3);

        LineData cd = new LineData(getTimes(num), sets);
        return cd;
    }

    /**
     * 得到x轴的数据
     * @return
     */
    private ArrayList<String> getTimes(int num) {
        ArrayList<String> m = new ArrayList<String>();
        for (int i = 0; i < num; i++) {
            m.add("2016年"+(i+1)+"月");
        }
//        m.add("");
        return m;
    }
}
