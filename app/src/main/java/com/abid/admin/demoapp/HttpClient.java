package com.abid.admin.demoapp;

import com.abid.admin.demoapp.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {

    private static String base_url = "http://api.lamusica.com/api/categories";

    public String getApiCall(){
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(base_url);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
           // httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            StringBuffer buffer = new StringBuffer();

            System.out.print(""+httpURLConnection.getResponseCode());
            inputStream = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = br.readLine())!= null){
                buffer.append(line+"\r\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();
            System.out.println("data buffer-------"+buffer.toString());
            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try { inputStream.close(); } catch(Throwable t) {}
            try { httpURLConnection.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }


    public List<DataModel> getJSONParse(String response){
        DataModel dataModel = null;
        List<DataModel> datalist= new ArrayList<DataModel>();

        try {
            if (response !=null) {
                JSONObject jobj = new JSONObject(response);
                JSONArray dataArray = jobj.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    dataModel = new DataModel();
                    JSONObject jDataObj = (JSONObject) dataArray.getJSONObject(i);
                    JSONObject jObject = jDataObj.getJSONObject("_id");
                    dataModel.set_id("" + jObject.getString("$oid"));
                    dataModel.setTitle("" + jDataObj.getString("title"));
                    dataModel.setSlug("" + jDataObj.getString("slug"));
                    dataModel.setColor("" + jDataObj.getString("color"));
                    dataModel.setOrder(jDataObj.getInt("order"));
                    datalist.add(dataModel);
                }

                System.out.println("json-------" + datalist.size());
            }else{
                throw new NullPointerException("Response Is null");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return datalist;
    }
}
