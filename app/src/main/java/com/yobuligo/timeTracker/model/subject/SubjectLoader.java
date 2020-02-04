package com.yobuligo.timeTracker.model.subject;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yobuligo.timeTracker.activity.subjectOverviewActivity.SubjectOverviewActivity.SubjectAdapter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SubjectLoader extends AsyncTask<Void, Integer, ArrayList<ISubject>> {

    private SubjectAdapter subjectAdapter;
    private ISubjectContext subjectContext;

    public SubjectLoader(SubjectAdapter subjectAdapter, ISubjectContext subjectContext) {
        this.subjectAdapter = subjectAdapter;
        this.subjectContext = subjectContext;
    }

    @Override
    protected ArrayList<ISubject> doInBackground(Void... voids) {
        ArrayList<ISubject> subjects = new ArrayList<>();

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
    protected void onPostExecute(ArrayList<ISubject> subjects) {
        super.onPostExecute(subjects);
        for (ISubject subject :
                subjects) {
            subjectContext.getSubjectList().AddSubject(subject);
        }
        subjectAdapter.notifyDataSetChanged();
    }
}
