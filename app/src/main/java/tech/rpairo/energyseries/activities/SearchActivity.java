package tech.rpairo.energyseries.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.rpairo.energyseries.R;
import tech.rpairo.energyseries.adapters.AdapterRecyclerSearchResults;
import tech.rpairo.energyseries.model.Serie;
import tech.rpairo.energyseries.retrofit.api.ApiAdapter;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;
import tech.rpairo.energyseries.retrofit.response.ResponseSeries;

/**
 * Created by Raul on 24/6/16.
 */
public class SearchActivity extends AppCompatActivity implements Callback<ResponseSeries> {

    //region Variables
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterRecyclerSearchResults adapterRecyclerSearchResults;
    private ArrayList<Serie> series;
    private FloatingSearchView searchView;
    private final static int VOICE_SEARCH_SERIE = 1;
    //endregion

    //region Constructores
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        this.prepararRecycler(R.id.recycler_series_search);
        this.searchView = (FloatingSearchView) findViewById(R.id.floating_search_view);

        this.prepararSugerencias();
    }
    //endregion

    //region Funciones Auxiliares
    private void prepararSugerencias() {

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    searchView.clearSuggestions();
                } else {
                    if (!newQuery.equals("")) {
                        request(newQuery);
                        searchView.swapSuggestions(series);
                    }
                }
            }
        });

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                ArrayList<Serie> aux = new ArrayList<Serie>();
                aux.add((Serie) searchSuggestion);
                adapterRecyclerSearchResults.addAll(aux);
            }

            @Override
            public void onSearchAction(String currentQuery) {
                if (!currentQuery.equals(""))
                    request(currentQuery);
            }
        });

        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                searchView.clearQuery();
            }

            @Override
            public void onFocusCleared() {
            }
        });

        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.item_busqueda_voz)
                    preguntar();
            }
        });
    }

    private void preguntar() {

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_content);

        try {
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-ES");
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
            i.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, true);
            startActivityForResult(i, VOICE_SEARCH_SERIE);
        } catch (ActivityNotFoundException e) {
            Snackbar.make(coordinatorLayout, R.string.snackbar_search_voz, Snackbar.LENGTH_LONG).show();
        }
    }
    //endregion

    //region RecyclerView
    protected void prepararRecycler(int idRecycler) {

        this.series = new ArrayList<Serie>();

        //Obtención del RecyclerView
        this.recycler = (RecyclerView) findViewById(idRecycler);

        //Creación del LayoutManager del RecyclerView
        this.layoutManager = new LinearLayoutManager(this);
        this.recycler.setLayoutManager(this.layoutManager);

        this.adapterRecyclerSearchResults = new AdapterRecyclerSearchResults(this.series, this);
        this.recycler.setAdapter(this.adapterRecyclerSearchResults);
        this.recycler.setItemAnimator(new DefaultItemAnimator());

        this.recycler.refreshDrawableState();
    }
    //endregion

    //region Retrofit
    protected void request(String text) {

        Call<ResponseSeries> call;

        call = ApiAdapter.getApiService()
                .getSerieSearchByTitle(text, "es", ApiConstants.API_KEY);

        if (call != null)
            call.clone().enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseSeries> call, Response<ResponseSeries> response) {

        if (response.body() != null) {
            this.series = response.body().getSeries();
            this.adapterRecyclerSearchResults.addAll(this.series);
        }
    }

    @Override
    public void onFailure(Call<ResponseSeries> call, Throwable t) {
        t.printStackTrace();
    }
    //endregion

    //region Eventos del Activity
    @Override
    public void onBackPressed() {
        if (!searchView.setSearchFocused(false)) {
            super.onBackPressed();
        }
    }
    //endregion

    //region OnActivityResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<String> frasesReconocidas = null;

        switch (requestCode) {
            case VOICE_SEARCH_SERIE:
                if (resultCode == RESULT_OK)
                    frasesReconocidas = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                switch (resultCode) {
                    case RESULT_OK:
                        this.request(frasesReconocidas.get(0));
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
        }
    }
    //endregion
}