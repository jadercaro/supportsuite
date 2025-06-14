package com.telco.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Clase de configuración para crear manualmente el bean DataSource.
 * Este enfoque proporciona un control total sobre la configuración de la conexión
 * a la base de datos, evitando problemas de autoconfiguración en entornos de despliegue.
 */
@Configuration
public class DataSourceConfig {

    // Inyectamos cada parte de la conexión desde las variables de entorno.
    @Value("${DB_HOST}")
    private String dbHost;

    @Value("${DB_PORT}")
    private String dbPort;

    @Value("${DB_NAME}")
    private String dbName;

    @Value("${DB_USER}")
    private String dbUser;

    @Value("${DB_PASS}")
    private String dbPassword;

    @Bean
    public DataSource getDataSource() {
        // Construimos la URL en el formato JDBC estándar y seguro.
        String jdbcUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName + "?sslmode=require";

        System.out.println("--- CONSTRUYENDO DATASOURCE MANUALMENTE ---");
        System.out.println("JDBC URL: " + jdbcUrl);
        System.out.println("Usuario: " + dbUser);
        System.out.println("--- --- --- --- --- --- --- --- --- --- ---");

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(jdbcUrl)
                .username(dbUser)
                .password(dbPassword)
                .build();
    }
}