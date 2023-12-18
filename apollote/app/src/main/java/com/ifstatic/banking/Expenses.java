package com.ifstatic.banking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.ifstatic.banking.Adapters.TransactionAdapter;
import com.ifstatic.banking.Models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends Fragment {

    private LineChart lineChart;
    private RecyclerView recyclerSpendingBreakdown;


    private TransactionAdapter transactionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_expenses, container, false);
        initializeView(view);

        transactionAdapter=new TransactionAdapter(requireContext());
        recyclerSpendingBreakdown.setAdapter(transactionAdapter);

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0,50));
        entries.add(new Entry(1,100));
        entries.add(new Entry(2,200));
        entries.add(new Entry(3,300));
        entries.add(new Entry(4,250));
        entries.add(new Entry(5,400));
        entries.add(new Entry(6,500));
        entries.add(new Entry(7,400));
        entries.add(new Entry(8,300));
        entries.add(new Entry(9,200));
        entries.add(new Entry(10,100));
        entries.add(new Entry(11,50));

        List<Entry> entries2 = new ArrayList<Entry>();
        entries2.add(new Entry(0,400));
        entries2.add(new Entry(1,500));
        entries2.add(new Entry(2,400));
        entries2.add(new Entry(3,200));
        entries2.add(new Entry(4,250));
        entries2.add(new Entry(5,600));
        entries2.add(new Entry(6,200));
        entries2.add(new Entry(7,600));
        entries2.add(new Entry(8,200));
        entries2.add(new Entry(9,100));
        entries2.add(new Entry(10,150));
        entries2.add(new Entry(11,650));

        LineDataSet lineDataSet=new LineDataSet(entries,"Income");
        lineDataSet.setColor(getResources().getColor(R.color.colorSuccessDark));
        lineDataSet.setValueTextColor(getResources().getColor(R.color.colorSuccessDark));
        lineDataSet.setLineWidth(2);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleColor(getResources().getColor(R.color.colorSuccessDark));

        LineDataSet lineDataSet2=new LineDataSet(entries2,"Expenses");
        lineDataSet2.setColor(getResources().getColor(R.color.colorDangerDark));
        lineDataSet2.setValueTextColor(getResources().getColor(R.color.colorDangerDark));
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setCircleColor(getResources().getColor(R.color.colorDangerDark));


        String[] months ={"January","February","March","April","May","June","July","August","September","October","November","December"};
        XAxis xAxis=lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return months[(int)value];
            }
        });

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setDrawLabels(true);

        YAxis lAxis=lineChart.getAxisLeft();

//        lAxis.setDrawGridLines(false);
//        lAxis.setDrawAxisLine(false);

        YAxis yAxis=lineChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(false);

        LineData lineData=new LineData(lineDataSet);
        lineData.addDataSet(lineDataSet2);

        Legend legend=lineChart.getLegend();
        legend.setDrawInside(true);
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        lineChart.setData(lineData);
        lineChart.enableScroll();
        lineChart.setNestedScrollingEnabled(true);
        lineChart.setVisibleXRangeMaximum(6.5f);
        lineChart.setDragOffsetX(10);
        Description description=new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();

        setTransactions();

        return view;
    }

    private void setTransactions(){
        List<TransactionModel>transactionModels=new ArrayList<>();

        TransactionModel transactionModel1=new TransactionModel();
        transactionModel1.setTitle("Shopping");
        transactionModel1.setAmount("-$79");
        transactionModel1.setDate("17 Monday June");
        transactionModel1.setIcon(R.drawable.shopping);

        transactionModels.add(transactionModel1);

        TransactionModel transactionModel=new TransactionModel();
        transactionModel.setTitle("Shell");
        transactionModel.setAmount("-$35");
        transactionModel.setDate("17 Monday June");
        transactionModel.setIcon(R.drawable.gas);

        transactionModels.add(transactionModel);

        transactionAdapter.setTransactions(transactionModels);
    }


    private void initializeView(View view){
        lineChart=view.findViewById(R.id.lineChart);
        recyclerSpendingBreakdown=view.findViewById(R.id.recyclerSpendingBreakdown);
        recyclerSpendingBreakdown.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}