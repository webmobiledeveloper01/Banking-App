package com.ifstatic.banking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.ifstatic.banking.Adapters.TransactionTabAdapter;
import com.triggertrap.seekarc.SeekArc;


public class Home extends Fragment {

    private TabLayout tabTransaction;
    private ViewPager viewPager;
    private MaterialButton btnSendMoney,btnReceiveDirect;
    private TextView txtAddBudget;
    private RelativeLayout relNotification;

    private SeekArc budgetArc;

    private TransactionTabAdapter transactionTabAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        initializeView(view);

        budgetArc.setProgress(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int speed=15;
                int percent=80;
                new CountDownTimer(percent*speed, speed) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        budgetArc.setProgress(percent-(int)(millisUntilFinished/speed));
                    }

                    @Override
                    public void onFinish() {
                        budgetArc.setProgress(budgetArc.getProgress()+1);
                    }
                }.start();
            }
        },500);


        btnSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(),SendMoney.class);
                startActivity(intent);
            }
        });

        btnReceiveDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(),ReceiveDirectStatus.class);
                startActivity(intent);
            }
        });

        txtAddBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBudgetDialog();
            }
        });

        relNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(),Notifications.class);
                startActivity(intent);
            }
        });

        setTabs();

        return view;
    }

    private void openAddBudgetDialog(){
        AlertDialog dialog=new AlertDialog.Builder(requireContext()).create();
        View view=LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_budget,null);
        dialog.setView(view);

        ImageButton imgBtnCancel=view.findViewById(R.id.imgBtnCancel);
        TextView txtCurrency=view.findViewById(R.id.txtCurrency);
        EditText textAmount=view.findViewById(R.id.textAmount);
        TextView txtInfo=view.findViewById(R.id.txtInfo);
        MaterialButton btnSave=view.findViewById(R.id.btnSave);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textAmount.setSelection(textAmount.getText().length());
                textAmount.requestFocusFromTouch();
                InputMethodManager imm = (InputMethodManager)requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(textAmount, 0);
            }
        },300);

        imgBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void initializeView(View view){
        tabTransaction=view.findViewById(R.id.tabTransaction);
        viewPager=view.findViewById(R.id.viewPager);
        budgetArc=view.findViewById(R.id.budgetArc);
        btnSendMoney=view.findViewById(R.id.btnSendMoney);
        btnReceiveDirect=view.findViewById(R.id.btnReceiveDirect);
        txtAddBudget=view.findViewById(R.id.txtAddBudget);
        relNotification=view.findViewById(R.id.relNotification);
    }

    private void setTabs(){
        transactionTabAdapter=new TransactionTabAdapter(getChildFragmentManager());

        Bundle dayBundle=new Bundle();
        TransactionFragment dayFragment=new TransactionFragment();
        dayBundle.putString("type","day");
        dayFragment.setArguments(dayBundle);
        transactionTabAdapter.addFragment(dayFragment,"DAY");

        Bundle weekBundle=new Bundle();
        TransactionFragment weekFragment=new TransactionFragment();
        weekBundle.putString("type","week");
        weekFragment.setArguments(weekBundle);
        transactionTabAdapter.addFragment(weekFragment,"WEEK");

        Bundle monthBundle=new Bundle();
        TransactionFragment monthFragment=new TransactionFragment();
        monthBundle.putString("type","month");
        monthFragment.setArguments(monthBundle);
        transactionTabAdapter.addFragment(monthFragment,"MONTH");

        Bundle yearBundle=new Bundle();
        TransactionFragment yearFragment=new TransactionFragment();
        yearBundle.putString("type","year");
        yearFragment.setArguments(yearBundle);
        transactionTabAdapter.addFragment(yearFragment,"YEAR");

        viewPager.setAdapter(transactionTabAdapter);

        tabTransaction.setupWithViewPager(viewPager);
    }
}