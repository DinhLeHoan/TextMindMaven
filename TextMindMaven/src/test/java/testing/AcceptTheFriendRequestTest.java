package testing;

import static com.TextMind.Socket.SocketManager.getSocket;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.component.Friend_Found;
import com.TextMind.component.Friend_Request;
import com.TextMind.entity.User;
import com.TextMind.main.main;

public class AcceptTheFriendRequestTest {
	@BeforeMethod
	public void init() {
		String jsonString = "{\"name\":\"nobita122\",\"username\":\"nobita122\",\"password\":\"nobita122\",\"uID\":\"1jrVmkG4VNmLHcWpuO1M\",\"email\":\"anhbndpc05737@fpt.edu.vn\",\"role\":\"user\"}";
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

//	@Test
//	public void TC_MODULE_FIND_FRIEND_01() {
//		main m = new main();
//		m.setVisible(true);
//
//		m.getHome().setUser(Auth.user);
//		try {
//			TimeUnit.SECONDS.sleep(5);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		m.getHome().getMenu_left().getMenuBox().doClick();
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		assertNotNull(m.getHome().getMenu_left().getFr());
//	}
//
//	@Test
//	public void TC_MODULE_ACCEPT_THE_FRIEND_REQUEST_02() {
//		main m = new main();
//		m.setVisible(true);
//		m.getHome().setUser(Auth.user);
//
//		m.getHome().getMenu_left().getMenuFind().doClick();
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		m.getHome().getMenu_left().getFad().getTextField().setText("Duc Anh");
//		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
//
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Friend_Found ff = (Friend_Found) m.getHome().getMenu_left().getMenuList().getComponent(1);
//		ff.getBtnAddFriend().doClick();
//		
//		if (Auth.user != null) {
//			getSocket().emit("signOutStatus", Auth.user.getuID());
//		}
//		Auth.user = null;
//		String jsonString = "{\"name\":\"Duc Anh\",\"username\":\"ducanh122\",\"password\":\"ducanh123\",\"uID\":\"XmZNNKJONosS4XD6cxzD\",\"email\":\"ducanh.tuyen.kiukiu@gmail.com\",\"role\":\"user\"}";
//		try {
//			JSONObject jsonObject = new JSONObject(jsonString);
//			String name = jsonObject.optString("name");
//			String username = jsonObject.optString("username");
//			String password = jsonObject.optString("password");
//			String uID = jsonObject.optString("uID");
//			String email = jsonObject.optString("email");
//			String role = jsonObject.optString("role");
//
//			if (role.equals("admin")) {
//				Auth.user = new User(uID, name, username, password, email, true);
//			} else {
//				Auth.user = new User(uID, name, username, password, email, false);
//			}
//
//		} catch (JSONException e) {
//			System.out.println(e);
//		}
//
//		main m2 = new main();
//		m2.setVisible(true);
//		m2.getHome().setUser(Auth.user);
//
//		m2.getHome().getMenu_left().getMenuBox().doClick();
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Friend_Request fr = (Friend_Request) m2.getHome().getMenu_left().getMenuList().getComponent(0);
//		fr.getBtnDeny().doClick();
//		
//		try {
//			TimeUnit.SECONDS.sleep(2);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		if (m2.getHome().getMenu_left().getMenuList().getComponentCount() != 0) {
//			fr = (Friend_Request) m2.getHome().getMenu_left().getMenuList().getComponent(0);
//			String frName = fr.getLblName().getText();
//			System.out.println(frName);
//			assertNotEquals(frName, "nobita122");
//		}else {
//			assertEquals(m2.getHome().getMenu_left().getMenuList().getComponentCount(), 0);
//		}	
//	}

	@Test
	public void TC_MODULE_ACCEPT_THE_FRIEND_REQUEST_03() {
		main m = new main();
		m.setVisible(true);
		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText("Duc Anh");
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Friend_Found ff = (Friend_Found) m.getHome().getMenu_left().getMenuList().getComponent(1);
		ff.getBtnAddFriend().doClick();
		
		if (Auth.user != null) {
			getSocket().emit("signOutStatus", Auth.user.getuID());
		}
		Auth.user = null;
		String jsonString = "{\"name\":\"Duc Anh\",\"username\":\"ducanh122\",\"password\":\"ducanh123\",\"uID\":\"XmZNNKJONosS4XD6cxzD\",\"email\":\"ducanh.tuyen.kiukiu@gmail.com\",\"role\":\"user\"}";
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

		m2.getHome().getMenu_left().getMenuBox().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Friend_Request fr = (Friend_Request) m2.getHome().getMenu_left().getMenuList().getComponent(0);
		fr.getBtnAccept().doClick();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		m2.getHome().getMenu_left().getMenuMess().doClick();
		int totalFriend = m2.getHome().getMenu_left().getMenuList().getComponentCount();
		
//		for (int i = 0; i < totalFriend; i++) {
//			
//		}
//		
//		
//		if (m2.getHome().getMenu_left().getMenuList().getComponentCount() != 0) {
//			fr = (Friend_Request) m2.getHome().getMenu_left().getMenuList().getComponent(0);
//			String frName = fr.getLblName().getText();
//			System.out.println(frName);
//			assertNotEquals(frName, "nobita122");
//		}else {
//			assertEquals(m2.getHome().getMenu_left().getMenuList().getComponentCount(), 0);
//		}	
//	}
	
}
