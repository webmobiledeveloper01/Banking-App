package com.ifstatic.banking;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private FloatingActionButton btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new Home()).commit();

        bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewDialog();
            }
        });
    }

    private void openAddNewDialog(){
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_add_transaction,null);
        alertDialog.setView(view);

        CardView cardRequest=view.findViewById(R.id.cardRequest);
        CardView cardSend=view.findViewById(R.id.cardSend);
        CardView cardAddIncome=view.findViewById(R.id.cardAddIncome);
        CardView cardAddExpense=view.findViewById(R.id.cardAddExpense);
        TextView txtCancel=view.findViewById(R.id.txtCancel);

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        cardRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent=new Intent(MainActivity.this,RequestPayment.class);
                launchRequestPayment.launch(intent);
            }
        });

        cardSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent=new Intent(MainActivity.this,SendMoney.class);
                launchSendMoney.launch(intent);
            }
        });

        cardAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent=new Intent(MainActivity.this,AddIncome.class);
                startActivity(intent);
            }
        });

        cardAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent=new Intent(MainActivity.this,AddExpense.class);
                startActivity(intent);
            }
        });

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    public void openAddMoney(){
        Intent intent=new Intent(MainActivity.this,SendMoney.class);
        launchSendMoney.launch(intent);
    }

    ActivityResultLauncher<Intent> launchRequestPayment = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // your operation....
                        Menu menu=bottomNav.getMenu();
                        MenuItem menuItem=menu.findItem(R.id.navHome);
                        if (menuItem!=null){
                            menuItem.setChecked(true);
                        }
                    }
                }
            });

    ActivityResultLauncher<Intent> launchSendMoney = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // your operation....
                        Menu menu=bottomNav.getMenu();
                        MenuItem menuItem=menu.findItem(R.id.navHome);
                        if (menuItem!=null){
                            menuItem.setChecked(true);
                        }
                    }
                }
            });

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment =null;

            if (menuItem.getItemId()==R.id.navHome){
                selectedFragment = new Home();
            }else if (menuItem.getItemId()==R.id.navExpense){
                selectedFragment = new Expenses();
            }else if (menuItem.getItemId()==R.id.navWallet){
                selectedFragment = new Wallet();
            }else if (menuItem.getItemId()==R.id.navProfile){
                selectedFragment = new Profile();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,selectedFragment).commit();
            return true;
        }
    };

    private void initializeView(){
        bottomNav=findViewById(R.id.bottomNav);
        btnAddNew=findViewById(R.id.btnAddNew);
    }
}