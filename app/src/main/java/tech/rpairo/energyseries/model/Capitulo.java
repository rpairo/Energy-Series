package tech.rpairo.energyseries.model;

import com.google.gson.annotations.SerializedName;

import tech.rpairo.energyseries.gson.GsonKeys;
import tech.rpairo.energyseries.retrofit.api.ApiConstants;

/**
 * Created by Raul on 27/6/16.
 */
public class Capitulo {

    //region Variables
    @SerializedName(GsonKeys.FECHA_ESTRENO)
    private String date;

    @SerializedName(GsonKeys.BACKDROP_CAPITULO)
    private String imagen;

    @SerializedName(GsonKeys.TITULO_CAPITULO)
    private String nombre;

    @SerializedName(GsonKeys.VALORACION)
    private String valoracion;

    @SerializedName(GsonKeys.DESCRIPCION)
    private String descripcion;
    //endregion

    //region Getters & Setters
    public String getImagen() {
        return ApiConstants.PATH_GET_IMAGES +
                ApiConstants.PATH_SIZE_POSTER +
                imagen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //endregion
}