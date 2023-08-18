/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.form;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.component.Forgot_Password;
import com.TextMind.entity.User;
import com.TextMind.event.PublicEvent;
import com.TextMind.main.Login;
import com.TextMind.swing.MyPasswordField;
import com.TextMind.swing.MyTextField;
import io.socket.emitter.Emitter;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class P_Login extends javax.swing.JPanel {

    MyTextField txtUsername = new MyTextField();
    MyPasswordField txtPassword = new MyPasswordField();
    JLabel forgot = new JLabel("<HTML><U>Forgot password</U></HTML>", SwingConstants.CENTER);
    

    Button cmd = new Button();

    /**
     * Creates new form P_Login
     */
    public P_Login() {
        initComponents();
        init();
        setOpaque(false);
        initLogin();
        login.start();
    }

    private User validateLogin() {
        try {
            if (txtUsername != null && txtPassword != null) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                // Kiểm tra username và password có đủ độ dài và không chứa kí tự đặc biệt
                if (isValidUsername(username) && isValidPassword(password)) {
                    return new User(username, password.trim());
                } else {
                    lblError.setText("Invalid username or password!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isValidUsername(String username) {
        // Kiểm tra độ dài của username (>= 6 kí tự) và không chứa kí tự đặc biệt
        return username.length() >= 6 && username.matches("^[a-zA-Z0-9]*$");
    }

    private boolean isValidPassword(String password) {
        // Kiểm tra độ dài của password (>= 6 kí tự) và không chứa kí tự đặc biệt
        return password.length() >= 6 && password.matches("^[a-zA-Z0-9]*$");
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "60[]35[]10[]20[]20[]1[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(204, 255, 255));
        login.add(label);

        txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        txtUsername.setHint("Username");

        login.add(txtUsername, "w 90%");

        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));
        txtPassword.setHint("Password");
        login.add(txtPassword, "w 90%");

        lblError.setText("");

        lblError.setHorizontalAlignment(JLabel.CENTER);
        lblError.setVerticalAlignment(JLabel.CENTER);
        login.add(lblError, "w 80%, h 40");

        btnLogin.setText("Login");
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(new Color(250, 250, 250));
        
        forgot.setFont(new Font("sansserif", 2, 14));
        forgot.setForeground(new Color(5, 232, 43));
        forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Forgot_Password fp = new Forgot_Password(Login.getFrames()[0], true);
                fp.setVisible(true);
            }
        });

        login.add(btnLogin, "w 40%, h 40");
        btnRegister.setText("Register");
        btnRegister.setBackground(new Color(0, 102, 204));
        btnRegister.setForeground(new Color(250, 250, 250));
        login.add(btnRegister, "w 40%, h 40");
        login.add(forgot, "w 90%");
        
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == 10) {
                    login() ;
                }
            }
        });
        
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == 10) {
                    login() ;
                }
            }
        });

    }

    private void init() {
        getSocket().on("signInSuccess", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                String jsonString = os[0].toString();
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String name = jsonObject.optString("name");
                    String username = jsonObject.optString("username");
                    String password = jsonObject.optString("password");
                    String uID = jsonObject.optString("uID");
                    String email = jsonObject.optString("email");
                    String role = jsonObject.optString("role");
                    if(role.equals("admin")){
                        Auth.user = new User(uID, name, username, password,email,true);
                    }
                    else{
                        Auth.user = new User(uID, name, username, password,email,false);
                    }

                    PublicEvent.getInstance().getEventLogin().login();
                } catch (JSONException e) {
                    System.out.println(e);
                }
            }
        });

        // Handle sign-in error event
        getSocket().on("signInError", args -> {
            String errorMessage = (String) args[0];
//            System.out.println(errorMessage);
            lblError.setText(errorMessage);
        });

        getSocket().on(getSocket().EVENT_CONNECT, (Object... os) -> {
            System.out.println("connection");
        });
    }

    public void login() {
        if (validateLogin() != null) {
            getSocket().emit("signIn", validateLogin().getUsername() + " : " + validateLogin().getPassword());
        } else {
            System.out.println("null");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new com.TextMind.Helper.CurvesPanel();
        lblError = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();

        lblError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 51, 51));
        lblError.setText("#Error");
        lblError.setToolTipText("");

        btnLogin.setForeground(new java.awt.Color(0, 132, 245));
        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });

        btnRegister.setForeground(new java.awt.Color(46, 163, 0));
        btnRegister.setText("Register");
        btnRegister.setContentAreaFilled(false);
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegister)
                .addGap(0, 130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        PublicEvent.getInstance().getEventLogin().goRegister();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        
    }//GEN-LAST:event_btnLoginKeyPressed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login() ;
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblError;
    private com.TextMind.Helper.CurvesPanel login;
    // End of variables declaration//GEN-END:variables
}
