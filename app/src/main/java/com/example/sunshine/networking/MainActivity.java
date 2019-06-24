package com.example.sunshine.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetextView();
            }


        });
    }
    private void updatetextView() {
        // Make the Network Call here..
        //TextView textView=findViewById(R.id.tv);

         NetwokTask networkTask= new NetwokTask();
         networkTask.execute("https://www.google.com","https://www.facebook.com","https://www.github.com");
    }
     class NetwokTask extends AsyncTask<String,Void,String>{

         @Override
         protected String doInBackground(String... strings) {
             String stringUrl=strings[2];
             try {
                 URL url= new URL(stringUrl);

                 //  now we need to make the api call with this url,, make the connection wihh the url..,.,
    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                 InputStream inputStream= httpURLConnection.getInputStream();

                 Scanner scanner= new Scanner(inputStream);

                 // usedelimeter is used to read the entire content  of inputstream in one go
                 scanner.useDelimiter("\\A");
                 if(scanner.hasNext()){
                     String s=  scanner.next();
                     return s;

                 }

             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return "Failed To Load";
         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             TextView textView=findViewById(R.id.tv);
             textView.setText(s);
         }
     }


}
