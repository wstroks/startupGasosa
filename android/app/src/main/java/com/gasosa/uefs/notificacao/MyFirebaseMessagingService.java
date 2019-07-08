package com.gasosa.uefs.notificacao;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.gasosa.uefs.R;
import com.gasosa.uefs.acitivity.MainActivity;
import com.gasosa.uefs.acitivity.PromocoesActivity;
import com.gasosa.uefs.model.Comentarios;
import com.gasosa.uefs.model.Notificacao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage notificacao) {
       // db= FirebaseDatabase.getInstance().getReference();
       // nt = new Notificacao();

        //DatabaseReference add = db.child("notificacao");


        if( notificacao.getNotification() != null ){

            String titulo = notificacao.getNotification().getTitle();
            String corpo = notificacao.getNotification().getBody();

           // nt.setTitulo(titulo);
            //nt.setCorpo(corpo);
            //nt.setLink("");
           // add.push().setValue(nt);
            enviarNotificacao(titulo, corpo);

            //Log.i("Notificacao", "recebida titulo: " + titulo + " corpo: " + corpo );

        }

    }

    private void enviarNotificacao(String titulo, String corpo){

        //Configuraçõe para notificação
        String canal = getString(R.string.default_notification_channel_id);
        Uri uriSom = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_NOTIFICATION );
        Intent intent = new Intent(getApplicationContext(), PromocoesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        //Criar notificação
        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(this, canal)
                .setContentTitle( titulo )
                .setContentText( corpo )
                .setSmallIcon( R.drawable.ic_action_name )
                .setSound( uriSom )
                .setAutoCancel( true )
                .setContentIntent( pendingIntent );

        //Recupera notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Verifica versão do Android a partir do Oreo para configurar canal de notificação
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            NotificationChannel channel = new NotificationChannel(canal, "canal", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel( channel );
        }

        //Envia notificação
        notificationManager.notify(0, notificacao.build() );

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

}
