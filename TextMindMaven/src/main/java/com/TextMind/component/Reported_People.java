/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.Auth.Auth;
import com.TextMind.entity.UserReported;
import com.TextMind.event.PublicEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.TextMind.main.main;

/**
 *
 * @author KHOA
 */
public class Reported_People extends javax.swing.JPanel {
    private boolean mouseOver;
    private UserReported friend;
    
    /**
     * Creates new form Item_People
     */
    public Reported_People(UserReported user) {

        friend = user;
        initComponents();
        lblName.setText(user.getName());
        init();
    }

    public UserReported getFriend() {
        return friend;
    }

    public void setActive(boolean status) {
        this.friend.setIsOnline(status);
        
    }
    
    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                panelRound.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                setCursor(new Cursor(Cursor.HAND_CURSOR));
//                setBackground(new Color(190, 247, 245));
                setColor(190, 247, 245) ;
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
//                setBackground(new Color(255,255,255));
                setColor(255, 255, 255) ;
                mouseOver = false;
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                setColor(190, 247, 245) ;
                if (mouseOver) {
                    Auth.uIDCurrentChat = friend.getuIDto();
                }
            }
            
        });
    }
    
    private void setColor(int a, int b, int c) {
        setBackground(new Color(a, b, c));
        panelRound.setBackground(new Color(a, b, c));
        avt.setBackground(new Color(a, b, c));
        lblName.setBackground(new Color(a, b, c));
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

        setBackground(new java.awt.Color(168, 168, 167));

        panelRound.setBackground(new java.awt.Color(255, 255, 255));

        avt.setBorderSize(1);
        avt.setImage(new javax.swing.ImageIcon(getClass().getResource("/images/userNonActive.png"))); // NOI18N

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 51, 51));
        lblName.setText("Name");

        javax.swing.GroupLayout panelRoundLayout = new javax.swing.GroupLayout(panelRound);
        panelRound.setLayout(panelRoundLayout);
        panelRoundLayout.setHorizontalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(avt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRoundLayout.setVerticalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.TextMind.swing.ImageAvatar avt;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblName;
    private com.TextMind.swing.PanelRound panelRound;
    // End of variables declaration//GEN-END:variables
}
