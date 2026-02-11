package com.example.maquina_vendas_mariojunior_gerry.models;


public class Utilizador
{
    private String nome;

    private double saldo;


    public Utilizador(String nome)
    {

        this.nome = nome;

        this.saldo = 0;
    }

    public String getNome()
    {
        return this.nome;
    }

    public double getSaldo()
    {
        return this.saldo;
    }

    public boolean carregarSaldo(double valor)
    {

        if(valor > 0)
        {

            this.saldo = this.saldo + valor;
            return true;
        }

        return false;
    }

    public boolean descontarSaldo(double valor)
    {
        {
            if(valor > 0 && valor <= this.saldo)
            {

                this.saldo = this.saldo - valor;
                return true;
            }

            return false;
        }
    }

    @Override
    public String toString()
    {
        return "Utilizador: " + nome + "\nSaldo: " + saldo;
    }


    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
}


