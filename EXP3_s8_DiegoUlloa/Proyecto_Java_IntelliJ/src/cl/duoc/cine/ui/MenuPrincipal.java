package cl.duoc.cine.ui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("🎬 Magenta Cinema - Gestión de Películas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JButton btnAgregar = new JButton("➕ Agregar");
        JButton btnModificar = new JButton("✏️ Modificar");
        JButton btnEliminar = new JButton("🗑️ Eliminar");
        JButton btnListar = new JButton("📋 Listar");
        JButton btnGenero = new JButton("🎭 Filtrar por género");
        JButton btnRango = new JButton("📆 Filtrar por años");

        add(btnAgregar); add(btnModificar); add(btnEliminar);
        add(btnListar); add(btnGenero); add(btnRango);

        // Acciones
        btnAgregar.addActionListener(e -> new FormularioAgregar());
        btnModificar.addActionListener(e -> new FormularioModificar());
        btnEliminar.addActionListener(e -> new FormularioEliminar());
        btnListar.addActionListener(e -> new FormularioListar());

        setVisible(true);
    }
}
