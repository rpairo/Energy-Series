package tech.rpairo.energyseries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;

/**
 * Created by Raul on 27/6/16.
 */
public class Temporada implements Parcelable{

    //region Variables
    @SerializedName(GsonKeys.ID)
    private int id;
    @SerializedName(GsonKeys.NUMERO_TEMPORADA)
    private int numberSeason;
    @SerializedName(GsonKeys.POSTER)
    private String poster;
    @SerializedName(GsonKeys.FECHA_ESTRENO)
    private String fechaEstreno;
    //endregion

    //region Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberSeason() {

        return "" + (this.numberSeason);
    }

    public void setNumberSeason(int numberSeason) {
        this.numberSeason = numberSeason;
    }

    public String getPoster() {
        return ApiConstants.PATH_GET_IMAGES +
                ApiConstants.PATH_SIZE_POSTER +
                this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
    //endregion

    //region Parcelable
    protected Temporada(Parcel in) {
        id = in.readInt();
        numberSeason = in.readInt();
        poster = in.readString();
        fechaEstreno = in.readString();
    }

    public static final Creator<Temporada> CREATOR = new Creator<Temporada>() {
        @Override
        public Temporada createFromParcel(Parcel in) {
            return new Temporada(in);
        }

        @Override
        public Temporada[] newArray(int size) {
            return new Temporada[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(numberSeason);
        parcel.writeString(poster);
        parcel.writeString(fechaEstreno);
    }
    //endregion
}