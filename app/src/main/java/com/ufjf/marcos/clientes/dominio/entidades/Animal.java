package com.ufjf.marcos.clientes.dominio.entidades;

import java.io.Serializable;

public class Animal implements Serializable {

    public int codigo;
    public String especie;
    public String raca;
    public String idade;
    public String local;
    public String contato;

    public Animal(){

        codigo = 0;

    }

}
