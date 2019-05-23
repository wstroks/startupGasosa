package com.gasosa.uefs.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gasosa.uefs.R;
import com.gasosa.uefs.acitivity.contribuirActivity;
import com.gasosa.uefs.helper.Local;
import com.gasosa.uefs.model.Posto;
import com.gasosa.uefs.model.PostoGas;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class gasAdapter extends  RecyclerView.Adapter<gasAdapter.MyViewHolder> {
    private List<PostoGas> listaPosto;
    private Context context;
    private GoogleApiClient googleApiClient;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FusedLocationProviderClient cli;
    private static final int REQUEST_LOCATION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 0;
    String distanciaFormatada;
    public gasAdapter(List<PostoGas> l, Context c) {
        this.listaPosto = l;
        this.context = c;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adpter_gas, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final PostoGas posto = listaPosto.get(i);
        getLocation(posto,myViewHolder);
        myViewHolder.nome.setText(posto.getNome());
        myViewHolder.gas.setText("R$ "+posto.getGas().toString());
        myViewHolder.bairro.setText(posto.getBairro());
        myViewHolder.data.setText(posto.getData());
        if(posto.getLogo()!=null){
        if(posto.getLogo().equals("ipiranga")){
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/ipiranga.jpg?alt=media&token=246775ef-0904-4806-92a2-4dd8e7133449").into(myViewHolder.circleImageView);
           // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ipiranga));

            // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
        }
        if(posto.getLogo().equals("perfil")){
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/perfil.png?alt=media&token=954762d2-3401-472d-8404-a0cf3178c5e7").into(myViewHolder.circleImageView);
            //myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.perfil));

        }
        if(posto.getLogo().equals("petro")){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/petro.png?alt=media&token=3f182855-49ea-4a09-8b63-839fc973ebf6").into(myViewHolder.circleImageView);
          //  myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.petro));

            //  myViewHolder.circleImageView.setImageURI(Uri.parse(""));
        }
        if(posto.getLogo().equals("shell")){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/shell.png?alt=media&token=3eff6798-d51f-4113-b323-890c73120caa").into(myViewHolder.circleImageView);
           // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.shell));

            // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
        }
        if(posto.getLogo().equals("menor")){
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/menor.jpg?alt=media&token=00530df1-63c5-4a32-88d2-19a479108460").into(myViewHolder.circleImageView);
          //  myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menor));

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

        myViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, contribuirActivity.class);
                intent.putExtra("nome",posto.getNome());
                intent.putExtra("gas",posto.getGas().toString());
                context.startActivity(intent);
            }
        });
    }

    private void getLocation(final PostoGas posto, final MyViewHolder my) {

        System.out.println("ashduashduasdhausdha");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#
            System.out.println("ashduashduasdhausdha2222");
            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION);
        }
        else{
            cli= LocationServices.getFusedLocationProviderClient(context);
            cli.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //System.out.println(location.getLatitude()+ "hahahaha");
                    if(location!=null) {
                        LatLng posicaoInicial = new LatLng(location.getLatitude(), location.getLongitude());
                        LatLng posicaiFinal = new LatLng(Double.parseDouble(posto.getLatitude()), Double.parseDouble(posto.getLongitude()));
                        //double distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);

                        float distancia = Local.calcularDistancia(posicaoInicial, posicaiFinal);
                        String distanciaFormatada = Local.formatarDistancia(distancia);
                        my.distan.setText(distanciaFormatada);
                        //myViewHolder..setText(posto.getBairro()+"\n"+agora);
                    }

                }
            }).addOnFailureListener((Activity)context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                  //  Log.d("MapDemoActivity", "Error trying to get last GPS location");
                    e.printStackTrace();

                }
            });}







    }




    @Override
    public int getItemCount() {
        return listaPosto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView gas;
        TextView data;
        TextView bairro;
        Button button;
        TextView distan;
        ImageView circleImageView;
        ImageButton add;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.TituloViewGas);
            gas = itemView.findViewById(R.id.gasolinaViewGas);
            data = itemView.findViewById(R.id.dataviewGas);
            bairro = itemView.findViewById(R.id.bairroViewGas);
            button = itemView.findViewById(R.id.buttonLinkGas);
            distan= itemView.findViewById(R.id.distanciaKMGas);
            circleImageView= itemView.findViewById(R.id.profile_GAS);
            add= itemView.findViewById(R.id.addGas);


        }

    }
}
