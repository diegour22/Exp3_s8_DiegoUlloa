package cl.duoc.cine.ui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("🎬 Magenta Cinema");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));
        setLocationRelativeTo(null);

        JButton btnAgregar = new JButton("➕ Agregar Película");
        JButton btnListar = new JButton("📋 Listar Películas");
        JButton btnModificar = new JButton("✏️ Modificar Película");
        JButton btnEliminar = new JButton("🗑️ Eliminar Película");
        JButton btnSalir = new JButton("Salir");

        btnAgregar.addActionListener(e -> new FormularioAgregar());
        btnListar.addActionListener(e -> new FormularioListar());
        btnModificar.addActionListener(e -> new FormularioModificar());
        btnEliminar.addActionListener(e -> new FormularioEliminar());
        btnSalir.addActionListener(e -> System.exit(0));

        add(btnAgregar);
        add(btnListar);
        add(btnModificar);
        add(btnEliminar);
        add(btnSalir);

        setVisible(true);
    }
}
