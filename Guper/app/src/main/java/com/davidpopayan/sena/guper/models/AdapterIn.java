package com.davidpopayan.sena.guper.models;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidpopayan.sena.guper.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterIn extends RecyclerView.Adapter<AdapterIn.Holder> {
    List<Persona> personaList = new ArrayList<>();
    List<Ficha> fichaList = new ArrayList<>();

    private OnItemClickListener mlistener;
    public interface  OnItemClickListener{
        void itemClick(int position);
    }

    public AdapterIn(List<Persona> personaList, List<Ficha> fichaList) {
        this.personaList = personaList;
        this.fichaList = fichaList;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listar_permiso_instructor,parent, false);
        Holder myHolder = new Holder(view,mlistener);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(personaList.get(position),fichaList.get(position));
    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtnombre = itemView.findViewById(R.id.txtnombreIt);
        TextView txtdocumento = itemView.findViewById(R.id.txtdocumentoIn);
        TextView txtficha = itemView.findViewById(R.id.txtfichaIn);
        public Holder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position= getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){

                        }
                    }
                }
            });


        }
        public void connectData(Persona persona, Ficha ficha){
            txtnombre.setText(persona.getNombres() +"\n"+ persona.getApellidos() );
            txtdocumento.setText(persona.getDocumentoIdentidad());
            txtficha.setText(ficha.getNumeroFicha());
        }



    }


}
