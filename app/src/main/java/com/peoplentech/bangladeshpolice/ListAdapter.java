package com.peoplentech.bangladeshpolice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2/1/2018.
 */

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> S_Name;
    ArrayList<String> Designation;
    ArrayList<String> phone;
    ArrayList<String> mobile;
    ArrayList<String> email;
    ArrayList<String> fax;


    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> des,
            ArrayList<String> phone,
            ArrayList<String> mobile,
            ArrayList<String> fax,
            ArrayList<String> email
    ) {

        this.context = context2;
        this.ID = id;
        this.S_Name = name;
        this.Designation = des;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.fax = fax;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.name = (TextView) child.findViewById(R.id.textViewSubject);
            holder.designation = (TextView) child.findViewById(R.id.textViewSubjectFullForm);
            holder.phone = (TextView) child.findViewById(R.id.textViewPhone);
            holder.mobile = (TextView) child.findViewById(R.id.textViewMobile);
            holder.email = (TextView) child.findViewById(R.id.textViewEmail);
            holder.fax = (TextView) child.findViewById(R.id.textViewFax);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.name.setText(S_Name.get(position));
        holder.designation.setText(Designation.get(position));
        holder.phone.setText(phone.get(position));
        holder.mobile.setText(mobile.get(position));
        holder.email.setText(email.get(position));
        holder.fax.setText(fax.get(position));

        return child;
    }

    public class Holder {

        TextView name;
        TextView designation;
        TextView phone, mobile, email, fax;
    }
}