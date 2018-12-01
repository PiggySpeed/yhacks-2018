package com.hacks.yale.yhacks_2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder>{
    Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDosage;
        public TextView tvNDC;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.drugName);
            tvDosage = (TextView) itemView.findViewById(R.id.drugDosage);
            tvNDC = (TextView) itemView.findViewById(R.id.drugNDC);
        }

    }
    private List<Drug> mDrugs;

    public DrugAdapter(List<Drug> drugs) {
        mDrugs = drugs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View drugView = inflater.inflate(R.layout.item_drug, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(drugView);
        return viewHolder;
    }

    // populating the data into item through holder
    @Override
    public void onBindViewHolder(@NonNull DrugAdapter.ViewHolder viewHolder, int position) {
        Drug drug = mDrugs.get(position);

        TextView tvName = viewHolder.tvName;
        tvName.setText(drug.getName());

        TextView tvDosage = viewHolder.tvDosage;
        tvDosage.setText(drug.getDosage() + " mg");

        TextView tvNDC = viewHolder.tvNDC;
        tvNDC.setText("NDC: " + drug.getNDC());
    }

    @Override
    public int getItemCount() {
        return mDrugs.size();
    }
}
