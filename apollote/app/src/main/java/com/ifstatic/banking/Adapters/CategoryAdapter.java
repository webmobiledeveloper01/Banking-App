package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ifstatic.banking.Models.CategoryModel;
import com.ifstatic.banking.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mCxt;
    private CategoryClickEvents categoryClickEvents;
    private List<CategoryModel>categoryModels=new ArrayList<>();
    private int selectedCategoryPos=-1;

    public CategoryAdapter(Context mCxt, CategoryClickEvents categoryClickEvents) {
        this.mCxt = mCxt;
        this.categoryClickEvents = categoryClickEvents;
    }

    public void setCategories(List<CategoryModel>categoryModels){
        this.categoryModels=categoryModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.view_categories,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel categoryModel=categoryModels.get(position);
        if (categoryModel!=null){
            holder.txtCategoryName.setText(categoryModel.getCategoryName());
            Glide.with(mCxt.getApplicationContext()).load(categoryModel.getCategoryIcon()).into(holder.imgIcon);

            if (selectedCategoryPos==position){
                holder.relIcon.setBackground(mCxt.getDrawable(R.drawable.bg_category_selected));
            }else {
                holder.relIcon.setBackground(mCxt.getDrawable(R.drawable.bg_category_normal));
            }
        }
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relIcon;
        private ImageView imgIcon;
        private TextView txtCategoryName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            relIcon=itemView.findViewById(R.id.relIcon);
            imgIcon=itemView.findViewById(R.id.imgIcon);
            txtCategoryName=itemView.findViewById(R.id.txtCategoryName);

            relIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition()!=-1&&getAdapterPosition()<categoryModels.size()){
                        categoryClickEvents.onCategoryClicked(categoryModels.get(getAdapterPosition()));
                        int pos=selectedCategoryPos;
                        selectedCategoryPos=getAdapterPosition();
                        if (pos!=-1){
                            notifyItemChanged(pos);
                        }
                        notifyItemChanged(selectedCategoryPos);
                    }
                }
            });
        }
    }

    public interface CategoryClickEvents{
        void onCategoryClicked(CategoryModel categoryModel);
    }
}
