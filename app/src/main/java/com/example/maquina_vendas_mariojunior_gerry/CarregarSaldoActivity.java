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

        private Utilizador user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_saldo);

            user = (Utilizador) getIntent().getSerializableExtra("user");

            TextView saldoAtualTextView = findViewById(R.id.saldoAtualTextView);
            EditText valorEditText = findViewById(R.id.valorEditText);
            Button carregarButton = findViewById(R.id.carregarButton);

            saldoAtualTextView.setText("Saldo atual: " + user.getSaldo() + " €");

            carregarButton.setOnClickListener(v -> {
                double valor = Double.parseDouble(valorEditText.getText().toString());
                user.carregarSaldo(valor);
                saldoAtualTextView.setText("Saldo atual: " + user.getSaldo() + " €");
                Toast.makeText(this, "Carregamento efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            });
        }
    }


