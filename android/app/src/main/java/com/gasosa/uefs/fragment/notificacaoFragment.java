package com.gasosa.uefs.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.icu.text.RelativeDateTimeFormatter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gasosa.uefs.R;
import com.gasosa.uefs.acitivity.MainActivity;
import com.gasosa.uefs.adapter.dieselAdapter;
import com.gasosa.uefs.adapter.gasAdapter;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.helper.Local;
import com.gasosa.uefs.model.Posto;
import com.gasosa.uefs.model.PostoGas;
import com.gasosa.uefs.model.PostoGasDistancia;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class notificacaoFragment extends Fragment {

    private RecyclerView listarGas;
    private FusedLocationProviderClient cli;
    private List<PostoGasDistancia> gasD = new ArrayList<>();
    private List<PostoGas> gass = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerFeed;
    private gasAdapter gasAdapter;
    private FirebaseDatabase database;
    private Button buttonLink;
    private Query query;
    private String escolha;

    public notificacaoFragment(String t) {
        // Required empty public constructor
        this.escolha = t;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificacao, container, false);

        listarGas = view.findViewById(R.id.listGas);
        buttonLink = view.findViewById(R.id.buttonLinkGas);
        database = ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();
        System.out.println("Agora vai " +escolha);
        if (escolha == "Menor preço") {
            query = usuariosRef.child("postosgas").orderByChild("gas").startAt(2.0);


            //gasAdapter.notifyDataSetChanged();
        }
        if (escolha =="Por data de atualização dos preços") {
            query = usuariosRef.child("postosgas").orderByChild("data").startAt("2019-01-01").endAt("2019-12-31");

            //gasAdapter.notifyDataSetChanged();
        }if(escolha =="Menor distância") {
            query = usuariosRef.child("postosgas").orderByChild("gas");
        }
        if(escolha==null){
            query = usuariosRef.child("postosgas").orderByChild("gas").startAt(2.0);
            //gasAdapter.notifyDataSetChanged();
        }

//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);
        usuariosRef.getDatabase();
        usuariosRef.keepSynced(true);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //abri_link(view);
        listarGas.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //listarGasolina.setLayoutManager(new LinearLayoutManager(getActivity()));
        listarGas.setLayoutManager(layoutManager);


        gasAdapter = new gasAdapter(gass, getActivity());
        listarGas.setAdapter(gasAdapter);
        return view;
    }

    private void listarFeed() {

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gass.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    gass.add(ds.getValue(PostoGas.class));






                }
               if(escolha=="Menor preço" || escolha==null){
                    Collections.reverse( gass );}



                gasAdapter.notifyDataSetChanged();
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
