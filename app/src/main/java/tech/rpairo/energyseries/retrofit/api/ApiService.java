package tech.rpairo.energyseries.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.rpairo.energyseries.retrofit.response.ResponseActores;
import tech.rpairo.energyseries.retrofit.response.ResponseCapitulos;
import tech.rpairo.energyseries.retrofit.response.ResponseSeries;
import tech.rpairo.energyseries.retrofit.response.ResponseTemporadas;

/**
 * Created by Raul on 24/6/16.
 */
public interface ApiService {

    //region Peticiones GET

    //devuelve un lista de las peliculas populares del momento
    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_POPULAR)
    Call<ResponseSeries> getSeriesPopulares(@Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_ONAIR)
    Call<ResponseSeries> getSeriesEnEmision(@Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_TOP_RATED)
    Call<ResponseSeries> getSeriesMejorValoradas(@Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_TODAY)
    Call<ResponseSeries> getSeriesToday(@Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_TIMEZONE) String timezone, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_SEARCH_TITLE)
    Call<ResponseSeries> getSerieSearchByTitle(@Query(ApiConstants.PARAM_QUERY) String title, @Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_SEARCH)
    Call<ResponseTemporadas> getSerieSearch(@Path("id") int idSerie, @Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_CREDITS)
    Call<ResponseActores> getActoresSerie(@Path("id") int idSerie, @Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);

    @GET(ApiConstants.PATH_VERSION + ApiConstants.PATH_GET_SHOWS_EPISODES)
    Call<ResponseCapitulos> getSerieCapitulos(@Path("id") int idSerie, @Path("se") String temporada, @Query(ApiConstants.PARAM_LANGUAGE) String lenguaje, @Query(ApiConstants.PARAM_API_KEY) String apiKey);
    //endregion
}