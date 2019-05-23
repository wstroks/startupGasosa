package com.gasosa.uefs.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gasosa.uefs.R;

import java.text.DecimalFormat;

public class alcoolGasolinaActivity extends AppCompatActivity {
     EditText gasolina,alcool;
     TextView resultado;
     Button calculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_gasolina);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Álcool x Gasolina");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        gasolina= findViewById(R.id.GasolinaCalculo);
        alcool= findViewById(R.id.AlcoolCalculo);
        calculo=findViewById(R.id.buttonCalcular);
        resultado=findViewById(R.id.resultado);

        calculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //resultado.setFocusable(true);
                //resultado.requestFocus();
                ((InputMethodManager) getSystemService(alcoolGasolinaActivity.INPUT_METHOD_SERVICE))
                        .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);


                String al=alcool.getText().toString();
                String gaso=gasolina.getText().toString();
                if(!al.isEmpty() && !gaso.isEmpty() ){
                double n= Double.parseDouble(gasolina.getText().toString())*0.7;
                //System.out.println(n);
                    if(Double.parseDouble(gaso)!=0 && Double.parseDouble(al)!=0){
                if(Double.parseDouble(alcool.getText().toString())<n){
                    resultado.setText("Melhor abastecer com Álcool!");


                }else{
                    resultado.setText("Melhor abastecer com Gasolina!");
                    resultado.setFocusable(true);
                }}else{
                        Toast.makeText(alcoolGasolinaActivity.this, "Um de seus valores está zero, com isso não será possivel fazer o calculo!",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(alcoolGasolinaActivity.this, "Digite os preços para obter resultado!",Toast.LENGTH_SHORT).show();
                }

        }});
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
