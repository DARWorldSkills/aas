package com.davidpopayan.sena.guper.Fragments;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.davidpopayan.sena.guper.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPermiso extends Fragment implements View.OnClickListener{
    ImageButton btnHora1, btnHora2, btnHora11, btnHora22;
    EditText txtHora1, txtHora2,txtHoraT1, txtHoraT2;


    private static  final String Cero = "0";
    private static  final  String DOS_PUNTOS = ":";

    public final Calendar c = Calendar.getInstance();

    private int hora = c.get(Calendar.HOUR_OF_DAY);
    private int minuto = c.get(Calendar.MINUTE);


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
        txtHoraT2= view.findViewById(R.id.txtHoraT2);

       btnHora1.setOnClickListener(this);
       btnHora2.setOnClickListener(this);
       btnHora11.setOnClickListener(this);
       btnHora22.setOnClickListener(this);



        return view;
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
        }
    }

    private void obtenerHora22() {


    }

    private void obtenerHora11() {


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
}
