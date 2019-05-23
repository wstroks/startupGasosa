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

public class gastoIreiActivity extends AppCompatActivity {
     EditText litro,km, media;
     TextView resultado;
     Button calculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_irei);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quanto irei gastar?");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        litro= findViewById(R.id.litroCalculoGasto);
        km= findViewById(R.id.GasolinaCalculoGasto);
        media= findViewById(R.id.AlcoolCalculoGasto);
        calculo=findViewById(R.id.buttonCalcularGasto);
        resultado=findViewById(R.id.resultadoGasto);
        calculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(alcoolGasolinaActivity.INPUT_METHOD_SERVICE))
                        .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                String campoLitro=litro.getText().toString();
                String campoKm=km.getText().toString();
                String campoMedia=media.getText().toString();

                if(!campoKm.isEmpty() && !campoLitro.isEmpty()&& !campoMedia.isEmpty()){

                    double dinheiro=  (Double.parseDouble(campoKm)/Double.parseDouble(campoMedia))*Double.parseDouble(campoLitro);
                    double litross=  (Double.parseDouble(campoKm)/Double.parseDouble(campoMedia));
                    if(Double.parseDouble(campoKm)==0 || Double.parseDouble(campoLitro)==0|| Double.parseDouble(campoMedia)==0){
                        Toast.makeText(gastoIreiActivity.this, "Um de seus valores está zero, com isso não será possivel fazer o calculo!",Toast.LENGTH_SHORT).show();

                    }else{
                    resultado.setText("Você irá gastar R$"+ String.format("%.2f", dinheiro)+" utilizando "+String.format("%.2f", litross)+ " litros no seu trajeto!");}
                }else{
                    Toast.makeText(gastoIreiActivity.this, "Digite os valores para obter o resultado!",Toast.LENGTH_SHORT).show();
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
