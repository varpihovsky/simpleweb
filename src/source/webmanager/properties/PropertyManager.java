package webmanager.properties;

import webmanager.Controller;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private final Properties properties = new Properties();
    String path;
    private FileReader reader;

    public PropertyManager(ServletContext context) {
        path = context.getRealPath("/properties/");
        Controller.logger.info("PropertyManager initialization");
        try {
            reader = new FileReader(path + "/db.properties");
            properties.load(reader);
            Controller.logger.info("/db.properties loaded successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Controller.logger.warning("/db.properties not found!");
        } catch (IOException e) {
            e.printStackTrace();
            Controller.logger.warning("IOException in PropertyManager");
        }
    }

    public String getProperty(String key) {
        if (key.equals("DatabaseInitialize") && properties.getProperty("DatabaseInitialize").equals("yes")) {
            properties.setProperty("DatabaseInitialize", "no");
            try {
                properties.store(new FileOutputStream(path + "/db.properties"), null);
            } catch (IOException e) {
                e.printStackTrace();
                Controller.logger.warning("IOEXCEPTION:\n\t" + e.getMessage() + "\n\t" +
                        e.getCause() + "\n\t" + e.getLocalizedMessage() + "\n\tCant save db.properties");
            }
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
            Controller.logger.warning(name + " not found!");
            e.printStackTrace();
        } catch (IOException e) {
            Controller.logger.warning("IOException in PropertyManager.setPropertyFile()");
            e.printStackTrace();
        }
    }
}
