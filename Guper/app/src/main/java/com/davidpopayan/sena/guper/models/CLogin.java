package com.davidpopayan.sena.guper.models;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CLogin extends AsyncTask<String,Void,String> {

    OkHttpClient client = new OkHttpClient();
    public interface AsyncRespponse{
        void processFinish(String output);
    }

    public AsyncRespponse delegate = null;

    public CLogin(AsyncRespponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... strings) {
        RequestBody body = new FormBody.Builder().add("username",strings[1])
                                                 .add("email",strings[2])
                                                 .add("password",strings[3]).build();
        Request request = new Request.Builder().url(strings[0]).post(body).build();

        try{
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()){
                throw new IOException("Error en la peticion "+response.toString());


            }
            return  response.body().string();

        }catch (IOException e){

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }



}
