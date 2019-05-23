package com.gasosa.uefs.acitivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.gasosa.uefs.R;
import com.gasosa.uefs.fragment.SobreFragment;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.model.Comentarios;
import com.gasosa.uefs.model.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastro_BugActivity extends AppCompatActivity {
     private EditText bug;
     private Button enviar;
     private Comentarios comenta;
     private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__bug);
        bug = findViewById(R.id.textBug);
        enviar = findViewById(R.id.bugEnviar);
        db = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sugestão");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoBug = bug.getText().toString();
                if (!textoBug.isEmpty()) {
                    comenta = new Comentarios();
                    comenta.setTexto(textoBug);
                    System.out.println(textoBug);
                    DatabaseReference add = db.child("bug");
                    add.push().setValue(comenta);
                    Toast.makeText(cadastro_BugActivity.this, "Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();




                } else {
                    Toast.makeText(cadastro_BugActivity.this, "Campo vazio, Digite seu comentário, bug ou sugestão!", Toast.LENGTH_SHORT).show();
                }


            }

        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
