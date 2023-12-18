package com.ifstatic.banking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ifstatic.banking.Adapters.CardAdapter;
import com.ifstatic.banking.Adapters.UserAdapter;
import com.ifstatic.banking.Models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class Wallet extends Fragment implements UserAdapter.UserClickEvents {

    private RecyclerView recyclerUsers;
    private ViewPager2 viewPager;
    private ImageButton imgBtnAddCard;

    private CardAdapter cardAdapter;
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_wallet, container, false);
        initializeView(view);

        cardAdapter=new CardAdapter(requireActivity().getSupportFragmentManager(), getLifecycle());
        cardAdapter.addFragment(new CardFragment());
        cardAdapter.addFragment(new CardFragment());
        cardAdapter.addFragment(new CardFragment());

        userAdapter=new UserAdapter(requireContext(),this);
        recyclerUsers.setAdapter(userAdapter);

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(cardAdapter);

        imgBtnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCardDialog();
            }
        });

        setUsers();

        return view;
    }


    private void openAddCardDialog(){
        AlertDialog alertDialog=new AlertDialog.Builder(requireContext()).create();
        View view=LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_card,null);
        alertDialog.setView(view);

        ImageButton imgBtnCancel=view.findViewById(R.id.imgBtnCancel);
        TextView txtAddManually=view.findViewById(R.id.txtAddManually);

        imgBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void setUsers(){
        List<UserModel>userModels=new ArrayList<>();
        userModels.add(new UserModel(0,R.drawable.plus,"Add new contacts"));
        userModels.add(new UserModel(1,R.drawable.josie,"Josie Maran"));
        userModels.add(new UserModel(2,R.drawable.ion_wizard,"Ion Wizard"));
        userModels.add(new UserModel(3,R.drawable.ricardo,"Ricardo"));
        userModels.add(new UserModel(4,R.drawable.yogie,"Yogie"));

        userAdapter.setUsers(userModels);
    }

    private void initializeView(View view){
        viewPager=view.findViewById(R.id.viewPager);
        recyclerUsers=view.findViewById(R.id.recyclerUsers);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        imgBtnAddCard=view.findViewById(R.id.imgBtnAddCard);
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        if (userModel.getId()==0){
            //open add contact
            Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
            contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            launchAddContact.launch(contactIntent);
        }else {
            //Open send money
            MainActivity mainActivity=(MainActivity) requireActivity();
            mainActivity.openAddMoney();
        }
    }

    ActivityResultLauncher<Intent> launchAddContact = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // contact added

                    }else if (result.getResultCode()==Activity.RESULT_CANCELED){
                        //failed to add contact

                    }
                }
            });
}