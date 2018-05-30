package com.davidpopayan.sena.guper.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.davidpopayan.sena.guper.R;
import com.davidpopayan.sena.guper.models.CLogin;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    String urll = "https://guper.herokuapp.com/rest-auth/login/";
    EditText txtUser, txtPass;
    boolean banderaLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();

    }

    private void inicializar() {
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
    }

    public void enviar(View view) {
        String user = txtUser.getText().toString();
        String pass = txtPass.getText().toString();

        logeo(user, "", pass);
        if (user.isEmpty()){
            txtUser.setError("Campo obligatorio");
            txtUser.requestFocus();
        }else if (pass.isEmpty()){
            txtPass.setError("Campo obligatorio");
            txtPass.requestFocus();
        }

    }

    public void logeo(final String username, String email , final String password){
        CLogin cLogin =(CLogin) new CLogin(new CLogin.AsyncRespponse() {
            @Override
            public void processFinish(String output) {
                if (output!=null){
                    Log.d("Key",output);
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    entrar(username,password);

                    try{
                        JSONObject res = new JSONObject(output);
                        if (res.getString("error")!=null){
                            JSONObject user = res.getJSONObject("username");


                        }


                    }catch (JSONException e){

                    }
                }else {
                    Toast.makeText(Login.this, "Error de inicio", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(urll,username,email,password);
    }


    public void entrar(String username, String password){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("nu1", username);
        intent.putExtra("nu2", password);
        startActivity(intent);
        finish();
    }

}

