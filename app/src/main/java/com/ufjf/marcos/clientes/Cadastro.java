package com.ufjf.marcos.clientes;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
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

import com.ufjf.marcos.clientes.banco.BancoOpenHelper;
import com.ufjf.marcos.clientes.dominio.entidades.Animal;
import com.ufjf.marcos.clientes.dominio.repositorio.AnimalRepositorio;

public class Cadastro extends AppCompatActivity {

    private EditText edtEspecie;
    private EditText edtRaca;
    private EditText edtIdade;
    private EditText edtLocal;
    private EditText edtContato;

    private AnimalRepositorio animalRepositorio;

    private SQLiteDatabase conexao;

    private BancoOpenHelper bancoOpenHelper;

    private ConstraintLayout layoutCadastro;

    private Animal animal;


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

        layoutCadastro = (ConstraintLayout) findViewById(R.id.layoutCadastro);


        criarConexao();
    }


    private void criarConexao(){

        try{

            bancoOpenHelper = new BancoOpenHelper(this);

            conexao = bancoOpenHelper.getWritableDatabase();

            Snackbar.make(layoutCadastro, R.string.lbl_boas_vindas, Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok), null).show();

            animalRepositorio = new AnimalRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);

            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }

    }


    private void confirmar(){

        animal = new Animal();

        if(!validaCampos()){

            try{

                animalRepositorio.inserirAnimal(animal);

                finish();


            }catch (SQLException ex){

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);

                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();

            }


        }

//        if(!validaCampos()){
//
//            try {
//
//                if(animal.codigo == 0){
//
//                    animalRepositorio.inserirAnimal(animal);
//                    Toast.makeText(getApplicationContext(), "Animal cadastrado com sucesso!", Toast.LENGTH_LONG).show();
//                    finish();
//                    //finish();
//                }else {
//                    animalRepositorio.alterarAnimal(animal);
//                    Toast.makeText(getApplicationContext(), "Animal editado com sucesso!", Toast.LENGTH_LONG).show();
//                    finish();
//                }
//
//            }catch (SQLException ex){
//
//                android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
//                dlg.setTitle(getString(R.string.title_erro));
//                dlg.setMessage(ex.getMessage());
//                dlg.setNeutralButton(getString(R.string.action_ok), null);
//                dlg.show();
//
//            }
//        }

    }


    // valida todos os campos do cadastro
    private boolean validaCampos(){

        boolean res = false;

        String especie = edtEspecie.getText().toString();
        String raca = edtRaca.getText().toString();
        String idade = edtIdade.getText().toString();
        String local = edtLocal.getText().toString();
        String contato = edtContato.getText().toString();


        animal.especie = especie;
        animal.raca = raca;
        animal.idade = idade;
        animal.local = local;
        animal.contato = contato;


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

        return res;
    }

    // verifica se campo está preenchido ou vazio
    private boolean isCampoVazio(String valor){

        boolean resultado = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
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
                confirmar();
                Toast.makeText(this, "Animal Cadastrado!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.actCancelar:
                Toast.makeText(this, "Cadastro cancelado!", Toast.LENGTH_SHORT).show();
                finish();     // finaliza a atividade


                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
