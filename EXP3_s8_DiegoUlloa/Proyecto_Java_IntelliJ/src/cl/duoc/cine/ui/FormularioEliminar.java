package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import javax.swing.*;
import java.awt.*;

public class FormularioEliminar extends JFrame {
    private final PeliculaDAO dao = new PeliculaDAO();
    private final JTextField txtId;

    public FormularioEliminar() {
        setTitle("Eliminar Película");
        setSize(350, 150);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("ID de la película a eliminar:"));
        txtId = new JTextField();
        add(txtId);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminar());
        add(btnEliminar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(txtId.getText());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar la película con ID " + id + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = dao.eliminar(id);
                if (ok)
                    JOptionPane.showMessageDialog(this, "✅ Película eliminada correctamente.");
                else
                    JOptionPane.showMessageDialog(this, "⚠️ No se encontró película con ese ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Ingresa un número válido de ID.");
        }
    }
}
