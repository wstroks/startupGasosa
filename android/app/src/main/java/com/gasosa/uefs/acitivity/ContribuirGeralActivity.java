package com.gasosa.uefs.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gasosa.uefs.R;
import com.gasosa.uefs.model.CadastrarContribuirGeral;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContribuirGeralActivity extends AppCompatActivity {
    private EditText gasolina_enviar,gasolinaAd_enviar,diesel_enviar,dieselAd_enviar,alcool_enviar;
    private TextView nome_enviar;
    private Button enviar;
    private DatabaseReference db;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nome =getIntent().getStringExtra("nomeX");
        String gasolina =getIntent().getStringExtra("gasolina");
        String gasolinaAd =getIntent().getStringExtra("gasolinaAd");
        String diesel =getIntent().getStringExtra("diesel");
        String dieselAd =getIntent().getStringExtra("dieselAd");
        String alcool =getIntent().getStringExtra("alcool");
        setContentView(R.layout.activity_contribuir_geral);

        setContentView(R.layout.activity_contribuir_geral);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contribuir Preço");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        db = FirebaseDatabase.getInstance().getReference();
        autenticacao = FirebaseAuth.getInstance();


        enviar=findViewById(R.id.enviarPrecosAlcool);
        nome_enviar=findViewById(R.id.TextoPrecoAlcool);

        gasolina_enviar=findViewById(R.id.precoGasolina);
        gasolinaAd_enviar=findViewById(R.id.precoGasolinaAd);
        diesel_enviar= findViewById(R.id.precodiesel);
        dieselAd_enviar=findViewById(R.id.precodieselaAd);
        alcool_enviar=findViewById(R.id.precoAlcool);


        nome_enviar.setText(nome);
        gasolina_enviar.setText(gasolina);
        gasolinaAd_enviar.setText(gasolinaAd);
        diesel_enviar.setText(diesel);
        dieselAd_enviar.setText(dieselAd);
        alcool_enviar.setText(alcool);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarContribuirGeral agora = new CadastrarContribuirGeral();

                agora.setNome(nome_enviar.getText().toString());


                agora.setAlcool(Double.parseDouble(alcool_enviar.getText().toString()));
                agora.setDieselAd(Double.parseDouble(dieselAd_enviar.getText().toString()));
                agora.setDiesel(Double.parseDouble(diesel_enviar.getText().toString()));
                agora.setGasolina(Double.parseDouble(gasolina_enviar.getText().toString()));
                agora.setGasolinaAd(Double.parseDouble(gasolinaAd_enviar.getText().toString()));

                SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
                Date data = new Date();
                String dataFormatada = formataData.format(data);
                agora.setData(dataFormatada);
                DatabaseReference add = db.child("cadastroPrecosGeral");

                String email = autenticacao.getCurrentUser().getEmail();
                agora.setUsuario(email);
                add.push().setValue(agora);
                Toast.makeText(ContribuirGeralActivity.this, "Enviado com sucesso!", Toast.LENGTH_SHORT).show();
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
