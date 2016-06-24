package tech.rpairo.energyseries.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tech.rpairo.energyseries.R;

/**
 * Created by Raul on 24/6/16.
 */
public class ViewHolderSeries extends RecyclerView.ViewHolder {

    //region Variables
    public ImageView poster;
    public TextView titulo;
    //endregion

    //region Constructores
    public ViewHolderSeries(View view) {
        super(view);

        this.poster = (ImageView) view.findViewById(R.id.poster_serie);
        this.titulo = (TextView) view.findViewById(R.id.titulo_serie);
    }
    //endregion
}