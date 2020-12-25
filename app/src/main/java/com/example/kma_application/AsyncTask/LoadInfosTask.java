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
import com.example.kma_application.Models.Admin;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.ResponseModel;
import com.example.kma_application.Models.Teacher;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class LoadInfosTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private String phone;
    private String role;

    EditText txtName, txtPhone, txtId;

    public InfoModel getInfoModel() {
        return infoModel;
    }

    private InfoModel infoModel;

    public interface AsyncResponse {
        void processFinish(InfoModel output, String role);
//        void processFinish(Admin output);
//        void processFinish(Teacher output);
//        void processFinish(Parent output);

    }

    public AsyncResponse mainActivity, homeFrag, contactFrag, notifiFrag, userFrag;


    public LoadInfosTask(String phone, String role, AsyncResponse mainActivity) {
        this.phone = phone;
        this.role = role;
        this.mainActivity = mainActivity;
    }

    public LoadInfosTask(String phone, String role, AsyncResponse mainActivity, AsyncResponse homeFrag, AsyncResponse contactFrag, AsyncResponse notifiFrag, AsyncResponse userFrag) {
        this.phone = phone;
        this.role = role;
        this.mainActivity = mainActivity;
        this.homeFrag = homeFrag;
        this.contactFrag = contactFrag;
        this.notifiFrag = notifiFrag;
        this.userFrag = userFrag;
    }

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(Void... voids) {
        try {
            //Log.e("MY_TAG",this.infoModel.getName());
            String postResponse = doPostRequest("https://nodejscloudkenji.herokuapp.com/getInfo", userJson(phone,role));
            //String postResponse = doPostRequest("http://192.168.1.68:3000/getInfo", jsons[0]);

            return postResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String postResponse) {
        Gson gson = new Gson();
        if (role.equals("admin")) {

            Admin admin = gson.fromJson(postResponse, Admin.class);
            passInfo(admin, role);
        }
        else if (role.equals("parent")) {

            Parent parent = gson.fromJson(postResponse, Parent.class);
            passInfo(parent, role);
        }
        else if (role.equals("teacher")) {

            Teacher teacher= gson.fromJson(postResponse, Teacher.class);
            passInfo(teacher, role);
        }else
            Toast.makeText(this.context, "Role: "+role, Toast.LENGTH_LONG).show();
//        if (infoModel.getRes()){
//            Toast.makeText(this.context, infoModel.getResponse(), Toast.LENGTH_LONG).show();
//            startMainActivities();
//        }else
//            Toast.makeText(this.context, infoModel.getResponse(), Toast.LENGTH_LONG).show();

//        txtName.setText(infoModel.getName());
//        txtId.setText(infoModel.getId());
//        txtPhone.setText(infoModel.getPhone());
    }

    private void passInfo(InfoModel infoModel, String role) {
        mainActivity.processFinish(infoModel, role);
        homeFrag.processFinish(infoModel, role);
        contactFrag.processFinish(infoModel, role);
        notifiFrag.processFinish(infoModel, role);
        userFrag.processFinish(infoModel, role);
    }

    // post request code here

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String userJson(String phone, String role) {
        return "{\"phone\":\"" + phone + "\","
                +"\"role\":\"" + role +"\"}";
    }

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
