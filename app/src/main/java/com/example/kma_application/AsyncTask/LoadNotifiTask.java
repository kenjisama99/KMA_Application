package com.example.kma_application.AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kma_application.Activity.AdminCreateNotificationActivity;
import com.example.kma_application.Activity.ContactActivity;
import com.example.kma_application.Models.Parent;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class LoadNotifiTask extends AsyncTask<Void,Void,String> {
    Context context;
    ListView listView;
    String role;

    public LoadNotifiTask(Context context, ListView listView, String role) {
        this.context = context;
        this.listView = listView;
        this.role = role;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String postResponse = doPostRequest(
                    "https://nodejscloudkenji.herokuapp.com/getNotify",
                    classJson("")
            );
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

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> contents = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        try {
            JSONArray jsonarray = new JSONArray(postResponse);
            System.out.println(jsonarray);
            for (int i = jsonarray.length()-1 ; i >= 0 ; i--) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                titles.add(jsonobject.getString("title"));
                contents.add(jsonobject.getString("content"));
                images.add(jsonobject.getString("image"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if ( !titles.isEmpty()){
            ArrayAdapter adapter = new ArrayAdapter(
                    context,
                    android.R.layout.simple_list_item_1,
                    titles
            );
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, AdminCreateNotificationActivity.class);

                    intent.putExtra("title", titles.get(position));
                    intent.putExtra("content", contents.get(position));
                    intent.putExtra("image", images.get(position));
                    intent.putExtra("role", role);

                    context.startActivity(intent);
                }
            });
        }else
            Toast.makeText(this.context, "Không có thông báo", Toast.LENGTH_LONG).show();

    }



    // post request code here
    String classJson(String _class) {
        return "{\"_class\":\"" + _class + "\"}";
    }
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String doPostRequest(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
