package tech.rpairo.energyseries.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.model.Serie;

/**
 * Created by Raul on 24/6/16.
 */
public class ResponseSeries {

    //region Variables
    @SerializedName(GsonKeys.RESULTS)
    private ArrayList<Serie> series;
    //endregion

    //region Getters & Setters
    public ArrayList<Serie> getSeries() {
        return this.series;
    }
    //endregion
}