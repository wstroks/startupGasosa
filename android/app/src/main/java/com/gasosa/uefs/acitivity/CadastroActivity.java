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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {
    private EditText campoUsuario, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private ProgressBar progressBar;
    private Usuario us;
    FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();

        //Cadastro usuario
        progressBar.setVisibility(View.GONE);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
                String textoNome= campoUsuario.getText().toString();
                String textoEmail= campoEmail.getText().toString();
                String textoSenha= campoSenha.getText().toString();
                if(!textoNome.isEmpty()){
                    if(!textoEmail.isEmpty()){
                        if(!textoSenha.isEmpty()){
                            us= new Usuario();
                            us.setNome(textoNome);
                            us.setEmail(textoEmail);
                            us.setSenha(textoSenha);
                            CadastrarUsuario(us);

                        }else{
                            Toast.makeText(CadastroActivity.this, "Preencha a Senha!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroActivity.this, "Preencha o Email!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this, "Preencha o nome!",Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
    /*
        cadastrar usuario
     */
    public void CadastrarUsuario(Usuario usuario){
        progressBar.setVisibility(View.VISIBLE);
        autenticacao= ConfiguracaoFirebase.getReferenciaAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
             usuario.getEmail(),
             usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(CadastroActivity.this,"Cadastro realizado com sucesso, Bem Vindo ao Gasosa!" ,
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    progressBar.setVisibility(View.GONE);
                    String erroExcecao="";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "Por favor, digite um e-mail válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Este conta já foi cadastrada";
                    } catch (Exception e) {
                        erroExcecao = "ao cadastrar usuário: "  + e.getMessage();
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public void inicializarComponentes(){
        campoUsuario = findViewById(R.id.editCadastroNome);
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha= findViewById(R.id.EditCadastroSenhaC);
        botaoCadastrar=findViewById(R.id.botaoCadastrar);
        progressBar= findViewById(R.id.progressCadastro);

        campoUsuario.requestFocus();
    }
}
