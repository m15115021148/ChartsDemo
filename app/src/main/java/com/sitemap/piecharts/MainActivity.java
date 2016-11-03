package com.sitemap.piecharts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView line,pie,bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        line = (TextView) findViewById(R.id.line_chart);
        line.setOnClickListener(this);
        pie = (TextView) findViewById(R.id.pie_chart);
        pie.setOnClickListener(this);
        bar = (TextView) findViewById(R.id.bar_chart);
        bar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == line){
            Intent intent = new Intent(this,LineActivity.class);
            startActivity(intent);
        }
        if(v == pie){
            Intent intent = new Intent(this,PieActivity.class);
            startActivity(intent);
        }
        if (v == bar){
            Intent intent = new Intent(this,BarActivity.class);
            startActivity(intent);
        }
    }
}
