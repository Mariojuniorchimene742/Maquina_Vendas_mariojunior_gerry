package com.example.maquina_vendas_mariojunior_gerry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Utilizador user;
    MaquinaVendas maquina;
    TextView txtSaldo;
    ListView listView;

    Produto[] produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtSaldo = findViewById(R.id.txtSaldo);
        listView = findViewById(R.id.listProdutos);

        user = new Utilizador(5);

        produtos = new Produto[]{
                new Bebida("Refrigerante Cósmico", 1.5, 5, ""),
                new Bebida("Chá Alienígena", 1.2, 3, ""),
                new Snack("Batatas Explosivas", 2.0, 4, ""),
                new Doce("Chocolito Turbo", 1.8, 6, "")
        };

        maquina = new MaquinaVendas(produtos);

        atualizarSaldo();

        String[] nomes = new String[produtos.length];

        for (int i = 0; i < produtos.length; i++) {
            nomes[i] = produtos[i].getNome() +
                    " - " + produtos[i].getPreco() + "€ (" +
                    produtos[i].getQuantidade() + ")";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nomes
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((a, b, pos, id) -> {
            maquina.comprarProduto(pos, user);
            atualizarSaldo();
            recreate(); // atualiza lista
        });

        Button btnSaldo = findViewById(R.id.btnSaldo);

        btnSaldo.setOnClickListener(v -> {
            startActivity(new Intent(this, SaldoActivity.class));
        });
    }

    private void atualizarSaldo() {
        txtSaldo.setText("Saldo: " + user.getSaldo() + "€");
    }
}
