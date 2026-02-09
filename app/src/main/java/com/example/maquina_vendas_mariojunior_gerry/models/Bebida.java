package com.example.maquina_vendas_mariojunior_gerry.models;


public class Bebida extends Produto {
    private boolean gelada;

    public Bebida(String nome, double preco, int quantidade, String caminhoImagem, boolean gelada) {
        super(nome, preco, quantidade, caminhoImagem);
        this.gelada = gelada;
    }

    public boolean isGelada()
    { return gelada;
    }

    @Override
    public String toString()
    {
        return super.toString() + (gelada ? " [gelada]" : "");
    }
}

