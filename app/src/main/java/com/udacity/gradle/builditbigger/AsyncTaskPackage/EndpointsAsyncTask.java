package com.udacity.gradle.builditbigger.AsyncTaskPackage;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.elsahier.Jokes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import elsaghier.example.com.androidlib.Activities.AndLibActivity;

/**
 * Created by ELSaghier on 1/8/2018.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    public EndpointsAsyncTask() {
    }

    @Override
    public String doInBackground(Void... voids) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        Jokes j = new Jokes();
        String name = j.getJoke();

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if (context != null) {
            Intent intent = new Intent(context, AndLibActivity.class);
            intent.putExtra("javaJoke", result);
            context.startActivity(intent);
        }
    }
}