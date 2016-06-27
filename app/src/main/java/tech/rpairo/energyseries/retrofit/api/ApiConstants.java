package tech.rpairo.energyseries.retrofit.api;

/**
 * Created by Raul on 24/6/16.
 */
public class ApiConstants {

    //region Constantes
    public static final String API_KEY = "452cce8f527688dcda20118eaa3d672e";
    public static final String URL_BASE = "http://api.themoviedb.org";

    public static final String PATH_VERSION = "/3";
    public static final String PATH_LANGUAGE = "es";

    public static final String PATH_GET_IMAGES = "http://image.tmdb.org/t/p";
    public static final String PATH_SIZE_POSTER = "/original";

    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_LANGUAGE = "language";
    public static final String PARAM_QUERY = "query";
    public static final String PARAM_TIMEZONE = "timezone";

    public static final String PATH_GET_SHOWS_SEARCH = "/tv/{id}";
    public static final String PATH_GET_SHOWS_SEARCH_TITLE = "/search/tv";
    public static final String PATH_GET_SHOWS_ONAIR = "/tv/on_the_air";
    public static final String PATH_GET_SHOWS_TODAY = "/tv/airing_today";
    public static final String PATH_GET_SHOWS_POPULAR = "/tv/popular";
    public static final String PATH_GET_SHOWS_TOP_RATED = "/tv/top_rated";
    public static final String PATH_GET_SHOWS_CREDITS = "/tv/{id}/credits";
    //endregion
}