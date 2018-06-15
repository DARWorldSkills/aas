package com.davidpopayan.sena.guper.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.davidpopayan.sena.guper.R;
import com.davidpopayan.sena.guper.models.Persona;
import com.davidpopayan.sena.guper.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    String urll = "https://guper.herokuapp.com/rest-auth/login/";
    EditText txtUser, txtPass;
    Button btnLogin;
    public static String userUrl;
    public static String personaN;
    public static String personaUrl;
    public static Persona personaT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
        btnLogin.setEnabled(true);

    }

    private void inicializar() {

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void enviar(View view) {
        btnLogin.setEnabled(false);
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

    public void logeo(final String username, final String email , final String password){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://guper.herokuapp.com/rest-auth/login/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                obtenerUrlUsuario(username);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Error de inicio", Toast.LENGTH_SHORT).show();
                btnLogin.setEnabled(true);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("username", username);
                parameters.put("email", email);
                parameters.put("password", password);
                return parameters;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void obtenerUrlUsuario(final String username){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url ="https://guper.herokuapp.com/api/user/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type tipodelista= new TypeToken<List<User>>(){}.getType();
                List<User> userList = gson.fromJson(response,tipodelista);

                for (int i=0; i<userList.size();i++){
                    if (userList.get(i).getUsername().equals(username)){
                        userUrl=userList.get(i).getUrl();

                    }
                }

                obtenerPersona();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Falta algo :( ", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


    }


    public void obtenerPersona(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="https://guper.herokuapp.com/api/actulizar_perfil/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type tipodelista = new TypeToken<List<Persona>>(){}.getType();
                List<Persona> personaList = gson.fromJson(response,tipodelista);
                for (int i=0; i<personaList.size();i++){
                    if (personaList.get(i).getUsuario().equals(userUrl)){
                        personaN = personaList.get(i).getNombres();
                        personaUrl = personaList.get(i).getUrl();
                        personaT = personaList.get(i);
                    }
                }

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Error de inicio", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }


    public void entrar(String username, String password){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("nu1", username);
        intent.putExtra("nu2", password);
        startActivity(intent);
        finish();
    }

}

