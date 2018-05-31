package com.davidpopayan.sena.guper.models;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class CPermiso extends AsyncTask<String, Void, String>{
    OkHttpClient client = new OkHttpClient();

    public interface AsyncRespponse{
        void processFinish(String output);
    }

    public CPermiso(OkHttpClient client) {
        this.client = client;
    }

    @Override
    protected String doInBackground(String... strings) {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {

            }
        }
    }






}
