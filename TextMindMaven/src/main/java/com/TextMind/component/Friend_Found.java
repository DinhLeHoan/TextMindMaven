/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.entity.User;
import com.TextMind.event.PublicEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class Friend_Found extends javax.swing.JPanel {
    private boolean mouseOver;
    private User friend;
    
    /**
     * Creates new form Item_People
     */
    public Friend_Found(User user) {

        initComponents();
        friend = user;

        lblName.setText(user.getName());
        init();
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
    
    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
//                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(new Color(235, 235, 235));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(224, 224, 224));
                mouseOver = false;
            }
            @Override
            public void mouseReleased(MouseEvent me) {

            }
        });
            btnAddFriend.setEnabled(true);
    }
    
    public void setDisableSend(){
        this.btnAddFriend.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound = new com.TextMind.swing.PanelRound();
        avt = new com.TextMind.swing.ImageAvatar();
        lblName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAddFriend = new javax.swing.JButton();

        panelRound.setBackground(new java.awt.Color(255, 255, 255));

        avt.setBorderSize(1);
        avt.setImage(new javax.swing.ImageIcon(getClass().getResource("/images/userNonActive.png"))); // NOI18N

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 51, 51));
        lblName.setText("Name");

        btnAddFriend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAddFriend.setBorderPainted(false);
        btnAddFriend.setContentAreaFilled(false);
        btnAddFriend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFriendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRoundLayout = new javax.swing.GroupLayout(panelRound);
        panelRound.setLayout(panelRoundLayout);
        panelRoundLayout.setHorizontalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addComponent(avt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        panelRoundLayout.setVerticalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(btnAddFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFriendActionPerformed
        JSONObject requestFriend = new JSONObject();
        try {
            requestFriend.put("uidTo", friend.getuID());
            requestFriend.put("nameFrom", Auth.user.getName());

            requestFriend.put("uidFrom", Auth.user.getuID());
            getSocket().emit("sendRequestFriend", requestFriend);
            btnAddFriend.setEnabled(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnAddFriendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.TextMind.swing.ImageAvatar avt;
    private javax.swing.JButton btnAddFriend;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblName;
    private com.TextMind.swing.PanelRound panelRound;
    // End of variables declaration//GEN-END:variables
}
