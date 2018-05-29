package com.davidpopayan.sena.guper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.davidpopayan.sena.guper.Fragments.FragmentInicio;

public class Login extends AppCompatActivity {

    EditText txtUser, txtPass;


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


        if (user.isEmpty()){
            txtUser.setError("Campo obligatorio");
            txtUser.requestFocus();
        }else if (pass.isEmpty()){
            txtPass.setError("Campo obligatorio");
            txtPass.requestFocus();
        }else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("nu1",user);
            intent.putExtra("nu2",pass);
            startActivity(intent);
        }
    }
}

