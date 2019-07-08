package com.gasosa.uefs.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class PromocoesActivity extends AppCompatActivity {

    private RecyclerView listaNotifica;
    private List<Notificacao>  postoNotificao;
    private DatabaseReference postosRef;
    private promoAdapter promoAdapter;
    private ValueEventListener valueEventListenerFeed;
    private Query query;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocoes);
        listaNotifica=findViewById(R.id.list_notifica_firebase);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notificação");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        postoNotificao= new ArrayList<>();
        postoNotificao.clear();
        database= ConfiguracaoFirebase.getDatabase();
        postosRef = ConfiguracaoFirebase.getFirebase();
        query = postosRef.child("notificacao").orderByChild("data");
//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);
        postosRef.getDatabase();
        postosRef.keepSynced(true);

        //Configura recyclerview
        listaNotifica.setLayoutManager(new LinearLayoutManager(this));
        listaNotifica.setHasFixedSize(true);
        promoAdapter = new promoAdapter(postoNotificao, this);
        listaNotifica.setAdapter( promoAdapter );
    }
    private void listarFeed(){

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postoNotificao.clear();
                for ( DataSnapshot ds: dataSnapshot.getChildren() ){
                    postoNotificao.add( ds.getValue(Notificacao.class) );
                }
                Collections.reverse( postoNotificao );
                promoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        listarFeed();
    }

    @Override
    public void onStop() {
        super.onStop();
        postosRef.removeEventListener( valueEventListenerFeed );
    }
}
