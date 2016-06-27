package tech.rpairo.energyseries.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.model.Capitulo;

/**
 * Created by Raul on 27/6/16.
 */
public class AdapterRecyclerInfoCapitulos extends RecyclerView.Adapter<ViewHolderCapitulos> {

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
    public ViewHolderCapitulos onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_capitulo, viewGroup, false);

        return new ViewHolderCapitulos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderCapitulos viewHolderCapitulos, final int i) {

        Glide.with(this.context)
                .load(this.capitulos.get(i).getImagen())
                .into(viewHolderCapitulos.backdrop);

        Glide.with(this.context)
                .load(R.drawable.ic_star_white_24dp)
                .into(viewHolderCapitulos.icono);


        try {
            viewHolderCapitulos.titulo.setText(this.capitulos.get(i).getNombre());
            viewHolderCapitulos.valoracion.setText(this.capitulos.get(i).getValoracion().substring(0, 3));
            viewHolderCapitulos.fecha.setText(formateDateFromstring("yyyy-MM-dd", "dd/MM/yyyy", this.capitulos.get(i).getDate()));
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return this.capitulos.size();
    }
    //endregion

    //region Funciones auxiliares
    private String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);
        } catch (ParseException e) {
        }

        return outputDate;
    }

    public void addAll(ArrayList<Capitulo> capitulos) {
        if (capitulos == null)
            throw new NullPointerException("No puedes pasar una lista nula");

        this.capitulos = new ArrayList<>();

        this.capitulos.addAll(capitulos);
        this.notifyDataSetChanged();
    }
    //endregion
}