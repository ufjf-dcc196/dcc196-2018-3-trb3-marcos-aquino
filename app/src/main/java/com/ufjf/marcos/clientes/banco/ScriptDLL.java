package com.ufjf.marcos.clientes.banco;

public class ScriptDLL {


    public static String getCreateTableAnimal(){

        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS ANIMAL( ");

        sql.append("    CODIGO   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    ESPECIE  VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("    RACA     VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("    IDADE    VARCHAR (200) NOT NULL DEFAULT (''), ");
        sql.append("    LOCAL    VARCHAR (200) NOT NULL DEFAULT (''), ");
        sql.append("    CONTATO  VARCHAR (200) NOT NULL DEFAULT ('')) ");

        return sql.toString();
    }
}
