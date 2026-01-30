package com.example.maquina_vendas_mariojunior_gerry;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maquina_vendas_mariojunior_gerry.models.Bebida;
import com.example.maquina_vendas_mariojunior_gerry.models.Doce;
import com.example.maquina_vendas_mariojunior_gerry.models.MaquinaVendas;
import com.example.maquina_vendas_mariojunior_gerry.models.Produto;
import com.example.maquina_vendas_mariojunior_gerry.models.Utilizador;
import com.example.maquina_vendas_mariojunior_gerry.models.Snack;

public class HomeActivity extends AppCompatActivity {

    private Utilizador usuario;
    private MaquinaVendas maquina;
    private TextView txtSaldo;
    private ListView listProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtSaldo = findViewById(R.id.saldoTextView);
        listProdutos = findViewById(R.id.produtosListView);
        Button btnSaldo = findViewById(R.id.carregarSaldoButton);

        // Criar usuário e máquina
        usuario = new Utilizador("Alice", 10.0);
        maquina = new MaquinaVendas();
        maquina.adicionarProduto(new Bebida("Refrigerante Cósmico", 2.5, 5, 0));
        maquina.adicionarProduto(new Snack("Batatas Explosivas", 1.5, 3, 0));
        maquina.adicionarProduto(new Doce("Chocolito Turbo", 1.0, 10, 0));

        atualizarSaldo();

        String[] nomes = new String[maquina.getProdutos().size()];
        for (int i = 0; i < maquina.getProdutos().size(); i++) {
            Produto p = maquina.getProdutos().get(i);
            nomes[i] = p.getNome() + " - " + p.getPreco() + "€ (" + p.getQuantidade() + ")";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomes);
        listProdutos.setAdapter(adapter);

        listProdutos.setOnItemClickListener((parent, view, position, id) -> {
            Produto produto = maquina.getProdutos().get(position);
            if (usuario.comprarProduto(produto)) {
                Toast.makeText(this, "Produto comprado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Saldo insuficiente ou produto indisponível", Toast.LENGTH_SHORT).show();
            }
            atualizarSaldo();
            // atualizar nomes
            for (int i = 0; i < maquina.getProdutos().size(); i++) {
                Produto p = maquina.getProdutos().get(i);
                nomes[i] = p.getNome() + " - " + p.getPreco() + "€ (" + p.getQuantidade() + ")";
            }
            adapter.notifyDataSetChanged();
        });

        btnSaldo.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CarregarSaldoActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        });
    }

    private void atualizarSaldo() {
        txtSaldo.setText("Saldo: " + usuario.getSaldo() + " €");
    }
}
