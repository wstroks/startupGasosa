package com.gasosa.uefs.acitivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.gasosa.uefs.R;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.model.InformaPosto;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class informaPosto extends AppCompatActivity {
    TextView titulo,borracharia,olio, coveniencia;
    ImageView imagem;
    ImageView circleImageView;
    private DatabaseReference usuariosRef;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String nome =getIntent().getStringExtra("nomeX");
        String gasolina =getIntent().getStringExtra("gasolina");
        String gasolinaAd =getIntent().getStringExtra("gasolinaAd");
        String diesel =getIntent().getStringExtra("diesel");
        String dieselAd =getIntent().getStringExtra("dieselAd");
        String alcool =getIntent().getStringExtra("alcool");
        setContentView(R.layout.activity_informa_posto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(nome);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        titulo=findViewById(R.id.informaTitulo);
        imagem=findViewById(R.id.imageInforma);
        borracharia=findViewById(R.id.Borracharia);
        olio=findViewById(R.id.Troca_oleo);
        coveniencia=findViewById(R.id.lojaCoveniencia);
        circleImageView= findViewById(R.id.profile_informa);


        database= ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();
        Query query= usuariosRef.child("Informa").orderByChild("nome").startAt(nome).endAt(nome + "\uf8ff");
        usuariosRef.keepSynced(true);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                InformaPosto newPost = dataSnapshot.getValue(InformaPosto.class);

                System.out.println("Author xxxx: " + newPost.getFoto());
                System.out.println("Author www1: " + newPost.getBorracharia());
                titulo.setText(newPost.getHorario());

                borracharia.setText(newPost.getBorracharia());
                olio.setText(newPost.getOleo());
                coveniencia.setText(newPost.getLoja());

                    // imagem.setImageURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/adrielli.png?alt=media&token=b8e773fa-cc7f-47f1-becf-98dbadd96cfd"));

                Picasso.get().load(newPost.getFoto().toString()).into(imagem);

                    Picasso.get().load(newPost.getProfile().toString()).into(circleImageView);
                    //circleImageView.setImageDrawable(Drawable.createFromPath("petro"));


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

}