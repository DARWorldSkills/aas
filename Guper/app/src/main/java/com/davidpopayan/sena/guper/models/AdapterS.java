package com.davidpopayan.sena.guper.models;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidpopayan.sena.guper.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterS extends RecyclerView.Adapter<AdapterS.Holder> {
    List<Permiso> permisoList = new ArrayList<>();
    List<AprendizPermiso> aprendizPermisoList = new ArrayList<>();

    private OnItemClickListener mlistener;
    public interface  OnItemClickListener{
        void itemClick(int position);
    }

    public AdapterS(List<Permiso> permisoList, List<AprendizPermiso> aprendizPermisoList) {
        this.permisoList = permisoList;
        this.aprendizPermisoList = aprendizPermisoList;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listar_permisos_a,parent, false);
        Holder myHolder = new Holder(view,mlistener);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(permisoList.get(position),aprendizPermisoList.get(position));
    }

    @Override
    public int getItemCount() {
        return permisoList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtmotivo = itemView.findViewById(R.id.txtmotivo);
        TextView txtfecha = itemView.findViewById(R.id.txtmotivo);
        TextView txtestado = itemView.findViewById(R.id.txtmotivo);
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
        public void connectData(Permiso permiso, AprendizPermiso aprendizPermiso){
            txtmotivo.setText(permiso.getMotivo());
            txtfecha.setText(permiso.getFecha());
            txtestado.setText(aprendizPermiso.getEstado());
        }



    }


}
