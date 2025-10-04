package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final PeliculaDAO dao = new PeliculaDAO();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🎥 Bienvenido a Magenta Cinema – Sistema de Gestión de Películas");
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(in.nextLine());
            switch (opcion) {
                case 1 -> crear();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 5 -> filtrarPorRango();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción inválida, intenta nuevamente.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1) Crear película");
        System.out.println("2) Listar todas");
        System.out.println("3) Actualizar película");
        System.out.println("4) Eliminar película");
        System.out.println("5) Filtrar por rango de años");
        System.out.println("0) Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void crear() {
        try {
            System.out.print("Título: ");
            String titulo = in.nextLine();
            System.out.print("Director: ");
            String director = in.nextLine();
            System.out.print("Género: ");
            String genero = in.nextLine();
            System.out.print("Año: ");
            int anio = Integer.parseInt(in.nextLine());
            System.out.print("Duración (min): ");
            int duracion = Integer.parseInt(in.nextLine());
            System.out.print("Clasificación: ");
            String clasificacion = in.nextLine();
            System.out.print("Sinopsis: ");
            String sinopsis = in.nextLine();

            Pelicula nueva = new Pelicula(titulo, director, genero, anio, duracion, clasificacion, sinopsis);
            int id = dao.crear(nueva);
            if (id > 0)
                System.out.println("✅ Película creada con ID: " + id);
            else
                System.out.println("⚠️ Error al crear película.");

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    private static void listar() {
        List<Pelicula> lista = dao.listarTodas();
        if (lista.isEmpty())
            System.out.println("⚠️ No hay películas registradas.");
        else
            lista.forEach(System.out::println);
    }

    private static void actualizar() {
        try {
            System.out.print("Ingrese ID de película a actualizar: ");
            int id = Integer.parseInt(in.nextLine());
            Pelicula p = dao.buscarPorId(id);
            if (p == null) {
                System.out.println("❌ No se encontró película con ese ID.");
                return;
            }

            System.out.print("Nuevo título (" + p.getTitulo() + "): ");
            String titulo = in.nextLine();
            if (!titulo.isBlank()) p.setTitulo(titulo);

            System.out.print("Nuevo director (" + p.getDirector() + "): ");
            String director = in.nextLine();
            if (!director.isBlank()) p.setDirector(director);

            System.out.print("Nuevo género (" + p.getGenero() + "): ");
            String genero = in.nextLine();
            if (!genero.isBlank()) p.setGenero(genero);

            System.out.print("Nuevo año (" + p.getAnio() + "): ");
            String inputAnio = in.nextLine();
            if (!inputAnio.isBlank()) p.setAnio(Integer.parseInt(inputAnio));

            System.out.print("Nueva duración (" + p.getDuracionMin() + "): ");
            String inputDur = in.nextLine();
            if (!inputDur.isBlank()) p.setDuracionMin(Integer.parseInt(inputDur));

            System.out.print("Nueva clasificación (" + p.getClasificacion() + "): ");
            String clas = in.nextLine();
            if (!clas.isBlank()) p.setClasificacion(clas);

            System.out.print("Nueva sinopsis: ");
            String sinopsis = in.nextLine();
            if (!sinopsis.isBlank()) p.setSinopsis(sinopsis);

            System.out.println("✅ Película actualizada correctamente (simulado).");

        } catch (Exception e) {
            System.err.println("❌ Error al actualizar: " + e.getMessage());
        }
    }

    private static void eliminar() {
        try {
            System.out.print("Ingrese ID de película a eliminar: ");
            int id = Integer.parseInt(in.nextLine());
            boolean exito = dao.eliminar(id);
            System.out.println(exito ? "✅ Película eliminada." : "⚠️ No se encontró película con ese ID.");
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar: " + e.getMessage());
        }
    }

    private static void filtrarPorRango() {
        try {
            System.out.print("Año desde: ");
            int desde = Integer.parseInt(in.nextLine());
            System.out.print("Año hasta: ");
            int hasta = Integer.parseInt(in.nextLine());
            List<Pelicula> lista = dao.listarPorRangoAnios(desde, hasta);

            if (lista.isEmpty())
                System.out.println("⚠️ No se encontraron películas en ese rango.");
            else
                lista.forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("❌ Error al filtrar: " + e.getMessage());
        }
    }
}
