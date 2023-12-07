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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FindAndAdd extends JPanel {

    private JTextField textField;
    private JButton btnFindAndAdd;
    private JSONObject data;
    public FindAndAdd() {
        setPreferredSize(new Dimension(200, 48)); // Set preferred size

        setLayout(new GridBagLayout());

        textField = new JTextField(10); // Adjust the number of columns based on your font and other factors
//        textField.setPreferredSize(new Dimension(200, 24)); // Set the preferred size to 200 pixels wide and 24 pixels high
        btnFindAndAdd = new JButton("Find");
        btnFindAndAdd.setBackground(new Color(0, 102, 204));
        btnFindAndAdd.setForeground(new Color(250, 250, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10); // Top padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 10, 10); // Bottom padding
        add(btnFindAndAdd, gbc);
        
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == 10) {
                    try {
                        findFriend();
                    } catch (JSONException ex) {
                        System.out.println("Err");
                    }
                }
            }
        });

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
    
    private void findFriend() throws JSONException {
        if (!textField.getText().isBlank()) {
            String username = textField.getText().trim();

            // Send the username to the server through Socket.IO
            data = new JSONObject();
            data.put("nameOrMail", username);
            data.put("uID", Auth.user.getuID());
         
            getSocket().emit("findAndAdd", data);

            // Listen for the validation result from the server
        }
    }
    
    private void findAndUnban() throws JSONException {
        if (!textField.getText().isBlank()) {
            String mail = textField.getText().trim();

            // Send the username to the server through Socket.IO
            data = new JSONObject();
            data.put("mail", mail);
            data.put("uID", Auth.user.getuID());
         
            getSocket().emit("findAndUnban", data);
            getSocket().on("banResult" + Auth.user.getuID(), new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String jsonString = args[0].toString();
                try {                  
                    JOptionPane.showMessageDialog(null, jsonString);
                } catch (Exception e) {
                    System.out.println(e);
                }
                }
            });
            // Listen for the validation result from the server
        }
    }

    private void btnFindAndAddActionPerformed(java.awt.event.ActionEvent evt) throws JSONException  {
        if(!Auth.isAdmin()){
                findFriend();
        }
        else{
            findAndUnban();
        }
    }

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnFindAndAdd() {
		return btnFindAndAdd;
	}

	public void setBtnFindAndAdd(JButton btnFindAndAdd) {
		this.btnFindAndAdd = btnFindAndAdd;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
    
    // Your other methods and code here...
}
