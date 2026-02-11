package com.example.maquina_vendas_mariojunior_gerry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maquina_vendas_mariojunior_gerry.models.Bebida;
import com.example.maquina_vendas_mariojunior_gerry.models.Produto;
import com.example.maquina_vendas_mariojunior_gerry.models.Snack;
import com.example.maquina_vendas_mariojunior_gerry.models.Utilizador;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imgProduto;
    TextView txtNome, txtPreco, txtQuantidade, txtSaldo;
    Button buttonProximo, buttonAnterior, buttonComprar, buttonCarregarSaldo;

    ArrayList<Produto> produtos;
    Utilizador utilizador;

    int posicaoAtual = 0;
    private static final int REQUEST_SALDO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        imgProduto = findViewById(R.id.imgProduto);
        txtNome = findViewById(R.id.txtNome);
        txtPreco = findViewById(R.id.txtPreco);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        txtSaldo = findViewById(R.id.txtSaldo);

        buttonAnterior = findViewById(R.id.buttonAnterior);
        buttonProximo = findViewById(R.id.buttonProximo);
        buttonComprar = findViewById(R.id.buttonComprar);
        buttonCarregarSaldo = findViewById(R.id.buttonCarregarSaldo);



        utilizador = new Utilizador("Mario");

        produtos = new ArrayList<>();
        produtos.add(new Bebida("Sumol", 6.00, 10, "sumol_removebg_preview", true));
        produtos.add(new Bebida("Gurana", 6.32, 10, "gurana_removebg_preview", true));
        produtos.add(new Bebida("Água", 0.10, 10, "agua_drink", false));
        produtos.add(new Snack("Chocolate", 3.54, 10, "chocolate_milk", false));
        produtos.add(new Snack("Ruffles", 0.99, 10, "batata_removebg_preview", true));

        listarProduto();
        atualizarSaldo();
        

        buttonAnterior.setOnClickListener(v -> {
            posicaoAtual = (posicaoAtual - 1 + produtos.size()) % produtos.size();
            listarProduto();
        });

        buttonProximo.setOnClickListener(v -> {
            posicaoAtual = (posicaoAtual + 1 + produtos.size()) % produtos.size();
            listarProduto();
        });

        buttonComprar.setOnClickListener(v -> comprarProduto());

        buttonCarregarSaldo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SaldoActivity.class);
            intent.putExtra("saldo", utilizador.getSaldo());
            startActivityForResult(intent, REQUEST_SALDO);
        });
    }

    private void listarProduto() {

        Produto produtoAtual = produtos.get(posicaoAtual);

        String nomeImagem = produtoAtual.getCaminhoImagem();

        int idImagem = getResources().getIdentifier(
                nomeImagem,
                "drawable",
                this.getPackageName()
        );

        if (idImagem != 0) {
            imgProduto.setImageResource(idImagem);
        }

        txtNome.setText(produtoAtual.getNome());
        txtPreco.setText(String.format("%.2f €", produtoAtual.getPreco()));
        txtQuantidade.setText("Stock disponível: " + produtoAtual.getQuantidade());
    }


    private void atualizarSaldo() {
        txtSaldo.setText(
                String.format("Saldo: %.2f €", utilizador.getSaldo())
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SALDO && resultCode == RESULT_OK && data != null) {
            double saldoAtualizado = data.getDoubleExtra("saldoAtualizado", 0.0);
            utilizador.setSaldo(saldoAtualizado);

            atualizarSaldo();

            Toast.makeText(this,
                    "Saldo atualizado: " + saldoAtualizado + " €",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void comprarProduto() {
        Produto p1 = produtos.get(posicaoAtual);

        if (p1.getQuantidade() == 0) {
            Toast.makeText(this, "Produto sem stock.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (utilizador.getSaldo() < p1.getPreco()) {
            Toast.makeText(this, "Saldo invalido.", Toast.LENGTH_SHORT).show();
            return;
        }

        p1.comprar();
        utilizador.descontarSaldo(p1.getPreco());
        atualizarSaldo();

        Toast.makeText(this,
                "Ja foi comprado",
                Toast.LENGTH_SHORT).show();

        listarProduto();
    }
}
