package org.adhes.hemostase.config;

import java.nio.file.Path;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hemostase.import")
public class FicheImportProperties {

    private Path csvPath;

    private String apiBase;

    private String token;

    private String salt;

    public Path getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(Path csvPath) {
        this.csvPath = csvPath;
    }

    public String getApiBase() {
        return apiBase;
    }

    public void setApiBase(String apiBase) {
        this.apiBase = apiBase;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
