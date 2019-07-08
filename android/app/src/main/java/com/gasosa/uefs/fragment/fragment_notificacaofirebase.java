package com.gasosa.uefs.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gasosa.uefs.R;
import com.gasosa.uefs.adapter.promoAdapter;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.model.Notificacao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_notificacaofirebase.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_notificacaofirebase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_notificacaofirebase extends Fragment {


    private RecyclerView listarNotificacao;
    private List<Notificacao> notifica = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerFeed;
    private com.gasosa.uefs.adapter.promoAdapter promoAdapter;
    private FirebaseDatabase database;
    //private Button buttonLink;
    private Query query;

    public fragment_notificacaofirebase() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_notificacaofirebase, container, false);

        listarNotificacao=view.findViewById(R.id.Lista_notifica);
       // buttonLink=view.findViewById(R.id.buttonLinkGas);
        database= ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();
        query = usuariosRef.child("notificacao").orderByChild("data");
//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);
        usuariosRef.getDatabase();
        usuariosRef.keepSynced(true);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //abri_link(view);
        listarNotificacao.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //listarGasolina.setLayoutManager(new LinearLayoutManager(getActivity()));
        listarNotificacao.setLayoutManager(layoutManager);


        promoAdapter= new promoAdapter(notifica,getActivity());
        listarNotificacao.setAdapter(promoAdapter);
        return view;
    }
    private void listarFeed(){

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notifica.clear();
                for ( DataSnapshot ds: dataSnapshot.getChildren() ){
                    notifica.add( ds.getValue(Notificacao.class) );
                }
                Collections.reverse( notifica );
                promoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        listarFeed();
    }

    @Override
    public void onStop() {
        super.onStop();
        usuariosRef.removeEventListener( valueEventListenerFeed );
    }
}
