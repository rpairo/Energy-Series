package tech.rpairo.energyseries.adapters;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tech.rpairo.energyseries.model.Actor;

/**
 * Created by Raul on 27/6/16.
 */
public class AdapterRecyclerInfoActores extends AdapterRecyclerSeries {

    //region Variables
    private List<Actor> actores;
    //endregion

    //region Constructores
    public AdapterRecyclerInfoActores(List<Actor> items, Context context) {
        super(null, context);

        this.actores = items;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSeries viewHolderSeries, final int i) {

        Glide.with(this.context)
                .load(this.actores.get(i).getFoto())
                .override(350, 400)
                .into(viewHolderSeries.poster);

        viewHolderSeries.titulo.setText(this.actores.get(i).getNombre());

        viewHolderSeries.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(i);
            }
        });
    }
    //endregion

    //region Funciones del Adapter
    @Override
    public int getItemCount() {
        return this.actores.size();
    }
    //endregion

    //region Funciones auxiliares
    @Override
    protected void launchActivity(int position) {

    }

    public void addAllActores(ArrayList<Actor> actores) {
        if (actores == null)
            throw new NullPointerException("No puedes pasar una lista nula");

        this.actores.addAll(actores);
        this.notifyDataSetChanged();
    }
    //endregion
}