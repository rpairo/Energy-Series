package tech.rpairo.energyseries.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tech.rpairo.energyseries.R;

/**
 * Created by Raul on 24/6/16.
 */
public class ViewHolderSeriesSearch extends RecyclerView.ViewHolder {

    //region Variables
    public ImageView backdrop;
    public TextView titulo;
    //endregion

    //region Constructores
    public ViewHolderSeriesSearch(View view) {
        super(view);

        this.backdrop = (ImageView) view.findViewById(R.id.backdrop_serie_search);
        this.titulo = (TextView) view.findViewById(R.id.titulo_serie_search);
    }
    //endregion
}