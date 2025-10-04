package cl.duoc.cine.ui;

import javax.swing.*;

public class AppLauncher {

    public static void main(String[] args) {
        // Ajusta el estilo visual (Look & Feel) del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("⚠️ No se pudo aplicar el estilo del sistema: " + e.getMessage());
        }

        // Mostrar un mensaje inicial
        System.out.println("🎬 Bienvenido a Magenta Cinema — Sistema de Gestión de Películas");

        // Crear e iniciar el menú principal
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}
