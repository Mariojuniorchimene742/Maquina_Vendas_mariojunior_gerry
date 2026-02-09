package com.example.maquina_vendas_mariojunior_gerry.adapters;

import static com.example.maquina_vendas_mariojunior_gerry.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.maquina_vendas_mariojunior_gerry.models.Produto;
import com.example.maquina_vendas_mariojunior_gerry.R;

import java.util.List;

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    public ProdutoAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(layout.produtos_estaticos, parent, false);
        TextView nomeView = convertView.findViewById(id.txtNomeProduto);
        TextView precoView = convertView.findViewById(id.txtPrecoProduto);
        TextView qtdView = convertView.findViewById(id.txtQtdProduto);
        ImageView imgView = convertView.findViewById(id.imgProduto);

        nomeView.setText(produto.getNome());
        precoView.setText(String.format("%.2f €", produto.getPreco()));
        qtdView.setText("Qtd: " + produto.getQuantidade());
        int resId = getContext().getResources().getIdentifier(produto.getCaminhoImagem(), "drawable", getContext().getPackageName());
        if (resId != 0) {
            imgView.setImageResource(resId);
        } else {
            imgView.setImageResource(drawable.png_transparent_coca_cola_coca_cola_bottle_glass_bottles_thumbnail); // fallback
        }
        return convertView;
    }
}
