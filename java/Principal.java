package com.example.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class Principal extends AppCompatActivity {

    ListView listaCarros;
    Banco banco;

    EditText editBuscaCarro;

    int x = 0;

    Activity eu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eu = this;

        banco = new Banco(this);

        listaCarros = findViewById(R.id.listaCarros);
        editBuscaCarro = findViewById(R.id.editBuscaCarro);

        //implementamos o evento que dispara toda vez que uma tecla é pressionada ou levantada
        editBuscaCarro.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                //toda vez que algo mudar no EditText, vamos atualizar a lista de carros
                String nomeBuscar = editBuscaCarro.getText().toString();
                //busca os carros no banco
                List<Carro> carros = banco.buscaPorNome(nomeBuscar);
                //ponte que conecta os dados a interface gráfica (gambiarra para passar a activity para o adaptador)
                AdaptadorCarrosPersonalizado adaptador = new AdaptadorCarrosPersonalizado(carros, eu);
                //componente da interface gráfica
                listaCarros.setAdapter(adaptador);

                return false;
            }
        });

        listaCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                //abre uma tela para edição do carro de id igual ao id do parâmetro
                Intent it = new Intent(getApplicationContext(), TelaEdicao.class);
                //passa o id por parâmetro dentro da activity
                it.putExtra("paramId", id);
                startActivity(it);
            }
        });

        criarBanco();
    }

    @Override
    protected void onResume(){
        super.onResume();
        atualizaListaVeiculos();
    }

    public void atualizaListaVeiculos(){
        //dados vindos do banco
        List<Carro> carros = banco.listaTodosOsCarros();

        //ponte que conecta os dados a interface gráfica
        AdaptadorCarrosPersonalizado adaptador = new AdaptadorCarrosPersonalizado(carros, this);

        //componente da interface gráfica
        listaCarros.setAdapter(adaptador);
    }

    public void criarBanco(){
        //cria o banco
        banco.criaBanco();
    }

    public void abreTelaCadastroClick(View view) {
        Intent it = new Intent(this, TelaCadastro.class);
        startActivity(it);
    }
}