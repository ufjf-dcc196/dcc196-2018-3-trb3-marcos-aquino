package com.ufjf.marcos.clientes.banco;

public class ScriptDLL {


    public static String getCreateTableCliente(){

        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS CLIENTE( ");

        sql.append(" CODIGO INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL, ");
        sql.append(" NOME VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append(" NOME VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append(" NOME VARCHAR (200) NOT NULL DEFAULT (''), ");
        sql.append(" NOME VARCHAR (20)  NOT NULL DEFAULT ('')) ");

        return sql.toString();
    }
}
