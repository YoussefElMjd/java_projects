package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final String FILE = "config.properties";
    private final Properties prop;
    private final String url;

    private ConfigManager() {
        prop = new Properties();
        url = getClass().getClassLoader().getResource(FILE).getFile();
    }

    public void load() throws IOException {
        try (InputStream input = new FileInputStream(url)) {
            prop.load(input);
        } catch (IOException e) {
            throw new IOException("Chargement configuration impossible" + e.getMessage());
        }
    }

public String getProperties(String name){
        return prop.getProperty(name);
}

private static class ConfigManagerHolder{
        public final static ConfigManager instance = new ConfigManager();
}

public static ConfigManager getInstance(){
        return ConfigManagerHolder.instance;
}
}


