package com.ufjf.marcos.clientes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufjf.marcos.clientes.dominio.entidades.Animal;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolderAnimal> {

    private List<Animal> dados;

    public AnimalAdapter(List<Animal> dados){

        this.dados = dados;

    }

    @NonNull
    @Override
    public AnimalAdapter.ViewHolderAnimal onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.linha_animais, viewGroup, false);

        ViewHolderAnimal holderAnimal = new ViewHolderAnimal(view);

        return holderAnimal;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.ViewHolderAnimal viewHolder, int i) {

        if ((dados != null) && (dados.size() > 0) ){

            Animal animal = dados.get(i);

            viewHolder.txtRaca.setText(animal.raca);
            viewHolder.txtLocal.setText(animal.local);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }


    public class ViewHolderAnimal extends RecyclerView.ViewHolder{

        public TextView txtRaca;
        public TextView txtLocal;


        public ViewHolderAnimal(@NonNull View itemView) {
            super(itemView);

            txtRaca = (TextView) itemView.findViewById(R.id.txtRaca);
            txtLocal = (TextView) itemView.findViewById(R.id.txtLocal);
        }
    }
}
