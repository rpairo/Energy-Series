package tech.rpairo.energyseries.model;

import com.google.gson.annotations.SerializedName;

import tech.rpairo.energyseries.gson.GsonKeys;

/**
 * Created by Raul on 27/6/16.
 */
public class Actor {

    //region Variables
    @SerializedName(GsonKeys.FOTO_ACTOR)
    private String foto;

    @SerializedName(GsonKeys.NOMBRE_ACTOR)
    private String nombre;

    @SerializedName(GsonKeys.PERSONAJE)
    private String personaje;

    @SerializedName(GsonKeys.ID_ACTOR)
    private int id;
    //endregion

    //region Constructores
    public Actor(String foto, String nombre, String personaje, int id) {
        this.foto = foto;
        this.nombre = nombre;
        this.personaje = personaje;
        this.id = id;
    }
    //endregion

    //region Getters & Setters
    public String getFoto() {
        return "http://image.tmdb.org/t/p/original/" + this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion
}