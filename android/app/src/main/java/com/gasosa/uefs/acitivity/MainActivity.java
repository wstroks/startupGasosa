package com.gasosa.uefs.acitivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gasosa.uefs.R;
import com.gasosa.uefs.fragment.AlcoolFragment;
import com.gasosa.uefs.fragment.DiFragment;
import com.gasosa.uefs.fragment.GasolinaFragment;
import com.gasosa.uefs.fragment.SobreFragment;
import com.gasosa.uefs.fragment.gasoFragment;
import com.gasosa.uefs.fragment.gasolinaTabFragment;
import com.gasosa.uefs.fragment.notificacaoFragment;
import com.gasosa.uefs.fragment.tabDiFragment;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    public Toolbar it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Toolbar toolbar= findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Gasolina");
        setSupportActionBar( toolbar );
       // toolbar.getMenu().getItem(0);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_local_gas_station_black_24dp);

        FirebaseMessaging.getInstance().subscribeToTopic("brasil");
        autenticacao= ConfiguracaoFirebase.getReferenciaAutenticacao();
        ConfiguraBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new gasolinaTabFragment()).commit();


    }

    private void ConfiguraBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx= (BottomNavigationViewEx)findViewById(R.id.bottomNavigation);
        //getSupportActionBar();


         // bottomNavigationViewEx.isSaveEnabled();
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(true);

       // bottomNavigationViewEx.setTextSize(12);
       // bottomNavigationViewEx.setTextVisibility(true);
       // bottomNavigationViewEx.setOnNavigationItemSelectedListener(ItemSelectedListener);
        habilitarNavegacao(bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        //bottomNavigationViewEx.enableShiftingMode(false);


    }

    /*
    habilitar navegaçã
     */

    private void habilitarNavegacao(final BottomNavigationViewEx viewEx){


        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Toolbar toolbar= findViewById(R.id.toolbarPrincipal);
                        toolbar.setTitle("Gasolina");

                        fragmentTransaction.replace(R.id.viewPager, new gasolinaTabFragment()).commit();



                        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_);
                        return true;/*
                    case R.id.ic_disel:
                         toolbar= findViewById(R.id.toolbarPrincipal);
                        toolbar.setTitle("Diesel");
                        fragmentTransaction.replace(R.id.viewPager, new DiFragment()).commit();*/
                    case R.id.ic_disel:
                        toolbar= findViewById(R.id.toolbarPrincipal);
                        toolbar.setTitle("Diesel");
                        fragmentTransaction.replace(R.id.viewPager, new tabDiFragment()).commit();

                        return true;
                    case R.id.ic_alcool:
                        toolbar= findViewById(R.id.toolbarPrincipal);
                        toolbar.setTitle("Álcool");
                        fragmentTransaction.replace(R.id.viewPager, new AlcoolFragment()).commit();

                        return true;


                    case R.id.ic_publicacao:
                        toolbar= findViewById(R.id.toolbarPrincipal);
                        toolbar.setTitle("Gás(Gnv)");
                        fragmentTransaction.replace(R.id.viewPager, new notificacaoFragment()).commit();
                        return true;
                    case R.id.ic_sobre:
                        toolbar= findViewById(R.id.toolbarPrincipal);
                        toolbar.setTitle("Menu");
                       // toolbar.setLogo(R.mipmap.ic_launcher_round);
                        fragmentTransaction.replace(R.id.viewPager, new SobreFragment()).commit();
                        return true;


                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_sair){
                deslogarUsuario();
                startActivity( new Intent(getApplicationContext(), LoginActivity.class));
                return true;}
        if(item.getItemId()==R.id.menu_compartilhar){
             Intent it = new Intent(Intent.ACTION_SEND);
             it.setType("text/plain");
            String Texto= "O “Gasosa!” é uma ferramenta que tem o intuito de auxiliar na busca de melhores preços de combustíveis em Feira de Santana e Região (em construção), Seja Bem vindo ao Gasosa!";
            it.putExtra(Intent.EXTRA_SUBJECT,Texto);
            it.putExtra(Intent.EXTRA_TEXT, Texto +"https://play.google.com/store/apps/details?id=com.gasosa.uefs");

            startActivity(Intent.createChooser(it,"Compartilhar o app Gassosa!"));
            return true;
        }if(item.getItemId()==R.id.notifica){
            startActivity( new Intent(getApplicationContext(), PromocoesActivity.class));
            return true;
        }
        if(item.getItemId()==R.id.buscar){
            startActivity( new Intent(getApplicationContext(), BuscarActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    private void deslogarUsuario(){
        try{
            autenticacao.signOut();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
