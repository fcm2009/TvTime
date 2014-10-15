import org.json.JSONObject;

/**
 * Created by fcm2009 on 3/22/14.
 */
public class Image implements Comparable {

    String file_path;
    int width;
    int height;
    double aspect_ratio;

    public Image(JSONObject data) {
        file_path = data.getString("file_path");
        width = data.getInt("width");
        height = data.getInt("height");
        aspect_ratio = data.getDouble("aspect_ratio");
    }

    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj.getClass().getName().equals("Image")) {
                Image img = (Image) obj;
                return file_path.compareTo(img.file_path);
            }
            else
                throw new ClassCastException();
        }
        else throw new NullPointerException();
    }
}
