package com.example.maquina_vendas_mariojunior_gerry.models;

import java.io.Serializable;


    public class Produto implements Serializable {

        private String nome;
        private final double preco;
        private int quantidade;
        private int imagem;

        public Produto(String nome, double preco, int quantidade, int imagem) {
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
            this.imagem = imagem;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public int getImagem() {
            return imagem;
        }

        public boolean comprar() {
            if (quantidade > 0) {
                quantidade--;
                return true;
            }
            return false;
        }
    }

