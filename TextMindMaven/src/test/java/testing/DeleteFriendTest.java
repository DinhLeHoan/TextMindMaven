package testing;

import static com.TextMind.Socket.SocketManager.getSocket;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.component.Chat_Title;
import com.TextMind.component.Friend_Found;
import com.TextMind.component.Friend_Request;
import com.TextMind.component.Item_People;
import com.TextMind.entity.User;
import com.TextMind.event.PublicEvent;
import com.TextMind.main.main;

public class DeleteFriendTest {
	@BeforeMethod
	public void init() {
		String jsonString = "{\"name\":\"doremi123\",\"username\":\"doremi123\",\"password\":\"doremi123\",\"uID\":\"MvamJAGAOPR2uzE3jRYF\",\"email\":\"kczilk@gmail.com\",\"role\":\"user\"}";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String name = jsonObject.optString("name");
			String username = jsonObject.optString("username");
			String password = jsonObject.optString("password");
			String uID = jsonObject.optString("uID");
			String email = jsonObject.optString("email");
			String role = jsonObject.optString("role");
			if (role.equals("admin")) {
				Auth.user = new User(uID, name, username, password, email, true);
			} else {
				Auth.user = new User(uID, name, username, password, email, false);
			}

		} catch (JSONException e) {
			System.out.println(e);
		}
	}

	@AfterMethod
	public void logout() {

		if (Auth.user != null) {
			getSocket().emit("signOutStatus", Auth.user.getuID());
		}
		Auth.user = null;
	}

	@Test
	public void TC_MODULE_DELETE_FRIEND_02() {
		// Đăng nhập tài khoản 1
		main m = new main();
		m.setVisible(true);
		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText("shizuka123");
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Friend_Found ff = (Friend_Found) m.getHome().getMenu_left().getMenuList().getComponent(1);
		ff.getBtnAddFriend().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.setVisible(false);
		// Đăng xuất tài khoản 1
		if (Auth.user != null) {
			getSocket().emit("signOutStatus", Auth.user.getuID());
		}
		Auth.user = null;
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Đăng nhập tài khoản 2
		String jsonString = "{\"name\":\"shizuka123\",\"username\":\"shizuka123\",\"password\":\"shizuka123\",\"uID\":\"Y92SRzFQYFuFaaCUuzwf\",\"email\":\"ducanhnhatbui@gmail.com\",\"role\":\"user\"}";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String name = jsonObject.optString("name");
			String username = jsonObject.optString("username");
			String password = jsonObject.optString("password");
			String uID = jsonObject.optString("uID");
			String email = jsonObject.optString("email");
			String role = jsonObject.optString("role");

			if (role.equals("admin")) {
				Auth.user = new User(uID, name, username, password, email, true);
			} else {
				Auth.user = new User(uID, name, username, password, email, false);
			}
		} catch (JSONException e) {
			System.out.println(e);
		}

		main m2 = new main();
		m2.setVisible(true);
		m2.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2.getHome().getMenu_left().getMenuBox().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Đồng ý kết bạn
		Friend_Request fr = (Friend_Request) m2.getHome().getMenu_left().getMenuList().getComponent(0);
		fr.getBtnAccept().doClick();

		// Đăng xuất tài khoản 2
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (Auth.user != null) {
			getSocket().emit("signOutStatus", Auth.user.getuID());
		}
		m2.setVisible(false);
		Auth.user = null;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Đăng nhập tài khoản 1
		jsonString = "{\"name\":\"doremi123\",\"username\":\"doremi123\",\"password\":\"doremi123\",\"uID\":\"MvamJAGAOPR2uzE3jRYF\",\"email\":\"kczilk@gmail.com\",\"role\":\"user\"}";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String name = jsonObject.optString("name");
			String username = jsonObject.optString("username");
			String password = jsonObject.optString("password");
			String uID = jsonObject.optString("uID");
			String email = jsonObject.optString("email");
			String role = jsonObject.optString("role");
			if (role.equals("admin")) {
				Auth.user = new User(uID, name, username, password, email, true);
			} else {
				Auth.user = new User(uID, name, username, password, email, false);
			}

		} catch (JSONException e) {
			System.out.println(e);
		}

		main m3 = new main();
		m3.setVisible(true);
		m3.getHome().setUser(Auth.user);

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int totalFriend = m3.getHome().getMenu_left().getMenuList().getComponentCount();
		System.out.println(totalFriend);
		int indexFriendFind = -1;
		for (int i = 0; i < totalFriend; i++) {
			Item_People fre = (Item_People) m3.getHome().getMenu_left().getMenuList().getComponent(i);
			System.out.println(fre.getLblName().getText());
			if (fre.getLblName().getText().equalsIgnoreCase("shizuka123")) {
				indexFriendFind = i;
				break;
			}
		}
		System.out.println(indexFriendFind);

		Item_People friend = (Item_People) m3.getHome().getMenu_left().getMenuList().getComponent(indexFriendFind);
		
		Auth.uIDCurrentChat = friend.getFriend().getuID();
		
		JSONObject removeFriend = new JSONObject();
		try {
			removeFriend.put("uidFrom", Auth.uIDCurrentChat);
			removeFriend.put("uidTo", Auth.user.getuID());
			getSocket().emit("removeFriend", removeFriend);
			PublicEvent.getInstance().getEventChatBody().reset();
			PublicEvent.getInstance().getEventTitleChat().changeTitle(false);
			PublicEvent.getInstance().getEventChatBottom().setTyping(false);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m3.getHome().getMenu_left().getMenuBox();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m3.getHome().getMenu_left().getMenuMess();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(m3.getHome().getMenu_left().getMenuList().getComponentCount(), totalFriend - 1);
	}
	
	
}
