package com.davidpopayan.sena.guper.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.app.TimePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.davidpopayan.sena.guper.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment{


    ImageButton btnHora;
    EditText txtHora;
    private static final String Cero= "0";
    private static final String DOS_PUNTOS = ":";

    public final Calendar c = Calendar.getInstance();

    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);



    public FragmentInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_inicio, container, false);

        btnHora = view.findViewById(R.id.btnHora);
        txtHora = view.findViewById(R.id.txtHora);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog recoger = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hora = (hourOfDay <10) ? String.valueOf(Cero + hourOfDay) : String.valueOf(hourOfDay);
                        String minuto = (minute <10) ? String.valueOf(Cero + minute) : String.valueOf(minute);
                        String AM_PM;

                        if (hourOfDay<12){
                            AM_PM = "a.m.";

                        }else {
                            AM_PM = "p.m.";
                        }
                        txtHora.setText(hora + DOS_PUNTOS + minuto +"" + AM_PM);
                    }
                }, hora ,minuto, false);
                recoger.show();

            }
        });

         return view;
    }
}





