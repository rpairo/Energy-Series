package tech.rpairo.energyseries.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.model.Serie;

/**
 * Created by Raul on 24/6/16.
 */
public class AdapterRecyclerSeries extends RecyclerView.Adapter<ViewHolderSeries> {

    //region Variables
    private List<Serie> items;
    private Context context;
    //endregion

    //region Constructores
    public AdapterRecyclerSeries(List<Serie> items, Context context) {
        this.items = items;
        this.context = context;
    }
    //endregion

    //region Funciones del Recycler
    @Override
    public ViewHolderSeries onCreateViewHolder(ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_serie, viewGroup, false);

        return new ViewHolderSeries(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderSeries viewHolderSeries, int i) {

        Glide.with(this.context)
                .load(this.items.get(i).getPoster())
                .override(350, 400)
                .into(viewHolderSeries.poster);

        viewHolderSeries.titulo.setText(this.items.get(i).getTitle());

        viewHolderSeries.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
    //endregion

    //region Funciones auxiliares
    public void addAll(ArrayList<Serie> series) {
        if (series == null)
            throw new NullPointerException("No puedes pasar una lista nula");

        this.items.addAll(series);
        this.notifyDataSetChanged();
    }
    //endregion
}