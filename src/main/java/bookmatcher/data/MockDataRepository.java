package bookmatcher.data;
import java.lang.StringBuilder;
import org.json.*;

public class MockDataRepository implements UserRepository{
    String userJSONBlob = "";

    public MockDataRepository(){
        buildUserString();
    }

    private void buildUserString(){
        this.userJSONBlob =      "{\n"
                        +   "\"username\":\"Testerino\",\n"
                        +   "\"books\":[\"Dune\",\"Flowers for Algernon\",\"Watership Down\"]"
                        +   "}";

    }

    public String getUserJSONBlob(){
        return this.userJSONBlob;
    }

    public String getUsername(){
        JSONObject json = new JSONObject(this.userJSONBlob);
        return json.getString("username");
    }
}