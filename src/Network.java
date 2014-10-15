import org.json.JSONObject;

/**
 * Created by fcm2009 on 3/21/14.
 */
public class Network implements Comparable{

    protected int id;
    protected String name;

    public Network(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Network(JSONObject data) {
        this(data.getInt("id"), data.getString("name"));
    }

    public Network() {
        this(-1, null);
    }

    public String toString() {
        return name;
    }

    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj.getClass().getName().equals("Network")) {
                Network network = (Network) obj;
                return id - network.id;
            }
            else
                throw new ClassCastException();
        }
        else
            throw new NullPointerException();
    }
}
