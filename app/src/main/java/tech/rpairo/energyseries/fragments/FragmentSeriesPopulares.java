package tech.rpairo.energyseries.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.rpairo.energyseries.R;

/**
 * Created by Raul on 24/6/16.
 */
public class FragmentSeriesPopulares extends FragmentSeriesMaster {

    //region Constructores
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_series_populares, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.prepararRecycler(view, R.id.recycler_series_populares);

        this.request(this.SERIES_POPULARES);
    }
    //endregion
}