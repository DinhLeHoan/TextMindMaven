package testing;

import static com.TextMind.Socket.SocketManager.getSocket;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.entity.User;
import com.TextMind.main.main;

public class FindFriendTest {
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

	@Test
	public void TC_MODULE_FIND_FRIEND_01() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertNotNull(m.getHome().getMenu_left().getFad());
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_02() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText("contrau");
		;
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() > 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_03() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText("");
		;
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_04() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText("  ");
		;
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_05() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText("@@@@");
		;
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_06() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String a = "a";
		for (int i = 0; a.length() < 155; i++) {
			a = a + "a";
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getMenu_left().getFad().getTextField().setText(a);
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_07() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getMenu_left().getFad().getTextField().setText("☆☆☆☆☆☆");
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_08() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getMenu_left().getFad().getTextField().setText("cont");
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}

	@Test
	public void TC_MODULE_FIND_FRIEND_09() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getMenu_left().getFad().getTextField().setText("contraucutephomaique");
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount() == 1);
	}
}
