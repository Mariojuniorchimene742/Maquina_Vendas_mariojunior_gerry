package com.example.maquina_vendas_mariojunior_gerry.models;

public class Doce extends Produto {

    private boolean temChocolate; // atributo extra específico de doces

    public Doce(String nome, double preco, int quantidade, String caminhoImagem, boolean temChocolate) {
        super(nome, preco, quantidade, caminhoImagem);
        this.temChocolate = temChocolate;
    }

    public boolean temChocolate() {
        return temChocolate;
    }

    @Override
    public String toString() {
        return super.toString() + (temChocolate ? " [Com Chocolate]" : " [Sem Chocolate]");
    }
}

