package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;
import javax.swing.*;
import java.awt.*;

public class FormularioModificar extends JFrame {
    private final PeliculaDAO dao = new PeliculaDAO();
    private JTextField txtId, txtTitulo, txtDirector, txtGenero, txtAnio, txtDuracion, txtClasificacion, txtSinopsis;

    public FormularioModificar() {
        setTitle("Modificar Película");
        setSize(450, 400);
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel("ID:"));
        txtId = new JTextField(); add(txtId);
        add(new JLabel("Título:"));
        txtTitulo = new JTextField(); add(txtTitulo);
        add(new JLabel("Director:"));
        txtDirector = new JTextField(); add(txtDirector);
        add(new JLabel("Género:"));
        txtGenero = new JTextField(); add(txtGenero);
        add(new JLabel("Año:"));
        txtAnio = new JTextField(); add(txtAnio);
        add(new JLabel("Duración (min):"));
        txtDuracion = new JTextField(); add(txtDuracion);
        add(new JLabel("Clasificación:"));
        txtClasificacion = new JTextField(); add(txtClasificacion);
        add(new JLabel("Sinopsis:"));
        txtSinopsis = new JTextField(); add(txtSinopsis);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnGuardar = new JButton("Guardar cambios");

        btnBuscar.addActionListener(e -> buscar());
        btnGuardar.addActionListener(e -> guardar());

        add(btnBuscar);
        add(btnGuardar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Pelicula p = dao.buscarPorId(id);
            if (p != null) {
                txtTitulo.setText(p.getTitulo());
                txtDirector.setText(p.getDirector());
                txtGenero.setText(p.getGenero());
                txtAnio.setText(String.valueOf(p.getAnio()));
                txtDuracion.setText(String.valueOf(p.getDuracionMin()));
                txtClasificacion.setText(p.getClasificacion());
                txtSinopsis.setText(p.getSinopsis());
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ No se encontró película con ese ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ ID inválido, ingresa un número.");
        }
    }

    private void guardar() {
        try {
            if (txtId.getText().isBlank() || txtTitulo.getText().isBlank() ||
                    txtDirector.getText().isBlank() || txtGenero.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "⚠️ Todos los campos obligatorios deben estar completos.");
                return;
            }

            Pelicula p = new Pelicula(
                    Integer.parseInt(txtId.getText()),
                    txtTitulo.getText(),
                    txtDirector.getText(),
                    txtGenero.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    Integer.parseInt(txtDuracion.getText()),
                    txtClasificacion.getText(),
                    txtSinopsis.getText()
            );

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Deseas guardar los cambios?",
                    "Confirmar actualización",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = dao.eliminar(p.getId()) && dao.crear(p) > 0;
                if (ok)
                    JOptionPane.showMessageDialog(this, "✅ Película actualizada correctamente.");
                else
                    JOptionPane.showMessageDialog(this, "⚠️ Error al actualizar película.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Revisa los campos numéricos (ID, año, duración).");
        }
    }
}
