package com.example.maquina_vendas_mariojunior_gerry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.maquina_vendas_mariojunior_gerry.adapters.ProdutoAdapter;
import com.example.maquina_vendas_mariojunior_gerry.models.Bebida;
import com.example.maquina_vendas_mariojunior_gerry.models.Doce;
import com.example.maquina_vendas_mariojunior_gerry.models.MaquinaVendas;
import com.example.maquina_vendas_mariojunior_gerry.models.Produto;
import com.example.maquina_vendas_mariojunior_gerry.models.Snack;
import com.example.maquina_vendas_mariojunior_gerry.models.Utilizador;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TextView saldoTextView;
    private ListView produtosListView;
    private Button comprarButton;
    private Button carregarSaldoButton;
    private int produtoSelecionado = -1;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private Utilizador utilizador;
    private MaquinaVendas maquinaVendas;
    private ProdutoAdapter adapter;
    private static final int REQUEST_CARREGAR_SALDO = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        saldoTextView = findViewById(R.id.saldoTextView);
        produtosListView = findViewById(R.id.produtosListView);
        comprarButton = findViewById(R.id.comprarButton);
        carregarSaldoButton = findViewById(R.id.button2);

        utilizador = new Utilizador("Cliente");
        produtos.add(new Bebida("Água", 1.00, 10, "logo_maqvendas", true));
        produtos.add(new Bebida("Refrigerante", 1.50, 8, "coca_cola", false));
        produtos.add(new Snack("Batatas", 2.00, 5, "ic_launcher_background", true));
        produtos.add(new Doce("Chocolate", 2.50, 7, "logo_maqvendas", true));
        maquinaVendas = new MaquinaVendas("Maquina 1", produtos, utilizador);

        atualizarSaldo();

        adapter = new ProdutoAdapter(this, produtos);
        produtosListView.setAdapter(adapter);
        produtosListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        produtosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produtoSelecionado = position;
            }
        });

        comprarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produtoSelecionado == -1) {
                    Toast.makeText(HomeActivity.this, "Selecione um produto", Toast.LENGTH_SHORT).show();
                    return;
                }
                Produto produto = produtos.get(produtoSelecionado);
                double preco = produto.getPreco();
                if (maquinaVendas.comprarProduto(produto.getNome()) && utilizador.descontarSaldo(preco)) {
                    adapter.notifyDataSetChanged();
                    atualizarSaldo();
                    Toast.makeText(HomeActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                } else if (produto.getQuantidade() == 0) {
                    Toast.makeText(HomeActivity.this, "Produto esgotado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        carregarSaldoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, saldoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("saldo", utilizador.getSaldo());
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CARREGAR_SALDO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CARREGAR_SALDO && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                double valor = bundle.getDouble("saldoAtualizado", utilizador.getSaldo());
                utilizador.carregarSaldo(valor - utilizador.getSaldo());
                atualizarSaldo();
            }
        }
    }

    private void atualizarSaldo() {
        saldoTextView.setText(String.format("Saldo: %.2f €", utilizador.getSaldo()));
    }
}
