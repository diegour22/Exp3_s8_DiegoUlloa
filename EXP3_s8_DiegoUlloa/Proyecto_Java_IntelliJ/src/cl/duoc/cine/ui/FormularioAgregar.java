package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;
import javax.swing.*;
import java.awt.*;

public class FormularioAgregar extends JFrame {
    private JTextField txtTitulo, txtDirector, txtGenero, txtAnio, txtDuracion, txtClasificacion, txtSinopsis;
    private final PeliculaDAO dao = new PeliculaDAO();

    public FormularioAgregar() {
        setTitle("Agregar Película");
        setSize(400, 400);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Título:")); txtTitulo = new JTextField(); add(txtTitulo);
        add(new JLabel("Director:")); txtDirector = new JTextField(); add(txtDirector);
        add(new JLabel("Género:")); txtGenero = new JTextField(); add(txtGenero);
        add(new JLabel("Año:")); txtAnio = new JTextField(); add(txtAnio);
        add(new JLabel("Duración (min):")); txtDuracion = new JTextField(); add(txtDuracion);
        add(new JLabel("Clasificación:")); txtClasificacion = new JTextField(); add(txtClasificacion);
        add(new JLabel("Sinopsis:")); txtSinopsis = new JTextField(); add(txtSinopsis);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar);

        setVisible(true);
    }

    private void guardar() {
        try {
            Pelicula p = new Pelicula(
                    txtTitulo.getText(),
                    txtDirector.getText(),
                    txtGenero.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    Integer.parseInt(txtDuracion.getText()),
                    txtClasificacion.getText(),
                    txtSinopsis.getText()
            );

            int id = dao.crear(p);
            if (id > 0)
                JOptionPane.showMessageDialog(this, "✅ Película agregada con ID: " + id);
            else
                JOptionPane.showMessageDialog(this, "⚠️ No se pudo agregar la película.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }
}
