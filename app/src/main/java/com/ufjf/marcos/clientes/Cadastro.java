package com.ufjf.marcos.clientes;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private EditText edtEspecie;
    private EditText edtRaca;
    private EditText edtIdade;
    private EditText edtLocal;
    private EditText edtContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtEspecie = (EditText) findViewById(R.id.edtEspecie);
        edtRaca = (EditText) findViewById(R.id.edtRaca);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        edtLocal = (EditText) findViewById(R.id.edtLocal);
        edtContato = (EditText) findViewById(R.id.edtContato);
    }

    // valida todos os campos do cadastro
    private void validaCampos(){

        boolean res = false;

        String especie = edtEspecie.getText().toString();
        String raca = edtRaca.getText().toString();
        String idade = edtIdade.getText().toString();
        String local = edtLocal.getText().toString();
        String contato = edtContato.getText().toString();

        if(res = isCampoVazio(especie)){
            edtEspecie.requestFocus();
        }
        else
            if(res = isCampoVazio(raca)){
                edtRaca.requestFocus();
            }
            else
                if(res = isCampoVazio(idade)){
                    edtIdade.requestFocus();
                }
                else
                    if(res = isCampoVazio(local)){
                        edtLocal.requestFocus();
                    }
                    else
                        if(res = isCampoVazio(contato)){
                            edtContato.requestFocus();
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

//    // verifica se o email é válido
//    private boolean isEmailValido(String email){
//
//        boolean resultado = ( !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() );
//        return resultado;
//    }



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
