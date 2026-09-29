import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ChatbotGUI gui =
                    new ChatbotGUI();

            gui.setVisible(true);
        });
    }
}