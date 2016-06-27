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
import tech.rpairo.energyseries.model.Capitulo;

/**
 * Created by Raul on 27/6/16.
 */
public class AdapterRecyclerInfoCapitulos extends RecyclerView.Adapter<ViewHolderSeriesSearch> {

    //region Variables
    private List<Capitulo> capitulos;
    private Context context;
    //endregion

    //region Constructores
    public AdapterRecyclerInfoCapitulos(List<Capitulo> items, Context context) {
        this.capitulos = items;
        this.context = context;
    }

    @Override
    public ViewHolderSeriesSearch onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_item_search, viewGroup, false);

        return new ViewHolderSeriesSearch(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSeriesSearch viewHolderSearchSeries, final int i) {

        Glide.with(this.context)
                .load(this.capitulos.get(i).getImagen())
                .into(viewHolderSearchSeries.backdrop);

        viewHolderSearchSeries.titulo.setText(this.capitulos.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return this.capitulos.size();
    }
    //endregion

    //region Funciones auxiliares
    public void addAll(ArrayList<Capitulo> capitulos) {
        if (capitulos == null)
            throw new NullPointerException("No puedes pasar una lista nula");

        this.capitulos = new ArrayList<>();

        this.capitulos.addAll(capitulos);
        this.notifyDataSetChanged();
    }
    //endregion
}