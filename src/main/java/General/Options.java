package General;

import org.json.simple.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;

public class Options<T> {
    private static JSONObject fullJSON = new JSONObject();
    public static final String FILE_PATH = "map.json";
    public static final String FILE_EMPLOYEES_JSON = "Employees.json";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.y HH:mm:ss z");
    public static final String FILE_GROUPS = "Groups.json";

    public static JSONObject getFullJSON() {
        return fullJSON;
    }

    public static void setFullJSON(JSONObject fullJSON) {
        Options.fullJSON = fullJSON;
    }

    public T copy(T t)  {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            T t2 = (T) objectInputStream.readObject();
            return t2;
        }
        catch (IOException | ClassNotFoundException ex)  {
            ex.printStackTrace();
            return (T) new Object();
        }
    }
}
