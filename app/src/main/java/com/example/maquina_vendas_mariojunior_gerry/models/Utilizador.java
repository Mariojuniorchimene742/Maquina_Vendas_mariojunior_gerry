package com.example.maquina_vendas_mariojunior_gerry.models;

import java.io.Serializable;

public class Utilizador implements Serializable {
    private String nome;
    private double saldo;

    public Utilizador(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public boolean comprarProduto(Produto p) {
        if (p.getQuantidade() > 0 && saldo >= p.getPreco()) {
            saldo -= p.getPreco();
            p.comprar();
            return true;
        }
        return false;
    }

    public void carregarSaldo(double valor) {
        saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNome() {
        return nome;
    }
}
