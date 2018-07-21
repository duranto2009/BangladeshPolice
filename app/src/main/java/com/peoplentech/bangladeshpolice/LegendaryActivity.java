package com.peoplentech.bangladeshpolice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LegendaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legendary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        //added
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView designation = (TextView) findViewById(R.id.textdesignation);
        final TextView mobile = (TextView) findViewById(R.id.textMobile);
        final TextView email = (TextView) findViewById(R.id.textEmail);
        final TextView phone = (TextView) findViewById(R.id.textphone);
        final ImageView message = (ImageView) findViewById(R.id.message);

        final ImageView mobileIcon = (ImageView) findViewById(R.id.mobileicon);
        final ImageView phoneIcon = (ImageView) findViewById(R.id.phoneicon);
        final ImageView emailIcon = (ImageView) findViewById(R.id.emailicon);
        CardView mobileCard = (CardView) findViewById(R.id.cardmobile);
        CardView phoneCard = (CardView) findViewById(R.id.cardphone);
        CardView messageCard = (CardView) findViewById(R.id.cardmessage);
        CardView mailCard = (CardView) findViewById(R.id.cardmail);

        mobile.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        phone.setVisibility(View.GONE);
        message.setVisibility(View.GONE);

        mobileIcon.setVisibility(View.GONE);
        phoneIcon.setVisibility(View.GONE);
        emailIcon.setVisibility(View.GONE);

        mobileCard.setVisibility(View.GONE);
        phoneCard.setVisibility(View.GONE);
        messageCard.setVisibility(View.GONE);
        mailCard.setVisibility(View.GONE);

        if (bundle != null) {
            String dmp_des = (String) bundle.get("des");
            String dmp_mobile = (String) bundle.get("mobile");
            String dmp_email = (String) bundle.get("email");
            String dmp_phone = (String) bundle.get("phone");
            String dmp_fax = (String) bundle.get("fax");
            designation.setText(dmp_des);


            if (dmp_email != null && !dmp_email.isEmpty()) {
                email.setVisibility(View.VISIBLE);
                emailIcon.setVisibility(View.VISIBLE);
                mailCard.setVisibility(View.VISIBLE);
                email.setText(dmp_email);
            }
            if (dmp_phone != null && !dmp_phone.isEmpty()) {
                phone.setVisibility(View.VISIBLE);
                phoneIcon.setVisibility(View.VISIBLE);
                phoneCard.setVisibility(View.VISIBLE);
                phone.setText(dmp_phone);
            }
            if (dmp_mobile != null && !dmp_mobile.isEmpty()) {
                mobile.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                mobileIcon.setVisibility(View.VISIBLE);
                mobileCard.setVisibility(View.VISIBLE);
                messageCard.setVisibility(View.VISIBLE);
                mobile.setText(dmp_mobile);
            }


        }


        mobileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mobile.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });

        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });

        emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                if (!TextUtils.isEmpty(mail)){
                    mailIntent.setData(Uri.parse("mailto:" + mail));
                    startActivity(mailIntent);
                }
            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mobile.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                if (!TextUtils.isEmpty(mail)){
                    mailIntent.setData(Uri.parse("mailto:" + mail));
                    startActivity(mailIntent);
                }
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mobile.getText().toString();
                if (!TextUtils.isEmpty(number)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
                    startActivity(smsIntent);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:

                onBackPressed();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
