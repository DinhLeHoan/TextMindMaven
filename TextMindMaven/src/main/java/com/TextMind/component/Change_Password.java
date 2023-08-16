/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.swing.MyPasswordField;
import com.TextMind.swing.MyTextField;
import io.socket.emitter.Emitter;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author khang
 */
public class Change_Password extends javax.swing.JDialog {

    MyTextField txtEmail = new MyTextField();
    MyTextField txtVerify = new MyTextField();
    MyPasswordField txtOldPassword = new MyPasswordField();
    MyPasswordField txtNewPassword = new MyPasswordField();
    MyPasswordField txtConfirm = new MyPasswordField();
    JLabel lblError = new JLabel("JoptionPane Error", SwingConstants.CENTER);

    private String code = null;
    private int pX;
    private int pY;

    /**
     * Creates new form ReportUser
     */
    public Change_Password(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initForm();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
    }

    public void initForm() {
        title.setText("CHANGE PASSWORD");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(204, 255, 255));
        title.setBackground(new Color(51, 153, 255));        
        changePass.setLayout(new MigLayout("wrap", "push[center]push", "0[]15[]10[]10[]10[]10[]10[]10[]push"));
        changePass.add(title2);
        changePass.add(btnChange);
        changePass.add(btnClose);
        
        lblError.setFont(new Font("sansserif", 1, 12));
        lblError.setForeground(new Color(255,0,0));
        lblError.setBackground(new Color(51, 153, 255));
        lblError.setVisible(false);
        
//        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
//        txtEmail.setHint("Email");
//        changePass.add(txtEmail, "w 90%");
//
        txtOldPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));
        txtOldPassword.setHint("Old Password");
        changePass.add(txtOldPassword, "w 90%");

        txtNewPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));
        txtNewPassword.setHint("New Password");
        
        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));
        txtConfirm.setHint("Confirm Password");

        txtVerify.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        txtVerify.setHint("Verify code");

        btnSend.setBackground(new Color(0, 102, 204));
        btnSend.setForeground(new Color(250, 250, 250));
        btnChange.setBackground(new Color(0, 102, 204));
        btnChange.setForeground(new Color(250, 250, 250));
        btnClose.setBackground(new Color(0, 102, 204));
        btnClose.setForeground(new Color(250, 250, 250));
        
        changePass.add(txtVerify, "w 90%");       
        changePass.add(txtNewPassword, "w 90%");
        changePass.add(txtConfirm, "w 90%");
        changePass.add(lblError, "w 90%");
        changePass.add(btnSend, "w 40%, h 40");
        changePass.add(btnChange, "w 40%, h 40");
        changePass.add(btnClose, "w 40%, h 40");        
        
    }
    
    private void checkChangePassword() throws JSONException{
        String passwordOld = new String(txtOldPassword.getPassword()).trim();
        String verifyCode = txtVerify.getText().trim();
        String passwordNew = new String(txtNewPassword.getPassword()).trim();
        String passwordConfirm = new String(txtConfirm.getPassword()).trim();
        String pattermPassword = "^[A-Za-z0-9]{8,}$";

        if(passwordOld.isBlank() || verifyCode.isBlank() || passwordNew.isBlank() || passwordConfirm.isBlank()){
            lblError.setVisible(true);
            lblError.setText("Please fill all input field");
            return;
        }
        
        if(!passwordOld.equals(Auth.user.getPassword())){
                        lblError.setVisible(true);

            lblError.setText("Incorrect old password. Please try again.");
            txtOldPassword.grabFocus();
            return;
        }
        
        if(!verifyCode.equals(code)){
                        lblError.setVisible(true);

            lblError.setText("Verify Code wrong");
            txtVerify.grabFocus();
            return;
        }
        
        if (!passwordNew.matches(pattermPassword)) {
                        lblError.setVisible(true);

            lblError.setText("<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
            txtNewPassword.grabFocus();
            return;
        }
        
        if(passwordNew.equals(Auth.user.getPassword())){
                        lblError.setVisible(true);

            lblError.setText("New password cannot be the same as the old password");
            txtNewPassword.grabFocus();
            return;
        }
        
        if(!passwordNew.equals(passwordConfirm)){
                        lblError.setVisible(true);

            lblError.setText("Password do not match with confirm");
            txtConfirm.grabFocus();
            return;
        }
        
        JSONObject data = new JSONObject();
        data.put("uID", Auth.user.getuID());
        data.put("password", passwordNew);

        getSocket().emit("changePassword", data);
        getSocket().once("passwordChangeSuccess" + Auth.user.getuID(), new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                boolean isChangeValid = (boolean) os[0];
                // Handle the logic based on the received boolean value
                if (isChangeValid) {
                    lblError.setText("Change password success");
                    btnCloseActionPerformed(null);
                    return;
                } else {
                    lblError.setText("Error");                    
                    return;
                }
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changePass = new com.TextMind.Helper.GradientPanel();
        title2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        title2.setBackground(new java.awt.Color(51, 153, 255));
        title2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                title2MouseDragged(evt);
            }
        });
        title2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                title2MousePressed(evt);
            }
        });

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout title2Layout = new javax.swing.GroupLayout(title2);
        title2.setLayout(title2Layout);
        title2Layout.setHorizontalGroup(
            title2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, title2Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );
        title2Layout.setVerticalGroup(
            title2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(title2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSend.setText("SEND CODE");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnChange.setText("CHANGE");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changePassLayout = new javax.swing.GroupLayout(changePass);
        changePass.setLayout(changePassLayout);
        changePassLayout.setHorizontalGroup(
            changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(changePassLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(btnSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChange)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(26, 26, 26))
        );
        changePassLayout.setVerticalGroup(
            changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePassLayout.createSequentialGroup()
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                .addGroup(changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(btnChange)
                    .addComponent(btnClose))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void title2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_title2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_title2MouseDragged

    private void title2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_title2MousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_title2MousePressed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        try {
            // TODO add your handling code here:
            checkChangePassword();
        } catch (JSONException ex) {
            Logger.getLogger(Change_Password.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnChangeActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        JSONObject data = new JSONObject();
            String randomString = RandomStringUtils.randomAlphanumeric(6);
        try {
            data.put("email", Auth.user.getEmail());
            data.put("random", randomString);
        } catch (JSONException ex) {
            Logger.getLogger(Change_Password.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            getSocket().emit("getValicateEmail", data);
            getSocket().once("verificationCodeSent"+randomString, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String jsonString = args[0].toString();
                try {                  

                        JSONObject jsonObject = new JSONObject(jsonString);
                        String mailCode = jsonObject.optString("code");
                        String mailOfCode = jsonObject.optString("mailOfThis");
                        code = mailCode;
                    }
                catch (Exception e) {
                    System.out.println(e);
                }
                }
            });
            
      
    }//GEN-LAST:event_btnSendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Change_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Change_Password dialog = new Change_Password(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSend;
    private com.TextMind.Helper.GradientPanel changePass;
    private javax.swing.JLabel title;
    private javax.swing.JPanel title2;
    // End of variables declaration//GEN-END:variables
}
