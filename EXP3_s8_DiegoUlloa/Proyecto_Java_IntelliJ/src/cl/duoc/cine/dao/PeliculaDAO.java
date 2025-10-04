package cl.duoc.cine.dao;

import cl.duoc.cine.db.DatabaseConnection;
import cl.duoc.cine.model.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {
    private Connection conn;

    public PeliculaDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public int crear(Pelicula p) {
        String sql = "INSERT INTO pelicula (titulo, director, genero, anio, duracion_min, clasificacion, sinopsis) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getDirector());
            stmt.setString(3, p.getGenero());
            stmt.setInt(4, p.getAnio());
            stmt.setInt(5, p.getDuracionMin());
            stmt.setString(6, p.getClasificacion());
            stmt.setString(7, p.getSinopsis());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("❌ Error al crear: " + e.getMessage());
        }
        return -1;
    }

    public List<Pelicula> listarTodas() {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM pelicula";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getString("genero"),
                        rs.getInt("anio"),
                        rs.getInt("duracion_min"),
                        rs.getString("clasificacion"),
                        rs.getString("sinopsis")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public Pelicula buscarPorId(int id) {
        String sql = "SELECT * FROM pelicula WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getString("genero"),
                        rs.getInt("anio"),
                        rs.getInt("duracion_min"),
                        rs.getString("clasificacion"),
                        rs.getString("sinopsis")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar por ID: " + e.getMessage());
        }
        return null;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM pelicula WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public List<Pelicula> listarPorRangoAnios(int desde, int hasta) {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM pelicula WHERE anio BETWEEN ? AND ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, desde);
            stmt.setInt(2, hasta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getString("genero"),
                        rs.getInt("anio"),
                        rs.getInt("duracion_min"),
                        rs.getString("clasificacion"),
                        rs.getString("sinopsis")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al filtrar por rango: " + e.getMessage());
        }
        return lista;
    }
}
