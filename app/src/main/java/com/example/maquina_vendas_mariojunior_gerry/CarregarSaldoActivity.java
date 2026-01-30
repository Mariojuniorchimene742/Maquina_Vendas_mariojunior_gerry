package com.example.maquina_vendas_mariojunior_gerry;
//imports
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maquina_vendas_mariojunior_gerry.models.Utilizador;

    public class CarregarSaldoActivity extends AppCompatActivity {

        private Utilizador usuario;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_saldo);

            usuario = (Utilizador) getIntent().getSerializableExtra("usuario");

            TextView saldoAtualTextView = findViewById(R.id.saldoAtualTextView);
            EditText valorEditText = findViewById(R.id.valorEditText);
            Button carregarButton = findViewById(R.id.carregarButton);

            saldoAtualTextView.setText("Saldo atual: " + usuario.getSaldo() + " €");

            carregarButton.setOnClickListener(v -> {
                double valor = Double.parseDouble(valorEditText.getText().toString());
                usuario.carregarSaldo(valor);
                saldoAtualTextView.setText("Saldo atual: " + usuario.getSaldo() + " €");
                Toast.makeText(this, "Carregamento efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            });
        }
    }


