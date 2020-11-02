package configuration;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ConfigDriver {
    private final static String CONFIG_NAME = "config.json";
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
            String configPath = buildConfigPath();
            JsonReader jsonConfigFile = new JsonReader(new FileReader(configPath));
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            configMap = gson.fromJson(jsonConfigFile, type);
        } catch(IOException e) {
            System.err.println("Missing Configuration file.");
            System.exit(1);
        }
        return configMap;
    }

    private static String buildConfigPath() throws IOException {
        Path rootPath = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        if(rootPath.toString().contains("tomcat")) {
            rootPath = rootPath.getParent();
        }
        Optional<Path> hit = Files.walk(rootPath)
                .filter(file -> file.getFileName().toString().equals(CONFIG_NAME))
                .findAny();

        return hit.get().toString();
    }
}
