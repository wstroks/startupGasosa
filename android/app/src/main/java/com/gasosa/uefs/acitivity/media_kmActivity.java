package com.gasosa.uefs.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gasosa.uefs.R;

public class media_kmActivity extends AppCompatActivity {
    EditText km,litros;
    TextView resultado;
    Button calculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_km);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Média por km Percorrido");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        km= findViewById(R.id.GasolinaCalculoMedia);
        litros= findViewById(R.id.AlcoolCalculoMedia);
        calculo=findViewById(R.id.buttonCalcularMedia);
        resultado=findViewById(R.id.resultadoMedia);


        calculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(alcoolGasolinaActivity.INPUT_METHOD_SERVICE))
                        .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                String campokmm= km.getText().toString();
                String campolitro=litros.getText().toString();
                if(!campokmm.isEmpty() && !campolitro.isEmpty() ){
                    if(Double.parseDouble(campokmm)!=0 && Double.parseDouble(campolitro)!=0) {
                        double n = Double.parseDouble(campokmm) / Double.parseDouble(campolitro);
                        //System.out.println(n);

                        resultado.setText("O seu veículo está fazendo uma média de " + String.format("%.2f", n) + " Km por litro!");
                    }else{
                        Toast.makeText(media_kmActivity.this, "Um de seus valores está zero, com isso não será possivel fazer o calculo!",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(media_kmActivity.this, "Digite os valores para obter resultado!",Toast.LENGTH_SHORT).show();
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
