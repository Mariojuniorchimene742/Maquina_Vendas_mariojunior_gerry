package com.example.maquina_vendas_mariojunior_gerry.models;

import java.util.ArrayList;

public class MaquinaVendas {
    private ArrayList<Produto> produtos;

    public MaquinaVendas() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
}
