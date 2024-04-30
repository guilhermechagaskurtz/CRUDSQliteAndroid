package com.example.principal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    SQLiteDatabase db;
    String BANCO = "banco.db";

    Context ctx;

    public Banco(Context _ctx){
        this.ctx = _ctx;
    }

    public void criaBanco(){
        //abrimos ou criamos o banco caso não exista
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //cria-se as tabelas, executando um SQL
        db.execSQL("CREATE TABLE IF NOT EXISTS carro (" +
                "ID INTEGER PRIMARY KEY, " +
                "NOME TEXT, " +
                "PLACA TEXT, " +
                "ANO TEXT);");
        //fecha o banco
        db.close();
    }

    public void insereCarro(String nomeCarro, String placaCarro, String anoCarro){
        //abre o banco
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //executa o SQL para inserção do carro
        db.execSQL("INSERT INTO carro (NOME, PLACA, ANO) VALUES (" +
                "'"+nomeCarro+"','"+placaCarro+"','"+anoCarro+"')");
        //fecha o banco
        db.close();
    }

    public List<Carro> listaTodosOsCarros(){
        //abre o banco
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //executa uma consulta
        Cursor linhas = db.rawQuery("SELECT * FROM carro", null);

        //declara a lista de carros
        List<Carro> carros = new ArrayList<>();
        if(linhas.moveToFirst()){ //se houver pelo menos 1 carro
            do{
                String ID = linhas.getString(0);
                String NOME = linhas.getString(1);
                String PLACA = linhas.getString(2);
                String ANO = linhas.getString(3);
                Carro c = new Carro(Integer.parseInt(ID),NOME, PLACA, ANO);
                carros.add(c);
            }
            while(linhas.moveToNext()); //vai para o próximo carro, enquanto houverem carros
        }
        //retorna a lista de carros para a interface gráfica
        return carros;
    }

    public List<Carro> buscaPorNome(String nome){
        //abre o banco
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //executa uma consulta de acordo com o nome do carro
        Cursor linhas = db.rawQuery("SELECT * FROM carro WHERE nome LIKE '%"+nome+"%'", null);

        //declara a lista de carros
        List<Carro> carros = new ArrayList<>();
        if(linhas.moveToFirst()){ //se houver pelo menos 1 carro
            do{
                String ID = linhas.getString(0);
                String NOME = linhas.getString(1);
                String PLACA = linhas.getString(2);
                String ANO = linhas.getString(3);
                Carro c = new Carro(Integer.parseInt(ID),NOME, PLACA, ANO);
                carros.add(c);
            }
            while(linhas.moveToNext()); //vai para o próximo carro, enquanto houverem carros
        }
        //retorna a lista de carros para a interface gráfica
        return carros;
    }

    public Carro buscaCarroPorId(long id){
        //abre o banco
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //executa uma consulta
        Cursor linhas = db.rawQuery("SELECT * FROM carro WHERE id = "+id, null);

        //verifica se existe um carro com esse id no banco
        if(linhas.moveToFirst()){
            String ID = linhas.getString(0);
            String NOME = linhas.getString(1);
            String PLACA = linhas.getString(2);
            String ANO = linhas.getString(3);
            Carro c = new Carro(Integer.parseInt(ID),NOME, PLACA, ANO);
            return c;
        }
        else{
            return null;
        }
    }

    public void alterarCarro(long id, String nome, String placa, String ano){
        //abre o banco
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //executa o SQL de alteração do carro
        db.execSQL("UPDATE carro SET NOME = '"+nome+"'," +
                "PLACA = '"+placa+"'," +
                "ANO = '"+ano+"' WHERE ID = "+id);
        //fecha o banco
        db.close();
    }

    public void excluirCarro(long id){
        //abre o banco
        db = ctx.openOrCreateDatabase(BANCO, Context.MODE_PRIVATE, null);
        //executa o SQL de exclusão
        db.execSQL("DELETE FROM carro WHERE ID = "+id);
        //fecha o banco
        db.close();
    }
}
