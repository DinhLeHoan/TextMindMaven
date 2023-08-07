package com.TextMind.form;

import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.event.EventMessage;
import com.TextMind.event.PublicEvent;
import com.TextMind.model.Model_Message;
import com.TextMind.model.Model_Register;
import com.TextMind.swing.MyPasswordField;
import com.TextMind.swing.MyTextField;
import io.socket.emitter.Emitter;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class P_Register extends javax.swing.JPanel {
    MyTextField txtName = new MyTextField();
    MyTextField txtEmail = new MyTextField();
    MyTextField txtUsername = new MyTextField();
    MyPasswordField txtPassword = new MyPasswordField();
    MyPasswordField txtConfirm = new MyPasswordField();
    Button cmd = new Button();
    /**
     * Creates new form P_Login
     */
    public P_Register() {
        initComponents();
        initLogin();
        
    }
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "30[]15[]10[]10[]10[]10[]push"));
        JLabel label = new JLabel("Register");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        txtName.setHint("Name");
        login.add(txtName, "w 90%");
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 90%");
        txtUsername.setHint("Username");
        login.add(txtUsername, "w 90%");
        txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/images/user.png")));
        txtName.setPrefixIcon(new ImageIcon(getClass().getResource("/images/user.png")));
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));
        txtPassword.setHint("Password");
        login.add(txtPassword, "w 90%");
        txtConfirm.setHint("Password confirm");
        login.add(txtPassword, "w 90%");
        
        btnRegister.setText("Register");
        btnRegister.setBackground(new Color(0, 130, 130));
        btnRegister.setForeground(new Color(250, 250, 250));
        login.add(btnRegister, "w 40%, h 40");
        btnLogin.setText("Login");
        btnLogin.setBackground(new Color(0, 130, 130));
        btnLogin.setForeground(new Color(250, 250, 250));
        login.add(btnLogin, "w 40%, h 40");
        
    }
    
    
     public void validateInfor() throws JSONException{
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = (new String(txtPassword.getPassword())).trim();
        String username = txtUsername.getText().trim();
        String confirmPassword = (new String(txtConfirm.getPassword())).trim();
        String pattermPassword = "^[A-Za-z0-9]{8,}$";
        if(name.isBlank() || email.isBlank() || password.isBlank() || username.isBlank() || confirmPassword.isBlank()){
            JOptionPane.showMessageDialog(this, "Please fill all input field");
            return ;
        }
        if(!EmailValidator.getInstance().isValid(email)){
            JOptionPane.showMessageDialog(this, "Email is wrong format");
            txtEmail.grabFocus();
            return ;
        }

        if(!password.matches(pattermPassword) || !username.matches(pattermPassword)){
            JOptionPane.showMessageDialog(this, "Password or Username is at least 8 word and contain only alpha bet and number");
            txtPassword.grabFocus();
            return ;
        }
        
        if(!username.matches(pattermPassword)){
            JOptionPane.showMessageDialog(this, "username is at least 8 word and contain only alpha bet and number");
            txtUsername.grabFocus();
            return;
        }
        
        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this, "Password do not match with confirm");
            txtConfirm.grabFocus();
            return ;
        }

                
        JSONObject data = new JSONObject();
        String randomString = RandomStringUtils.randomAlphanumeric(6);
        data.put("username", username);
        data.put("password", password);
        data.put("email", email);
        data.put("name", name);
        data.put("random", randomString);
                
        getSocket().emit("signUpCheck", data);
        getSocket().once("signUpValidate"+randomString,new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                boolean isSignUpValid = (boolean) os[0];
                // Handle the logic based on the received boolean value
                if (!isSignUpValid) {
                    JOptionPane.showMessageDialog(null, "Sign up error, username or email already exist in database");
                    return;
                } 
                else{
                    JOptionPane.showMessageDialog(null, "Sign up success");
                    resetField();
                    PublicEvent.getInstance().getEventLogin().goLogin();
                    return;
                }
            }
        });
        
        return ;
    }
     
     private void resetField(){
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtUsername.setText("");
        txtConfirm.setText("");
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        btnRegister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();

        login.setBackground(new java.awt.Color(0, 153, 153));

        btnRegister.setForeground(new java.awt.Color(46, 163, 0));
        btnRegister.setText("Register");
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnLogin.setForeground(new java.awt.Color(0, 132, 245));
        btnLogin.setText("Back to login");
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addContainerGap())
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(btnRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        try {
            //        String username = txtUsername.getText().trim() ;
//        String password = String.valueOf(txtPassword.getPassword()) ;
//        String confirmPassword = String.valueOf(txtConfirm.getPassword());
//        if (username.equals("")) {
//            txtUsername.grabFocus();
//        } else if (password.equals("")) {
//            txtPassword.grabFocus();
//        } else if (!password.equals(confirmPassword)) {
//            txtPassword.grabFocus();
//        } else {
//            Model_Register data = new Model_Register(username, password) ;
//            PublicEvent.getInstance().getEventLogin().register(data, new EventMessage() {
//                @Override
//                public void callMessage(Model_Message message) {
//                    if (!message.isAction()) {
//                        lblError.setText(message.getMessage());
//                    } else {
//                        PublicEvent.getInstance().getEventLogin().login();
//                    }
//                }
//                
//            });
//        }        
//          PublicEvent.getInstance().getEventLogin().register();
            validateInfor() ;
        } catch (JSONException ex) {
            Logger.getLogger(P_Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        PublicEvent.getInstance().getEventLogin().goLogin();
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JPanel login;
    // End of variables declaration//GEN-END:variables
}
