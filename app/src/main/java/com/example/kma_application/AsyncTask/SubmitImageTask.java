package com.example.kma_application.AsyncTask;

import android.os.AsyncTask;

import com.example.kma_application.Models.Person;
import com.example.kma_application.Models.Teacher;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SubmitImageTask extends AsyncTask<Void, Void, Void> {

    Teacher teacher;
    String originalBase64;
    String resizeBase64;
    String activityName;
    String _class;
    AsyncResponse activity;

    public interface AsyncResponse {
        void onSubmitImageTaskFinish();
    }

    public SubmitImageTask(Teacher teacher, String originalBase64, String resizeBase64, String fromActivity, AsyncResponse activity) {
        this.teacher = teacher;
        this.originalBase64 = originalBase64;
        this.resizeBase64 = resizeBase64;
        this.activityName = fromActivity;
        this.activity = activity;
    }

    public SubmitImageTask(String originalBase64, String resizeBase64, String activityName, String _class, AsyncResponse activity) {
        this.originalBase64 = originalBase64;
        this.resizeBase64 = resizeBase64;
        this.activityName = activityName;
        this._class = _class;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            if (activityName.equals("main")){
                doPostRequest(
                        "https://nodejscloudkenji.herokuapp.com/submitImage",
                        imagesJson(teacher.get_class(),
                                originalBase64,
                                resizeBase64,
                                teacher.getPhone()
                        )
                );
            }else
                doPostRequest(
                        "https://nodejscloudkenji.herokuapp.com/submitImage",
                        imagesJson(_class,
                                originalBase64,
                                resizeBase64,
                                "teacher"
                        )
                );


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        activity.onSubmitImageTaskFinish();
    }

    // post request code here
    String imagesJson(String _class, String originalBase64, String resizeBase64, String phone) {
        return "{\"_class\":\"" + _class + "\","
                + "\"id\":\"" + phone + "\","
                + "\"fromActivity\":\"" + activityName + "\","
                + "\"originalBase64\":\"" + originalBase64 + "\","
                + "\"resizeBase64\":\"" + resizeBase64 + "\"}";
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    void doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        Response res = client.newCall(request).execute();
        //return response.body().string();
    }
}
