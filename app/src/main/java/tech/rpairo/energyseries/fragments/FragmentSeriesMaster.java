package tech.rpairo.energyseries.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.rpairo.energyseries.adapters.AdapterRecyclerSeries;
import tech.rpairo.energyseries.model.Serie;
import tech.rpairo.energyseries.retrofit.api.ApiAdapter;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;
import tech.rpairo.energyseries.retrofit.response.ResponseSeries;

/**
 * Created by Raul on 24/6/16.
 */
public class FragmentSeriesMaster extends Fragment implements Callback<ResponseSeries> {

    //region Variables
    protected RecyclerView recycler;
    protected RecyclerView.LayoutManager layoutManager;
    protected AdapterRecyclerSeries adapterRecyclerSeries;
    protected ArrayList<Serie> series;

    protected static final int SERIES_POPULARES = 1;
    protected static final int SERIES_EN_EMISION = 2;
    protected static final int SERIES_MEJOR_VALORADAS = 3;
    protected static final int SERIES_TODAY = 4;
    //endregion

    //region RecyclerView
    protected void prepararRecycler(View view, int idRecycler) {

        this.series = new ArrayList<>();

        //Obtención del RecyclerView
        this.recycler = (RecyclerView) view.findViewById(idRecycler);

        //Creación del LayoutManager del RecyclerView
        this.layoutManager = new GridLayoutManager(view.getContext(), 2);
        this.recycler.setLayoutManager(this.layoutManager);

        this.adapterRecyclerSeries = new AdapterRecyclerSeries(this.series, view.getContext());
        this.recycler.setAdapter(this.adapterRecyclerSeries);
        this.recycler.setItemAnimator(new DefaultItemAnimator());

        this.recycler.refreshDrawableState();
    }
    //endregion

    //region Retrofit
    protected void request(int request) {

        Call<ResponseSeries> call = null;

        switch (request) {
            case SERIES_POPULARES:
                call = ApiAdapter.getApiService()
                        .getSeriesPopulares("es", ApiConstants.API_KEY);
                break;
            case SERIES_EN_EMISION:
                call = ApiAdapter.getApiService()
                        .getSeriesEnEmision("es", ApiConstants.API_KEY);
                break;
            case SERIES_MEJOR_VALORADAS:
                call = ApiAdapter.getApiService()
                        .getSeriesMejorValoradas("es", ApiConstants.API_KEY);
                break;
            case SERIES_TODAY:
                call = ApiAdapter.getApiService()
                        .getSeriesToday("es", "ES", ApiConstants.API_KEY);
                break;
        }

        if (call != null)
            call.clone().enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseSeries> call, Response<ResponseSeries> response) {
        this.series = response.body().getSeries();
        this.adapterRecyclerSeries.addAll(this.series);
    }

    @Override
    public void onFailure(Call<ResponseSeries> call, Throwable t) {
        t.printStackTrace();
    }
    //endregion
}