package tech.rpairo.energyseries.model;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.energysistem.uitest.tvshow.TvShow;
import com.google.gson.annotations.SerializedName;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;

/**
 * Created by Raul on 24/6/16.
 */
public class Serie extends TvShow implements SearchSuggestion {

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
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion

    //region SearchSuggestion
    @Override
    public String getBody() {
        return this.title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.descripcion);
        parcel.writeString(this.poster);
        parcel.writeString(this.fanArt);
        parcel.writeInt(this.id);
    }

    public static final Creator<Serie> CREATOR = new Creator<Serie>() {
        @Override
        public Serie createFromParcel(Parcel in) {
            return new Serie(in);
        }

        @Override
        public Serie[] newArray(int size) {
            return new Serie[size];
        }
    };

    public Serie(Parcel source) {
        super("", "", "", 0);

        this.title = source.readString();
        this.descripcion = source.readString();
        this.poster = source.readString();
        this.fanArt = source.readString();
        this.id = source.readInt();
    }
    //endregion
}