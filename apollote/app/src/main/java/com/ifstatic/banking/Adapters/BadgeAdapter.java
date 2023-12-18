package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ifstatic.banking.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {

    private Context mCxt;
    private List<Integer>badges=new ArrayList<>();

    public BadgeAdapter(Context mCxt) {
        this.mCxt = mCxt;
    }

    public void setBadges(List<Integer>integers){
        this.badges=integers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.view_badge,parent,false);
        return new BadgeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
        Integer badge=badges.get(position);

        Glide.with(mCxt.getApplicationContext()).load(badge).into(holder.circleBadge);
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

    public class BadgeViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView circleBadge;

        public BadgeViewHolder(@NonNull View itemView) {
            super(itemView);
            circleBadge=itemView.findViewById(R.id.circleBadge);
        }
    }
}
