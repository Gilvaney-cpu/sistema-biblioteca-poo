package br.com.biblioteca.view;

import br.com.biblioteca.service.BibliotecaFachada;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        try {
            // 1. Tenta deixar o visual com cara do sistema operacional (Windows/Linux)
            // Em vez daquela cara de Java antigo (Metal)
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName()) || "Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Erro ao definir Layout: " + ex.getMessage());
        }

        // 2. Inicializa a Fachada (Carrega listas, cria admin se não existir, etc)
        System.out.println("Inicializando sistema...");
        BibliotecaFachada fachada = BibliotecaFachada.getInstance();

        // 3. Abre a Tela de Login
        // Como a tela de login ainda não existe (Membro 3), vamos deixar um aviso
        // ou chamar a tela se você quiser criar o esqueleto dela agora.
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Quando a TelaLogin existir, você vai descomentar a linha abaixo:
                // new TelaLogin().setVisible(true);
                
                // Por enquanto, mostra isso pra provar que rodou:
                JOptionPane.showMessageDialog(null, "Sistema Iniciado com Sucesso!\n(Aguardando Tela de Login)");
            }
        });
    }
}