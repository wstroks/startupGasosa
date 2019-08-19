package com.gasosa.uefs.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.RelativeDateTimeFormatter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.LocationCallback;
import com.gasosa.uefs.R;
import com.gasosa.uefs.acitivity.MainActivity;
import com.gasosa.uefs.adapter.dieselAdapter;
import com.gasosa.uefs.adapter.gasAdapter;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.helper.Local;
import com.gasosa.uefs.model.Posto;
import com.gasosa.uefs.model.PostoGas;
import com.gasosa.uefs.model.PostoGasDistancia;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class notificacaoFragment extends Fragment  {

    private RecyclerView listarGas;
    private static final String LOG_TAG = notificacaoFragment.class.getSimpleName();
    private FusedLocationProviderClient cli;
    private List<PostoGasDistancia> gasD = new ArrayList<>();
    private List<PostoGas> gass = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerFeed;
    private gasAdapter gasAdapter;
    private FirebaseDatabase database;
    String distanciaFormatada = "";
    private GoogleMap mMap;
    private static final int REQUEST_LOCATION = 1;
    private Button buttonLink;
    private Query query;
    private String escolha;
    private int numero = 0;
    private Context context;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private LatLng posicaoInicial;
    public notificacaoFragment(String t) {
        // Required empty public constructor
        this.escolha = t;
        context = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificacao, container, false);

        listarGas = view.findViewById(R.id.listGas);
        buttonLink = view.findViewById(R.id.buttonLinkGas);
        database = ConfiguracaoFirebase.getDatabase();
        usuariosRef = ConfiguracaoFirebase.getFirebase();
        System.out.println("Agora vai " + escolha);
        if (escolha == "Menor preço") {
            query = usuariosRef.child("postosgas").orderByChild("gas").startAt(2.0);


            //gasAdapter.notifyDataSetChanged();
        }
        if (escolha == "Por data de atualização dos preços") {
            query = usuariosRef.child("postosgas").orderByChild("data").startAt("2019-01-01").endAt("2019-12-31");

            //gasAdapter.notifyDataSetChanged();
        }
        if (escolha == "Menor distância") {
            query = usuariosRef.child("postosgas").orderByChild("gas").startAt(1.0);
            numero = 1;
        }
        if (escolha == null) {
            query = usuariosRef.child("postosgas").orderByChild("gas").startAt(2.0);
            //gasAdapter.notifyDataSetChanged();
        }

//query= usuariosRef.orderByKey("Postos").orderBy("population", Direction.DESCENDING);
        usuariosRef.getDatabase();
        usuariosRef.keepSynced(true);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //abri_link(view);
        listarGas.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //listarGasolina.setLayoutManager(new LinearLayoutManager(getActivity()));
        listarGas.setLayoutManager(layoutManager);


        gasAdapter = new gasAdapter(gass, gasD, numero, getActivity());
        listarGas.setAdapter(gasAdapter);
        return view;
    }

    private void listarFeed() {

        valueEventListenerFeed = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gass.clear();
                gasD.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    final PostoGas y = ds.getValue(PostoGas.class);
                    System.out.println(y.getNome() + "asdasd221");
                    final PostoGasDistancia ddd = new PostoGasDistancia();


                    gass.add(ds.getValue(PostoGas.class));

                    ddd.setBairro(y.getBairro());
                    ddd.setNome(y.getNome());
                    ddd.setLogo(y.getLogo());
                    ddd.setData(y.getData());
                    ddd.setLink(y.getLink());
                    ddd.setGas(y.getGas());

                    gasD.add(ddd);



                }
                for(int a=0; a<gasD.size(); a++){
                    PostoGasDistancia s=gasD.get(a);
                    System.out.println("Numeros "+a +" "+ s.getDistancia()+ " " + s.getNome());

                }





               if(escolha=="Menor preço" || escolha==null){
                    Collections.reverse( gass );}
               if(escolha=="Menor distância"){
                   numero=1;
                  // Collections.sort(gasD, new FuncionarioComparator());
               }



                gasAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        listarFeed();

    }

    @Override
    public void onStop() {
        super.onStop();
        usuariosRef.removeEventListener( valueEventListenerFeed );
    }



    public class FuncionarioComparator implements Comparator<PostoGasDistancia> {



        @Override
        public int compare(PostoGasDistancia postoGasDistancia, PostoGasDistancia t1) {
            return postoGasDistancia.getDistancia().compareTo(t1.getDistancia());
        }
    }

    public String distancias(final PostoGas y){

        cli = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }

        cli.getLastLocation().addOnSuccessListener((Activity) getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //System.out.println(location.getLatitude()+ "hahahaha");

                if (location != null) {
                    LatLng posicaoInicial = new LatLng(location.getLatitude(), location.getLongitude());
                    LatLng posicaiFinal = new LatLng(Double.parseDouble(y.getLatitude()), Double.parseDouble(y.getLongitude()));
                    //double distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);

                    float distancia = Local.calcularDistancia(posicaoInicial, posicaiFinal);

                   distanciaFormatada = Local.formatarDistancia(distancia);




                    //myViewHolder..setText(posto.getBairro()+"\n"+agora);
                }


            }

        }).addOnFailureListener((Activity) getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //  Log.d("MapDemoActivity", "Error trying to get last GPS location");
                //my.distan.setText();


                e.printStackTrace();


            }
        });
        System.out.println("AUSHDUASHDUAHSDAS2222 " +distanciaFormatada);
        return  distanciaFormatada;
    }
}
