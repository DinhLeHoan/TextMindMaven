package testing;

import static com.TextMind.Socket.SocketManager.getSocket;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.entity.User;
import com.TextMind.event.PublicEvent;
import com.TextMind.form.P_Login;
import com.TextMind.main.Login;
import com.TextMind.main.main;

public class LoginAdminTest {

	@BeforeMethod
	public void init() {
		String jsonString = "{\"name\":\"kakashi\",\"username\":\"kakashi123\",\"password\":\"kakashi1234\",\"uID\":\"QObYrwAkpoy69dW90Ou4\",\"email\":\"hoandlpc05401@fpt.edu.vn\",\"role\":\"admin\"}";
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
	public void TC_SignIn_Admin_01() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(Auth.user.isIsAdmin());

	}

	@Test
	public void TC_getReport_Admin_01() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(m.getHome().getMenu_left().getMenuList().getComponentCount()>0);
		

	}
	@Test
	public void TC_unban_Admin_01() {
		main m = new main();
		Login login = new Login();

		m.setVisible(true);
		String emailBan = "hoanledinhlv100@gmail.com";
		m.getHome().setUser(Auth.user);

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText(emailBan);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_right().getBtnSignOut().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("hoanledinh456");
		loginTest.getTxtPassword().setText("hoanledinh456");

		try {
			Robot robot = new Robot();
			int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
			robot.mouseMove(screenWidth / 2, screenHeight / 2);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		loginTest.getBtnLogin().doClick();
		

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(Auth.user.getEmail(), emailBan);

		
		
	}
	
	@Test
	public void TC_unban_Admin_02() {
		main m = new main();
		Login login = new Login();

		m.setVisible(true);
		String emailBan = "hoanledinhlv100@gmail.com";
		m.getHome().setUser(Auth.user);

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText(" ");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_right().getBtnSignOut().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Robot robot = new Robot();
			int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
			robot.mouseMove(screenWidth / 2, screenHeight / 2);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		assertTrue(false);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void TC_unban_Admin_05() {
		main m = new main();
		Login login = new Login();

		m.setVisible(true);
		String emailBan = "hoanledinhlv100@gmail.com";
		m.getHome().setUser(Auth.user);
		int countRPBefore = m.getHome().getMenu_left().getMenuList().getComponentCount();
		int countRPNow = 0;
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getMenuFind().doClick();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getTextField().setText(emailBan);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_left().getFad().getBtnFindAndAdd().doClick();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Robot robot = new Robot();
			int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
			robot.mouseMove(screenWidth / 2, screenHeight / 2);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		m.getHome().getMenu_left().getMenuMess().doClick();
		countRPNow =  m.getHome().getMenu_left().getMenuList().getComponentCount();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(countRPNow, countRPBefore);
		
		
	}

}
