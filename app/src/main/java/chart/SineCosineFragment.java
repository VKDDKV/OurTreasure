package chart;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.mannermakethman.vkd.ourtreasure.R;

/**
 * Created by nguyentviet3513 on 1/8/2018.
 */

public class SineCosineFragment extends Fragment {

    private LineChart mChart;

    public static Fragment newInstance() {
        return new SineCosineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pie_chart, container, false);

     /*   mChart = (LineChart) v.findViewById(R.id.lineChart1);

        mChart.getDescription().setEnabled(false);

        mChart.setDrawGridBackground(false);

        mChart.setData(generateLineData());*/
        mChart.animateX(3000);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        Legend l = mChart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMaximum(1.2f);
        leftAxis.setAxisMinimum(-1.2f);

        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);

        return v;
    }
}
