package com.ufjf.marcos.clientes.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ufjf.marcos.clientes.dominio.entidades.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalRepositorio {

    private SQLiteDatabase conexão;

    public AnimalRepositorio(SQLiteDatabase conexão){
        this.conexão = conexão;
    }

    public void inserirAnimal(Animal animal){

        ContentValues contentValues = new ContentValues();

        contentValues.put("ESPECIE", animal.especie);
        contentValues.put("RACA", animal.raca);
        contentValues.put("IDADE", animal.idade);
        contentValues.put("LOCAL", animal.local);
        contentValues.put("CONTATO", animal.contato);

        conexão.insertOrThrow("ANIMAL", null, contentValues);
    }

    public void excluirAnimal(int codigo){

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexão.delete("ANIMAL","CODIGO = ?", paramentros);

    }

    public void alterarAnimal(Animal animal){

        ContentValues contentValues = new ContentValues();

        contentValues.put("ESPECIE", animal.especie);
        contentValues.put("RACA", animal.raca);
        contentValues.put("IDADE", animal.idade);
        contentValues.put("LOCAL", animal.local);
        contentValues.put("CONTATO", animal.contato);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(animal.codigo);

        conexão.update("ANIMAL", contentValues, "CODIGO = ?", paramentros);

    }

    public Animal buscarAnimal(int codigo){

        Animal animal = new Animal();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ESPECIE, RACA, IDADE, LOCAL, CONTATO ");
        sql.append(" FROM ANIMAL ");
        sql.append(" WHERE CODIGO = ? ");

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        Cursor resultado = conexão.rawQuery(sql.toString(), paramentros);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();

            animal.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
            animal.especie = resultado.getString(resultado.getColumnIndexOrThrow("ESPECIE"));
            animal.raca = resultado.getString(resultado.getColumnIndexOrThrow("RACA"));
            animal.idade = resultado.getString(resultado.getColumnIndexOrThrow("IDADE"));
            animal.local = resultado.getString(resultado.getColumnIndexOrThrow("LOCAL"));
            animal.contato = resultado.getString(resultado.getColumnIndexOrThrow("CONTATO"));

            return animal;
        }

        return null;
    }

    public List<Animal> buscarAnimais(){

        List<Animal> animais = new ArrayList<Animal>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, ESPECIE, RACA, IDADE, LOCAL, CONTATO ");
        sql.append(" FROM ANIMAL ");


        Cursor resultado = conexão.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();
        }

        do{

            Animal ev = new Animal();

            ev.codigo = resultado.getInt( resultado.getColumnIndexOrThrow("CODIGO") );
            ev.especie = resultado.getString( resultado.getColumnIndexOrThrow("ESPECIE") );
            ev.raca = resultado.getString( resultado.getColumnIndexOrThrow("RACA") );
            ev.idade = resultado.getString( resultado.getColumnIndexOrThrow("IDADE") );
            ev.local = resultado.getString( resultado.getColumnIndexOrThrow("LOCAL") );
            ev.contato = resultado.getString( resultado.getColumnIndexOrThrow("CONTATO") );

            animais.add(ev);

        }while (resultado.moveToNext());

        return animais;
    }


}
