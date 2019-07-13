package com.gasosa.uefs.fragment;

import android.annotation.SuppressLint;
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
import com.gasosa.uefs.adapter.gasolinaAdapter;
import com.gasosa.uefs.adapter.gasolinaAditivadaAdapter;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.model.Posto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@SuppressLint("ValidFragment")
public class GasolinaFragment extends Fragment {
    private RecyclerView listarGasolina;
    private List<Posto> gasolina = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerFeed;
    private gasolinaAditivadaAdapter gasolinaAdapter;
    private FirebaseDatabase database;
    private Button buttonLink;
    private Query query;

   private String escolha;
    public GasolinaFragment(String t) {
        // Required empty public constructor
        escolha=t;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gasolina2, container, false);



        listarGasolina=view.findViewById(R.id.gasolinaAditivadaList);
        buttonLink=view.findViewById(R.id.buttonLinkAdit);
        database= ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();
       // query = usuariosRef.child("Postos").orderByChild("gasolinaAd").startAt(1);

        if (escolha == "Menor preço") {
            query = usuariosRef.child("Postos").orderByChild("gasolinaAd").startAt(1);


            //gasAdapter.notifyDataSetChanged();
        }
        if (escolha =="Por data de atualização dos preços") {
            query = usuariosRef.child("Postos").orderByChild("data").startAt("01-01-2019").endAt("31-12-2019");

            //gasAdapter.notifyDataSetChanged();
        }if(escolha =="Menor distância") {
            query = usuariosRef.child("Postos").orderByChild("gasolinaAd");
        }
        if(escolha==null){
            query = usuariosRef.child("Postos").orderByChild("gasolinaAd").startAt(2.0);
            //gasAdapter.notifyDataSetChanged();
        }
//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);
        usuariosRef.getDatabase();
        usuariosRef.keepSynced(true);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //abri_link(view);
        listarGasolina.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //listarGasolina.setLayoutManager(new LinearLayoutManager(getActivity()));
        listarGasolina.setLayoutManager(layoutManager);


        gasolinaAdapter= new gasolinaAditivadaAdapter(gasolina,getActivity());
        listarGasolina.setAdapter(gasolinaAdapter);
        return view;
    }


    private void listarFeed(){

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gasolina.clear();
                for ( DataSnapshot ds: dataSnapshot.getChildren() ){


                    gasolina.add( ds.getValue(Posto.class) );
                }
                if(escolha=="Menor preço" || escolha==null){

                    Collections.reverse( gasolina );
                }if(escolha=="Por data de atualização dos preços"){
                    for(int i = 0;i<gasolina.size();i++){  //enquanto i for menor, não maior
                        Posto p= gasolina.get(i);
                        if(p.getGasolinaAd()==0.0){
                            gasolina.remove(i);
                        }

                    }
                }

                gasolinaAdapter.notifyDataSetChanged();
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
