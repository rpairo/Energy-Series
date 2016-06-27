package tech.rpairo.energyseries.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.adapters.AdapterRecyclerInfoTemporadas;
import tech.rpairo.energyseries.model.Temporada;
import tech.rpairo.energyseries.retrofit.api.ApiAdapter;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;
import tech.rpairo.energyseries.retrofit.response.ResponseTemporadas;

/**
 * Created by Raul on 27/6/16.
 */
public class FragmentInfoTemporadas extends Fragment implements Callback<ResponseTemporadas> {

    //region Variables
    protected RecyclerView recycler;
    protected RecyclerView.LayoutManager layoutManager;
    private ArrayList<Temporada> temporadas;
    private AdapterRecyclerInfoTemporadas adapterReyclerInfoTemporadas;
    protected static final int TEMPORADAS = 4;
    public static final String KEY_BUNDLE = "SERIE";
    private int serieID;
    //endregion

    //region Constructores
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null)
            this.serieID = bundle.getInt(KEY_BUNDLE);

        return inflater.inflate(R.layout.fragment_temporadas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.prepararRecycler(view, R.id.recycler_temporadas);

        this.request(this.TEMPORADAS);
    }
    //endregion

    //region RecyclerView
    protected void prepararRecycler(View view, int idRecycler) {
        this.temporadas = new ArrayList<>();

        //Obtención del RecyclerView
        this.recycler = (RecyclerView) view.findViewById(idRecycler);

        //Creación del LayoutManager del RecyclerView
        this.layoutManager = new GridLayoutManager(view.getContext(), 2);
        this.recycler.setLayoutManager(this.layoutManager);

        this.adapterReyclerInfoTemporadas = new AdapterRecyclerInfoTemporadas(this.temporadas, view.getContext());
        this.recycler.setAdapter(this.adapterReyclerInfoTemporadas);
        this.recycler.setItemAnimator(new DefaultItemAnimator());

        this.recycler.refreshDrawableState();
    }
    //endregion

    //region Retrofit
    protected void request(int request) {

        Call<ResponseTemporadas> call = null;

        switch (request) {
            case TEMPORADAS:
                call = ApiAdapter.getApiService()
                        .getSerieSearch(this.serieID, "es", ApiConstants.API_KEY);
                break;
        }

        if (call != null)
            call.clone().enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseTemporadas> call, Response<ResponseTemporadas> response) {
        this.temporadas = response.body().getTemporadas();
        this.adapterReyclerInfoTemporadas.addAllTemporadas(this.temporadas);
    }

    @Override
    public void onFailure(Call<ResponseTemporadas> call, Throwable t) {
        t.printStackTrace();
    }
    //endregion
}