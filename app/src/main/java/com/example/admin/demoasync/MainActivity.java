package com.example.admin.demoasync;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2;
    Button btnMulti;
    String strURL;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);
        btnMulti = (Button) findViewById(R.id.btnMulti);

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(num1.getText().toString());
                int j = Integer.parseInt(num2.getText().toString());
                strURL = "http://www.telusko.com/addition.htm?t1= " +i+ "&t2=" +j;

                new MultiplyAsync().execute();

            }
        });
    }

    public class MultiplyAsync extends AsyncTask<String,String,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s)
            Toast.makeText(getApplicationContext(),"Result is: "+result, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())); //it will specify from where you will getting the input

                //Now,fetch the result from the server
                String value = bufferedReader.readLine();
                System.out.println("result is: "+value);
                result = value;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
