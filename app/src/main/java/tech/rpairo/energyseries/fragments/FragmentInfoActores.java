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
import tech.rpairo.energyseries.adapters.AdapterRecyclerInfoActores;
import tech.rpairo.energyseries.model.Actor;
import tech.rpairo.energyseries.retrofit.api.ApiAdapter;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;
import tech.rpairo.energyseries.retrofit.response.ResponseActores;

/**
 * Created by Raul on 27/6/16.
 */
public class FragmentInfoActores extends Fragment implements Callback<ResponseActores> {

    //region Variables
    protected RecyclerView recycler;
    protected RecyclerView.LayoutManager layoutManager;
    private ArrayList<Actor> actores;
    private AdapterRecyclerInfoActores adapterRecyclerInfoActores;
    protected static final int ACTORES = 5;
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

        return inflater.inflate(R.layout.fragment_actores, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.prepararRecycler(view, R.id.recycler_actores);

        this.request(this.ACTORES);
    }
    //endregion

    //region RecyclerView
    protected void prepararRecycler(View view, int idRecycler) {
        this.actores = new ArrayList<>();

        //Obtención del RecyclerView
        this.recycler = (RecyclerView) view.findViewById(idRecycler);

        //Creación del LayoutManager del RecyclerView
        this.layoutManager = new GridLayoutManager(view.getContext(), 2);
        this.recycler.setLayoutManager(this.layoutManager);

        this.adapterRecyclerInfoActores = new AdapterRecyclerInfoActores(this.actores, view.getContext());
        this.recycler.setAdapter(this.adapterRecyclerInfoActores);
        this.recycler.setItemAnimator(new DefaultItemAnimator());

        this.recycler.refreshDrawableState();
    }
    //endregion

    //region Retrofit
    protected void request(int request) {

        Call<ResponseActores> call = null;

        switch (request) {
            case ACTORES:
                call = ApiAdapter.getApiService()
                        .getActoresSerie(this.serieID, "es", ApiConstants.API_KEY);
                break;
        }

        if (call != null)
            call.clone().enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseActores> call, Response<ResponseActores> response) {
        this.actores = response.body().getActores();
        this.adapterRecyclerInfoActores.addAllActores(this.actores);
    }

    @Override
    public void onFailure(Call<ResponseActores> call, Throwable t) {
        t.printStackTrace();
    }
    //endregion
}