package com.example.principal;

public class Carro {
    int id;
    String nome, placa, ano;

    public Carro(int id, String nome, String placa, String ano){
        this.id = id;
        this.nome = nome;
        this.placa = placa;
        this.ano = ano;
    }

    @Override
    public String toString(){
        String exibir = id+","+nome+","+placa+","+ano;
        return exibir;
    }
}
