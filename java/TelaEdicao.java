package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaEdicao extends AppCompatActivity {

    Banco banco;

    EditText editIdCarro, editNomeCarro, editPlacaCarro, editAnoCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edicao);

        editIdCarro = findViewById(R.id.editIdCarro);
        editNomeCarro = findViewById(R.id.editNomeCarro);
        editPlacaCarro = findViewById(R.id.editPlacaCarro);
        editAnoCarro = findViewById(R.id.editAnoCarro);

        banco = new Banco(this);

        //pega o id do carro recebido do listView da tela principal
        long id = getIntent().getLongExtra("paramId",0);
        //busca informações sobre o carro com o id acima no banco
        Carro c = banco.buscaCarroPorId(id);

        //joga os dados do carro na interface gráfica
        editIdCarro.setText(c.id+"");
        editNomeCarro.setText(c.nome);
        editPlacaCarro.setText(c.placa);
        editAnoCarro.setText(c.ano);

    }

    public void alterarClick(View view) {
        //pega o que está digitado na interface gráfica
        long id = Long.parseLong(editIdCarro.getText().toString());
        String nome = editNomeCarro.getText().toString();
        String placa = editPlacaCarro.getText().toString();
        String ano = editAnoCarro.getText().toString();

        //altera no banco
        banco.alterarCarro(id, nome, placa, ano);
        Toast.makeText(this, "Carro alterado com sucesso!", Toast.LENGTH_SHORT).show();

        //fecha a tela
        finish();
    }

    public void excluirClick(View view) {
        //pega o id que está digitado na interface gráfica
        long id = Long.parseLong(editIdCarro.getText().toString());

        //exclui o carro no banco
        banco.excluirCarro(id);
        Toast.makeText(this, "Carro excluído com sucesso!", Toast.LENGTH_SHORT).show();

        //fecha a tela
        finish();
    }
}