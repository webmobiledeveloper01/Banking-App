package com.ifstatic.banking;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ifstatic.banking.Adapters.UserCardAdapter;
import com.ifstatic.banking.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RequestPayment extends AppCompatActivity implements UserCardAdapter.UserCardClickEvents {

    private ImageButton imgBtnBack,imgBtnCancel;
    private RecyclerView recyclerUsers;
    private EditText textSearch;

    private UserCardAdapter userCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_payment);
        initializeView();

        userCardAdapter=new UserCardAdapter(this,this);
        recyclerUsers.setAdapter(userCardAdapter);

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    imgBtnCancel.setVisibility(View.GONE);
                }else{
                    imgBtnCancel.setVisibility(View.VISIBLE);
                }
            }
        });

        imgBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearch.setText("");
                imgBtnCancel.setVisibility(View.GONE);
            }
        });

        setUsers();
    }

    private void setUsers(){
        List<UserModel> userModels=new ArrayList<>();
        userModels.add(new UserModel(1,R.drawable.josie,"Josie Maran","josiemaran@gmail.com"));
        userModels.add(new UserModel(2,R.drawable.ion_wizard,"Ion Wizard","ionwizard@gmail.com"));
        userModels.add(new UserModel(3,R.drawable.ricardo,"Ricardo","ricardo@gmail.com"));
        userModels.add(new UserModel(4,R.drawable.yogie,"Yogie","yogie@gmail.com"));
        userModels.add(new UserModel(1,R.drawable.josie,"Josie Maran","josiemaran@gmail.com"));
        userModels.add(new UserModel(2,R.drawable.ion_wizard,"Ion Wizard","ionwizard@gmail.com"));
        userModels.add(new UserModel(3,R.drawable.ricardo,"Ricardo","ricardo@gmail.com"));
        userModels.add(new UserModel(4,R.drawable.yogie,"Yogie","yogie@gmail.com"));
        userModels.add(new UserModel(1,R.drawable.josie,"Josie Maran","josiemaran@gmail.com"));
        userModels.add(new UserModel(2,R.drawable.ion_wizard,"Ion Wizard","ionwizard@gmail.com"));
        userModels.add(new UserModel(3,R.drawable.ricardo,"Ricardo","ricardo@gmail.com"));
        userModels.add(new UserModel(4,R.drawable.yogie,"Yogie","yogie@gmail.com"));
        userModels.add(new UserModel(1,R.drawable.josie,"Josie Maran","josiemaran@gmail.com"));
        userModels.add(new UserModel(2,R.drawable.ion_wizard,"Ion Wizard","ionwizard@gmail.com"));
        userModels.add(new UserModel(3,R.drawable.ricardo,"Ricardo","ricardo@gmail.com"));
        userModels.add(new UserModel(4,R.drawable.yogie,"Yogie","yogie@gmail.com"));

        userCardAdapter.setUsers(userModels);
    }

    private void initializeView(){
        imgBtnBack=findViewById(R.id.imgBtnBack);
        imgBtnCancel=findViewById(R.id.imgBtnCancel);
        recyclerUsers=findViewById(R.id.recyclerUsers);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));
        textSearch=findViewById(R.id.textSearch);
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        Intent intent=new Intent(RequestPayment.this,RequestPaymentAmount.class);
        launchRequestPaymentAmount.launch(intent);
    }

    ActivityResultLauncher<Intent> launchRequestPaymentAmount = registerForActivityResult(
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
}