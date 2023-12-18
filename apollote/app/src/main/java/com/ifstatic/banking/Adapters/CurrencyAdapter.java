package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ifstatic.banking.Models.CurrencyModel;
import com.ifstatic.banking.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private Context mCxt;
    private List<CurrencyModel>currencyModels=new ArrayList<>();
    int selectedCurrencyPosition=0;

    public CurrencyAdapter(Context mCxt) {
        this.mCxt = mCxt;
    }

    public void setCurrencies(List<CurrencyModel>currencyModels){
        this.currencyModels=currencyModels;
        notifyDataSetChanged();
    }

    public CurrencyModel getSelectedCurrency(){
        if (selectedCurrencyPosition>=0){
            return currencyModels.get(selectedCurrencyPosition);
        }

        return null;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mCxt).inflate(R.layout.view_currency,parent,false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        CurrencyModel currencyModel=currencyModels.get(position);
        if (currencyModel!=null){
            holder.radioCurrency.setText(currencyModel.getCurrencyName());
        }
        if (selectedCurrencyPosition==position){
            holder.radioCurrency.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return currencyModels.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder{
        private RadioButton radioCurrency;
        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            radioCurrency=itemView.findViewById(R.id.radioCurrency);

            radioCurrency.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (getAdapterPosition()!=-1&&getAdapterPosition()<currencyModels.size()&&radioCurrency.isPressed()&&isChecked){
                        int pos=selectedCurrencyPosition;
                        selectedCurrencyPosition=getAdapterPosition();
                        notifyItemChanged(pos);
                        notifyItemChanged(selectedCurrencyPosition);
                    }
                }
            });
        }
    }

}
