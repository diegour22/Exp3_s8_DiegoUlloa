
package cl.duoc.cine.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnection {
    private static Connection singleton;

    public static Connection get() throws SQLException {
        if (singleton != null && !singleton.isClosed()) return singleton;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties p = new Properties();
            try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (is == null) throw new RuntimeException("No se encontró resources/db.properties");
                p.load(is);
            }
            String url = p.getProperty("db.url");
            String usr = p.getProperty("db.user");
            String pwd = p.getProperty("db.password");
            singleton = DriverManager.getConnection(url, usr, pwd);
            return singleton;
        } catch (Exception e) {
            throw new SQLException("❌ Error al conectar: " + e.getMessage(), e);
        }
    }

    public static void close() {
        try { if (singleton != null && !singleton.isClosed()) singleton.close(); }
        catch (SQLException ignored) {}
    }
}
