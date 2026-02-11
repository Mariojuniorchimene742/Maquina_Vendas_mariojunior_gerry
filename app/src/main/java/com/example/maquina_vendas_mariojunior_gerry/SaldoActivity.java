package com.example.maquina_vendas_mariojunior_gerry;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SaldoActivity extends AppCompatActivity {

    private TextView saldoAtualTextView;
    private EditText valorEditText;
    private Button carregarButton;
    private Button voltarButton;

    private double saldoAtual = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        saldoAtualTextView = findViewById(R.id.saldoAtualTextView);
        valorEditText = findViewById(R.id.valorEditText);
        carregarButton = findViewById(R.id.carregarButton);
        voltarButton = findViewById(R.id.voltarButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            saldoAtual = bundle.getDouble("saldo", 0.0);
        }

        atualizarSaldo();

        carregarButton.setOnClickListener(v -> {
            String valorTexto = valorEditText.getText().toString();

            if (valorTexto.isEmpty()) {
                Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorTexto);

            if (valor <= 0) {
                Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            saldoAtual += valor;
            atualizarSaldo();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("saldoAtualizado", saldoAtual);
            setResult(RESULT_OK, resultIntent);

            finish();
        });

        // ALTERADO: agora só volta para a activity anterior
        voltarButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void atualizarSaldo() {
        saldoAtualTextView.setText(
                String.format("Saldo atual: %.2f €", saldoAtual)
        );
    }
}
