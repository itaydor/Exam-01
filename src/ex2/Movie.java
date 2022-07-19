package ex2;

import java.util.*;
import java.util.stream.Collectors;

public class Movie {
    
    private final Boolean adult;
    private final String backdrop_path;
    private final List<Integer> genre_ids;
    private final Integer id;
    private final String original_language;
    private final String original_title;
    private final String overview;
    private final String poster_path;
    private final String release_date;
    private final String title;
    private final Boolean video;
    private final Float vote_average;
    private final Float vote_count;
    private final Float popularity;

    public Movie(String movieResult) {
        Map<String, String> mapOfProperties = stringToMap(movieResult);
        adult = mapOfProperties.get("adult") != null ? mapOfProperties.get("adult").equals("true") : null;
        backdrop_path = mapOfProperties.get("backdrop_path");
        genre_ids = stringToGenreIds(mapOfProperties.get("genre_ids"));
        id = mapOfProperties.get("id") != null ? Integer.parseInt(mapOfProperties.get("id")) : null;
        original_language = mapOfProperties.get("original_language");
        original_title = mapOfProperties.get("original_title");
        overview = mapOfProperties.get("overview");
        poster_path = mapOfProperties.get("poster_path");
        release_date = mapOfProperties.get("release_date");
        title = mapOfProperties.get("title");
        video = mapOfProperties.get("video") != null ? mapOfProperties.get("video").equals("true") : null;
        vote_average = mapOfProperties.get("vote_average") != null ? Float.parseFloat(mapOfProperties.get("vote_average")) : 0;
        vote_count = mapOfProperties.get("vote_count") != null ? Float.parseFloat(mapOfProperties.get("vote_count")) : 0;
        popularity = mapOfProperties.get("popularity") != null ? Float.parseFloat(mapOfProperties.get("popularity")) : 0;
    }

    /**
     * Convert the property genre_ids from string to list
     * @param genre_ids
     * @return List of ids.
     */
    private List<Integer> stringToGenreIds(String genre_ids) {
        if(genre_ids == null)
            return null;
        String[] ids = genre_ids.replace("[", "").replace("]", "").split(",");
        return Arrays.stream(ids)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * Build map of properties
     * @param movieResult movieResult represented by String
     * @return map of key - property, value for each property.
     */
    private Map<String, String> stringToMap(String movieResult) {
        Map<String, String> mapOfProperties = new HashMap<>();
        String[] properties = movieResult.split(",\"");
        for (String property : properties) {
            String[] keyValuePair = property.split(":");
            String key = keyValuePair[0].replace("\"", "");
            String value = key.equals("overview") ? keyValuePair[1] : keyValuePair[1].replace("\"", "");
            if(value.equals("null")){
                value = null;
            }
            mapOfProperties.put(key, value);
        }
        return mapOfProperties;
    }

    //Getters:

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public float getVote_count() {
        return vote_count;
    }

    public float getPopularity() {
        return popularity;
    }
}
