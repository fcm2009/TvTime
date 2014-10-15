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
public class Genre {

    protected int id;
    protected String name;
    private static String key = "f5aba604e8878f03abafb36d6f3fd996";
    private static String api = "http://api.themoviedb.org/3";

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(JSONObject data) throws MalformedURLException {
        this(data.getInt("id"), data.getString("name"));
    }

    public Genre(String name) throws MalformedURLException, IOException {
        this(nameHelper(name), name);
    }

    public Genre() {
        this(-1, null);
    }

    public Movie[] getMoviesList() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/genre/" + id + "/movies?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        JSONArray arr = data.getJSONArray("results");
        Movie movie[] = new Movie[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            //movie[i] = new Movie(arr.getJSONObject(i).getInt("id"));
        }
        return movie;
    }

    private static int nameHelper(String name) throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/genre/list?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        JSONArray arr = data.getJSONArray("genres");
        for(int i = 0; i < arr.length(); i++) {
            if(arr.getJSONObject(i).getString("name").equals(name))
                return arr.getJSONObject(i).getInt("id");
        }
        return -1;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj.getClass().getName().equals("Genre")) {
                Genre genre = (Genre) obj;
                return id - genre.id;
            }
            else
                throw new ClassCastException();
        }
        else
            throw new NullPointerException();
    }
}
