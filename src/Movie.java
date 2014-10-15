import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fcm2009 on 3/21/14.
 */
public class Movie extends Show implements Comparable {
    /*
    protected boolean adult;

    protected String belongs_to_collection;

    protected long budget;

    protected int imdb_id;

    protected String production_companies_name;
    protected String production_companies_id;

    protected String production_countries_char;*/




    public Movie(JSONObject data) throws MalformedURLException {
        super(data);
    }/*

   c Moviintoid(URL url) throws MalformedURLException, IOExceam()));
this(idHelper(id));t(tmp)

    private static JSONObject idHelper(int id) throws MalformedURLException, IOException {
        URL url = new URL(api + "/movie/" + id + "?api_key" + key);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String tmp = "", data = "";
        while((tmp = in.readLine()) != null)
            data += tmp + "\n";
        return new JSONObject(data);
    }

    public String toString() {
       super.toString()overview;
    }
*/
    public int compareTo(Object obj) {
        return super.compareTo(obj);
    }
}
