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
public class Company {
    protected String description;
    protected String headquarters;
    protected URL homepage;
    protected int id;
    protected String logo_path;
    protected String name;
    protected Company parent_company;
    private static String key = "f5aba604e8878f03abafb36d6f3fd996";
    private static String api = "http://api.themoviedb.org/3";

    public Company(JSONObject data) throws MalformedURLException {
        description = data.getString("description");
        headquarters = data.getString("headquarters");
        homepage = new URL(data.getString("homepage"));
        id = data.getInt("id");
        logo_path = data.getString("logo_path");
        name = data.getString("name");
        parent_company = new Company(data.getJSONObject("parent_company"));
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

    public String toString() {
        return name + "\n" + description;
    }

    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj.getClass().getName().equals("Company")) {
                Company company = (Company) obj;
                return id - company.id;
            }
            else
                throw new ClassCastException();
        }
        else
            throw new NullPointerException();
    }
}
