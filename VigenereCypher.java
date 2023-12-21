
//importing entire java.util library for future reference

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the Vigenere Cypher
 *
 * @author Faiyaz Kazi
 * @version 1.0
 * @since Friday, September 16, 2022
 */
public class VigenereCypher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Vigenere Cipher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);
            frame.setLayout(new BorderLayout());

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel titleLabel = new JLabel("Shh... Let's Encrypt!");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titlePanel.add(titleLabel);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel plainLabel = new JLabel("Enter message:");
            JTextField plaintextField = new JTextField(20);
            JLabel keywordLabel = new JLabel("Enter keyword:");
            JTextField keywordField = new JTextField(20);

            JButton encryptButton = new JButton("Encrypt");

            JLabel resultLabel = new JLabel();

            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(plainLabel, gbc);

            gbc.gridx = 1;
            mainPanel.add(plaintextField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(keywordLabel, gbc);

            gbc.gridx = 1;
            mainPanel.add(keywordField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(encryptButton, gbc);

            frame.add(titlePanel, BorderLayout.NORTH);
            frame.add(mainPanel, BorderLayout.CENTER);

            encryptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String plaintext = plaintextField.getText();
                    String keyword = keywordField.getText();

                    String encryptedMessage = encryption(plaintext, keyword);

                    mainPanel.removeAll();
                    mainPanel.add(resultLabel, gbc);
                    resultLabel.setText("Encrypted Message: " + encryptedMessage);
                    animateFadeIn(resultLabel);

                    frame.revalidate();
                    frame.repaint();
                }
            });

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        });
    }

    /**
     * This is the encryption of the plaintext using the keyword.
     *
     * @param plaintext The message to be encrypted.
     * @param keyword   The key to encrypting the message.
     */
    public static String encryption(String plaintext, String keyword) {

        // creating char objects for future use
        char[] plaintextArray = plaintext.toCharArray(); // Converting plaintext to char array for future reference
        char[] keywordLengthened = new char[plaintextArray.length]; // char array for plaintext-lengthened keyword
        char[] plaintextEncrypted = new char[plaintextArray.length]; // char array for encrypted plaintext

        // expanding keyword length to match the length of the plaintext by looping
        // through the
        // letters in the keyword and adding them to the keyword
        for (int i = 0, j = 0; i < plaintextArray.length; i++, j++) {
            // to make sure the keyword can be repeated
            if (j == keyword.length()) {
                j = 0;
            }
            // adding each char of the keyword in a char array until it reaches the length
            // of the plaintext
            keywordLengthened[i] = keyword.charAt(j);
        }

        // encrypting the plaintext by looping through each char of the plaintext, and
        // it's corresponding keyword char
        for (int i = 0; i < plaintextArray.length; i++) {
            plaintextEncrypted[i] = (char) (((plaintextArray[i] + keywordLengthened[i]) % 26) + 'A');
        }

        // outputting encrypted message
        System.out.println("Encrypted Message: " + String.valueOf(plaintextEncrypted));

        // returning encrypted message
        return String.valueOf(plaintextEncrypted);
    }

    /**
     * Animate fade-in effect for a JLabel.
     *
     * @param label The JLabel to animate.
     */
    private static void animateFadeIn(JLabel label) {
        javax.swing.Timer timer = new javax.swing.Timer(20, new ActionListener() {
            private float alpha = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    ((javax.swing.Timer) e.getSource()).stop();
                }
                label.setForeground(new Color(0, 0, 0, alpha));
            }
        });
        timer.start();
    }
}