/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import javax.swing.*;
import java.awt.*;

public class YourClassName extends JFrame {
    private JTextField textField;
    private JButton btnFindAndAdd;

    public YourClassName() {
        setTitle("Find and Add Friend");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        textField = new JTextField(20); // 20 characters wide
        btnFindAndAdd = new JButton("Find and Add");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10); // Top padding
        add(textField, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 10, 10); // Bottom padding
        add(btnFindAndAdd, gbc);

        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
    }

    // Your other methods and code here...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            YourClassName frame = new YourClassName();
            frame.setVisible(true);
        });
    }
}

