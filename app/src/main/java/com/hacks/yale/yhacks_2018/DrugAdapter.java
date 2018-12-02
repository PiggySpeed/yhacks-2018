package com.hacks.yale.yhacks_2018;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.ArrayList;
import java.util.List;


public class DrugAdapter extends ExpandableRecyclerAdapter<DrugAdapter.MyParentViewHolder, DrugAdapter.MyChildViewHolder> {
    private LayoutInflater mInflater;
    List<Drug> mDrugs;
    private Context mContext;

    public DrugAdapter(Context context, ArrayList<Drug> drugs) {
        super(drugs);
        mInflater = LayoutInflater.from(context);
        mDrugs = drugs;
    }

    public class MyParentViewHolder extends ParentViewHolder {
        public TextView tvName;
        public TextView tvDosage;
        public TextView tvNDC;
        public ImageButton btnEmail;

        public MyParentViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.drugName);
            tvDosage = (TextView) itemView.findViewById(R.id.drugDosage);
            tvNDC = (TextView) itemView.findViewById(R.id.drugNDC);

            btnEmail = (ImageButton) itemView.findViewById(R.id.btnEmail);
            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendMail();
                }
            });
        }
    }

    private void sendMail() {
        String[] TO = {"vanessahlyan@gmail.com"};

        String subject = "Medication Instructions";
        String message = "Here are your instructions";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, TO);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            mContext.startActivity(Intent.createChooser(intent, "Choose an email client"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext.getApplicationContext(), "There is no email client installed", Toast.LENGTH_SHORT).show();
        }

    }

    public class MyChildViewHolder extends ChildViewHolder {
        public TextView tvChildField;

        public MyChildViewHolder(View itemView) {
            super(itemView);

            tvChildField = (TextView) itemView.findViewById(R.id.tvChildField);
            tvChildField.setText("This drug is used to treat diabetes.");
        }
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.item_drug, viewGroup, false);
        mContext = viewGroup.getContext();
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
        childViewHolder.tvChildField.setText(drugDetailed.getTitle());
    }
}