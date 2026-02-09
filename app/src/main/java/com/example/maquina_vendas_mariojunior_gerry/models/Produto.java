package com.example.maquina_vendas_mariojunior_gerry.models;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private double preco;
    private int quantidade;
    private String caminhoImagem;

    public Produto(String nome, double preco, int quantidade, String caminhoImagem) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caminhoImagem = caminhoImagem;
    }

    public boolean comprar() {
        if (quantidade > 0) {
            quantidade--;
            return true;
        }
        return false;
    }

    public String getNome()
    {
        return nome;
    }

    public double getPreco()
    {
        return preco;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public String getCaminhoImagem()
    {
        return caminhoImagem;
    }

    @Override
    public String toString() {
        return nome + " | Preço: " + preco + "€ | Quantidade: " + quantidade;
    }
}
