import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fcm2009 on 3/21/14.
 */
public class Authentication {
    protected String expires_at;
    protected String request_token;
    protected String session_id;
    protected boolean success;

    private static String key = "f5aba604e8878f03abafb36d6f3fd996";
    private static String api = "http://api.themoviedb.org/3";

    public Authentication() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/authentication/token/new?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        expires_at = data.getString("expires_at");
        request_token = data.getString("request_token");
        success = data.getBoolean("success");

        in = new BufferedReader(new InputStreamReader(new URL(api + "/authentication/session/new?api_key=" + key + "&request_token=" + request_token).openStream()));
        str = "";
        while((tmp = in.readLine()) != null)
            str += tmp;
        data = new JSONObject(str);
        session_id = data.getString("session_id");
    }

    public static String session_id() throws MalformedURLException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(api + "/authentication/token/new?api_key=" + key).openStream()));
        String str = "", tmp = "";
        while((tmp = in.readLine()) != null)
            str += tmp + "\n";
        JSONObject data = new JSONObject(str);
        String token = data.getString("request_token");
        in = new BufferedReader(new InputStreamReader(new URL(api + "/authentication/session/new?api_key=" + key + "&request_token=" + str).openStream()));
        str = "";
        while((tmp = in.readLine()) != null)
            str += tmp;
        data = new JSONObject(str);
        return data.getString("session_id");
    }
}
