package com.gasosa.uefs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.net.Uri;

import com.gasosa.uefs.R;
import com.gasosa.uefs.model.Notificacao;

import java.util.List;

public class promoAdapter extends RecyclerView.Adapter<promoAdapter.MyViewHolder> {

    private List<Notificacao> listaNotifica;
    private Context context;
    public promoAdapter(List<Notificacao> l, Context c) {
        this.listaNotifica = l;
        this.context = c;
    }
    @NonNull
    @Override
    public promoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_promo, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull promoAdapter.MyViewHolder myViewHolder, int i) {
        final Notificacao nt = listaNotifica.get(i);

        myViewHolder.titulo.setText(nt.getTitulo());
        myViewHolder.data.setText(nt.getData());
        myViewHolder.corpo.setText(nt.getCorpo());

    }

    @Override
    public int getItemCount() {
        return listaNotifica.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView corpo;
        TextView data;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_notificappp);
            corpo = itemView.findViewById(R.id.corpoNotifica);
            data = itemView.findViewById(R.id.Data_notifica);
            cardView = itemView.findViewById(R.id.card_notifica);



        }

    }
}
