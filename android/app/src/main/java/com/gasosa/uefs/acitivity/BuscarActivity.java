package com.gasosa.uefs.acitivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.gasosa.uefs.R;
import com.gasosa.uefs.adapter.alcoolAdapter;
import com.gasosa.uefs.adapter.buscarAdapter;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.model.Posto;
import com.gasosa.uefs.model.PostoGas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuscarActivity extends AppCompatActivity {
    private SearchView buscar;
    private RecyclerView listaBuscar;
    private List<Posto>  postoBuscar;
    private DatabaseReference postosRef;
    private buscarAdapter buscarAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        buscar=findViewById(R.id.SearchView1);
        listaBuscar=findViewById(R.id.buscarList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buscar");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        postoBuscar= new ArrayList<>();
        postoBuscar.clear();
        postosRef= ConfiguracaoFirebase.getFirebase().child("Postos");

        //Configura recyclerview
        listaBuscar.setLayoutManager(new LinearLayoutManager(this));
        listaBuscar.setHasFixedSize(true);
        buscarAdapter = new buscarAdapter(postoBuscar, this);
        listaBuscar.setAdapter( buscarAdapter );
        buscar.setQueryHint("Buscar Postos");
        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String textoDigitado= s.toUpperCase();
                pesquisarPostos(textoDigitado);

                return true;
            }
        });
    }

    private void pesquisarPostos(String texto){
     //limpar lista
        postoBuscar.clear();

        if(texto.contains("POSTO")){
            if(texto.length()>0){
                Query query= postosRef.orderByChild("nome_filtro").startAt(texto).endAt(texto + "\uf8ff");

                query.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for( DataSnapshot ds:dataSnapshot.getChildren()){
                            postoBuscar.add(ds.getValue(Posto.class));

                        }

                        int total =postoBuscar.size();
                        Log.i("Total postos", "total " + total);
                        buscarAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        }}else{
                if(texto.length()>0){
                    Query query= postosRef.orderByChild("nome_filtro").startAt("POSTO "+texto).endAt("POSTO "+texto + "\uf8ff");

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for( DataSnapshot ds:dataSnapshot.getChildren()){
                                postoBuscar.add(ds.getValue(Posto.class));

                            }
                            buscarAdapter.notifyDataSetChanged();

                            int total =postoBuscar.size();
                            Log.i("Total postos", "total " + total);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });}
        }




    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
