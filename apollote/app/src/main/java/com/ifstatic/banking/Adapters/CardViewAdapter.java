package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ifstatic.banking.Models.CardModel;
import com.ifstatic.banking.R;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private Context mCxt;
    private List<CardModel>cardModels=new ArrayList<>();

    public CardViewAdapter(Context mCxt) {
        this.mCxt = mCxt;
    }

    public void setCards(List<CardModel>cardModels){
        this.cardModels=cardModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.view_card,parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardModel cardModel=cardModels.get(position);
        if (cardModel!=null){
            holder.txtName.setText(cardModel.getName());
            holder.txtCardNumber.setText(cardModel.getCardNo());
            holder.txtExpiryDate.setText(cardModel.getExpiry());
        }
    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCardNumber,txtName,txtExpiryDate;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCardNumber=itemView.findViewById(R.id.txtCardNumber);
            txtName=itemView.findViewById(R.id.txtName);
            txtExpiryDate=itemView.findViewById(R.id.txtExpiryDate);
        }
    }
}
