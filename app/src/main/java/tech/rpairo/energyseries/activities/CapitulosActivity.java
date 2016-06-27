package tech.rpairo.energyseries.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.adapters.AdapterRecyclerInfoCapitulos;
import tech.rpairo.energyseries.model.Capitulo;
import tech.rpairo.energyseries.retrofit.api.ApiAdapter;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;
import tech.rpairo.energyseries.retrofit.response.ResponseCapitulos;

/**
 * Created by Raul on 27/6/16.
 */
public class CapitulosActivity extends AppCompatActivity implements Callback<ResponseCapitulos> {

    public static final String ID_BUNDLE = "ID";
    public static final String NUM_TEMPORADA = "TEMPORADA";
    private ArrayList<Capitulo> capitulos;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterRecyclerInfoCapitulos adapterRecyclerInfoCapitulos;
    private final static int CAPITULOS = 1;
    private int idTemporada;
    private String numTemporada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulos);

        this.idTemporada = getIntent().getIntExtra(ID_BUNDLE, 1);
        this.numTemporada = getIntent().getStringExtra(NUM_TEMPORADA);

        Log.d("Capitulos", "ID temporada: " + idTemporada);
        Log.d("Capitulos", "Temporada: " + numTemporada);

        this.prepararRecycler(R.id.recycler_capitulos);
    }

    //region RecyclerView
    protected void prepararRecycler(int idRecycler) {

        this.capitulos = new ArrayList<>();

        //Obtención del RecyclerView
        this.recycler = (RecyclerView) findViewById(idRecycler);

        //Creación del LayoutManager del RecyclerView
        this.layoutManager = new LinearLayoutManager(this);
        this.recycler.setLayoutManager(this.layoutManager);

        this.adapterRecyclerInfoCapitulos = new AdapterRecyclerInfoCapitulos(this.capitulos, this);
        this.recycler.setAdapter(this.adapterRecyclerInfoCapitulos);
        this.recycler.setItemAnimator(new DefaultItemAnimator());

        this.recycler.refreshDrawableState();

        this.request(CAPITULOS);
    }
    //endregion

    //region Retrofit
    private void request(int request) {

        Call<ResponseCapitulos> call = null;

        switch (request) {
            case CAPITULOS:
                call = ApiAdapter.getApiService()
                        .getSerieCapitulos(this.idTemporada, this.numTemporada, "es", ApiConstants.API_KEY);
                break;
        }

        if (call != null)
            call.clone().enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseCapitulos> call, Response<ResponseCapitulos> response) {

        if (response.body() != null) {

            this.capitulos = response.body().getCapitulos();

            this.adapterRecyclerInfoCapitulos.addAll(this.capitulos);
        }
    }

    @Override
    public void onFailure(Call<ResponseCapitulos> call, Throwable t) {
        t.printStackTrace();
    }
    //endregion
}