package com.davidpopayan.sena.guper.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.davidpopayan.sena.guper.R;
import com.davidpopayan.sena.guper.models.Constantes;
import com.davidpopayan.sena.guper.models.Permiso;
import com.davidpopayan.sena.guper.models.Persona;

import java.util.HashMap;
import java.util.Map;

public class DetalleListaInstruc extends AppCompatActivity {

    Button btnAceptar, btnRechazar;
    String solicitud;
    Persona personaY;
    Permiso permisoY;
    TextView txtnombre, txtdocumento, txtfecha, txtmotivo, txthoras, txtdias, txthoraDeSalida, txtficha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista_instruc);
        inizialite();

    }

    public void inizialite(){
        btnAceptar = findViewById(R.id.btnAceptar);
        btnRechazar = findViewById(R.id.btnRechazar);
        txtnombre = findViewById(R.id.txtnombreDIn);
        txtdocumento = findViewById(R.id.txtdocumentoDIn);
        txtfecha = findViewById(R.id.txtfechaDIn);
        txtmotivo = findViewById(R.id.txtmotivoDIn);
        txthoras = findViewById(R.id.txtHorasDIn);
        txtdias = findViewById(R.id.txtdiasDIn);
        txthoraDeSalida = findViewById(R.id.txtHoraSalidaDIn);
        txtficha = findViewById(R.id.txtCursoFDIn);

    }

    public void inputData(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitud = "Aprobado";
                //solicitudDePermsio(solicitud);
            }
        });

        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitud = "Rechazado";
                //solicitudDePermsio(solicitud);
            }
        });
    }

    public void solicitudDePermsio(final String solicitud){
        RequestQueue requetQueue = Volley.newRequestQueue(this);
        String url = "";
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("estado",solicitud);
                return parameters;
            }
        };
        requetQueue.add(stringRequest);

    }







}
