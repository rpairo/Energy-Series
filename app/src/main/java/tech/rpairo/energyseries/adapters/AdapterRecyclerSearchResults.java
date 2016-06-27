package tech.rpairo.energyseries.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.activities.InfoActivity;
import tech.rpairo.energyseries.model.Serie;

/**
 * Created by Raul on 24/6/16.
 */
public class AdapterRecyclerSearchResults extends RecyclerView.Adapter<ViewHolderSeriesSearch> {

    //region Variables
    private List<Serie> items;
    private Context context;
    //endregion

    //region Constructores
    public AdapterRecyclerSearchResults(List<Serie> items, Context context) {
        this.items = items;
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
                .load(this.items.get(i).getFanArt())
                .into(viewHolderSearchSeries.backdrop);

        viewHolderSearchSeries.titulo.setText(this.items.get(i).getTitle());

        viewHolderSearchSeries.backdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
    //endregion

    //region Funciones auxiliares
    private void launchActivity(int position) {

        Intent intent = new Intent(context, InfoActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(InfoActivity.PARCELABLE_SERIE, items.get(position));

        intent.putExtras(bundle);

        context.startActivity(intent);
    }

    public void addAll(ArrayList<Serie> series) {
        if (series == null)
            throw new NullPointerException("No puedes pasar una lista nula");

        this.items = new ArrayList<>();

        this.items.addAll(series);
        this.notifyDataSetChanged();
    }
    //endregion
}