package com.gasosa.uefs.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gasosa.uefs.R;
import com.gasosa.uefs.acitivity.LoginActivity;
import com.gasosa.uefs.acitivity.alcoolGasolinaActivity;
import com.gasosa.uefs.acitivity.appActivity;
import com.gasosa.uefs.acitivity.cadastro_BugActivity;
import com.gasosa.uefs.acitivity.cadastro_PostoActivity;
import com.gasosa.uefs.acitivity.gastoIreiActivity;
import com.gasosa.uefs.acitivity.media_kmActivity;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {


        private FirebaseAuth autenticacao;


    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_sobre, container, false);
        autenticacao= ConfiguracaoFirebase.getReferenciaAutenticacao();
       Button b = view.findViewById(R.id.sobre_tim);

       Button bug= view.findViewById(R.id.buttonBug2);
       // Button postos= view.findViewById(R.id.alcoolGasolinaSobre);
        Button alcoolGasolina= view.findViewById(R.id.alcoolGasolinaSobre);
        Button media= view.findViewById(R.id.mediaKMPercorrido);
        Button ire= view.findViewById(R.id.ireiGastar);
        Button cadasdtroP=view.findViewById(R.id.buttonCadastrarPosto);

       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(),appActivity.class);
               startActivity(i);
           }
       });

       bug.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getActivity(), cadastro_BugActivity.class);
               startActivity(i);
           }
       });
      alcoolGasolina.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getActivity(), alcoolGasolinaActivity.class);
              startActivity(i);
          }
      });
      media.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getActivity(), media_kmActivity.class);
              startActivity(i);
          }
      });
      ire.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getActivity(), gastoIreiActivity.class);
              startActivity(i);
          }
      });
        cadasdtroP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), cadastro_PostoActivity.class);
                startActivity(i);
            }
        });

        return view;
    }



}
