package com.peoplentech.bangladeshpolice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2/1/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="testDB";

    public static final String TABLE_NAME="testdmp";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Designation="designation";

    public static final String Table_Column_3_Phone="phone";
    public static final String Table_Column_4_Mobile="mobile";
    public static final String Table_Column_5_Email="mail";
    public static final String Table_Column_6_Fax="fax";


    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Designation+" VARCHAR, "+Table_Column_3_Phone+" VARCHAR, "+Table_Column_4_Mobile+" VARCHAR, "+Table_Column_5_Email+" VARCHAR, "+Table_Column_6_Fax+" VARCHAR)";
        database.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}
