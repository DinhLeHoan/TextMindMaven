/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.swing;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.entity.User;
import io.socket.emitter.Emitter;
import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FindAndAdd extends JPanel {

    private JTextField textField;
    private JButton btnFindAndAdd;

    public FindAndAdd() {
        setPreferredSize(new Dimension(200, 48)); // Set preferred size

        setLayout(new GridBagLayout());

        textField = new JTextField(10); // Adjust the number of columns based on your font and other factors
        textField.setPreferredSize(new Dimension(200, 24)); // Set the preferred size to 200 pixels wide and 24 pixels high
        btnFindAndAdd = new JButton("Find and Add");
        btnFindAndAdd.setBackground(new Color(0, 102, 204));
        btnFindAndAdd.setForeground(new Color(250, 250, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10); // Top padding
        add(textField, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 10, 10); // Bottom padding
        add(btnFindAndAdd, gbc);

        // Add action listener to the button
        btnFindAndAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnFindAndAddActionPerformed(e);
                } catch (JSONException ex) {
                    Logger.getLogger(FindAndAdd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void btnFindAndAddActionPerformed(java.awt.event.ActionEvent evt) throws JSONException {
        if (!textField.getText().isBlank()) {
            String username = textField.getText().trim();

            // Send the username to the server through Socket.IO
            JSONObject data = new JSONObject();
            data.put("nameOrMail", username);
            data.put("uID", Auth.user.getuID());
            getSocket().emit("findAndAdd", data);

            // Listen for the validation result from the server
        }
    }

    // Your other methods and code here...
}
