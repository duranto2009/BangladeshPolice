package com.peoplentech.bangladeshpolice;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class DmpActivity extends AppCompatActivity {

    private static final String TAG = DmpActivity.class.getSimpleName();
    private List<Dmp> designation;
    private RecyclerView recyclerView;
    private LinearLayoutManager gridLayout;
    private DmpAdapter adapter;
    SQLiteDatabase sqLiteDatabase;
    String HttpJSonURL = "http://10.16.20.42/policeProject/show.php";

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmp);

        //Spinner
        initCustomSpinner();
    }

    private void initCustomSpinner() {

        Spinner spinnerCustom= (Spinner) findViewById(R.id.dmpspinner);
        // Spinner Drop down elements
        ArrayList<String> languages = new ArrayList<String>();
        languages.add("--Select--");
        languages.add("Headquarter Division");
        languages.add("Logistics and Procurement Division");
        languages.add("Transport Division");
        languages.add("Estate and Development Division");
        languages.add("Diplomatic Security Division");
        languages.add("Protection and Protocol Division");
        languages.add("Professional Standard and Internal Investigation");
        languages.add("Ramna Division");
        languages.add("Lalbag Division");
        languages.add("Motijheel Division");
        languages.add("Wari Division");
        languages.add("Mirpur Division");
        languages.add("Gulshan Division");
        languages.add("Uttara Division");
        languages.add("DB South");
        languages.add("DB North");
        languages.add("Traffic North Division");
        languages.add("Traffic South Division");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(DmpActivity.this,languages);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //clear();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                designation = new ArrayList<>();
                gridLayout = new LinearLayoutManager(DmpActivity.this);
                recyclerView.setLayoutManager(gridLayout);

                RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                itemAnimator.setAddDuration(1000);
                itemAnimator.setRemoveDuration(1000);
                recyclerView.setItemAnimator(itemAnimator);

                adapter = new DmpAdapter(DmpActivity.this, designation);
                recyclerView.setAdapter(adapter);

                if (adapter.getItemCount() > 0) {

                    designation.clear();
                    adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
                }

                String item = parent.getItemAtPosition(position).toString();
                if (position > 0) {

                    //Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                    if(item.equals("Headquarter Division")) {

                        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network

                            //store data in local sqlite database first

                            SQLiteDataBaseBuild();

                            SQLiteTableBuild();

                            DeletePreviousData();

                            new DmpActivity.StoreJSonDataInToSQLiteClass(DmpActivity.this).execute();

                            //now show data from web

                            getMoviesFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == designation.size() - 1) {
                                        getMoviesFromDB(designation.get(designation.size() - 1).getId());
                                    }

                                }
                            });

                        } else {
                            //No internet connection
                            Intent intent = new Intent(DmpActivity.this, ShowDataActivity.class);
                            startActivity(intent);
                        }


                    } else {
                        Toast.makeText(parent.getContext(), "World", Toast.LENGTH_SHORT).show();
                    }

                }
                int size = designation.size();
                if (size > 0){
                    for (int i = 0; i < size; i++){
                        designation.remove(0);
                    }
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, designation.size());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(DmpActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            if (position > 0) {
                txt.setTextColor(Color.parseColor("#000000"));
            } else {
                txt.setTextColor(Color.parseColor("#808080"));
            }
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(DmpActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(22);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_arrow_drop_down_circle_black_24dp, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }

    //Recyclerview method
    private void getMoviesFromDB(int id) {

          @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... designationIds) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.16.20.42/policeProject/show.php?id=" + designationIds[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                         JSONObject object = array.getJSONObject(i);

                        Dmp dmp = new Dmp(object.getInt("id"), object.getString("mail"),
                                object.getString("designation"), object.getString("mobile"), object.getString("phone"), object.getString("fax"));

                        DmpActivity.this.designation.add(dmp);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }

    //SQLite database methodes

    private class StoreJSonDataInToSQLiteClass extends AsyncTask<Void, Void, Void> {

        public Context context;

        String FinalJSonResult;

        public StoreJSonDataInToSQLiteClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = new ProgressDialog(DmpActivity.this);
            progressDialog.setTitle("LOADING");
            progressDialog.setMessage("Please Wait");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpJSonURL);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("name");

                                String tempDesignation = jsonObject.getString("designation");

                                String phone = jsonObject.getString("phone");
                                String mobile = jsonObject.getString("mobile");
                                String email = jsonObject.getString("mail");
                                String fax = jsonObject.getString("fax");


                                String SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name, designation, phone, mobile, mail, fax) VALUES('"+tempName+"', '"+tempDesignation+"', '"+phone+"', '"+mobile+"', '"+email+"', '"+fax+"');";

                                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            sqLiteDatabase.close();

            progressDialog.dismiss();

            Toast.makeText(DmpActivity.this,"Load Done", Toast.LENGTH_LONG).show();

        }
    }


    public void SQLiteDataBaseBuild(){

        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_Name+" VARCHAR, "+SQLiteHelper.Table_Column_2_Designation+" VARCHAR, "+SQLiteHelper.Table_Column_3_Phone+" VARCHAR, "+SQLiteHelper.Table_Column_4_Mobile+" VARCHAR, "+SQLiteHelper.Table_Column_5_Email+" VARCHAR, "+SQLiteHelper.Table_Column_6_Fax+" VARCHAR);");

    }

    public void DeletePreviousData(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            sqLiteDatabase.execSQL("DELETE FROM "+SQLiteHelper.TABLE_NAME+"");
        }


    }


}
