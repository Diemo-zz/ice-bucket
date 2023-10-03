package com.bucket.ice.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.flywaydb.core.Flyway;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableConfigurationProperties({R2dbcProperties.class, FlywayProperties.class})
public class DatabaseConfig extends AbstractR2dbcConfiguration {


    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, "user")
                        .option(PASSWORD, "password")
                        .option(DATABASE, "postgres")
                        .option(MAX_SIZE, 40)
                        .build()
        );
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(FlywayProperties flywayProperties, R2dbcProperties r2dbcProperties) {
        var LOG = LoggerFactory.getLogger("TS");
        LOG.info(flywayProperties.getUrl());
        LOG.info(r2dbcProperties.getUsername());
        LOG.info(r2dbcProperties.getPassword());
        return Flyway.configure()
                .dataSource(
                        flywayProperties.getUrl(),
                        r2dbcProperties.getUsername(),
                        r2dbcProperties.getPassword()
                )
                .locations(flywayProperties.getLocations()
                        .stream()
                        .toArray(String[]::new))
                .baselineOnMigrate(true)
                .load();
    }
}