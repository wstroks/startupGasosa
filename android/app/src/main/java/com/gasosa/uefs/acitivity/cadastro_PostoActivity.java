package com.gasosa.uefs.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gasosa.uefs.R;
import com.gasosa.uefs.model.CadastroPosto;
import com.gasosa.uefs.model.Comentarios;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastro_PostoActivity extends AppCompatActivity {
    private EditText nomePosto, bairroPosto;
    private Button enviar;
    private CadastroPosto cadastro;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__posto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastro Posto");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        nomePosto = findViewById(R.id.textoPosto);
        bairroPosto = findViewById(R.id.textoPostoBairro);
        enviar=findViewById(R.id.postoEnviar);
        db = FirebaseDatabase.getInstance().getReference();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String camponomePosto = nomePosto.getText().toString();
                String campoBairro= bairroPosto.getText().toString();
                if (!camponomePosto.isEmpty() && !campoBairro.isEmpty()) {
                    cadastro = new CadastroPosto();
                    cadastro.setBairro(campoBairro);
                    cadastro.setNome(camponomePosto);
                    DatabaseReference add = db.child("cadastroPosto");
                    add.push().setValue(cadastro);
                    Toast.makeText(cadastro_PostoActivity.this, "Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();




                } else {
                    Toast.makeText(cadastro_PostoActivity.this, "Campo vazio, digite o bairro e/ou nome do Posto!", Toast.LENGTH_SHORT).show();
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
