package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioListar extends JFrame {
    private final PeliculaDAO dao = new PeliculaDAO();
    private final JTable tabla;
    private final DefaultTableModel modelo;

    public FormularioListar() {
        setTitle("Listado de Películas");
        setSize(700, 300);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"ID", "Título", "Director", "Género", "Año", "Duración", "Clasificación"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> cargarDatos());
        add(btnRefrescar, BorderLayout.SOUTH);

        cargarDatos();
        setVisible(true);
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        List<Pelicula> lista = dao.listarTodas();
        for (Pelicula p : lista) {
            modelo.addRow(new Object[]{
                    p.getId(), p.getTitulo(), p.getDirector(), p.getGenero(),
                    p.getAnio(), p.getDuracionMin(), p.getClasificacion()
            });
        }
    }
}
