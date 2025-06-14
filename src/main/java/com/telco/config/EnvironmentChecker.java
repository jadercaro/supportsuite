package com.telco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentChecker implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- INICIANDO VERIFICACIÓN DE VARIABLES DE ENTORNO ---");

        String dbUrl = environment.getProperty("SPRING_DATASOURCE_URL");
        String dbUser = environment.getProperty("SPRING_DATASOURCE_USERNAME");
        String dbPass = environment.getProperty("SPRING_DATASOURCE_PASSWORD");
        String jwtKey = environment.getProperty("JWT_SECRET_KEY");

        System.out.println("SPRING_DATASOURCE_URL: " + dbUrl);
        System.out.println("SPRING_DATASOURCE_USERNAME: " + dbUser);
        System.out.println("SPRING_DATASOURCE_PASSWORD: " + (dbPass != null ? "[OCULTO POR SEGURIDAD]" : "null"));
        System.out.println("JWT_SECRET_KEY: " + (jwtKey != null ? "[OCULTO POR SEGURIDAD]" : "null"));

        if (dbUrl == null || dbUser == null || dbPass == null) {
            System.err.println("!!! ERROR CRÍTICO: Una o más variables de entorno de la base de datos son NULAS. La aplicación no puede arrancar. !!!");
            System.err.println("!!! Por favor, verifica los nombres de las variables en el dashboard de Render. !!!");
        } else {
            System.out.println("--- Verificación de variables de entorno completada. Todas las variables requeridas están presentes. ---");
        }
    }
}