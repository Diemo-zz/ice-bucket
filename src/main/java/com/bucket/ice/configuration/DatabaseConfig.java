package com.bucket.ice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
@ConfigurationPropertiesScan
@Data
public class DatabaseConfig {
    private String driver = "postgresql";
    private String hostName;
    private int port=5432;
    private String user;
    private String password;
    private String database;
    private int maxSize = 40;
}
