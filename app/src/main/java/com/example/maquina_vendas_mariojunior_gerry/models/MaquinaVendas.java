package com.example.maquina_vendas_mariojunior_gerry.models;

import java.util.ArrayList;

public class MaquinaVendas
{

    private String nome;


    private Utilizador Utilizador;


    private ArrayList<Produto> produtosDisponiveis;


    public MaquinaVendas(String nome, ArrayList<Produto> produtos, Utilizador Utilizador)
    {

        this.nome = nome;


        this.produtosDisponiveis = produtos;


        this.Utilizador = Utilizador;
    }


    public boolean existeProduto(String nome)
    {

        int i = 0;


        boolean encontrado = false;


        while (i < produtosDisponiveis.size() && encontrado == false)
        {

            String nomeProduto = produtosDisponiveis.get(i).getNome();


            if (nomeProduto.equals(nome))
            {

                encontrado = true;
            }
            else
            {

                i++;
            }
        }


        return encontrado;
    }

    public boolean ComprarProduto(String nome)
    {

        int i = 0;

        boolean encontrado = false;


        while (i < produtosDisponiveis.size() && encontrado == false)
        {

            Produto p = produtosDisponiveis.get(i);

            String nomeProduto = p.getNome();

            if (nomeProduto.equals(nome))
            {

                encontrado = true;


                boolean compraSucesso = p.comprar();


                if (compraSucesso == true)
                {


                    return true;
                }


                return false;
            }
            else
            {

                i++;
            }
        }


        return encontrado;
    }
}

