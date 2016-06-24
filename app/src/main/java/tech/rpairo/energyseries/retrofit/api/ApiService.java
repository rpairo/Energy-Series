package tech.rpairo.energyseries.retrofit.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.rpairo.energyseries.retrofit.response.ResponseSeries;

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
    //endregion
}