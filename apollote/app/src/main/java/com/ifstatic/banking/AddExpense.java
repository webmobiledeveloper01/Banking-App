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
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.google.android.material.button.MaterialButton;
import com.ifstatic.banking.Adapters.CategoryAdapter;
import com.ifstatic.banking.Adapters.CurrencyAdapter;
import com.ifstatic.banking.Models.CategoryModel;
import com.ifstatic.banking.Models.CurrencyModel;
import com.ifstatic.banking.Utils.BottomSheetDateSelector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddExpense extends AppCompatActivity implements BottomSheetDateSelector.BottomSheetListener,CategoryAdapter.CategoryClickEvents{


    private ImageButton imgBtnBack;
    private TextView txtCurrency;
    private EditText textAmount;
    private RelativeLayout relCurrency;
    private TextView txtSelectedCurrency;
    private TextView txtTransactionDate;
    private RecyclerView recyclerCategories;
    private EditText textNotes;
    private MaterialButton btnSave;

    String selectedDate="";

    private BottomSheetDateSelector bottomSheetDateSelector;

    private CategoryAdapter categoryAdapter;
    private CurrencyAdapter currencyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initializeView();

        categoryAdapter=new CategoryAdapter(this,this);
        recyclerCategories.setAdapter(categoryAdapter);

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        List<CurrencyModel> currencyModels=new ArrayList<>();
        currencyModels.add(new CurrencyModel("USD","$"));
        currencyModels.add(new CurrencyModel("EURO","e"));

        txtCurrency.setText(currencyModels.get(0).getCurrencySymbol());
        txtSelectedCurrency.setText(currencyModels.get(0).getCurrencyName());

        currencyAdapter=new CurrencyAdapter(this);
        currencyAdapter.setCurrencies(currencyModels);

        relCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurrencySelectionDialog();
            }
        });


        bottomSheetDateSelector=new BottomSheetDateSelector();
        bottomSheetDateSelector.setCancelable(true);
        Bundle bundle=new Bundle();

        txtTransactionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("date",selectedDate);
                bottomSheetDateSelector.setArguments(bundle);
                bottomSheetDateSelector.show(getSupportFragmentManager(),"dateSelector");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddExpense.this,ExpenseStatus.class);
                launchExpenseStatus.launch(intent);
            }
        });

        setCategories();
    }


    ActivityResultLauncher<Intent> launchExpenseStatus = registerForActivityResult(
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

    private void setCategories(){
        List<CategoryModel>categoryModels=new ArrayList<>();
        categoryModels.add(new CategoryModel("Transport",R.drawable.transport));
        categoryModels.add(new CategoryModel("Foods",R.drawable.foods));
        categoryModels.add(new CategoryModel("Shopping",R.drawable.shopping1));
        categoryModels.add(new CategoryModel("Bills",R.drawable.bill1));
        categoryModels.add(new CategoryModel("Tax",R.drawable.tax));

        categoryAdapter.setCategories(categoryModels);
    }

    private void initializeView(){
        imgBtnBack=findViewById(R.id.imgBtnBack);
        txtCurrency=findViewById(R.id.txtCurrency);
        textAmount=findViewById(R.id.textAmount);
        relCurrency=findViewById(R.id.relCurrency);
        txtSelectedCurrency=findViewById(R.id.txtSelectedCurrency);
        txtTransactionDate=findViewById(R.id.txtTransactionDate);
        recyclerCategories=findViewById(R.id.recyclerCategories);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        textNotes=findViewById(R.id.textNotes);
        btnSave=findViewById(R.id.btnSave);
    }

    @Override
    public void onOkClicked(String selectedDate) {
        this.selectedDate=selectedDate;
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf1=new SimpleDateFormat("EEEE, dd MMM, yyyy");
        try {
            Date date=sdf.parse(selectedDate);
            if (date != null) {
                txtTransactionDate.setText(sdf1.format(date));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

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
                    Toast.makeText(AddExpense.this,"Select currency",Toast.LENGTH_SHORT).show();
                }else {
                    txtCurrency.setText(currencyModel.getCurrencySymbol());
                    txtSelectedCurrency.setText(currencyModel.getCurrencyName());
                    alertDialog.dismiss();
                }
            }
        });

        alertDialog.show();
    }

    @Override
    public void onCategoryClicked(CategoryModel categoryModel) {

    }
}