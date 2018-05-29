package com.davidpopayan.sena.guper.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.davidpopayan.sena.guper.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment implements View.OnClickListener{

    ImageView ImgPerfil;
    Button btnCambiarP,btnGuardar;


    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_perfil, container, false);

        ImgPerfil = view.findViewById(R.id.ImgPerfil);
        btnCambiarP = view.findViewById(R.id.btnCambiarI);
        btnGuardar = view.findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(this);


        btnCambiarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarImagen();

            }
        });

        return view;
    }

    private void cargarImagen() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Selecciona la app"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            ImgPerfil.setImageURI(path);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnGuardar:
                Snackbar.make(v,"Se ha guardado correctamente",Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
