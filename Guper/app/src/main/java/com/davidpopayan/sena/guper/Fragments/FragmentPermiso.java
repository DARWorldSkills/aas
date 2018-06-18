package com.davidpopayan.sena.guper.Fragments;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.davidpopayan.sena.guper.Controllers.Login;
import com.davidpopayan.sena.guper.R;
import com.davidpopayan.sena.guper.models.Constantes;
import com.davidpopayan.sena.guper.models.Permiso;
import com.davidpopayan.sena.guper.models.Persona;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPermiso extends Fragment implements View.OnClickListener{
    ImageButton btnHora1, btnHora2, btnHora11, btnHora22;
    EditText txtHora1, txtHora2,txtHoraT1, txtHoraT2;
    EditText txtSolicitarP;
    Button btnenviar;
    Spinner spMotivo;

    private static  final String Cero = "0";
    private static  final  String DOS_PUNTOS = ":";

    public final Calendar c = Calendar.getInstance();

    private int hora = c.get(Calendar.HOUR_OF_DAY);
    private int minuto = c.get(Calendar.MINUTE);

    public Permiso permisoP = new Permiso();

    public Persona personaP;

    public FragmentPermiso() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_permiso, container, false);

        btnHora1 = view.findViewById(R.id.btnHora1);
        btnHora2 = view.findViewById(R.id.btnHora2);
        btnHora11 = view.findViewById(R.id.btnHora11);
        btnHora22 = view.findViewById(R.id.btnHora22);
        txtHora1 = view.findViewById(R.id.txtHota1);
        txtHora2 = view.findViewById(R.id.txtHora2);
        txtHoraT1 = view.findViewById(R.id.txtHoraT1);
        txtSolicitarP = view.findViewById(R.id.txtdetalleSP);
        txtHoraT2= view.findViewById(R.id.txtHoraT2);
        btnenviar = view.findViewById(R.id.btnEnviar);
        spMotivo = view.findViewById(R.id.spmotivoP);
        listarMotivos();
       btnHora1.setOnClickListener(this);
       btnHora2.setOnClickListener(this);
       btnHora11.setOnClickListener(this);
       btnHora22.setOnClickListener(this);
       btnenviar.setOnClickListener(this);



        return view;
    }

    public void listarMotivos(){
        List<String> datos = new ArrayList<>();
        datos.add("Enfermedad");
        datos.add("Accidente");
        datos.add("Calamidad domestica");
        datos.add("Otro");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,datos);
        spMotivo.setAdapter(arrayAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        personaP = Login.personaT;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnHora1:
                obtenerHora1();

                break;
            case R.id.btnHora2:

                obtenerHora2();
                break;

            case R.id.btnHora11:
                obtenerHora11();

                break;

            case R.id.btnHora22:
                obtenerHora22();

                break;

            case R.id.btnEnviar:
                solicitar_permiso();
                btnenviar.setEnabled(false);
                break;
        }
    }

    private void obtenerHora22() {

        numberpicker2();

    }

    private void obtenerHora11() {
        numberpicker1();


    }

    private void numberpicker1(){
        NumberPicker mynumberpicker = new NumberPicker(getActivity());
        mynumberpicker.setMaxValue(24);
        mynumberpicker.setMinValue(0);
        NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                txtHoraT1.setText(""+newVal);
            }
        };
        mynumberpicker.setOnValueChangedListener(myvaluechange);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(mynumberpicker);
        builder.setTitle("Hora");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void numberpicker2(){

        NumberPicker mynumberpicker = new NumberPicker(getActivity());
        mynumberpicker.setMaxValue(24);
        mynumberpicker.setMinValue(0);
        NumberPicker.OnValueChangeListener myvaluechange = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                txtHoraT2.setText(""+newVal);
            }
        };
        mynumberpicker.setOnValueChangedListener(myvaluechange);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(mynumberpicker);
        builder.setTitle("Hora");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }



    private void obtenerHora1() {

        TimePickerDialog recoger = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hora =(hourOfDay <10) ? String.valueOf(Cero + hourOfDay) : String.valueOf(hourOfDay);
                String minuto = (minute <10) ? String.valueOf(Cero + minute) : String.valueOf(minute);
                String AM_PM;

                if (hourOfDay<12){
                    AM_PM = "a.m.";
                }else {
                    AM_PM = "p.m.";
                }
                txtHora1.setText(hora + DOS_PUNTOS + minuto + "" + AM_PM);

            }
        }, hora , minuto , false);
        recoger.show();

    }

    private void obtenerHora2() {
        TimePickerDialog recoger = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hora =(hourOfDay <10) ? String.valueOf(Cero + hourOfDay) : String.valueOf(hourOfDay);
                String minuto = (minute <10) ? String.valueOf(Cero + minute) : String.valueOf(minute);
                String AM_PM;

                if (hourOfDay<12){
                    AM_PM = "a.m.";
                }else {
                    AM_PM = "p.m.";
                }
                txtHora2.setText(hora + DOS_PUNTOS + minuto + "" + AM_PM);

            }
        }, hora , minuto , false);
        recoger.show();
    }




    public void solicitar_permiso(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Constantes.urlPermiso;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                permisoP = gson.fromJson(response, permisoP.getClass());
                Toast.makeText(getContext(), permisoP.getUrl(), Toast.LENGTH_SHORT).show();
                aprendizPermiso();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("motivo",spMotivo.getSelectedItem().toString());
                parameters.put("solicitoPermisoPor",txtSolicitarP.getText().toString());
                parameters.put("permisoPorHora","0");
                parameters.put("permisoPorDias","2");
                parameters.put("horaSalida","1:00pm");
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                parameters.put("fecha",dateFormat.format(date));


                return  parameters;
            }
        };

        requestQueue.add(request);

    }

    public void aprendizPermiso(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url=Constantes.urlAprendizPermiso;
        StringRequest stringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
                parameters.put("estado","En espera");
                parameters.put("permiso",permisoP.getUrl());
                parameters.put("persona",personaP.getUrl());

                return parameters;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void obtenerPersona(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Constantes.urlPersona;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    public void obtenerInstructor(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Constantes.urlPersona;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }




}
