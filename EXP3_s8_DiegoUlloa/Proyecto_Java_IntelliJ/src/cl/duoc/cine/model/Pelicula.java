package cl.duoc.cine.model;

public class Pelicula {
    private int id;
    private String titulo;
    private String director;
    private String genero;
    private int anio;
    private int duracionMin;
    private String clasificacion;
    private String sinopsis;

    public Pelicula() {}

    public Pelicula(int id, String titulo, String director, String genero, int anio, int duracionMin, String clasificacion, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anio = anio;
        this.duracionMin = duracionMin;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
    }

    public Pelicula(String titulo, String director, String genero, int anio, int duracionMin, String clasificacion, String sinopsis) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anio = anio;
        this.duracionMin = duracionMin;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getDuracionMin() { return duracionMin; }
    public void setDuracionMin(int duracionMin) { this.duracionMin = duracionMin; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }

    @Override
    public String toString() {
        return id + " | " + titulo + " | " + director + " | " + genero + " | " + anio + " | " + duracionMin + "min | " + clasificacion;
    }
}
