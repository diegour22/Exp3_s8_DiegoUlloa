package cl.duoc.cine.ui;

import cl.duoc.cine.dao.PeliculaDAO;
import cl.duoc.cine.model.Pelicula;

import javax.swing.*;
import java.awt.*;

public class FormularioEliminar extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextArea txtInfo = new JTextArea(5, 20);
    private final PeliculaDAO dao = new PeliculaDAO();

    public FormularioEliminar() {
        setTitle("🗑️ Eliminar Película");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelCampos.add(new JLabel("ID de la película a eliminar:"));
        panelCampos.add(txtId);

        JButton btnBuscar = new JButton("🔍 Buscar");
        JButton btnEliminar = new JButton("🗑️ Eliminar");
        JButton btnLimpiar = new JButton("🧹 Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);

        add(panelCampos, BorderLayout.NORTH);
        add(new JScrollPane(txtInfo), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción: buscar película por ID
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Pelicula p = dao.buscarPorId(id);
                if (p != null) {
                    txtInfo.setText(
                            "🎬 " + p.getTitulo() + "\n" +
                                    "Director: " + p.getDirector() + "\n" +
                                    "Género: " + p.getGenero() + "\n" +
                                    "Año: " + p.getAnio() + "\n" +
                                    "Duración: " + p.getDuracionMin() + " min\n" +
                                    "Clasificación: " + p.getClasificacion()
                    );
                } else {
                    txtInfo.setText("❌ No se encontró ninguna película con ese ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Ingresa un ID válido.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: eliminar con confirmación
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Pelicula p = dao.buscarPorId(id);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "❌ No existe una película con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que quieres eliminar la película:\n" + p.getTitulo() + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.eliminar(id)) {
                        JOptionPane.showMessageDialog(this, "✅ Película eliminada correctamente.");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Error al eliminar la película.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Ingresa un ID válido.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: limpiar campos
        btnLimpiar.addActionListener(e -> limpiar());

        setVisible(true);
    }

    private void limpiar() {
        txtId.setText("");
        txtInfo.setText("");
    }
}
