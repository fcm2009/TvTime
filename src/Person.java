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
public class Person implements Comparable {

    protected boolean adult;
    protected Person[] also_known_as;
    protected String biography;
    protected String birthday;
    protected String deathday;
    protected URL homepage;
    protected int id;
    protected String name;
    protected String place_of_birth;
    protected String profile_path;

    private static String key = "f5aba604e8878f03abafb36d6f3fd996";
    private static String api = "http://api.themoviedb.org/3";

    public Person(int id) throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/person/" + id + "?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);

        adult = data.getBoolean("adult");

        JSONArray arr = data.getJSONArray("also_known_as");
        also_known_as = new Person[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            also_known_as[i] = new Person(arr.getJSONObject(i));
        }

        biography = data.getString("biography");
        birthday = data.getString("birthday");
        deathday = data.getString("deathday");
        homepage = new URL(data.getString("homepage"));
        id = data.getInt("id");
        name = data.getString("name");
        place_of_birth = data.getString("place_of_birth");
        profile_path = data.getString("profile_path");

    }

    public Person(JSONObject data) throws MalformedURLException {
        adult = data.getBoolean("adult");

        JSONArray arr = data.getJSONArray("also_known_as");
        also_known_as = new Person[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            also_known_as[i] = new Person(arr.getJSONObject(i));
        }

        biography = data.getString("biography");
        birthday = data.getString("birthday");
        deathday = data.getString("deathday");
        homepage = new URL(data.getString("homepage"));
        id = data.getInt("id");
        name = data.getString("name");
        place_of_birth = data.getString("place_of_birth");
        profile_path = data.getString("profile_path");

    }

    public Movie[] getMoviesList() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/person/" + id + "/movie_credits?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        JSONArray arr = new JSONArray("cast");
        Movie[] moviesList = new Movie[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            moviesList[i] = new Movie(arr.getJSONObject(i));
        }
        return moviesList;
    }

    public Tv[] getTvList() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/person/" + id + "/tv_credits?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        JSONArray arr = new JSONArray("cast");
        Tv[] tvList = new Tv[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            tvList[i] = new Tv(arr.getJSONObject(i));
        }
        return tvList;
    }

    public Image[] getImages() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/person/" + id + "/images?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        JSONArray arr = new JSONArray("profiles");
        Image[] img = new Image[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            img[i] = new Image(arr.getJSONObject(i));
        }
        return img;
    }

    public static Person[] getPopular() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/person/popular?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        JSONArray arr = new JSONArray("results");
        Person[] prsn = new Person[arr.length()];
        for(int i = 0; i < arr.length(); i++) {
            prsn[i] = new Person(arr.getJSONObject(i));
        }
        return prsn;
    }

    public static Person getLatest() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/person/latest?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        return new Person(data);
    }

    public String toString() {
        return name + "\n" + biography;
    }

    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj.getClass().getName().equals("Person")) {
                Person prsn = (Person) obj;
                return id - prsn.id;
            }
            else
                throw new ClassCastException();
        }
        else throw new NullPointerException();
    }
}
