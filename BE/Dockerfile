# Menggunakan image JDK 17 sebagai base image
FROM openjdk:17-jdk-slim

# Menentukan lokasi aplikasi yang akan dibangun
WORKDIR /app

# Menyalin file JAR ke container
COPY target/spring-wl-docker.jar app.jar

EXPOSE 8080

# Menentukan perintah untuk menjalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
