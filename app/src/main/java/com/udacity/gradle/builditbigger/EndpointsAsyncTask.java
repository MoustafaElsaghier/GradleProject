package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.elsahier.Jokes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import elsaghier.example.com.androidlib.AndLibActivity;

/**
 * Created by ELSaghier on 1/8/2018.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
//    private JsonGetTaskListener mListener = null;
//    private Exception mError = null;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        Jokes j = new Jokes();
        String name = j.getJoke();

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
//            mError = e;
            return e.getMessage();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, AndLibActivity.class);
        intent.putExtra("javaJoke", result);

//        if (this.mListener != null)
//            this.mListener.onComplete(result, mError);
        context.startActivity(intent);
    }

//    public EndpointsAsyncTask setListener(JsonGetTaskListener listener) {
//        this.mListener = listener;
//        return this;
//    }
//
//    // for test
//    public static interface JsonGetTaskListener {
//        public void onComplete(String jsonString, Exception e);
//    }
}