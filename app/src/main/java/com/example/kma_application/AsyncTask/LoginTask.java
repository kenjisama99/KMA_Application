package com.example.kma_application.AsyncTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import com.example.kma_application.Activity.AdminHomeActivity;
import com.example.kma_application.Activity.MainActivity;
import com.example.kma_application.Models.ResponseModel;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class LoginTask extends AsyncTask<String,Void,String> {
    private Context context;
    private String phone;
    private String role;

    public LoginTask(Context context, String phone) {
        this.context = context;
        this.phone = phone;
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... jsons) {
        try {
            String postResponse = doPostRequest("https://nodejscloudkenji.herokuapp.com/login", jsons[0]);
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
        ResponseModel responseModel= null;
        try {
            responseModel = gson.fromJson(postResponse,ResponseModel.class);
        }catch (Exception e){
            Toast.makeText(this.context, "Lỗi kết nối", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            this.cancel(true);
        }

        if (responseModel != null){
            if (responseModel.getRes()){
                //Toast.makeText(this.context, responseModel.getResponse(), Toast.LENGTH_LONG).show();
                this.role = responseModel.getRole();
                startMainActivities();
            }else
                Toast.makeText(this.context, responseModel.getResponse(), Toast.LENGTH_LONG).show();
        }
    }
    private void startMainActivities() {
        Intent mainActivity = new Intent(this.context, MainActivity.class);

        if (this.role.equals("admin") )
            mainActivity = new Intent(this.context, AdminHomeActivity.class);

        mainActivity.putExtra("phone", this.phone);
        mainActivity.putExtra("role", this.role);
        context.startActivity(mainActivity);
        ((Activity)context).finish();
        //this.cancel(true);
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
