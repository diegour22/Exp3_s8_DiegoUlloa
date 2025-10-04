package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;

import javax.swing.*;
import java.awt.*;

public class FormularioAgregar extends JFrame {
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtDirector = new JTextField();
    private final JTextField txtGenero = new JTextField();
    private final JSpinner spAnio = new JSpinner(new SpinnerNumberModel(2000, 1900, 2100, 1));
    private final JSpinner spDuracion = new JSpinner(new SpinnerNumberModel(90, 1, 600, 1));
    private final JTextField txtClasificacion = new JTextField();
    private final JTextArea txtSinopsis = new JTextArea(3, 20);

    public FormularioAgregar() {
        setTitle("Agregar Película");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 5, 5));

        add(new JLabel("Título:")); add(txtTitulo);
        add(new JLabel("Director:")); add(txtDirector);
        add(new JLabel("Género:")); add(txtGenero);
        add(new JLabel("Año:")); add(spAnio);
        add(new JLabel("Duración:")); add(spDuracion);
        add(new JLabel("Clasificación:")); add(txtClasificacion);
        add(new JLabel("Sinopsis:")); add(new JScrollPane(txtSinopsis));

        JButton btnGuardar = new JButton("💾 Guardar");
        JButton btnLimpiar = new JButton("🧹 Limpiar");
        add(btnGuardar); add(btnLimpiar);

        PeliculaDAO dao = new PeliculaDAO();

        btnGuardar.addActionListener(e -> {
            if (validar()) {
                Pelicula p = new Pelicula();
                p.setTitulo(txtTitulo.getText());
                p.setDirector(txtDirector.getText());
                p.setGenero(txtGenero.getText());
                p.setAnio((Integer) spAnio.getValue());
                p.setDuracionMin((Integer) spDuracion.getValue());
                p.setClasificacion(txtClasificacion.getText());
                p.setSinopsis(txtSinopsis.getText());
                if (dao.agregar(p)) {
                    JOptionPane.showMessageDialog(this, "✅ Película agregada correctamente.");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiar());

        setVisible(true);
    }

    private boolean validar() {
        if (txtTitulo.getText().isBlank() || txtDirector.getText().isBlank() || txtGenero.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Por favor completa los campos obligatorios: Título, Director y Género.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiar() {
        txtTitulo.setText("");
        txtDirector.setText("");
        txtGenero.setText("");
        spAnio.setValue(2000);
        spDuracion.setValue(90);
        txtClasificacion.setText("");
        txtSinopsis.setText("");
    }
}
