package com.davidpopayan.sena.guper.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.davidpopayan.sena.guper.Controllers.DetalleListaInstruc;
import com.davidpopayan.sena.guper.R;
import com.davidpopayan.sena.guper.models.AdapterIn;
import com.davidpopayan.sena.guper.models.Constantes;
import com.davidpopayan.sena.guper.models.Ficha;
import com.davidpopayan.sena.guper.models.Persona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListarPermisosIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListarPermisosIn extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    List<Persona> personaList= new ArrayList<>();
    List<Ficha> fichaList = new ArrayList<>();


    public FragmentListarPermisosIn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListarPermisosIn.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListarPermisosIn newInstance(String param1, String param2) {
        FragmentListarPermisosIn fragment = new FragmentListarPermisosIn();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_listar_permisos_in, container, false);

        recyclerView= v.findViewById(R.id.rvPermisoIn);
        AdapterIn adapterIn = new AdapterIn(personaList,fichaList);
        recyclerView.setAdapter(adapterIn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterIn.setMlistener(new AdapterIn.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(getContext(), DetalleListaInstruc.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public void listarPermisos(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Constantes.urlPermiso;
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

    public void listarPersona (){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Constantes.urlPersona;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Persona>>(){}.getType();
                List<Persona> personaList = gson.fromJson(response, type);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    public void listarFicha(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Constantes.urlFicha;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

}
