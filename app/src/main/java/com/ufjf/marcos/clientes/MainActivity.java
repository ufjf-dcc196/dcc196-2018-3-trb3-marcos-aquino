package com.ufjf.marcos.clientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lstAnimais;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        lstAnimais = (RecyclerView) findViewById(R.id.lstAnimais);

       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(MainActivity.this, Cadastro.class);
                startActivity(it);

            }
        }); */
    }

    public void cadastrar(View view) {

        Intent it = new Intent(MainActivity.this, Cadastro.class);
        startActivity(it);

    }

}
