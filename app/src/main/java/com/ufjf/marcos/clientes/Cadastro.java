package com.ufjf.marcos.clientes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
    }

    // valida todos os campos do cadastro
    private void validaCampos(){

        boolean res = false;

        String nome = edtNome.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();

        if(res = isCampoVazio(nome)){
            edtNome.requestFocus();
        }
        else
            if(res = isCampoVazio(endereco)){
                edtEndereco.requestFocus();
            }
            else
                if(res = !isEmailValido(email)){
                    edtEmail.requestFocus();
                }
                else
                    if(res = isCampoVazio(telefone)){
                        edtTelefone.requestFocus();
                    }

        if(res){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.msg_campos_invalidos);
            dlg.setNeutralButton(R.string.lbl_ok, null);
            dlg.show();
        }
    }

    // verifica se campo está preenchido ou vazio
    private boolean isCampoVazio(String valor){

        boolean resultado = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return resultado;
    }

    // verifica se o email é válido
    private boolean isEmailValido(String email){

        boolean resultado = ( !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() );
        return resultado;
    }



    // insere o Menu na página de cadastro
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.actCadastrar:
                validaCampos();
                Toast.makeText(this, "Botão cadastrar", Toast.LENGTH_SHORT).show();
                break;

            case R.id.actCancelar:
                Toast.makeText(this, "Botão cancelar", Toast.LENGTH_SHORT).show();
                finish();     // finaliza a atividade


                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
