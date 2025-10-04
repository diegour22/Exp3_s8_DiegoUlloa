package cl.duoc.cine.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn != null) return conn;

        try {
            Properties props = new Properties();

            // 1️⃣ Intentar leer desde el classpath (dentro de /resources)
            try (InputStream input = DatabaseConnection.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {
                if (input != null) {
                    props.load(input);
                } else {
                    // 2️⃣ Si no se encuentra, buscar directamente por ruta
                    try (InputStream inputFile = new FileInputStream("resources/db.properties")) {
                        props.load(inputFile);
                    }
                }
            }

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Conectado a la base de datos correctamente");

        } catch (Exception e) {
            System.err.println("❌ Error al conectar: " + e.getMessage());
        }

        return conn;
    }
}
