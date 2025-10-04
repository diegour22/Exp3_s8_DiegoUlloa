package cl.duoc.cine.ui;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("⚠️ No se pudo aplicar el tema visual.");
        }
        new MenuPrincipal();
    }
}
