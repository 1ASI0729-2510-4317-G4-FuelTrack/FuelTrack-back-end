# Usa Java 24
FROM eclipse-temurin:24-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia todo el contenido del proyecto
COPY . .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Construye el proyecto sin ejecutar tests
RUN ./mvnw clean package -DskipTests

# Ejecuta el JAR generado
CMD ["java", "-jar", "target/back-end-0.0.1-SNAPSHOT.jar"]
