package com.example.maquina_vendas_mariojunior_gerry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maquina_vendas_mariojunior_gerry.R;

public class saldoActivity extends AppCompatActivity {

    private TextView saldoAtualTextView;
    private EditText valorEditText;
    private Button carregarButton;
    private Button voltarHomeButton;

    private double saldoAtual = 0.0; // saldo recebido da MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        saldoAtualTextView = findViewById(R.id.saldoAtualTextView);
        valorEditText = findViewById(R.id.valorEditText);
        carregarButton = findViewById(R.id.carregarButton);
        voltarHomeButton = findViewById(R.id.voltarHomeButton);

        // RECEBENDO saldo via Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            saldoAtual = bundle.getDouble("saldo", 0.0);
        }

        atualizarSaldo();

        carregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorTexto = valorEditText.getText().toString();
                if (valorTexto.isEmpty()) {
                    Toast.makeText(saldoActivity.this, "Digite um valor", Toast.LENGTH_SHORT).show();
                    return;
                }

                double valor = Double.parseDouble(valorTexto);

                if (valor <= 0) {
                    Toast.makeText(saldoActivity.this, "Valor inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                saldoAtual += valor;
                atualizarSaldo();
                valorEditText.setText("");

                // Retornar saldo atualizado para MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("saldoAtualizado", saldoAtual);
                setResult(RESULT_OK, resultIntent);
            }
        });
        voltarHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("saldoAtualizado", saldoAtual);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void atualizarSaldo() {
        saldoAtualTextView.setText(String.format("Saldo atual: %.2f €", saldoAtual));
    }

    @Override
    public void onBackPressed() {
        // Retorna saldo atual mesmo se apertar voltar
        Intent resultIntent = new Intent();
        resultIntent.putExtra("saldoAtualizado", saldoAtual);
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }
}
