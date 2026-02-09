package com.example.maquina_vendas_mariojunior_gerry.models;

public class Doce extends Produto {

    private boolean acucar;

    public Doce(String nome, double preco, int quantidade, String caminhoImagem, boolean sabor) {
        super(nome, preco, quantidade, caminhoImagem);
        this.acucar = sabor;
    }

    @Override
    public String toString()
    {
        return super.toString() +  "| Tem:nao| sim:" + acucar;
    }
}

