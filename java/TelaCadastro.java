package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {

    SQLiteDatabase db;
    String BANCO = "banco.db";

    EditText editNomeCarro, editPlacaCarro, editAnoCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        editNomeCarro = findViewById(R.id.editNomeCarro);
        editPlacaCarro = findViewById(R.id.editPlacaCarro);
        editAnoCarro = findViewById(R.id.editAnoCarro);
    }

    public void cadastrarClick(View view) {
        //buscamos os campos preenchidos na interface gráfica
        String nomeCarro = editNomeCarro.getText().toString();
        String placaCarro = editPlacaCarro.getText().toString();
        String anoCarro = editAnoCarro.getText().toString();

        //insere o carro no banco
        Banco banco = new Banco(this);
        banco.insereCarro(nomeCarro,placaCarro,anoCarro);

        Toast.makeText(this, "Carro inserido com sucesso", Toast.LENGTH_SHORT).show();
        finish();

    }
}