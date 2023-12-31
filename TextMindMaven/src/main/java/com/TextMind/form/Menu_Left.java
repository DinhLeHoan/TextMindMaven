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
import com.TextMind.component.Banned_People;
import com.TextMind.component.Reported_People;
import com.TextMind.entity.User;
import com.TextMind.entity.UserReported;
import com.TextMind.event.EventMenuLeft;
import com.TextMind.event.PublicEvent;
import com.TextMind.swing.FindAndAdd;
import com.TextMind.swing.ScrollBar;
import io.socket.emitter.Emitter;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class Menu_Left extends javax.swing.JPanel implements UserDAO.ListUpdateListener {
	private UserDAO listFriend;
	private ArrayList<String> listFriendOnline = new ArrayList<>();
	
	private FindAndAdd fad;	
	private Friend_Request fr;
	private Friend_Found ff;

	/**
	 * Creates new form Menu_Left
	 */
	public Menu_Left() {
		initComponents();
		init();
		listFriend.setListUpdateListener((UserDAO.ListUpdateListener) this);

		getSocket().on("FindResult" + Auth.user.getuID(), new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				showFindFriend();
				String jsonString = args[0].toString();
				try {
					JSONArray jsonArray = new JSONArray(jsonString);
					if (jsonArray.length() == 0) {
						showFindFriend();
						return;
					}
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						User found = new User();
						String name = jsonObject.optString("name");
						String uID = jsonObject.optString("uID");
						if (uID.equals(Auth.user.getuID())) {
							return;
						}
						if (checkFindDeducate(uID)) {
							found.setName(name);
							found.setuID(uID);
							ff = new Friend_Found(found);
							if (!listFriend.checkDeducate(uID)) {
								ff.setDisableSend();
							}
							menuList.add(ff, "wrap");
						}
					}
				} catch (JSONException e) {
					menuList.removeAll();
				}
			}
		});

		getSocket().on("foundUsersSendRQ" + Auth.user.getuID(), new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				String jsonString = args[0].toString();
				try {
					JSONArray jsonArray = new JSONArray(jsonString);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						User found = new User();
						String name = jsonObject.optString("name");
						String uID = jsonObject.optString("uID");
						if (checkRQDeducate(uID) && menuBox.isSelected()) {
							found.setName(name);
							found.setuID(uID);
							fr = new Friend_Request(found);
							menuList.add(fr, "wrap");
						}
					}
				} catch (JSONException e) {
					menuList.removeAll();
				}
			}
		});

		getSocket().on("newFriend" + Auth.user.getuID(), new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				listFriend.fillList();
				String jsonString = args[0].toString();

				// Parse the JSON string to get the boolean value
				Boolean isSuccess = Boolean.parseBoolean(jsonString);
				PublicEvent.getInstance().getEventTitleChat().changeTitle(false);
				PublicEvent.getInstance().getEventChatBody().reset();
				showMess();
			}
		});

		getSocket().on("notNewFriend" + Auth.user.getuID(), new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				showBox();
			}
		});

		getSocket().on("someOneSendRQ" + Auth.user.getuID(), new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				String jsonString = args[0].toString();
				try {

					JSONObject jsonObject = new JSONObject(jsonString);
					User found = new User();
					String name = jsonObject.optString("name");
					String uID = jsonObject.optString("uID");
					if (checkRQDeducate(uID) && menuBox.isSelected()) {
						found.setName(name);
						found.setuID(uID);
						fr = new Friend_Request(found);
						menuList.add(fr, "wrap");

					}
				} catch (Exception e) {
					menuList.removeAll();
				}
			}
		});

		getSocket().on("banUser" + Auth.user.getuID(), new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				PublicEvent.getInstance().getEventMenuRight().logOut();
			}
		});

		if (Auth.isAdmin()) {
//            menuFind.setVisible(false);
			menuBox.setVisible(false);
			getSocket().on("newReport", new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					listFriend.fillList();

				}
			});

		}
	}

	private void init() {
		listFriend = new UserDAO();
		sp.setVerticalScrollBar(new ScrollBar());
		menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
		getSocket().on("getSignInStatus", new Emitter.Listener() {
			@Override
			public void call(Object... os) {
				JSONArray jsonArray = (JSONArray) os[0];
				listFriendOnline.clear();
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						String uIDFriend = jsonArray.getString(i);
						if (!listFriendOnline.contains(uIDFriend)) {
							listFriendOnline.add(uIDFriend);
						}
					} catch (JSONException ex) {
						System.out.println(ex);
					}

				}
				if (!listFriendOnline.contains(Auth.uIDCurrentChat)) {
					PublicEvent.getInstance().getEventTitleChat().changeStatus(false);
				} else {
					PublicEvent.getInstance().getEventTitleChat().changeStatus(true);
				}
				updateOnlineFriends(listFriendOnline);
			}

		});
		initEvent();
	}

	private void showMess() {
		menuList.removeAll();

		if (Auth.isAdmin()) {
			for (UserReported friendData : listFriend.getListReport()) {
				Reported_People friend = new Reported_People(friendData);
				if (listFriendOnline.contains(friendData.getuIDto())) {
					friend.setActive(true);
				} else {
					friend.setActive(false);
				}
				menuList.add(friend, "wrap");
			}
		} else {
			for (User friendData : listFriend.getListFriend()) {
				Item_People friend = new Item_People(friendData);
				if (listFriendOnline.contains(friendData.getuID())) {
					friend.setActive(true);
				} else {
					friend.setActive(false);
				}
				menuList.add(friend, "wrap");
			}
			refreshMenuList();
		}

	}

	private void showFindFriend() {
		menuList.removeAll();
		fad = new FindAndAdd();
		menuList.add(fad, "wrap");

		refreshMenuList();
	}

	private void showBox() {
		menuList.removeAll();
		getFriendRQ();
		refreshMenuList();
	}

	private void refreshMenuList() {
		menuList.repaint();
		menuList.revalidate();
	}

	private void getFriendRQ() {
		if (menuBox.isSelected()) {
			getSocket().emit("getAllRequestFriend", Auth.user.getuID());
		}
	}

	public void onListUpdated() {
		if (menuMess.isSelected()) {
			showMess();
		}
	}

	private void updateOnlineFriends(ArrayList<String> onlineFriends) {
		for (Component component : menuList.getComponents()) {
			if (component instanceof Item_People) {

				Item_People friend = (Item_People) component;
				String friendID = friend.getFriend().getuID();
				// Check if the friend's ID is in the online list
				boolean isOnline = listFriendOnline.contains(friendID);
				friend.setActive(isOnline);

			}
		}
		refreshMenuList();
	}

	private boolean checkFindDeducate(String uID) {
		for (Component component : menuList.getComponents()) {
			if (component instanceof Friend_Found) {

				ff = (Friend_Found) component;
				String friendID = ff.getFriend().getuID();
				// Check if the friend's ID is in the online list
				if (uID.equalsIgnoreCase(friendID)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkRQDeducate(String uID) {
		for (Component component : menuList.getComponents()) {
			if (component instanceof Friend_Request) {

				fr = (Friend_Request) component;
				String friendID = fr.getFriendRQ().getuID();
				// Check if the friend's ID is in the online list
				if (uID.equalsIgnoreCase(friendID)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean bannedUser(String uID) {
		for (Component component : menuList.getComponents()) {
			if (component instanceof Reported_People) {

				Reported_People friend = (Reported_People) component;
				if (friend.getFriend().getuIDto().equals(uID)) {
					friend.setVisible(false);
				}
			}
		}
		return true;
	}

	private void initEvent() {
		PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {

			@Override
			public void showFound(ArrayList<User> list) {
				throw new UnsupportedOperationException("Not supported yet."); // Generated from
																				// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
			}

			@Override
			public void unShowBanned(String id) {
				bannedUser(id);
			}

		});

	}

	public javax.swing.JLayeredPane getMenuList() {
		return menuList;
	}

	public void setMenuList(javax.swing.JLayeredPane menuList) {
		this.menuList = menuList;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		menu = new javax.swing.JLayeredPane();
		menuMess = new com.TextMind.component.MenuButton();
		menuFind = new com.TextMind.component.MenuButton();
		menuBox = new com.TextMind.component.MenuButton();
		sp = new javax.swing.JScrollPane();
		menuList = new javax.swing.JLayeredPane();

		setBackground(new java.awt.Color(218, 235, 242));

		menu.setBackground(new java.awt.Color(0, 153, 255));
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

		sp.setBackground(new java.awt.Color(218, 235, 242));
		sp.setBorder(null);
		sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		menuList.setBackground(new java.awt.Color(218, 235, 242));
		menuList.setForeground(new java.awt.Color(153, 204, 255));

		javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
		menuList.setLayout(menuListLayout);
		menuListLayout.setHorizontalGroup(menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		menuListLayout.setVerticalGroup(menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 534, Short.MAX_VALUE));

		sp.setViewportView(menuList);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(3, 3, 3).addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuBoxActionPerformed
		if (!menuBox.isSelected()) {
			menuMess.setSelected(false);
			menuFind.setSelected(false);
			menuBox.setSelected(true);

			getFriendRQ();

			showBox();
		}
	}// GEN-LAST:event_menuBoxActionPerformed

	private void menuFindActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuFindActionPerformed

		if (!menuFind.isSelected()) {
			menuMess.setSelected(false);
			menuFind.setSelected(true);
			menuBox.setSelected(false);
			showFindFriend();
		}
	}// GEN-LAST:event_menuFindActionPerformed

	private void menuMessActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuMessActionPerformed

		if (!menuMess.isSelected()) {
			menuMess.setSelected(true);
			menuFind.setSelected(false);
			menuBox.setSelected(false);

			getSocket().emit("signInStatus", Auth.user.getuID());
			showMess();
		}
	}// GEN-LAST:event_menuMessActionPerformed

	public com.TextMind.component.MenuButton getMenuFind() {
		return menuFind;
	}

	public void setMenuFind(com.TextMind.component.MenuButton menuFind) {
		this.menuFind = menuFind;
	}

	public FindAndAdd getFad() {
		return fad;
	}

	public void setFad(FindAndAdd fad) {
		this.fad = fad;
	}

	public com.TextMind.component.MenuButton getMenuBox() {
		return menuBox;
	}

	public void setMenuBox(com.TextMind.component.MenuButton menuBox) {
		this.menuBox = menuBox;
	}

	public com.TextMind.component.MenuButton getMenuMess() {
		return menuMess;
	}

	public void setMenuMess(com.TextMind.component.MenuButton menuMess) {
		this.menuMess = menuMess;
	}

	public Friend_Request getFr() {
		return fr;
	}

	public void setFr(Friend_Request fr) {
		this.fr = fr;
	}

	public Friend_Found getFf() {
		return ff;
	}

	public void setFf(Friend_Found ff) {
		this.ff = ff;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLayeredPane menu;
	private com.TextMind.component.MenuButton menuBox;
	private com.TextMind.component.MenuButton menuFind;
	private javax.swing.JLayeredPane menuList;
	private com.TextMind.component.MenuButton menuMess;
	private javax.swing.JScrollPane sp;
	// End of variables declaration//GEN-END:variables
}
