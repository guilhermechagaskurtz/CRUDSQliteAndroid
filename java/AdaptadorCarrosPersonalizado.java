package com.example.principal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorCarrosPersonalizado extends BaseAdapter {

    private final List<Carro> carros;
    Activity activity;

    public AdaptadorCarrosPersonalizado(List<Carro> _carros, Activity _activity){
        this.carros = _carros;
        this.activity = _activity;
    }

    @Override public int getCount() {
        return carros.size();
    }

    @Override public Object getItem(int position) {
        return carros.get(position);
    }

    @Override public long getItemId(int position) {
        return carros.get(position).id;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        //instanciamos um template de exibição de carros
        View template = activity.getLayoutInflater().inflate(R.layout.template_item_lista, parent, false);

        //pega o carro da lista que será criado o template de layout
        Carro c = carros.get(position);

        //joga os dados do carro para dentro do template
        TextView idCarro = template.findViewById(R.id.idCarro);
        TextView nomeCarro = template.findViewById(R.id.nomeCarro);
        TextView placaCarro = template.findViewById(R.id.placaCarro);
        TextView anoCarro = template.findViewById(R.id.anoCarro);

        idCarro.setText(c.id+"");
        nomeCarro.setText(c.nome);
        placaCarro.setText(c.placa);
        anoCarro.setText(c.ano);

        return template;
    }

}
