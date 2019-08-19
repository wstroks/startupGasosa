package com.gasosa.uefs.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
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
import com.gasosa.uefs.model.PostoGas;
import com.gasosa.uefs.model.PostoGasDistancia;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class gasAdapter extends  RecyclerView.Adapter<gasAdapter.MyViewHolder> {
    private List<PostoGas> listaPosto;
    private List<PostoGasDistancia> listaPostoD;
    private List<PostoGasDistancia> listaDistancia;

    private Context context;
    private GoogleApiClient googleApiClient;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FusedLocationProviderClient cli;
    private static final int REQUEST_LOCATION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 0;
    private int numero2;
    public String distanciax;
    private   final PostoGasDistancia ddd;

    public gasAdapter(List<PostoGas> l,List<PostoGasDistancia> vai, int ji,  Context c) {
        this.listaPosto = l;
        this.numero2=ji;
        this.listaPostoD=vai;
        listaDistancia= new ArrayList<>();
        ddd=new PostoGasDistancia();
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
        final PostoGasDistancia posto3=null;



        getLocation(posto,myViewHolder,ddd);

 if(numero2==1){
System.out.println("size"+listaDistancia.size());
     final PostoGasDistancia posto2 = listaDistancia.get(i);
     myViewHolder.nome.setText(posto2.getNome());
     myViewHolder.gas.setText("R$ " + posto2.getGas().toString());
     myViewHolder.bairro.setText(posto2.getBairro());
     DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
     Date date = null;
     try {
         date = formatUS.parse(posto2.getData().toString());
     } catch (ParseException e) {
         e.printStackTrace();
     }
     DateFormat formatBR = new SimpleDateFormat("dd-mm-yyyy");
     String dateFormated = formatBR.format(date);
     myViewHolder.data.setText("Atualizado:" + dateFormated);

     if (posto2.getLogo() != null) {
         if (posto2.getLogo().equals("ipiranga")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/ipiranga.jpg?alt=media&token=246775ef-0904-4806-92a2-4dd8e7133449").into(myViewHolder.circleImageView);
             // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ipiranga));

             // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
         }
         if (posto2.getLogo().equals("perfil")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/perfil.png?alt=media&token=954762d2-3401-472d-8404-a0cf3178c5e7").into(myViewHolder.circleImageView);
             //myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.perfil));

         }
         if (posto2.getLogo().equals("petro")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/petro.png?alt=media&token=3f182855-49ea-4a09-8b63-839fc973ebf6").into(myViewHolder.circleImageView);
             //  myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.petro));

             //  myViewHolder.circleImageView.setImageURI(Uri.parse(""));
         }
         if (posto2.getLogo().equals("shell")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/shell.png?alt=media&token=3eff6798-d51f-4113-b323-890c73120caa").into(myViewHolder.circleImageView);
             // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.shell));

             // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
         }
         if (posto2.getLogo().equals("menor")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/menor.jpg?alt=media&token=00530df1-63c5-4a32-88d2-19a479108460").into(myViewHolder.circleImageView);
             //  myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menor));

             // myViewHolder.circleImageView.setImageURI(load.("");
         }
     }


     myViewHolder.button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String url = posto2.getLink();

             Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

             context.startActivity(i);
         }
     });

     myViewHolder.add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, contribuirActivity.class);
             intent.putExtra("nome", posto2.getNome());
             intent.putExtra("gas", posto2.getGas().toString());
             context.startActivity(intent);
         }
     });

     myViewHolder.addCompartilharGas.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent it = new Intent(Intent.ACTION_SEND);
             it.setType("text/plain");
             String Texto = "O preço do Gás(GNV) no " + posto2.getNome().toString() + " está R$" + posto2.getGas().toString() + " " + posto2.getData();
             it.putExtra(Intent.EXTRA_SUBJECT, Texto);
             it.putExtra(Intent.EXTRA_TEXT, "Compartilhe o Aplicativo Gasosa! \n\n" + "https://play.google.com/store/apps/details?id=com.gasosa.uefs" + "\n\n" + Texto + "\n\n" + " Você pode se dirigir ao posto clicando no link: " + posto2.getLink());

             context.startActivity(Intent.createChooser(it, "Compartilhar preços de combustível!"));
         }
     });
        /*if(i>2){
        Collections.sort(listaDistancia, new Comparator<PostoGasDistancia>() {
            @Override
            public int compare(PostoGasDistancia  p1, PostoGasDistancia  p2)
            {

                return p1.getDistancia().compareToIgnoreCase(p2.getDistancia());
            }
        });}*/

 }else {
     myViewHolder.nome.setText(posto.getNome());
     myViewHolder.gas.setText("R$ " + posto.getGas().toString());
     myViewHolder.bairro.setText(posto.getBairro());
     DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
     Date date = null;
     try {
         date = formatUS.parse(posto.getData().toString());
     } catch (ParseException e) {
         e.printStackTrace();
     }
     DateFormat formatBR = new SimpleDateFormat("dd-mm-yyyy");
     String dateFormated = formatBR.format(date);
     myViewHolder.data.setText("Atualizado:" + dateFormated);
     myViewHolder.distan.setText("Gps(off)");
     if (posto.getLogo() != null) {
         if (posto.getLogo().equals("ipiranga")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/ipiranga.jpg?alt=media&token=246775ef-0904-4806-92a2-4dd8e7133449").into(myViewHolder.circleImageView);
             // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ipiranga));

             // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
         }
         if (posto.getLogo().equals("perfil")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/perfil.png?alt=media&token=954762d2-3401-472d-8404-a0cf3178c5e7").into(myViewHolder.circleImageView);
             //myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.perfil));

         }
         if (posto.getLogo().equals("petro")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/petro.png?alt=media&token=3f182855-49ea-4a09-8b63-839fc973ebf6").into(myViewHolder.circleImageView);
             //  myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.petro));

             //  myViewHolder.circleImageView.setImageURI(Uri.parse(""));
         }
         if (posto.getLogo().equals("shell")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/shell.png?alt=media&token=3eff6798-d51f-4113-b323-890c73120caa").into(myViewHolder.circleImageView);
             // myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.shell));

             // myViewHolder.circleImageView.setImageURI(Uri.parse(""));
         }
         if (posto.getLogo().equals("menor")) {
             Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/gasolina-8cc75.appspot.com/o/menor.jpg?alt=media&token=00530df1-63c5-4a32-88d2-19a479108460").into(myViewHolder.circleImageView);
             //  myViewHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menor));

             // myViewHolder.circleImageView.setImageURI(load.("");
         }
     }


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
             Intent intent = new Intent(context, contribuirActivity.class);
             intent.putExtra("nome", posto.getNome());
             intent.putExtra("gas", posto.getGas().toString());
             context.startActivity(intent);
         }
     });

     myViewHolder.addCompartilharGas.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent it = new Intent(Intent.ACTION_SEND);
             it.setType("text/plain");
             String Texto = "O preço do Gás(GNV) no " + posto.getNome().toString() + " está R$" + posto.getGas().toString() + " " + posto.getData();
             it.putExtra(Intent.EXTRA_SUBJECT, Texto);
             it.putExtra(Intent.EXTRA_TEXT, "Compartilhe o Aplicativo Gasosa! \n\n" + "https://play.google.com/store/apps/details?id=com.gasosa.uefs" + "\n\n" + Texto + "\n\n" + " Você pode se dirigir ao posto clicando no link: " + posto.getLink());

             context.startActivity(Intent.createChooser(it, "Compartilhar preços de combustível!"));
         }
     });
        /*if(i>2){
        Collections.sort(listaDistancia, new Comparator<PostoGasDistancia>() {
            @Override
            public int compare(PostoGasDistancia  p1, PostoGasDistancia  p2)
            {

                return p1.getDistancia().compareToIgnoreCase(p2.getDistancia());
            }
        });}*/


 }

    }

    private void getLocation(final PostoGas posto, final MyViewHolder my,final PostoGasDistancia ddd) {

        //System.out.println("ashduashduasdhausdha");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#
            //System.out.println("ashduashduasdhausdha2222");
           // my.distan.setText("Ative seu Gps");
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

                       // System.out.println("teste "+distancia + " " + distanciaFormatada);
                        my.distan.setText(distanciaFormatada);



                        int y=0;
                        int posicao;

                        ddd.setBairro(posto.getBairro());
                        ddd.setNome(posto.getNome());
                        ddd.setLogo(posto.getLogo());
                        ddd.setData(posto.getData());
                        ddd.setLink(posto.getLink());
                        ddd.setGas(posto.getGas());


                        ddd.setDistancia(distanciaFormatada);
                        System.out.println(distanciaFormatada+"po");
                        listaDistancia.add(ddd);






                        //myViewHolder..setText(posto.getBairro()+"\n"+agora);
                    }

                }
            }).addOnFailureListener((Activity)context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                  //  Log.d("MapDemoActivity", "Error trying to get last GPS location");
                    my.distan.setText("Gps(off)");
                    e.printStackTrace();


                }
            });}







    }




    @Override
    public int getItemCount() {
        if(numero2==1){
            return listaPostoD.size();
        }else{
        return listaPosto.size();}
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
        ImageButton addCompartilharGas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.titulo_notificappp);
            gas = itemView.findViewById(R.id.gasolinaViewGas);
            data = itemView.findViewById(R.id.dataviewGas);
            bairro = itemView.findViewById(R.id.bairroViewGas);
            button = itemView.findViewById(R.id.buttonLinkGas);
            distan= itemView.findViewById(R.id.distanciaKMGas);
            circleImageView= itemView.findViewById(R.id.profile_GAS);
            add= itemView.findViewById(R.id.addGas);
            addCompartilharGas=itemView.findViewById(R.id.addCompartilharGas);


        }

    }
}
