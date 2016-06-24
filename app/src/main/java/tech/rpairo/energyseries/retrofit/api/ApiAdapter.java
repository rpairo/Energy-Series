package tech.rpairo.energyseries.retrofit.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raul on 24/6/16.
 */
public class ApiAdapter {

    //region Variables
    private static ApiService apiService;
    //endregion

    //region Funciones de la Api
    public static ApiService getApiService() {

        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.URL_BASE)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }
    //endregion
}