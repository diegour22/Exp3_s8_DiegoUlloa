
package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.db.DatabaseConnection;
import cl.duoc.cine.model.Pelicula;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final PeliculaDAO dao = new PeliculaDAO();

    public static void main(String[] args) {
        // Validación de conexión inicial (S6)
        try (Connection c = DatabaseConnection.get()) {
            System.out.println("✅ Conexión OK");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("=== Cine — Semana 8 (desde cero) ===");
        while (true) {
            System.out.println("\n1) Crear  2) Listar  3) Actualizar  4) Eliminar  5) Género  6) Rango  0) Salir");
            System.out.print("> ");
            switch (in.nextLine().trim()) {
                case "1": crear(); break;
                case "2": listar(); break;
                case "3": actualizar(); break;
                case "4": eliminar(); break;
                case "5": filtrarGenero(); break;
                case "6": filtrarRango(); break;
                case "0": System.out.println("Adiós"); return;
                default: System.out.println("Opción inválida");
            }
        }
    }

    private static void crear() {
        try {
            System.out.print("Título*: "); String t=in.nextLine();
            System.out.print("Director*: "); String d=in.nextLine();
            System.out.print("Género*: "); String g=in.nextLine();
            System.out.print("Año*: "); int a=Integer.parseInt(in.nextLine());
            System.out.print("Duración*: "); int du=Integer.parseInt(in.nextLine());
            System.out.print("Clasificación: "); String c=in.nextLine();
            System.out.print("Sinopsis: "); String s=in.nextLine();
            dao.agregar(new Pelicula(t, d, g, a, du, c, s));
            System.out.println("Creada con ID ");
        } catch (Exception e) { System.err.println("Error: "+e.getMessage()); }
    }
    private static void listar() { try { List<Pelicula> l=dao.listarTodos(); l.forEach(System.out::println);} catch (Exception e){System.err.println(e.getMessage());} }
    private static void actualizar() {
        try {
            System.out.print("ID: "); int id=Integer.parseInt(in.nextLine());
            Pelicula p=dao.buscarPorId(id); if(p==null){System.out.println("No existe"); return;}
            System.out.print("Nuevo título ("+p.getTitulo()+"): "); String t=in.nextLine(); if(!t.isBlank()) p.setTitulo(t);
            System.out.print("Nuevo director ("+p.getDirector()+"): "); String d=in.nextLine(); if(!d.isBlank()) p.setDirector(d);
            System.out.print("Nuevo género ("+p.getGenero()+"): "); String g=in.nextLine(); if(!g.isBlank()) p.setGenero(g);
            System.out.print("Nuevo año ("+p.getAnio()+"): "); String a=in.nextLine(); if(!a.isBlank()) p.setAnio(Integer.parseInt(a));
            System.out.print("Nueva duración ("+p.getDuracionMin()+"): "); String du=in.nextLine(); if(!du.isBlank()) p.setDuracionMin(Integer.parseInt(du));
            System.out.print("Nueva clasificación ("+p.getClasificacion()+"): "); String c=in.nextLine(); if(!c.isBlank()) p.setClasificacion(c);
            System.out.print("Nueva sinopsis: "); String s=in.nextLine(); if(!s.isBlank()) p.setSinopsis(s);
            System.out.println(dao.actualizar(p) ? "Actualizada" : "Sin cambios");
        } catch (Exception e) { System.err.println("Error: "+e.getMessage()); }
    }
    private static void eliminar() {
        try {
            System.out.print("ID: "); int id=Integer.parseInt(in.nextLine());
            System.out.print("¿Eliminar? (s/n): "); if (!in.nextLine().trim().equalsIgnoreCase("s")) return;
            System.out.println(dao.eliminar(id) ? "Eliminada" : "No existe");
        } catch (Exception e) { System.err.println("Error: "+e.getMessage()); }
    }
    private static void filtrarGenero() {
        try { System.out.print("Género: "); String g=in.nextLine(); dao.listarPorGenero(g).forEach(System.out::println); }
        catch (Exception e) { System.err.println("Error: "+e.getMessage()); }
    }
    private static void filtrarRango() {
        try { System.out.print("Desde: "); int d=Integer.parseInt(in.nextLine());
              System.out.print("Hasta: "); int h=Integer.parseInt(in.nextLine());
              dao.listarPorRangoAnios(d,h).forEach(System.out::println); }
        catch (Exception e) { System.err.println("Error: "+e.getMessage()); }
    }
}
