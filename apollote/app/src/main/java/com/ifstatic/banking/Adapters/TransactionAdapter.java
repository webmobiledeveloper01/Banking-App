package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ifstatic.banking.Models.TransactionModel;
import com.ifstatic.banking.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private Context mCxt;

    private List<TransactionModel>transactionModels=new ArrayList<>();


    public TransactionAdapter(Context mCxt) {
        this.mCxt = mCxt;
    }

    public void setTransactions(List<TransactionModel>transactionModels){
        this.transactionModels=transactionModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.view_transaction,parent,false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionModel transactionModel=transactionModels.get(position);
        if (transactionModel!=null){
            Glide.with(mCxt).load(transactionModel.getIcon()).into(holder.imgTransactionIcon);
            holder.txtTransactionTitle.setText(transactionModel.getTitle());
            holder.txtTransactionDate.setText(transactionModel.getDate());
            holder.txtTransactionAmount.setText(transactionModel.getAmount());
        }
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgTransactionIcon;
        private TextView txtTransactionTitle,txtTransactionDate,txtTransactionAmount;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTransactionIcon=itemView.findViewById(R.id.imgTransactionIcon);
            txtTransactionTitle=itemView.findViewById(R.id.txtTransactionTitle);
            txtTransactionDate=itemView.findViewById(R.id.txtTransactionDate);
            txtTransactionAmount=itemView.findViewById(R.id.txtTransactionAmount);
        }
    }
}
