package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioListar extends JFrame {

    private final PeliculaDAO dao = new PeliculaDAO();
    private final DefaultTableModel modeloTabla = new DefaultTableModel();
    private final JTable tabla = new JTable(modeloTabla);

    private final JTextField txtGenero = new JTextField();
    private final JSpinner spDesde = new JSpinner(new SpinnerNumberModel(2000, 1900, 2100, 1));
    private final JSpinner spHasta = new JSpinner(new SpinnerNumberModel(2024, 1900, 2100, 1));

    public FormularioListar() {
        setTitle("📋 Listado de Películas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Encabezado de la tabla ---
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Director");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Duración");
        modeloTabla.addColumn("Clasificación");

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // --- Panel superior de filtros ---
        JPanel panelFiltros = new JPanel(new GridLayout(2, 4, 5, 5));
        panelFiltros.add(new JLabel("🎭 Género:"));
        panelFiltros.add(txtGenero);
        panelFiltros.add(new JLabel("📆 Desde:"));
        panelFiltros.add(spDesde);
        panelFiltros.add(new JLabel("📆 Hasta:"));
        panelFiltros.add(spHasta);

        JButton btnFiltrarGenero = new JButton("Filtrar por género");
        JButton btnFiltrarRango = new JButton("Filtrar por años");
        JButton btnRefrescar = new JButton("🔄 Refrescar");
        JButton btnLimpiar = new JButton("🧹 Limpiar filtros");

        panelFiltros.add(btnFiltrarGenero);
        panelFiltros.add(btnFiltrarRango);
        panelFiltros.add(btnRefrescar);
        panelFiltros.add(btnLimpiar);

        add(panelFiltros, BorderLayout.NORTH);

        // --- Cargar todos los datos por defecto ---
        cargarTabla(dao.listarTodos());

        // --- Acciones de los botones ---
        btnFiltrarGenero.addActionListener(e -> {
            String genero = txtGenero.getText().trim();
            if (genero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Ingresa un género para filtrar.");
                return;
            }
            cargarTabla(dao.listarPorGenero(genero));
        });

        btnFiltrarRango.addActionListener(e -> {
            int desde = (Integer) spDesde.getValue();
            int hasta = (Integer) spHasta.getValue();
            if (desde > hasta) {
                JOptionPane.showMessageDialog(this, "⚠️ El año inicial no puede ser mayor que el final.");
                return;
            }
            cargarTabla(dao.listarPorRangoAnios(desde, hasta));
        });

        btnRefrescar.addActionListener(e -> cargarTabla(dao.listarTodos()));

        btnLimpiar.addActionListener(e -> {
            txtGenero.setText("");
            spDesde.setValue(2000);
            spHasta.setValue(2024);
            cargarTabla(dao.listarTodos());
        });

        setVisible(true);
    }

    private void cargarTabla(List<Pelicula> lista) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Pelicula p : lista) {
            modeloTabla.addRow(new Object[]{
                    p.getId(),
                    p.getTitulo(),
                    p.getDirector(),
                    p.getGenero(),
                    p.getAnio(),
                    p.getDuracionMin(),
                    p.getClasificacion()
            });
        }
    }
}
