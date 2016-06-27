package tech.rpairo.energyseries.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.model.Actor;

/**
 * Created by Raul on 27/6/16.
 */
public class ResponseActores {

    //region Variables
    @SerializedName(GsonKeys.CAST)
    private ArrayList<Actor> actores;
    //endregion

    //region Funciones auxiliares
    public ArrayList<Actor> getActores() {
        return this.actores;
    }
    //endregion
}