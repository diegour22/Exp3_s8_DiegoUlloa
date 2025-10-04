
package cl.duoc.cine.model;

public class Pelicula {
    private Integer id;
    private String titulo;
    private String director;
    private String genero;
    private Integer anio;
    private Integer duracionMin;
    private String clasificacion;
    private String sinopsis;

    public Pelicula() {}
    public Pelicula(String titulo, String director, String genero, Integer anio, Integer duracionMin, String clasificacion, String sinopsis) {
        this.titulo = titulo; this.director = director; this.genero = genero;
        this.anio = anio; this.duracionMin = duracionMin; this.clasificacion = clasificacion; this.sinopsis = sinopsis;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public Integer getDuracionMin() { return duracionMin; }
    public void setDuracionMin(Integer duracionMin) { this.duracionMin = duracionMin; }
    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }
    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }

    @Override
    public String toString() {
        return "Pelicula{id=" + id + ", titulo='" + titulo + "', genero='" + genero + "', anio=" + anio + "}";
    }
}
