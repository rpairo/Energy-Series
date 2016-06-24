package tech.rpairo.energyseries.model;

import com.energysistem.uitest.tvshow.TvShow;
import com.google.gson.annotations.SerializedName;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;

/**
 * Created by Raul on 24/6/16.
 */
public class Serie extends TvShow {

    //region Variables
    @SerializedName(GsonKeys.TITULO_SERIE)
    private String title;
    @SerializedName(GsonKeys.DESCRIPCION)
    private String descripcion;
    @SerializedName(GsonKeys.POSTER)
    private String poster;
    @SerializedName(GsonKeys.BACKDROP)
    private String fanArt;
    @SerializedName(GsonKeys.ID)
    private int id;
    //endregion

    //region Constructores
    public Serie(String title, String poster, String fanArt, int numberOfSeasons) {
        super(title, poster, fanArt, numberOfSeasons);
    }
    //endregion

    //region Getters & Setters
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getPoster() {
        return ApiConstants.PATH_GET_IMAGES +
                ApiConstants.PATH_SIZE_POSTER +
                this.poster;
    }

    @Override
    public String getFanArt() {
        return ApiConstants.PATH_GET_IMAGES +
                ApiConstants.PATH_SIZE_POSTER +
                this.fanArt;
    }
    //endregion
}