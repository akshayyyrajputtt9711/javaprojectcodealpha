import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatbotGUI extends JFrame {

    private final Chatbot chatbot;

    private final JTextArea chatArea;

    private final JTextField inputField;

    private final JButton sendButton;

    public ChatbotGUI() {

        chatbot = new Chatbot();

        setTitle("AI Chatbot - Java NLP Project");

        setSize(700, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        JLabel titleLabel =
                new JLabel("AI Chatbot");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Java + NLP + Rule-Based AI + FAQ Knowledge Base"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        headerPanel.add(
                subtitleLabel,
                BorderLayout.SOUTH
        );

        chatArea = new JTextArea();

        chatArea.setEditable(false);

        chatArea.setLineWrap(true);

        chatArea.setWrapStyleWord(true);

        chatArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        chatArea.setMargin(
                new Insets(
                        10,
                        10,
                        10,
                        10
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(chatArea);

        inputField = new JTextField();

        inputField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        sendButton =
                new JButton("Send");

        sendButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JPanel inputPanel =
                new JPanel(new BorderLayout(8, 8));

        inputPanel.add(
                inputField,
                BorderLayout.CENTER
        );

        inputPanel.add(
                sendButton,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                inputPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        sendButton.addActionListener(
                this::sendMessage
        );

        inputField.addActionListener(
                this::sendMessage
        );

        addMessage(
                "Bot",
                "Hello! 👋 I'm your Java AI chatbot. Ask me something about Java, AI, NLP, OOP or this project."
        );
    }

    private void sendMessage(ActionEvent event) {

        String userMessage =
                inputField.getText().trim();

        if (userMessage.isEmpty()) {
            return;
        }

        addMessage(
                "You",
                userMessage
        );

        String botResponse =
                chatbot.getResponse(userMessage);

        addMessage(
                "Bot",
                botResponse
        );

        inputField.setText("");

        inputField.requestFocus();
    }

    private void addMessage(
            String sender,
            String message) {

        chatArea.append(
                sender +
                        ": " +
                        message +
                        "\n\n"
        );
    }
}
