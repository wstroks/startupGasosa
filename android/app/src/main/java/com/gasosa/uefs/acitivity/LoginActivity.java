package com.gasosa.uefs.acitivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gasosa.uefs.R;
import com.gasosa.uefs.helper.ConfiguracaoFirebase;
import com.gasosa.uefs.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private ProgressBar progressBar;
    private Usuario us;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verificarUsuarioLogado();
        inicializarComponentes();
        progressBar.setVisibility(View.GONE);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail= campoEmail.getText().toString();
                String textoSenha= campoSenha.getText().toString();
                if(!textoEmail.isEmpty()){
                    if(!textoSenha.isEmpty()){
                            us= new Usuario();
                            us.setEmail(textoEmail);
                            us.setSenha(textoSenha);
                            validarLogin(us);
                    }else{
                        Toast.makeText(LoginActivity.this, "Digite sua Senha!",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Digite seu Email!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirCadastro(View view){
        //Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
       // finish();
       // startActivity(i);
    }

    public void verificarUsuarioLogado(){
        autenticacao= ConfiguracaoFirebase.getReferenciaAutenticacao();
        if(autenticacao.getCurrentUser()!=null){
            startActivity( new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
    public void validarLogin(Usuario usuario){
        progressBar.setVisibility(View.VISIBLE);
        autenticacao= ConfiguracaoFirebase.getReferenciaAutenticacao();

        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Erro ao realizar login, tente novamente!",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });

    }
    public void inicializarComponentes(){

        campoEmail = findViewById(R.id.editLoginNome);
        campoSenha= findViewById(R.id.EditLoginSenhaC);
        botaoEntrar=findViewById(R.id.botaoEntrar);
        progressBar= findViewById(R.id.progressLogin);

        campoEmail.requestFocus();

    }

}
