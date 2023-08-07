/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.form;

import com.TextMind.Auth.Auth;
import com.TextMind.DAO.UserDAO;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.component.Friend_Found;
import com.TextMind.component.Friend_Request;
import com.TextMind.component.Item_People;
import com.TextMind.entity.User;
import com.TextMind.swing.FindAndAdd;
import com.TextMind.swing.ScrollBar;
import io.socket.emitter.Emitter;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author KHOA
 */
public class Menu_Left extends javax.swing.JPanel implements UserDAO.ListUpdateListener {
    private UserDAO listFriend;
    
    /**
     * Creates new form Menu_Left
     */
    public Menu_Left() {
        initComponents();
        init() ;
        listFriend.setListUpdateListener((UserDAO.ListUpdateListener) this);
        getSocket().on("FindResult" + Auth.user.getuID(), new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    boolean isValid = (boolean) args[0];
                    // Handle the result here
                    if (isValid) {
                        // User with the entered name does not exist, proceed with adding the user
                        // Code to add the user to Firestore or perform any other action
                                listFriend = new UserDAO();
                                System.out.println("addddd");
                                showMess();
                        // For example:
                        
                    } else {
                        // User with the entered name already exists
                    }
                }
            });
    }
    
    private void init() {
        listFriend = new UserDAO();
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
    }

    private void showMess() {
        menuList.removeAll();
        for (User friendData : listFriend.getListFriend()) {
            Item_People friend = new Item_People(friendData);
            if(listFriend.getListFriendOnline().contains(friendData.getuID()))
            {   
                System.out.println(friendData.getName());
                friend.setActive(true);
            }
            menuList.add(friend, "wrap");
        }
        refreshMenuList();
    }

    private void showFindFriend() {
        menuList.removeAll();
//        FindAndAdd fad = new FindAndAdd();
//        menuList.add(fad);
        for (int i = 0; i < 10; i++) {
            menuList.add(new Friend_Found("Name " + i), "wrap");
        }
        refreshMenuList();
    }

    private void showBox() {
        menuList.removeAll();
        for (int i = 0; i < 10; i++) {
            menuList.add(new Friend_Request("Box " + i), "wrap");
        }
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }
    
    
    public void onListUpdated() {
        showMess();
        getSocket().on("getSignInStatus", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                ArrayList<String> listOnline = new ArrayList<>();
                JSONArray jsonArray = (JSONArray) os[0];
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        String uIDFriend = jsonArray.getString(i);
                        if (!listFriend.getListFriend().contains(uIDFriend)) {
                            System.out.println(uIDFriend);
                            listOnline.add(uIDFriend);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                updateOnlineFriends(listOnline);
            }
        });
    }
    
    public void updateOnlineFriends(ArrayList<String> onlineFriends) {
        for (Component component : menuList.getComponents()) {
            if (component instanceof Item_People) {
                
                Item_People friend = (Item_People) component;
                String friendID = friend.getFriend().getuID();
                // Check if the friend's ID is in the online list
                boolean isOnline = onlineFriends.contains(friendID);
                friend.setActive(isOnline);
            }
        }
        refreshMenuList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMess = new com.TextMind.component.MenuButton();
        menuFind = new com.TextMind.component.MenuButton();
        menuBox = new com.TextMind.component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMess.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/images/chat40Selec.png"))); // NOI18N
        menuMess.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/chat40.png"))); // NOI18N
        menuMess.setSelected(true);
        menuMess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessActionPerformed(evt);
            }
        });
        menu.add(menuMess);

        menuFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search40.png"))); // NOI18N
        menuFind.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/images/search40Selec.png"))); // NOI18N
        menuFind.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/search40.png"))); // NOI18N
        menuFind.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search40Selec.png"))); // NOI18N
        menuFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFindActionPerformed(evt);
            }
        });
        menu.add(menuFind);

        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/request40.png"))); // NOI18N
        menuBox.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/images/request40Selec.png"))); // NOI18N
        menuBox.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/request40.png"))); // NOI18N
        menuBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/request40Selec.png"))); // NOI18N
        menuBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoxActionPerformed(evt);
            }
        });
        menu.add(menuBox);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
        if (!menuBox.isSelected()) {
            menuMess.setSelected(false);
            menuFind.setSelected(false);
            menuBox.setSelected(true);
            showBox();
        }
    }//GEN-LAST:event_menuBoxActionPerformed

    private void menuFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFindActionPerformed
        if (!menuFind.isSelected()) {
            menuMess.setSelected(false);
            menuFind.setSelected(true);
            menuBox.setSelected(false);
            showFindFriend();
        }
    }//GEN-LAST:event_menuFindActionPerformed

    private void menuMessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessActionPerformed
        if (!menuMess.isSelected()) {
            menuMess.setSelected(true);
            menuFind.setSelected(false);
            menuBox.setSelected(false);
            showMess();
        }
    }//GEN-LAST:event_menuMessActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private com.TextMind.component.MenuButton menuBox;
    private com.TextMind.component.MenuButton menuFind;
    private javax.swing.JLayeredPane menuList;
    private com.TextMind.component.MenuButton menuMess;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
