import com.sun.org.omg.SendingContext.CodeBasePackage.URLHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by fcm2009 on 3/20/14.
 */
public class Tv extends Show implements Comparable {


    protected int created_by_id;
    protected String created_by_name;
    protected String profile_path;

    protected String episode_run_time;

    protected String first_air_date;

    protected boolean in_production;

    protected String languages;

    protected String last_air_date;

    protected String name;

    protected int network_id;
    protected String network_name;

    protected int number_of_episodes;

    protected int number_of_seasons;

    protected String origin_country;

    protected String overview;

    protected Season[] season;

    public Tv(JSONObject data) throws MalformedURLException {
        super(data);
        JSONArray tmp;

        backdrop_path = data.getString("backdrop_path");

        tmp = data.getJSONArray("created_by");
        for(int i = 0; i < tmp.length(); i++) {
            created_by_id += tmp.getJSONObject(i).getInt("id");
            created_by_name += tmp.getJSONObject(i).getString("name");
            profile_path += tmp.getJSONObject(i).getString("profile_path");
            if(i != tmp.length() - 1) {
                network_name += ", ";
            }
        }

        tmp = data.getJSONArray("episode_run_time");
        episode_run_time = tmp.getInt(0) + ":" + tmp.getInt(1);

        first_air_date = data.getString("first_air_date");

        in_production = data.getBoolean("in_production");

        tmp = data.getJSONArray("languages");
        for(int i = 0; i < tmp.length(); i++) {
            languages += tmp.getString(i);
            if(i != tmp.length() - 1)
                languages += ", ";
        }

        last_air_date = data.getString("last_air_date");

        name = data.getString("name");

        tmp = data.getJSONArray("networks");
        for(int i = 0; i < tmp.length(); i++) {
            network_id += tmp.getJSONObject(i).getInt("id");
            network_name += tmp.getJSONObject(i).getString("name");
            if(i != tmp.length() - 1) {
                network_name += ", ";
            }
        }

        number_of_episodes = data.getInt("number_of_episodes");

        number_of_seasons = data.getInt("number_of_seasons");

        tmp = data.getJSONArray("origin_country");
        for(int i = 0; i < tmp.length(); i++) {
            origin_country = tmp.getString(i);
            if(i != tmp.length() - 1)
                languages += ", ";
        }

        tmp = data.getJSONArray("seasons");
        season = new Season[tmp.length()];
        for(int i = 0; i < season.length; i++) {
            season[i] = new Season(tmp.getJSONObject(0).getString("air_date"), tmp.getJSONObject(1).getString("poster_path"), tmp.getJSONObject(2).getInt("season_number"));
        }
    }

    public Tv(int id) throws MalformedURLException, IOException {
        this(idHelper(id));
    }

    private static JSONObject idHelper(int id) throws MalformedURLException, IOException {
            URL url = new URL(api + "/tv/" + id + "?api_key" + key);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String tmp = "", data = "";
            while((tmp = in.readLine()) != null)
                data += tmp + "\n";
            return new JSONObject(data);
    }

    public String toString() {
        return super.toString() + first_air_date;
    }

    public int compareTo(Object obj) {
        return super.compareTo(obj);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Season {
        int season_number;
        String air_date;
        String poster_path;

        public Season(String air_date, String poster_path, int season_number) {
            this.season_number = season_number;
            this.air_date = air_date;
            this.poster_path = poster_path;
        }

        public Season(int season_number) {
            this(null, null, season_number);
        }

        public Season() {
            this(null, null, -1);
        }

        public String toString() {
            return "Season: " + season_number + "\nAir Date:    " + air_date;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}