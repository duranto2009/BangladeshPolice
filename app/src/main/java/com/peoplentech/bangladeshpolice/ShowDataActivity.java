package com.peoplentech.bangladeshpolice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ShowDataActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> Designation;
    ArrayList<String> phone;
    ArrayList<String> mobile;
    ArrayList<String> fax;
    ArrayList<String> email;
    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID = new ArrayList<String>();

        Name = new ArrayList<String>();

        Designation = new ArrayList<String>();

        phone = new ArrayList<String>();
        mobile = new ArrayList<String>();
        fax = new ArrayList<String>();
        email = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                //Toast.makeText(ShowDataActivity.this, ListViewClickItemArray.get(position).toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShowDataActivity.this, LegendaryActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("des", Designation.get(position));
                intent.putExtra("mobile", mobile.get(position));
                intent.putExtra("email", email.get(position));
                intent.putExtra("phone", phone.get(position));
                intent.putExtra("fax", fax.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID.clear();
        Name.clear();
        Designation.clear();
        phone.clear();
        mobile.clear();
        fax.clear();
        email.clear();

        if (cursor.moveToFirst()) {
            do {

                ID.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                //Inserting Column Name into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                Name.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                Designation.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Designation)));

                phone.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Phone)));

                mobile.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Mobile)));

                email.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_5_Email)));

                fax.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_Fax)));


            } while (cursor.moveToNext());
        }


        listAdapter = new ListAdapter (this, ID, Name, Designation, phone, mobile, email, fax);

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }



}