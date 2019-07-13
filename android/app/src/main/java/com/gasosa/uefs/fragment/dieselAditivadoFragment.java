package com.gasosa.uefs.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gasosa.uefs.R;
import com.gasosa.uefs.adapter.dieselAdapter;
import com.gasosa.uefs.adapter.dieselAditivadoAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class dieselAditivadoFragment extends Fragment {
    private RecyclerView listarDiesel;
    private List<Posto> diesel = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerFeed;
    private dieselAditivadoAdapter dieselAdapter;
    private FirebaseDatabase database;
    private Button buttonLink;
    private Query query;
    private String escolha;
    public dieselAditivadoFragment(String t) {
        // Required empty public constructor
        escolha=t;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_diesel_aditivado, container, false);
        listarDiesel=view.findViewById(R.id.dieseAditiviadolList);
        buttonLink=view.findViewById(R.id.buttonLinkDiA);
        database= ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();
       // query = usuariosRef.child("Postos").orderByChild("dieselAd").startAt(1);
//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);

        if (escolha == "Menor preço") {
            query = usuariosRef.child("Postos").orderByChild("dieselAd").startAt(1);


            //gasAdapter.notifyDataSetChanged();
        }
        if (escolha =="Por data de atualização dos preços") {
            query = usuariosRef.child("Postos").orderByChild("data").startAt("01-01-2019").endAt("31-12-2019");

            //gasAdapter.notifyDataSetChanged();
        }if(escolha =="Menor distância") {
            query = usuariosRef.child("Postos").orderByChild("dieselAd");
        }
        if(escolha==null){
            query = usuariosRef.child("Postos").orderByChild("dieselAd").startAt(2.0);
            //gasAdapter.notifyDataSetChanged();
        }
        usuariosRef.getDatabase();
        usuariosRef.keepSynced(true);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //abri_link(view);
        listarDiesel.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //listarGasolina.setLayoutManager(new LinearLayoutManager(getActivity()));
        listarDiesel.setLayoutManager(layoutManager);


        dieselAdapter= new dieselAditivadoAdapter(diesel,getActivity());
        listarDiesel.setAdapter(dieselAdapter);
        return view;
    }
    private void listarFeed(){

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                diesel.clear();
                for ( DataSnapshot ds: dataSnapshot.getChildren() ){
                    diesel.add( ds.getValue(Posto.class) );
                }

                if(escolha=="Menor preço" || escolha==null){

                    Collections.reverse( diesel);
                }
                if(escolha=="Por data de atualização dos preços"){
                    for(int i = 0;i<diesel.size();i++){  //enquanto i for menor, não maior
                       Posto p= diesel.get(i);
                       if(p.getDieselAd()==0.0){
                           diesel.remove(i);
                       }

                    }
                }

                dieselAdapter.notifyDataSetChanged();
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
