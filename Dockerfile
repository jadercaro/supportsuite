# ---- Etapa 1: Compilación (Build Stage) ----
# Usamos una imagen que ya tiene Maven y Java 17 para compilar nuestro código.
FROM maven:3.8.5-openjdk-17 AS build

# Creamos un directorio de trabajo dentro de la imagen.
WORKDIR /app

# Copiamos pom.xml
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente.
COPY src ./src

# Compilamos la aplicación, creamos el JAR y omitimos los tests.
RUN mvn clean package -DskipTests


# ---- Etapa 2: Ejecución (Runtime Stage) ----
# Usamos una imagen base mucho más ligera, que solo tiene lo necesario para ejecutar Java.
FROM openjdk:17-jdk-slim

# Creamos un directorio de trabajo.
WORKDIR /app

# Copiamos el JAR que compilamos en la etapa anterior.
# El nombre del JAR suele ser artifactId-version.jar.
COPY --from=build /app/target/*.jar app.jar

# Le decimos a Docker que nuestra aplicación escuchará en el puerto 8080.
EXPOSE 8080

# El comando que se ejecutará cuando el contenedor se inicie.
ENTRYPOINT ["java","-jar","app.jar"]