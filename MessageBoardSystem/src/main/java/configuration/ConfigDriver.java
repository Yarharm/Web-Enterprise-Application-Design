package configuration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import helpers.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public final class ConfigDriver {
    private final static String CONFIG_NAME = "config.json";
    private final static Gson gson = new Gson();
    private ConfigDriver() {}

    public static int getPaginationSize() {
        return Integer.parseInt(getConfig().get(ConfigModel.PAGINATION_SIZE));
    }

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

    public static String getUserManagerClassName() { return getConfig().get(ConfigModel.USER_MANAGER_CLASS_NAME); }

    private static Map<String, String> getConfig() {
        Map<String, String> configMap = new HashMap<>();
        try {
            String configPath = Utils.buildTargetFilePath(CONFIG_NAME);
            JsonReader jsonConfigFile = new JsonReader(new FileReader(configPath));
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            configMap = gson.fromJson(jsonConfigFile, type);
        } catch(IOException e) {
            System.err.println("Missing Configuration file.");
            System.exit(1);
        }

        return configMap;
    }
}
