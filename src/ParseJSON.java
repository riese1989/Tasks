import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseJSON {
    public static JSONObject getJSON() throws IOException, ParseException {
        if (!Main.searchFile(Main.filePath)) {
            return new JSONObject();
        }
        FileReader reader = new FileReader(Main.filePath);
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(reader);
    }
}
