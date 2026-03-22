# --- ETAPA 1: Construcción (Build) ---
# Usamos una imagen de Maven con JDK 17 para compilar el proyecto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos el pom.xml y descargamos las dependencias (esto optimiza la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y generamos el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecución (Runtime) ---
# Usamos una imagen más ligera (JRE) solo para correr la app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos solo el archivo JAR desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto (Spring usa el 8080 por defecto)
EXPOSE 8080

# Comando para arrancar la app con optimización de memoria para Render
ENTRYPOINT ["java", "-Xmx384m", "-Xss512k", "-jar", "app.jar"]