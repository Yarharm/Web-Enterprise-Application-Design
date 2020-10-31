package configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class ConfigDriver {
    private final static String MODULE_NAME = "MessageBoardSystem";
    private final static String CONFIG_NAME = "config.json";
    private final static String CONFIG_PATH = Paths.get(System.getProperty("user.dir"), MODULE_NAME, CONFIG_NAME).toString();
    private final static Gson gson = new Gson();
    private ConfigDriver() {}

    public static String getJDBCDriver() {
        return getConfig().get(ConfigModel.JDBC_DRIVER);
    }

    public static String getDBURL() {
        return getConfig().get(ConfigModel.DB_URL);
    }

    public static String getDBName() {
        return getConfig().get(ConfigModel.DB_NAME);
    }

    public static String getDBUser() {
        return getConfig().get(ConfigModel.DB_USER);
    }

    public static String getDBPassword() {
        return getConfig().get(ConfigModel.DB_PASSWORD);
    }

    private static Map<String, String> getConfig() {
        Map<String, String> configMap = new HashMap<>();
        try {
            JsonReader jsonConfigFile = new JsonReader(new FileReader(CONFIG_PATH));
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            configMap = gson.fromJson(jsonConfigFile, type);
        } catch(FileNotFoundException e) {
            System.err.println("Missing Configuration file.");
            System.exit(1);
        }
        return configMap;
    }
}
