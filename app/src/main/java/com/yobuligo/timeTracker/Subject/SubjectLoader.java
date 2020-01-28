package com.yobuligo.timeTracker.Subject;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SubjectLoader extends AsyncTask<Void, Integer, ArrayList<Subject>> {

    private SubjectAdapter subjectAdapter;
    private SubjectList subjectList;

    public SubjectLoader(SubjectAdapter subjectAdapter, SubjectList subjectList) {
        this.subjectAdapter = subjectAdapter;
        this.subjectList = subjectList;
    }

    @Override
    protected ArrayList<Subject> doInBackground(Void... voids) {
        ArrayList<Subject> subjects = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:8080/api/subjects";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Gson gson = new Gson();
            subjects = gson.fromJson(json, new TypeToken<ArrayList<Subject>>() {
            }.getType());

            subjects.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return subjects;
    }

    @Override
    protected void onPostExecute(ArrayList<Subject> subjects) {
        super.onPostExecute(subjects);
        for (Subject subject :
                subjects) {
            subjectList.AddSubject(subject);
        }
        subjectAdapter.notifyDataSetChanged();
    }
}
