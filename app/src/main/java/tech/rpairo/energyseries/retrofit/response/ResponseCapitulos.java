package tech.rpairo.energyseries.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.model.Capitulo;

/**
 * Created by Raul on 27/6/16.
 */
public class ResponseCapitulos {

    //region Variables
    @SerializedName(GsonKeys.CAPITULOS)
    private ArrayList<Capitulo> capitulos;
    //endregion

    //region Getters & Setters
    public ArrayList<Capitulo> getCapitulos() {
        return this.capitulos;
    }
    //endregion
}