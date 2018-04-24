
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;

public class Test {

    public static void main(String a[]){
        boolean valid = checkValidation("jonathan","1223456");
        if(valid) {
            System.out.println("valid");
        }
        else {
            System.out.println("invalid");
        }
    }
    public static Boolean checkValidation(String username, String password) {
        boolean valid = false;
        File jsonInputFile = new File("config/users.json");
        InputStream iStream;
        try {
            iStream = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(iStream);
            // Get the JsonObject structure from JsonReader.
            JsonObject usersObj = reader.readObject();
            reader.close();
            JsonArray users = usersObj.getJsonArray("users");
            for(int i = 0; i < users.size(); i++) {
                JsonObject user = users.getJsonObject(i);
                if(user.getString("username").equals(username)) {
                    if(user.getString("password").equals(password)) {
                        valid = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return valid;
    }
}
