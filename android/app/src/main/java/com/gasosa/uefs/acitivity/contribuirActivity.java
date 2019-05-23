package com.gasosa.uefs.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gasosa.uefs.R;
import com.gasosa.uefs.model.CadastrarContribuirGas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class contribuirActivity extends AppCompatActivity {
    private EditText precoGas_enviar;
    private TextView  nome_enviar;
    private Button enviar;
    private DatabaseReference db;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contribuir);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contribuir Preço Gnv");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        db = FirebaseDatabase.getInstance().getReference();
        autenticacao = FirebaseAuth.getInstance();

        String nome =getIntent().getStringExtra("nome");
        String gas =getIntent().getStringExtra("gas");


        nome_enviar=findViewById(R.id.textoGasPreco);
        precoGas_enviar=findViewById(R.id.precoGas);
        enviar=findViewById(R.id.gasCalcular);
        nome_enviar.setText(nome);
        precoGas_enviar.setText(gas.toString());
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarContribuirGas agora = new CadastrarContribuirGas();

                agora.setNome(nome_enviar.getText().toString());
                agora.setGas(Double.parseDouble(precoGas_enviar.getText().toString()));
                SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
                Date data = new Date();
                String dataFormatada = formataData.format(data);
                agora.setData(dataFormatada);
                DatabaseReference add = db.child("cadastroPrecosGas");

                String email = autenticacao.getCurrentUser().getEmail();
                agora.setUsuario(email);
                add.push().setValue(agora);
                Toast.makeText(contribuirActivity.this, "Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
