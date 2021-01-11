package com.example.kma_application.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kma_application.Models.Prescription;
import com.example.kma_application.Models.ResponseModel;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class LoadMedicineTask extends AsyncTask<Void,Void,String> {
    Context context;
    String phone;
    ListView listViewMedicines;

    public LoadMedicineTask(Context context, String phone, ListView listViewMedicines) {
        this.context = context;
        this.phone = phone;
        this.listViewMedicines = listViewMedicines;
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String postResponse = doPostRequest("https://nodejscloudkenji.herokuapp.com/getMedicine", userJson(phone));
            //String postResponse = doPostRequest("http://192.168.1.68:3000/login", jsons[0]);
            return postResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String postResponse) {
        Gson gson = new Gson();
        Prescription prescription = null;
        ResponseModel responseModel= null;
        try {
            responseModel = gson.fromJson(postResponse,ResponseModel.class);
            prescription = gson.fromJson(postResponse, Prescription.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (responseModel.getResponse() == null){
            if (true){
                if (prescription != null){
                    //Toast.makeText(this.context, "Class: "+prescription.get_class(), Toast.LENGTH_LONG).show();
                    //String birth = invertedDate(prescription.getBirth());

                }else
                    Toast.makeText(this.context, "Prescription: "+ prescription, Toast.LENGTH_LONG).show();
            }

        }else
            Toast.makeText(this.context, "Response: "+ responseModel.getResponse(), Toast.LENGTH_LONG).show();
    }

    private String invertedDate(String birth) {
        String[] strings = birth.split("-",3);
        String result = strings[2]+"-"+strings[1]+"-"+strings[0];
        return result;
    }

    // post request code here
    String userJson(String phone) {
        return "{\"phone\":\"" + phone + "\"}";
    }
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}