/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.event.PublicEvent;

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
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author khang
 */
public class Delete_Friend extends javax.swing.JDialog {

    private String code = null;
    private int pX;
    private int pY;


    /**
     * Creates new form ReportUser
     */
    public Delete_Friend(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initForm();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
        changePass.setVisible(true);

    }

    public void initForm() {
        
        title.setText("Delete this friend ?");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(204, 255, 255));
        title.setBackground(new Color(51, 153, 255));        
        changePass.setLayout(new MigLayout("wrap", "push[center]push", "0[]15[]10[]10[]10[]push"));
        changePass.add(title);
        changePass.add(btnClose);
        

        btnSend.setBackground(new Color(0, 102, 204));
        btnSend.setForeground(new Color(250, 250, 250));

        btnClose.setBackground(new Color(0, 102, 204));
        btnClose.setForeground(new Color(250, 250, 250));
        
        
        changePass.add(btnSend, "w 40%, h 40");

        changePass.add(btnClose, "w 40%, h 40");        
        
    }
    

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        changePass = new com.TextMind.Helper.GradientPanel();
        btnSend = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        btnSend.setText("DELETE");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout changePassLayout = new javax.swing.GroupLayout(changePass);
        changePass.setLayout(changePassLayout);
        changePassLayout.setHorizontalGroup(
            changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePassLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePassLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        changePassLayout.setVerticalGroup(
            changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(btnClose))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
            .addComponent(changePass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        JSONObject removeFriend = new JSONObject();
        try {
            removeFriend.put("uidFrom", Auth.uIDCurrentChat);
            removeFriend.put("uidTo", Auth.user.getuID());
            getSocket().emit("removeFriend", removeFriend);
            PublicEvent.getInstance().getEventChatBody().reset();
            PublicEvent.getInstance().getEventTitleChat().changeTitle(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        
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
            java.util.logging.Logger.getLogger(Delete_Friend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Delete_Friend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Delete_Friend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Delete_Friend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Delete_Friend dialog = new Delete_Friend(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSend;
    private com.TextMind.Helper.GradientPanel changePass;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JPanel title2;
    private javax.swing.JPanel title3;
    private javax.swing.JLabel title4;
    // End of variables declaration//GEN-END:variables
}
