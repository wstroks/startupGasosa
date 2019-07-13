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
import com.gasosa.uefs.adapter.alcoolAdapter;
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
public class AlcoolFragment extends Fragment {

    private RecyclerView listarAlcool;
    private List<Posto> alcool = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerFeed;
    private alcoolAdapter alcoolAdapter;
    private FirebaseDatabase database;
    private Button buttonLink;
    private Query query;
    private String escolha;
    @SuppressLint("ValidFragment")
    public AlcoolFragment(String t) {
        escolha =t;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_alcool, container, false);
        listarAlcool=view.findViewById(R.id.alcoolList);
        buttonLink=view.findViewById(R.id.buttonLinkAlcoolBuscar);
        database= ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();

//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);

        if (escolha == "Menor preço") {
            query = usuariosRef.child("Postos").orderByChild("alcool").startAt(1);


            //gasAdapter.notifyDataSetChanged();
        }
        if (escolha =="Por data de atualização dos preços") {
            query = usuariosRef.child("Postos").orderByChild("data").startAt("01-01-2019").endAt("31-12-2019");

            //gasAdapter.notifyDataSetChanged();
        }if(escolha =="Menor distância") {
            query = usuariosRef.child("Postos").orderByChild("alcool");
        }
        if(escolha==null){
            query = usuariosRef.child("Postos").orderByChild("alcool").startAt(2.0);
            //gasAdapter.notifyDataSetChanged();
        }
        usuariosRef.getDatabase();
        usuariosRef.keepSynced(true);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //abri_link(view);
        listarAlcool.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //listarGasolina.setLayoutManager(new LinearLayoutManager(getActivity()));
        listarAlcool.setLayoutManager(layoutManager);


        alcoolAdapter= new alcoolAdapter(alcool,getActivity());
        listarAlcool.setAdapter(alcoolAdapter);


        return view;
    }

    private void listarFeed(){

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alcool.clear();
                for ( DataSnapshot ds: dataSnapshot.getChildren() ){
                    alcool.add( ds.getValue(Posto.class) );
                }

                if(escolha=="Menor preço" || escolha==null){

                Collections.reverse( alcool );
                }
                alcoolAdapter.notifyDataSetChanged();
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
