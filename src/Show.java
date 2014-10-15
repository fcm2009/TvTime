import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fcm2009 on 3/21/14.
 */
public abstract class Show {



    protected String backdrop_path;

    protected Genre[] genres;

    protected String homepage;

    protected int id;

    protected String original_name;


    protected String overview;

    protected double popularity;

    protected String poster_path;

    protected String status;

    protected double vote_average;

    protected int vote_count;

    protected static String key = "f5aba604e8878f03abafb36d6f3fd996";
    protected static String api = "http://api.themoviedb.org/3";

    public Show(JSONObject data) throws MalformedURLException {
        JSONArray tmp;

        backdrop_path = data.getString("backdrop_path");


        tmp = data.getJSONArray("genres");
        genres = new Genre[tmp.length()];
        for(int i = 0; i < tmp.length(); i++) {
            genres[i] = new Genre(tmp.getJSONObject(i));
        }

        homepage = data.getString("homepage");

        id = data.getInt("id");

        original_name = data.getString("original_name");

        overview = data.getString("overview");

        popularity = data.getDouble("popularity");

        poster_path = data.getString("poster_path");

        status = data.getString("status");

        vote_average = data.getDouble("vote_average");

        vote_count = data.getInt("vote_count");
    }

    public String toString() {
        return original_name + "\n" + overview;
    }

    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj.getClass().getName().equals("Show")) {
                Show show = (Show) obj;
                return id - show.id;
            }
            else
                throw new ClassCastException();
        }
        else
            throw new NullPointerException();
    }
}
