package com.example.oncare;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Random;

public class StepActivity extends AppCompatActivity {

    BarChart day_step_chart;
    XAxis xAxis;
    YAxis yAxis;
    TextView txt_selected_step;
    int default_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_step);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        day_step_chart = findViewById(R.id.day_step_chart);
        txt_selected_step = findViewById(R.id.txt_selected_step);


        day_step_chart.setTouchEnabled(true);
        day_step_chart.setDragXEnabled(true);
        day_step_chart.getLegend().setEnabled(false); // 범례 표시
        day_step_chart.setScaleEnabled(false);
        day_step_chart.animateY(1000);
        day_step_chart.setFitBars(true);
        day_step_chart.setVisibleXRangeMaximum(5f);
        day_step_chart.getDescription().setEnabled(false); // 그래프 제목 표시
        day_step_chart.setBackgroundColor(Color.TRANSPARENT);
        day_step_chart.zoom(5f, 1f, 0,0);
        day_step_chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int selected_step = (int) e.getY();
                txt_selected_step.setText(String.valueOf(selected_step));
            }

            @Override
            public void onNothingSelected() {

            }
        });


        xAxis = day_step_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularityEnabled(false);
        xAxis.setTextSize(12);
        xAxis.setTextColor(Color.GRAY);
        xAxis.setAxisLineColor(Color.TRANSPARENT);
        xAxis.setDrawGridLines(false);


        yAxis = day_step_chart.getAxisLeft();
        day_step_chart.getAxisRight().setEnabled(false);
        yAxis.setTextSize(10);
        yAxis.setTextColor(Color.GRAY);
        yAxis.setAxisLineColor(Color.TRANSPARENT);
        yAxis.setDrawGridLines(true);
        yAxis.setGranularity(10000);
        yAxis.setGridColor(ContextCompat.getColor(this, R.color.chart_grey));
        yAxis.setAxisMaximum(30000);
        yAxis.setAxisMinimum(0);


        ArrayList<BarEntry> values = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            int y = new Random().nextInt(20000);
            if(i==0){
                default_step = y;
                txt_selected_step.setText(String.valueOf(default_step));
            }
            values.add(new BarEntry(i, y));
        }


        BarDataSet set_example = new BarDataSet(values, "DataSet");
        set_example.setDrawValues(false);
        set_example.setHighLightColor(ContextCompat.getColor(this, R.color.theme_main));
        set_example.setColor(ContextCompat.getColor(this, R.color.menu_false)); // 색상 설정


        BarData data_example = new BarData(set_example);
        data_example.setBarWidth(0.6f);
        day_step_chart.setData(data_example);
        day_step_chart.invalidate();



    }


}