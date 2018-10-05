package com.abid.admin.demoapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.abid.admin.demoapp.model.DataModel;

import java.net.HttpURLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        relativeLayout = findViewById(R.id.container);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 new Task().execute();
            }
        });
    }

    private class Task extends AsyncTask<Void,Void,Boolean>{
        List<DataModel> datamodelList = null;

        @Override
        protected Boolean doInBackground(Void... voids) {
            HttpClient httpClient = new HttpClient();
            try {
                String response = httpClient.getApiCall();
                if(response!=null && !response.equalsIgnoreCase("")) {
                    datamodelList = httpClient.getJSONParse(response);
                }
                // return true;
            }catch(Exception e){
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
                if(datamodelList != null){
                    if (datamodelList.size()>6) {
                        DataModel dataModel = datamodelList.get(5);
                        String title = dataModel.getTitle();
                        String color = dataModel.getColor();
                        System.out.print("title----"+title+"   color -------"+color);

                        button.setText(""+title);
                        relativeLayout.setBackgroundColor(Color.parseColor(color));
                    }else{
                     //   System.out.println(""+datamodelList.size());
                    }
                }else{
                  //  System.out.println("-----------------------------------------"+datamodelList.size());

                }
        }
    }
}
