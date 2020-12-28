package com.example.kma_application.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import com.example.kma_application.Activity.MainActivity;
import com.example.kma_application.Fragment.HomeFragment;
import com.example.kma_application.Fragment.NotificationFragment;
import com.example.kma_application.Fragment.UserFragment;
import com.example.kma_application.Models.Admin;
import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
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
    TextView txtName;

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    private InfoModel infoModel;
    public InfoModel getInfoModel() {
        return infoModel;
    }

    public interface AsyncResponse {
        void onLoadInfoTaskFinish(InfoModel output, String role);
    }

    public AsyncResponse mainActivity;
    public AsyncResponse homeFrag;
    public AsyncResponse notifiFrag;
    public AsyncResponse userFrag;


    public LoadInfosTask(String phone, String role, AsyncResponse mainActivity) {
        this.phone = phone;
        this.role = role;
        this.mainActivity = mainActivity;
    }

    public LoadInfosTask(String phone, String role, AsyncResponse mainActivity, AsyncResponse homeFrag, AsyncResponse notifiFrag, AsyncResponse userFrag) {
        this.phone = phone;
        this.role = role;
        this.mainActivity = mainActivity;
        this.homeFrag = homeFrag;
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
            txtName.setText("Admin: "+admin.getName());
        }
        else if (role.equals("parent")) {

            Parent parent = gson.fromJson(postResponse, Parent.class);
            passInfo(parent, role);
            txtName.setText("Bé: "+parent.getChildName()+" - Lớp: "+parent.get_class());
        }
        else if (role.equals("teacher")) {

            Teacher teacher= gson.fromJson(postResponse, Teacher.class);
            passInfo(teacher, role);
            txtName.setText("Cô: "+teacher.getName()+" - Lớp: "+teacher.get_class());
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
        mainActivity.onLoadInfoTaskFinish(infoModel, role);
        homeFrag.onLoadInfoTaskFinish(infoModel, role);
        notifiFrag.onLoadInfoTaskFinish(infoModel, role);
        userFrag.onLoadInfoTaskFinish(infoModel, role);
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
