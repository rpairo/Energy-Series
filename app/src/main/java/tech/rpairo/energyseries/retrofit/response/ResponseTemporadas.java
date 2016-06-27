package tech.rpairo.energyseries.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.model.Temporada;

/**
 * Created by Raul on 27/6/16.
 */
public class ResponseTemporadas {

    //region Variables
    @SerializedName(GsonKeys.TEMPORADAS)
    private ArrayList<Temporada> temporadas;
    //endregion

    //region Getters & Setters
    public ArrayList<Temporada> getTemporadas() {
        return this.temporadas;
    }
    //endregion
}