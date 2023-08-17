/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.event.EventChatBody;
import com.TextMind.event.PublicEvent;
import com.TextMind.form.Menu_Left;
import com.TextMind.swing.ScrollBar;
import io.socket.emitter.Emitter;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class Chat_Body extends javax.swing.JPanel {
    private String uIDFriend;
    /**
     * Creates new form Chat_Body
     */
    public Chat_Body() {
        initComponents();
        init() ;
        initEvent();
    }

    public void setuIDFriend(String uIDFriend) {
        this.uIDFriend = uIDFriend;
        fillMess();
    }
    
    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5")); 
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
       
    }
    
    public void addItemLeft(String text, String user,String date, Icon... image) {
        Chat_Left item = new Chat_Left();
        item.setText(text);
//        item.setImage(image);
        item.setTime(date);
//        item.setUserProfile(user);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }

    public void addItemLeft(String text, String user, String[] image) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setImage(image);
//        item.setTime(date);
        item.setUserProfile(user);
        body.add(item, "wrap, w 100::80%"); 
        body.repaint();
        body.revalidate();
    }
    
    public void addItemRight(String text,String date, Icon... image) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setTime(date);
        item.setImage(image);
        body.add(item, "wrap, al right, w 100::80%");
        body.repaint();
        body.revalidate();
        scrollToBottom() ;
    }
    
    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }
    
    public void addItemFile(String text, String user, String fileName, String fileSize) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setFile(fileName, fileSize);
//        item.setTime();
        item.setUserProfile(user);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    public void addItemFileRight(String text, String fileName, String fileSize) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setFile(fileName, fileSize);
        body.add(item, "wrap, al right, w 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }
    
    private void fillMess(){
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("uidTo", uIDFriend); // Replace "destinationUserID" with the actual destination user's ID
            requestData.put("uidFrom", Auth.user.getuID());
        } catch (JSONException ex) {
            Logger.getLogger(Menu_Left.class.getName()).log(Level.SEVERE, null, ex);
        }

        getSocket().emit("LoadMess", requestData);
        getSocket().once("messToSwing", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                String jsonString = os[0].toString();
                try {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.optString("name");
                        String message = jsonObject.optString("message");
                        String date = jsonObject.optString("date");
                        if(name.trim().equalsIgnoreCase(Auth.user.getName()))
                            {
                                addItemRight(message,date);
                            }
                        else{
                            addItemLeft(message,name,date);
                        }
                    }
                } catch (JSONException e) {
                }
            }
        });
    }

    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }
    
    private void initEvent() {
        PublicEvent.getInstance().addEventChatBody(new EventChatBody() {
            @Override
            public void reset() {
                clearChat();
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

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        setBackground(new java.awt.Color(168, 168, 167));

        sp.setBackground(new java.awt.Color(167, 168, 167));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(153, 175, 186));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
