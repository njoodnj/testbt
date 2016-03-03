package com.example.nkssa.testbt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class auth2 extends AppCompatActivity {
    String id , name, email, address;
    String ID,NAME,timeStamp;
    TextView idTV ,nameTV, emailTV , addressTV;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);

            idTV = (TextView) findViewById(R.id.home_id);
            nameTV = (TextView) findViewById(R.id.home_name);
            emailTV = (TextView) findViewById(R.id.home_email);
            addressTV = (TextView) findViewById(R.id.home_address);

            id = getIntent().getStringExtra("r_id");
            name = getIntent().getStringExtra("r_name");
            email = getIntent().getStringExtra("r_email");
            address = getIntent().getStringExtra("r_address");


            idTV.setText("ID No:"+id);
            nameTV.setText("Name: "+name);
            emailTV.setText("Email:"+email);
            addressTV.setText("Address:"+address);

        }
    public void authorize(View v){
        ID = getIntent().getStringExtra("r_id");
        NAME = getIntent().getStringExtra("r_name");

        //Toast.makeText(ctx, id, Toast.LENGTH_LONG).show();
        BackGround b = new BackGround();
       b.execute(ID, NAME);
        startActivity(new Intent(this, MainActivity.class));


    }
    class BackGround extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {

            String ID = params[0];
            String NAME = params[1];
            String data="";
            int tmp;


            try {
                URL url = new URL("http://192.168.8.100/ES/auth.php");

                String urlParams = "ID="+ID+"&NAME="+NAME;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }


    }
    }
