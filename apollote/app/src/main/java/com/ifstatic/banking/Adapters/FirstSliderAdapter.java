package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifstatic.banking.Models.FirstSliderModel;
import com.ifstatic.banking.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstSliderAdapter extends SliderViewAdapter<FirstSliderAdapter.FirstSliderAdapterVH> {

    private Context context;
    private List<FirstSliderModel> firstSliderItems = new ArrayList<>();

    public FirstSliderAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<FirstSliderModel> firstSliderItems) {
        this.firstSliderItems = firstSliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.firstSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(FirstSliderModel firstSliderItem) {
        this.firstSliderItems.add(firstSliderItem);
        notifyDataSetChanged();
    }

    @Override
    public FirstSliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_slider, null);
        return new FirstSliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(FirstSliderAdapterVH viewHolder, final int position) {

        FirstSliderModel firstSliderItem = firstSliderItems.get(position);

        viewHolder.txtTitle.setText(firstSliderItem.getTitle());
        viewHolder.txtDescription.setText(firstSliderItem.getDescription());

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return firstSliderItems.size();
    }

    class FirstSliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        TextView txtTitle;

        TextView txtDescription;

        public FirstSliderAdapterVH(View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            this.itemView = itemView;
        }
    }

}