package com.hacks.yale.yhacks_2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDescription;
        public ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.alertTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.alertDescription);
            ivIcon = (ImageView) itemView.findViewById(R.id.alertIcon);
        }

    }
    private List<Alert> mAlerts;

    public MainAdapter(List<Alert> alerts) {
        mAlerts = alerts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mainView = inflater.inflate(R.layout.item_alert, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(mainView);
        return viewHolder;
    }

    // populating the data into item through holder
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int position) {
        Alert alert = mAlerts.get(position);

        TextView tvTitle = viewHolder.tvTitle;
        tvTitle.setText("Storage Alert");

        TextView tvDescription = viewHolder.tvDescription;
        tvDescription.setText("description");

        ImageView ivAlert = viewHolder.ivIcon;

        Glide.with(context).load(R.drawable.ic_alert).into(ivAlert);
    }

    @Override
    public int getItemCount() {
        return mAlerts.size();
    }
}
