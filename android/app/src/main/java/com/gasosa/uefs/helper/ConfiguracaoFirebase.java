package com.gasosa.uefs.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    public static FirebaseDatabase database;
    private  static DatabaseReference referenciaFirebase;
    private static FirebaseAuth referenciaAutenticacao;

    public static  DatabaseReference getFirebase(){
        if(referenciaFirebase==null){
            referenciaFirebase= FirebaseDatabase.getInstance().getReference();

            //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        return referenciaFirebase;
    }

    public static FirebaseAuth getReferenciaAutenticacao(){
        if(referenciaAutenticacao == null){
            referenciaAutenticacao= FirebaseAuth.getInstance();

        }
        return referenciaAutenticacao;
    }

    public static FirebaseDatabase getDatabase(){
        if (database == null){
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }
}
