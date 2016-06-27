package tech.rpairo.energyseries.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tech.rpairo.energyseries.R;

/**
 * Created by Raul on 27/6/16.
 */
public class ViewHolderCapitulos extends RecyclerView.ViewHolder {

    //region Variables
    public ImageView backdrop;
    public TextView titulo;
    public TextView fecha;
    public TextView valoracion;
    public ImageView icono;
    //endregion

    //region Constructores
    public ViewHolderCapitulos(View view) {
        super(view);

        this.backdrop = (ImageView) view.findViewById(R.id.backdrop_capitulo);
        this.titulo = (TextView) view.findViewById(R.id.titulo_capitulo);
        this.fecha = (TextView) view.findViewById(R.id.fecha_capitulo);
        this.valoracion = (TextView) view.findViewById(R.id.valoracion_capitulo);
        this.icono = (ImageView) view.findViewById(R.id.icono_valoracion);
    }
    //endregion
}