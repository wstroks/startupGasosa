package com.gasosa.uefs.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.telephony.CellLocation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gasosa.uefs.R;
import com.gasosa.uefs.acitivity.ContribuirGeralActivity;
import com.gasosa.uefs.acitivity.MainActivity;
import com.gasosa.uefs.helper.Local;
import com.gasosa.uefs.helper.UsuarioFirebase;
import com.gasosa.uefs.model.Posto;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.maps.android.SphericalUtil;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import static android.telephony.CellLocation.requestLocationUpdate;

public class gasolinaAdapter extends RecyclerView.Adapter<gasolinaAdapter.MyViewHolder> implements OnMapReadyCallback {

    private List<Posto> listaPosto;
    private static final int REQUEST_LOCATION = 1;
    private Context context;
    private String agora;
    private String distanciaFormatada;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private String lattitude, longitude;
    private FusedLocationProviderClient cli;
    private GoogleApiClient googleApiClient;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;

    private LocationRequest locationRequest;
    private DatabaseReference db;
    private LocationCallback locationCallback;

    private static final int REQUEST_CHECK_SETTINGS = 0;

    private static final long LOCATION_UPDATE_INTERVAL = 5000;


    public gasolinaAdapter(List<Posto> l, Context c) {
        this.listaPosto = l;
        this.context = c;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_gasolina, viewGroup, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Posto posto = listaPosto.get(i);
        //recuperarLocalizacaoUsuario();

        getLocation(posto, myViewHolder);
        myViewHolder.nome.setText(posto.getNome());
        myViewHolder.gasolina.setText("R$ " + posto.getGasolina().toString());
        myViewHolder.bairro.setText(posto.getBairro());
        myViewHolder.distan.setText("Gps(off)");
        myViewHolder.data.setText("Atualizado:"+posto.getData());
        if(posto.getLogo()!=null){
        if(posto.getLogo().equals("ipiranga")){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/ipiranga.jpg?alt=media&token=246775ef-0904-4806-92a2-4dd8e7133449").into(myViewHolder.circleImageView);
         //   myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ipiranga));

            // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
        }
        if(posto.getLogo().equals("perfil")){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/perfil.png?alt=media&token=954762d2-3401-472d-8404-a0cf3178c5e7").into(myViewHolder.circleImageView);
            //myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.perfil));

        }
        if(posto.getLogo().equals("petro")){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/petro.png?alt=media&token=3f182855-49ea-4a09-8b63-839fc973ebf6").into(myViewHolder.circleImageView);
           // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.petro));

            //  myViewHolder.circleImageView.setImageURI(Uri.parse(""));
        }
        if(posto.getLogo().equals("shell")){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/shell.png?alt=media&token=3eff6798-d51f-4113-b323-890c73120caa").into(myViewHolder.circleImageView);
           // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.shell));

            // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
        }
        if(posto.getLogo().equals("menor")){
           Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/menor.jpg?alt=media&token=00530df1-63c5-4a32-88d2-19a479108460").into(myViewHolder.circleImageView);
           // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menor));

            // myViewHolder.circleImageView.setImageURI(load.("");
        }}

        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = posto.getLink();

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                context.startActivity(i);
            }
        });

        myViewHolder.addGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ContribuirGeralActivity.class);
                intent.putExtra("nomeX",posto.getNome().toString());
                intent.putExtra("gasolina",posto.getGasolina().toString());
                intent.putExtra("gasolinaAd",posto.getGasolinaAd().toString());
                intent.putExtra("diesel",posto.getDiesel().toString());
                intent.putExtra("dieselAd",posto.getDieselAd().toString());
                intent.putExtra("alcool",posto.getAlcool().toString());
                context.startActivity(intent);
            }
        });

        myViewHolder.addCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.setType("text/plain");
                String Texto= "O preço da Gasolina no "+posto.getNome().toString()+" está R$"+posto.getGasolina().toString()+ " "+ posto.getData() ;
                it.putExtra(Intent.EXTRA_SUBJECT,Texto);
                it.putExtra(Intent.EXTRA_TEXT,"Compartilhe o Aplicativo Gasosa! \n\n"+ "https://play.google.com/store/apps/details?id=com.gasosa.uefs"+"\n\n" +Texto + "\n\n"+" Você pode se dirigir ao posto clicando no link: " +posto.getLink() );

                context.startActivity(Intent.createChooser(it,"Compartilhar preços de combustível!"));
            }
        });
    }




    private void getLocation(final Posto posto, final MyViewHolder my) {
        // final boolean gpsIsAtivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // System.out.println("ashduashduasdhausdha");

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#
            //System.out.println("ashduashduasdhausdha2222");

           // ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
        } else {
            createLocationRequest();

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            LocationSettingsRequest.Builder build = new LocationSettingsRequest.Builder();
            LocationManager uu = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            LocationListener pp = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    System.out.println("aquiiii " + location.getAltitude());
                }

            };
// ...

            SettingsClient client = LocationServices.getSettingsClient(context);
            final Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

//Get the most accurate location data available//




            cli= LocationServices.getFusedLocationProviderClient(context);
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(30000);
            //  }
            task.addOnSuccessListener((Activity) context, new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    // All location settings are satisfied. The client can initialize
                    // location requests here.
                    // ...




                    cli = LocationServices.getFusedLocationProviderClient(context);


                }
            });

            task.addOnFailureListener((Activity)context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        e.printStackTrace();
                            /*resolvable.startResolutionForResult((Activity) context,
                                    REQUEST_CHECK_SETTINGS);*/
                    }
                }
            });


           // ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);

            cli.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //System.out.println(location.getLatitude()+ "hahahaha");


                }
            }).addOnFailureListener((Activity)context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Log.d("MapDemoActivity", "Error trying to get last GPS location");
                    e.printStackTrace();
                }
            });



            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        // Update UI with location data
                        // ...
                        if (location != null) {

                            LatLng posicaoInicial = new LatLng(location.getLatitude(), location.getLongitude());
                            LatLng posicaiFinal = new LatLng(Double.parseDouble(posto.getLatitude()), Double.parseDouble(posto.getLogintude()));
                            //double distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);
                            System.out.println("hahhsdasda" + posicaoInicial);
                            float distancia = Local.calcularDistancia(posicaoInicial, posicaiFinal);
                            String distanciaFormatada = Local.formatarDistancia(distancia);
                            my.distan.setText(distanciaFormatada);
                            //myViewHolder..setText(posto.getBairro()+"\n"+agora);

                        }
                       // System.out.println("kkkk "+location.getAltitude());
                    }
                };
            };
            cli.requestLocationUpdates(locationRequest,locationCallback,Looper.myLooper());


        }

















        //  }


    }



    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(0);
        locationRequest.setFastestInterval(0);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public int getItemCount() {
        return listaPosto.size();
    }

    private void recuperarLocalizacaoUsuario() {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                //recuperar latitude e longitude
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //localPassageiro = new LatLng(latitude, longitude);
                 System.out.println("Naruto "+latitude);
                //Atualizar GeoFire
                UsuarioFirebase.atualizarDadosLocalizacao(latitude, longitude);

                //Altera interface de acordo com o status
                // alteraInterfaceStatusRequisicao( statusRequisicao );
                if (ActivityCompat.checkSelfPermission((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            0,
                            0,
                            (android.location.LocationListener) locationListener
                    );
                }
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //recuperarLocalizacaoUsuario();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView gasolina;
        TextView data;
        TextView bairro;
        Button button;
        TextView distan;
        ImageView circleImageView;
        ImageButton addGasolina;
        ImageButton addCompartilhar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            db= FirebaseDatabase.getInstance().getReference();
            nome = itemView.findViewById(R.id.TituloView);
            gasolina = itemView.findViewById(R.id.gasolinaView);
            data = itemView.findViewById(R.id.dataview);
            bairro = itemView.findViewById(R.id.bairroView);
            button = itemView.findViewById(R.id.buttonLink);
           distan= itemView.findViewById(R.id.distanciaKM);
            circleImageView= itemView.findViewById(R.id.profile_gasolina);
            addGasolina= itemView.findViewById(R.id.addgasolina);
            addCompartilhar= itemView.findViewById(R.id.addCompartilhar);
        }

    }



}
