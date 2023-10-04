package com.bucket.ice.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableConfigurationProperties({FlywayProperties.class})
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {


    @Autowired
    DatabaseConfig databaseConfig;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, databaseConfig.getDriver())
                        .option(HOST, databaseConfig.getHostName())
                        .option(PORT, databaseConfig.getPort())
                        .option(USER, databaseConfig.getUser())
                        .option(PASSWORD, databaseConfig.getPassword())
                        .option(DATABASE, databaseConfig.getDatabase())
                        .option(MAX_SIZE, databaseConfig.getMaxSize())
                        .build()
        );
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(FlywayProperties flywayProperties) {
        return Flyway.configure()
                .dataSource(
                        String.format("jdbc:%s://%s:%s/%s", databaseConfig.getDriver(),
                                databaseConfig.getHostName(), databaseConfig.getPort(),
                                databaseConfig.getDatabase()),
                        databaseConfig.getUser(),
                        databaseConfig.getPassword()
                )
                .locations(flywayProperties.getLocations().toArray(String[]::new))
                .baselineOnMigrate(true)
                .load();
    }
}