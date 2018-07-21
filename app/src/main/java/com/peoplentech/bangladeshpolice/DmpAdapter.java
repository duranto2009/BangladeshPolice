package com.peoplentech.bangladeshpolice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.PopupMenu;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import java.util.List;

/**
 * Created by User on 1/23/2018.
 */

public class DmpAdapter extends RecyclerView.Adapter<DmpAdapter.ViewHolder> {

    private Context context;
    private List<Dmp> designation;

    public DmpAdapter(Context context, List<Dmp> designation) {
        this.context = context;
        this.designation = designation;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.designation.setText(designation.get(position).getDesignation());
        holder.mobile.setText(designation.get(position).getMobile());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.email.setText(designation.get(position).getEmail());
        holder.phone.setText(designation.get(position).getPhone());
        holder.fax.setText(designation.get(position).getFax());
    }



    @Override
    public int getItemCount() {
        return designation.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView email;
        public TextView designation;
        public TextView mobile;
        public TextView phone;
        public TextView fax;

        public ViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.email);
            designation = (TextView) itemView.findViewById(R.id.designation);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            phone = (TextView) itemView.findViewById(R.id.phone);
            fax = (TextView) itemView.findViewById(R.id.fax);
            designation.setOnClickListener(this);

            email.setVisibility(View.GONE);
            mobile.setVisibility(View.GONE);
            phone.setVisibility(View.GONE);
            fax.setVisibility(View.GONE);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            showPopupMenu(v,position);

        }
    }

    private void showPopupMenu(View view, int position) {

        Intent intent = new Intent(context.getApplicationContext(), LegendaryActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("des", designation.get(position).getDesignation());
        intent.putExtra("mobile", designation.get(position).getMobile());
        intent.putExtra("email", designation.get(position).getEmail());
        intent.putExtra("phone", designation.get(position).getPhone());
        intent.putExtra("fax", designation.get(position).getFax());
        context.getApplicationContext().startActivity(intent);
    }

}
