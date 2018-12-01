package com.hacks.yale.yhacks_2018;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

public class DrugAdapter extends ExpandableRecyclerAdapter<DrugAdapter.MyParentViewHolder, DrugAdapter.MyChildViewHolder> {
    private LayoutInflater mInflater;
    List<Drug> mDrugs;

    public DrugAdapter(Context context, List<Drug> drugs) {
        super(drugs);
        mInflater = LayoutInflater.from(context);
        mDrugs = drugs;
    }

    public class MyParentViewHolder extends ParentViewHolder {
        public TextView tvName;
        public TextView tvDosage;
        public TextView tvNDC;

        public MyParentViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.drugName);
            tvDosage = (TextView) itemView.findViewById(R.id.drugDosage);
            tvNDC = (TextView) itemView.findViewById(R.id.drugNDC);
        }
    }

    public class MyChildViewHolder extends ChildViewHolder {
        public TextView childField;
        public MyChildViewHolder(View itemView) {
            super(itemView);
            childField = (TextView) itemView.findViewById(R.id.childField);
        }
    }
    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_drug, viewGroup, false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_drug_detailed, viewGroup, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Drug drug = (Drug) parentListItem;
        parentViewHolder.tvName.setText(drug.getName());
        parentViewHolder.tvDosage.setText(drug.getDosage());
        parentViewHolder.tvNDC.setText(drug.getNDC());

    }

    @Override
    public void onBindChildViewHolder(DrugAdapter.MyChildViewHolder childViewHolder, int position, Object childListItem) {
        DrugDetailed drugDetailed = (DrugDetailed) childListItem;
        childViewHolder.childField.setText(drugDetailed.getTitle());
    }
}
