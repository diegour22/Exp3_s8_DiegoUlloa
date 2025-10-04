package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;

import javax.swing.*;
import java.awt.*;

public class FormularioModificar extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtDirector = new JTextField();
    private final JTextField txtGenero = new JTextField();
    private final JSpinner spAnio = new JSpinner(new SpinnerNumberModel(2000, 1900, 2100, 1));
    private final JSpinner spDuracion = new JSpinner(new SpinnerNumberModel(90, 1, 600, 1));
    private final JTextField txtClasificacion = new JTextField();
    private final JTextArea txtSinopsis = new JTextArea(3, 20);

    private final PeliculaDAO dao = new PeliculaDAO();

    public FormularioModificar() {
        setTitle("✏️ Modificar Película");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel("ID a modificar:")); add(txtId);
        add(new JLabel("Título:")); add(txtTitulo);
        add(new JLabel("Director:")); add(txtDirector);
        add(new JLabel("Género:")); add(txtGenero);
        add(new JLabel("Año:")); add(spAnio);
        add(new JLabel("Duración:")); add(spDuracion);
        add(new JLabel("Clasificación:")); add(txtClasificacion);
        add(new JLabel("Sinopsis:")); add(new JScrollPane(txtSinopsis));

        JButton btnBuscar = new JButton("🔍 Buscar");
        JButton btnActualizar = new JButton("💾 Actualizar");
        JButton btnLimpiar = new JButton("🧹 Limpiar");

        add(btnBuscar); add(btnActualizar);
        add(btnLimpiar);

        // Acción de buscar película por ID
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Pelicula p = dao.buscarPorId(id);
                if (p != null) {
                    txtTitulo.setText(p.getTitulo());
                    txtDirector.setText(p.getDirector());
                    txtGenero.setText(p.getGenero());
                    spAnio.setValue(p.getAnio());
                    spDuracion.setValue(p.getDuracionMin());
                    txtClasificacion.setText(p.getClasificacion());
                    txtSinopsis.setText(p.getSinopsis());
                } else {
                    JOptionPane.showMessageDialog(this, "❌ No se encontró la película con ese ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Ingresa un ID válido.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción de actualizar
        btnActualizar.addActionListener(e -> {
            if (validar()) {
                try {
                    Pelicula p = new Pelicula();
                    p.setId(Integer.parseInt(txtId.getText()));
                    p.setTitulo(txtTitulo.getText());
                    p.setDirector(txtDirector.getText());
                    p.setGenero(txtGenero.getText());
                    p.setAnio((Integer) spAnio.getValue());
                    p.setDuracionMin((Integer) spDuracion.getValue());
                    p.setClasificacion(txtClasificacion.getText());
                    p.setSinopsis(txtSinopsis.getText());

                    if (dao.actualizar(p)) {
                        JOptionPane.showMessageDialog(this, "✅ Película actualizada correctamente.");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ No se pudo actualizar. Verifica el ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "⚠️ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción de limpiar
        btnLimpiar.addActionListener(e -> limpiar());

        setVisible(true);
    }

    private boolean validar() {
        if (txtTitulo.getText().isBlank() || txtDirector.getText().isBlank() || txtGenero.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Completa Título, Director y Género antes de guardar.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiar() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDirector.setText("");
        txtGenero.setText("");
        spAnio.setValue(2000);
        spDuracion.setValue(90);
        txtClasificacion.setText("");
        txtSinopsis.setText("");
    }
}
