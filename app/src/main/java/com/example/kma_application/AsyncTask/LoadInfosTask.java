package com.example.kma_application.AsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import com.example.kma_application.MainActivity;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.ResponseModel;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class LoadInfosTask extends AsyncTask<String,Void,String> {
    private Context context;

    EditText txtName, txtPhone, txtId;

    public InfoModel getInfoModel() {
        return infoModel;
    }

    private InfoModel infoModel;

    public LoadInfosTask(Context context) {
        this.context = context;
    }

    public LoadInfosTask(Context context, EditText txtName, EditText txtPhone, EditText txtId) {
        this.context = context;
        this.txtName = txtName;
        this.txtPhone = txtPhone;
        this.txtId = txtId;
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... jsons) {
        try {
            //Log.e("MY_TAG",this.infoModel.getName());
            String postResponse = doPostRequest("http://10.0.2.2:8080/getInfo", jsons[0]);

            return postResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String postResponse) {
        Gson gson = new Gson();
        this.infoModel = gson.fromJson(postResponse, InfoModel.class);

//        if (infoModel.getRes()){
//            Toast.makeText(this.context, infoModel.getResponse(), Toast.LENGTH_LONG).show();
//            startMainActivities();
//        }else
//            Toast.makeText(this.context, infoModel.getResponse(), Toast.LENGTH_LONG).show();

        txtName.setText(infoModel.getName());
        txtId.setText(infoModel.getId());
        txtPhone.setText(infoModel.getPhone());
    }

    // post request code here

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
