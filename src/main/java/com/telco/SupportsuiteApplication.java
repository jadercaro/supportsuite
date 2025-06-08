package com.telco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot.
 * La anotación @SpringBootApplication habilita tres funcionalidades clave:
 * 1. @EnableAutoConfiguration: Configura automáticamente la aplicación basada en las dependencias del classpath.
 * 2. @ComponentScan: Escanea en busca de otros componentes, configuraciones y servicios en este paquete y subpaquetes.
 * 3. @Configuration: Permite registrar beans adicionales en el contexto o importar otras clases de configuración.
 */
@SpringBootApplication
public class SupportsuiteApplication {

    /**
     * El método main, punto de entrada de cualquier aplicación Java.
     * Delega el arranque a la clase de utilidad SpringApplication.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(SupportsuiteApplication.class, args);
    }

}