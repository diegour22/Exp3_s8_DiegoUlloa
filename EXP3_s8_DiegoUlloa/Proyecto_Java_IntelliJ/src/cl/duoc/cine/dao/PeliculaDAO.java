package cl.duoc.cine.dao;

import cl.duoc.cine.db.DatabaseConnection;
import cl.duoc.cine.model.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {

    // ✅ Crear (Agregar película)
    public boolean agregar(Pelicula p) {
        String sql = "INSERT INTO PELICULA (titulo, director, genero, anio, duracion_min, clasificacion, sinopsis) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = DatabaseConnection.get();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setString(3, p.getGenero());
            ps.setInt(4, p.getAnio());
            ps.setInt(5, p.getDuracionMin());
            ps.setString(6, p.getClasificacion());
            ps.setString(7, p.getSinopsis());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al agregar película: " + e.getMessage());
            return false;
        }
    }

    // ✅ Leer (Listar todas las películas)
    public List<Pelicula> listarTodos() {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM PELICULA ORDER BY id DESC";
        try (Connection cn = DatabaseConnection.get();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setGenero(rs.getString("genero"));
                p.setAnio(rs.getInt("anio"));
                p.setDuracionMin(rs.getInt("duracion_min"));
                p.setClasificacion(rs.getString("clasificacion"));
                p.setSinopsis(rs.getString("sinopsis"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Leer por ID
    public Pelicula buscarPorId(int id) {
        String sql = "SELECT * FROM PELICULA WHERE id = ?";
        try (Connection cn = DatabaseConnection.get();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setGenero(rs.getString("genero"));
                p.setAnio(rs.getInt("anio"));
                p.setDuracionMin(rs.getInt("duracion_min"));
                p.setClasificacion(rs.getString("clasificacion"));
                p.setSinopsis(rs.getString("sinopsis"));
                return p;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar: " + e.getMessage());
        }
        return null;
    }

    // ✅ Actualizar
    public boolean actualizar(Pelicula p) {
        String sql = "UPDATE PELICULA SET titulo=?, director=?, genero=?, anio=?, duracion_min=?, clasificacion=?, sinopsis=? WHERE id=?";
        try (Connection cn = DatabaseConnection.get();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setString(3, p.getGenero());
            ps.setInt(4, p.getAnio());
            ps.setInt(5, p.getDuracionMin());
            ps.setString(6, p.getClasificacion());
            ps.setString(7, p.getSinopsis());
            ps.setInt(8, p.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // ✅ Eliminar
    public boolean eliminar(int id) {
        String sql = "DELETE FROM PELICULA WHERE id = ?";
        try (Connection cn = DatabaseConnection.get();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // ✅ Filtro por género
    public List<Pelicula> listarPorGenero(String genero) {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM PELICULA WHERE genero LIKE ?";
        try (Connection cn = DatabaseConnection.get();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, "%" + genero + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setGenero(rs.getString("genero"));
                p.setAnio(rs.getInt("anio"));
                p.setDuracionMin(rs.getInt("duracion_min"));
                p.setClasificacion(rs.getString("clasificacion"));
                p.setSinopsis(rs.getString("sinopsis"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al filtrar por género: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Filtro por rango de años
    public List<Pelicula> listarPorRangoAnios(int desde, int hasta) {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM PELICULA WHERE anio BETWEEN ? AND ? ORDER BY anio";
        try (Connection cn = DatabaseConnection.get();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, desde);
            ps.setInt(2, hasta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDirector(rs.getString("director"));
                p.setGenero(rs.getString("genero"));
                p.setAnio(rs.getInt("anio"));
                p.setDuracionMin(rs.getInt("duracion_min"));
                p.setClasificacion(rs.getString("clasificacion"));
                p.setSinopsis(rs.getString("sinopsis"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al filtrar por rango: " + e.getMessage());
        }
        return lista;
    }
}
