package com.ifstatic.banking.Utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ifstatic.banking.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BottomSheetDateSelector extends BottomSheetDialogFragment {

    private BottomSheetListener bottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet_date_picker,container,false);

        TextView txtCancel=view.findViewById(R.id.txtCancel);
        TextView txtDone=view.findViewById(R.id.txtDone);
        SingleDateAndTimePicker single_day_picker=view.findViewById(R.id.single_day_picker);

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                bottomSheetListener.onOkClicked(sdf.format(single_day_picker.getDate()));
                dismiss();
            }
        });

        single_day_picker.setDisplayMinutes(false);
        single_day_picker.setDisplayHours(false);
        single_day_picker.setDisplayDays(false);
        single_day_picker.setDisplayMonths(true);
        single_day_picker.setDisplayDaysOfMonth(true);
        single_day_picker.setDisplayYears(true);
        single_day_picker.setMaxDate(new Date());
        single_day_picker.setMonthFormat("MMM");
        single_day_picker.setSelectedTextColor(getResources().getColor(R.color.colorPrimaryDark));
        single_day_picker.setMustBeOnFuture(false);

        if (getArguments()!=null){
            String d=getArguments().getString("date");
            if (!TextUtils.isEmpty(d)){
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date date= null;
                    date = sdf.parse(d);
                    single_day_picker.setDefaultDate(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return view;
    }


    public interface  BottomSheetListener{
        void onOkClicked(String selectedDate);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListener=(BottomSheetListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ "must implement BottomSheetListener");
        }
    }
}
