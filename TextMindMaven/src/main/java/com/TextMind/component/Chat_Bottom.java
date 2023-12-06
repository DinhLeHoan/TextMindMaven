/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.TextMind.component;

import com.TextMind.event.EventChatBottom;
import com.TextMind.event.PublicEvent;
import com.TextMind.swing.JIMSendTextPane;
import com.TextMind.swing.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author KHOA
 */
public class Chat_Bottom extends javax.swing.JPanel {
    private MigLayout mig;
    private Panel_More panelMore;
    private JIMSendTextPane txt = new JIMSendTextPane();
    private JButton btn = new JButton();
    private JButton btnMore = new JButton();


    /**
     * Creates new form Chat_Title
     */
    public Chat_Bottom() {
        initComponents();
        init() ;
    }
    
    private void init() {
        mig = new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2[]0") ;
        setLayout(mig);
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        txt = new JIMSendTextPane();
        
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
                if (ke.getKeyChar() == 10 && ke.isControlDown()) {
                    eventSend(txt) ;
                }
            }
        });
        txt.setBorder(new EmptyBorder(5, 5, 5, 5));
        txt.setHintText("Nhập tin nhắn ...");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(229, 229, 229));
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]3[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        btn = new JButton();
        btn.setBorder(null);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setIcon(new ImageIcon(getClass().getResource("/images/send.png")));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eventSend(txt) ;
            }
        });
        btnMore = new JButton();
        btnMore.setBorder(null);
        btnMore.setContentAreaFilled(false);
        btnMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMore.setIcon(new ImageIcon(getClass().getResource("/images/more.png")));
        btnMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelMore.isVisible()) {
                    btnMore.setIcon(new ImageIcon(getClass().getResource("/images/more.png")));
                    panelMore.setVisible(false);
                    mig.setComponentConstraints(panelMore, "dock south,h 0!");
                    revalidate();
                } else {
                    btnMore.setIcon(new ImageIcon(getClass().getResource("/images/moreSelect.png")));
                    panelMore.setVisible(true);
                    mig.setComponentConstraints(panelMore, "dock south,h 170!");
                    revalidate();
                }
            }
        });
        panel.add(btnMore);
        panel.add(btn);
        add(panel, "wrap");
        panelMore = new Panel_More() ;
        panelMore.setVisible(false);
        add(panelMore, "dock south,h 0!");
        initEvent(txt);
    }
    
    private void eventSend(JIMSendTextPane txt) {
        String text = txt.getText().trim();
                if (!text.equals("")) {
                    PublicEvent.getInstance().getEventChat().sendMessage(text);
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                }
    }

    private void refresh() {
        revalidate();
    }
    
    
    
    public MigLayout getMig() {
		return mig;
	}

	public void setMig(MigLayout mig) {
		this.mig = mig;
	}

	public Panel_More getPanelMore() {
		return panelMore;
	}

	public void setPanelMore(Panel_More panelMore) {
		this.panelMore = panelMore;
	}

	public JIMSendTextPane getTxt() {
		return txt;
	}

	public void setTxt(JIMSendTextPane txt) {
		this.txt = txt;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public JButton getBtnMore() {
		return btnMore;
	}

	public void setBtnMore(JButton btnMore) {
		this.btnMore = btnMore;
	}

	private void initEvent(JIMSendTextPane txt) {
        PublicEvent.getInstance().setEventChatBottom(new EventChatBottom() {
            @Override
            public void setTyping(boolean isRemove) {
                txt.setEditable(isRemove);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
