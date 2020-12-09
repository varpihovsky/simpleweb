package webmanager.properties;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private final Properties properties = new Properties();
    String path;
    private FileReader reader;

    public PropertyManager(ServletContext context) {
        path = context.getRealPath("/properties/");

        try {
            reader = new FileReader(path + "/db.properties");
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        if (key.equals("DatabaseInitialize") && properties.getProperty("DatabaseInitialize").equals("yes")) {
            properties.setProperty("DatabaseInitialize", "no");
            return "yes";
        }
        return properties.getProperty(key);
    }

    public void setPropertyFile(String name) {
        try {
            if (reader.equals(new FileReader(path + "/" + name)))
                return;

            reader = new FileReader(path + "/" + name);
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
