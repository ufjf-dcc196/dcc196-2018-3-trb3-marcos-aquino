package com.ufjf.marcos.clientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ufjf.marcos.clientes.banco.BancoOpenHelper;
import com.ufjf.marcos.clientes.dominio.entidades.Animal;
import com.ufjf.marcos.clientes.dominio.repositorio.AnimalRepositorio;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layoutMain;

    private RecyclerView lstAnimais;
    private FloatingActionButton fab;

    private SQLiteDatabase conexao;    // conexao com o banco

    private BancoOpenHelper bancoOpenHelper;     // instancia de conexao

    private AnimalRepositorio animalRepositorio;

    private AnimalAdapter animalAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        lstAnimais = (RecyclerView) findViewById(R.id.lstAnimais);

        layoutMain = (ConstraintLayout) findViewById(R.id.layoutMain);

        lstAnimais.setHasFixedSize(true);

        criarConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstAnimais.setLayoutManager(linearLayoutManager);

        animalRepositorio = new AnimalRepositorio(conexao);

        List<Animal> dados = animalRepositorio.buscarAnimais();

        animalAdapter = new AnimalAdapter(dados);

        lstAnimais.setAdapter(animalAdapter);
    }

    private void criarConexao(){

        try{

            bancoOpenHelper = new BancoOpenHelper(this);

            conexao = bancoOpenHelper.getWritableDatabase();

            Snackbar.make(layoutMain, R.string.lbl_boas_vindas, Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok), null).show();

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);

            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }

    }

    public void cadastrar(View view) {

        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivityForResult(it, 0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        List<Animal> dados = animalRepositorio.buscarAnimais();
        animalAdapter = new AnimalAdapter(dados);
        lstAnimais.setAdapter(animalAdapter);

    }
}
