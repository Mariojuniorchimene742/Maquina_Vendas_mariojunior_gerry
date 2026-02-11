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
    Button btnProximo, btnAnterior, btnComprar, btnCarregarSaldo;

    ArrayList<Produto> produtos;
    Utilizador utilizador;

    int index = 0;
    private static final int REQUEST_SALDO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        utilizador = new Utilizador("Mario");

        imgProduto = findViewById(R.id.imgProduto);
        txtNome = findViewById(R.id.txtNome);
        txtPreco = findViewById(R.id.txtPreco);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        txtSaldo = findViewById(R.id.txtSaldo);

        btnAnterior = findViewById(R.id.btnAnterior);
        btnProximo = findViewById(R.id.btnProximo);
        btnComprar = findViewById(R.id.btnComprar);
        btnCarregarSaldo = findViewById(R.id.btnCarregarSaldo);

        produtos = new ArrayList<>();
        produtos.add(new Bebida("Coca Cola", 1.50, 2, "cocacola_drink", true));
        produtos.add(new Bebida("Pepsi", 2.50, 2, "pepsi_drink", true));
        produtos.add(new Bebida("Água", 0.70, 2, "agua_drink", false));
        produtos.add(new Snack("Chocolate", 5.00, 2, "chocolate_milk", false));
        produtos.add(new Snack("Lays", 2.13, 2, "lays_snake", true));

        mostrarProduto();
        atualizarSaldo();




        btnAnterior.setOnClickListener(v -> {
            index = (index - 1 + produtos.size()) % produtos.size();
            mostrarProduto();
        });

        btnComprar.setOnClickListener(v -> comprarProduto());

        btnCarregarSaldo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SaldoActivity.class);
            intent.putExtra("saldo", utilizador.getSaldo());
            startActivityForResult(intent, REQUEST_SALDO);
        });
    }

    private void mostrarProduto() {
        Produto p = produtos.get(index);

        int imageId = getResources().getIdentifier(
                p.getCaminhoImagem(),
                "drawable",
                getPackageName()
        );

        imgProduto.setImageResource(imageId);
        txtNome.setText(p.getNome());
        txtPreco.setText(String.format("%.2f €", p.getPreco()));
        txtQuantidade.setText("Stock: " + p.getQuantidade());
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
        Produto p = produtos.get(index);

        if (p.getQuantidade() == 0) {
            Toast.makeText(this, "Produto esgotado.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (utilizador.getSaldo() < p.getPreco()) {
            Toast.makeText(this, "Saldo insuficiente.", Toast.LENGTH_SHORT).show();
            return;
        }

        p.comprar();
        utilizador.descontarSaldo(p.getPreco());
        atualizarSaldo();

        Toast.makeText(this,
                "Produto comprado com sucesso!",
                Toast.LENGTH_SHORT).show();

        mostrarProduto();
    }
}
