package testing;

import static com.TextMind.Socket.SocketManager.getSocket;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.component.Friend_Found;
import com.TextMind.component.Friend_Request;
import com.TextMind.component.Item_People;
import com.TextMind.entity.User;
import com.TextMind.event.PublicEvent;
import com.TextMind.main.main;

public class ViewFriendListTest {
	
	String jsonString1 = "{\"name\":\"Vĩ Khang\",\"username\":\"khangchoi\",\"password\":\"12345678\",\"uID\":\"vcC4ngvss1lWWmM8TCQA\",\"email\":\"dkhang709@gmail.com\",\"role\":\"user\"}";
	String jsonString2 = "{\"name\":\"nobita122\",\"username\":\"nobita122\",\"password\":\"nobita122\",\"uID\":\"1jrVmkG4VNmLHcWpuO1M\",\"email\":\"anhbndpc05737@fpt.edu.vn\",\"role\":\"user\"}";
	String jsonString3 = "{\"name\":\"khangzero\",\"username\":\"khangzero\",\"password\":\"khangzero\",\"uID\":\"oC2tNLee8HmCITSfMjIc\",\"email\":\"khangdvpc05402@fpt.edu.vn\",\"role\":\"user\"}";

	public void initAccount(String str) {		
		try {
			JSONObject jsonObject = new JSONObject(str);
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
	public void TC_VIEW_FRIENDLIST_01() {
		initAccount(jsonString1) ;
		main m2 = new main();
		m2.setVisible(true);
		m2.getHome().setUser(Auth.user);
		
		m2.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int totalFriend = m2.getHome().getMenu_left().getMenuList().getComponentCount();
		
		System.out.println(totalFriend);
		assertEquals(1, totalFriend);
	}
	
	@Test
	public void TC_VIEW_FRIENDLIST_02() {
		initAccount(jsonString2) ;
		main m2 = new main();
		m2.setVisible(true);
		m2.getHome().setUser(Auth.user);
		
		m2.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
int totalFriend = m2.getHome().getMenu_left().getMenuList().getComponentCount();
		
		System.out.println(totalFriend);
		assertEquals(2, totalFriend);
	}
	
	@Test
	public void TC_VIEW_FRIENDLIST_03() {
		initAccount(jsonString3) ;
		main m2 = new main();
		m2.setVisible(true);
		m2.getHome().setUser(Auth.user);
		
		m2.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int totalFriend = m2.getHome().getMenu_left().getMenuList().getComponentCount();
		
		System.out.println(totalFriend);
		assertEquals(0, totalFriend);
	}
}