package com.ifstatic.banking;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.ifstatic.banking.Adapters.CurrencyAdapter;
import com.ifstatic.banking.Models.CurrencyModel;

import java.util.ArrayList;
import java.util.List;

public class SendMoneyAmount extends AppCompatActivity {

    private ImageButton imgBtnBack;
    private TextView txtCurrency;
    private EditText textAmount;
    private MaterialButton btnNext;
    private RelativeLayout relCurrency;
    private TextView txtSelectedCurrency;

    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money_amount);
        initializeView();

        List<CurrencyModel> currencyModels=new ArrayList<>();
        currencyModels.add(new CurrencyModel("USD","$"));
        currencyModels.add(new CurrencyModel("EURO","e"));

        txtCurrency.setText(currencyModels.get(0).getCurrencySymbol());
        txtSelectedCurrency.setText(currencyModels.get(0).getCurrencyName());

        currencyAdapter=new CurrencyAdapter(this);
        currencyAdapter.setCurrencies(currencyModels);


        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        relCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurrencySelectionDialog();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(textAmount.getText().toString())){
                    Toast.makeText(SendMoneyAmount.this,"Enter amount",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        double amount=Double.parseDouble(textAmount.getText().toString());
                        if (amount>0){
                            Intent intent=new Intent(SendMoneyAmount.this,SendReview.class);
                            launchRequestReview.launch(intent);
                        }else {
                            Toast.makeText(SendMoneyAmount.this,"Enter amount",Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(SendMoneyAmount.this,"Enter valid amount",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    ActivityResultLauncher<Intent> launchRequestReview = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // your operation....
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            });

    private void openCurrencySelectionDialog(){
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_currency,null);
        alertDialog.setView(view);

        RecyclerView recyclerCurrency=view.findViewById(R.id.recyclerCurrency);
        TextView txtCancel=view.findViewById(R.id.txtCancel);
        TextView txtOk=view.findViewById(R.id.txtOk);

        recyclerCurrency.setLayoutManager(new LinearLayoutManager(this));
        recyclerCurrency.setHasFixedSize(true);

        recyclerCurrency.setAdapter(currencyAdapter);

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyModel currencyModel=currencyAdapter.getSelectedCurrency();
                if (currencyModel==null){
                    Toast.makeText(SendMoneyAmount.this,"Select currency",Toast.LENGTH_SHORT).show();
                }else {
                    txtCurrency.setText(currencyModel.getCurrencySymbol());
                    txtSelectedCurrency.setText(currencyModel.getCurrencyName());
                    alertDialog.dismiss();
                }
            }
        });

        alertDialog.show();
    }

    private void initializeView(){
        imgBtnBack=findViewById(R.id.imgBtnBack);
        txtCurrency=findViewById(R.id.txtCurrency);
        textAmount=findViewById(R.id.textAmount);
        relCurrency=findViewById(R.id.relCurrency);
        btnNext=findViewById(R.id.btnNext);
        txtSelectedCurrency=findViewById(R.id.txtSelectedCurrency);
    }
}