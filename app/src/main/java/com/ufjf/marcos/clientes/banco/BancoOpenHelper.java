package com.ufjf.marcos.clientes.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoOpenHelper extends SQLiteOpenHelper {


    public BancoOpenHelper(Context context) {
        super(context, "banco", null, 1);
    }

    // criação da tabela do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDLL.getCreateTableCliente());

    }


    // atualizações da tabela do banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
