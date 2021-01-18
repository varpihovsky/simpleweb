package webmanager.properties;

import webmanager.Controller;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    public static final String DATABASE_URL = "DatabaseUrl";
    public static final String DATABASE_USER = "DatabaseUser";
    public static final String DATABASE_PASSWORD = "DatabasePassword";
    public static final String DATABASE_CURRENT = "DatabaseCurrent";
    public static final String DATABASE_INITIALIZE = "DatabaseInitialize";
    public static final String DATABASE_CONNECTIONS_AMOUNT = "DatabaseConnectionsAmount";

    private final Properties properties = new Properties();
    private static ServletContext context;
    private FileReader reader;
    private static PropertyManager propertyManager;
    private final String path;

    private PropertyManager(ServletContext context) {
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

    public static synchronized void setServletContext(ServletContext context) {
        PropertyManager.context = context;
    }

    public static synchronized PropertyManager getInstance() {
        if (propertyManager == null) {
            if (context == null)
                throw new IllegalStateException("Servlet context wasn't set");
            propertyManager = new PropertyManager(context);
        }
        return propertyManager;
    }

    public static void setInstance(PropertyManager propertyManager) {
        PropertyManager.propertyManager = propertyManager;
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
