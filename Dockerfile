# Usa la imagen oficial de Java 17 (compatible con Spring Boot)
FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el contenido del proyecto al contenedor
COPY . .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Construye el proyecto sin ejecutar los tests
RUN ./mvnw clean package -DskipTests

# Ejecuta el jar generado
CMD ["java", "-jar", "target/back-end-0.0.1-SNAPSHOT.jar"]
