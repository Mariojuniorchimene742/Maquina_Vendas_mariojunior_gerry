package com.example.maquina_vendas_mariojunior_gerry.models;



public class Snack extends Produto {

    private boolean salgada;

    public Snack(String nome, double preco, int quantidade, String caminhoImagem, boolean salgada) {
        super(nome, preco, quantidade, caminhoImagem);
        this.salgada = salgada;
    }

    public boolean isSalgada()

    {
        return salgada;
    }

    @Override
    public String toString() {
        return super.toString() + (salgada ? " [Salgada]" : " [Doce]");
    }
}
