package com.ifstatic.banking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ifstatic.banking.Adapters.TransactionAdapter;
import com.ifstatic.banking.Models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    private RecyclerView recyclerTransactions;

    private TransactionAdapter transactionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_transaction, container, false);
        initializeView(view);

        transactionAdapter=new TransactionAdapter(requireContext());
        recyclerTransactions.setAdapter(transactionAdapter);

        setTransactions();

        return view;
    }

    private void setTransactions(){

        List<TransactionModel>transactionModels=new ArrayList<>();

        TransactionModel transactionModel=new TransactionModel();
        transactionModel.setTitle("Shell");
        transactionModel.setAmount("-$35");
        transactionModel.setDate("17 Monday June");
        transactionModel.setIcon(R.drawable.gas);

        transactionModels.add(transactionModel);

        TransactionModel transactionModel1=new TransactionModel();
        transactionModel1.setTitle("Amazon");
        transactionModel1.setAmount("-$79");
        transactionModel1.setDate("17 Monday June");
        transactionModel1.setIcon(R.drawable.shopping);

        transactionModels.add(transactionModel1);

        TransactionModel transactionModel2=new TransactionModel();
        transactionModel2.setTitle("Vodafone");
        transactionModel2.setAmount("-$35");
        transactionModel2.setDate("17 Monday June");
        transactionModel2.setIcon(R.drawable.bill);

        transactionModels.add(transactionModel2);

        transactionAdapter.setTransactions(transactionModels);
    }

    private void initializeView(View view){
        recyclerTransactions=view.findViewById(R.id.recyclerTransactions);
        recyclerTransactions.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
