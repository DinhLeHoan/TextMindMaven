package testing;

import static com.TextMind.Socket.SocketManager.getSocket;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.component.Item_People;
import com.TextMind.entity.User;
import com.TextMind.event.PublicEvent;
import com.TextMind.form.P_Login;
import com.TextMind.main.Login;
import com.TextMind.main.main;

public class ChatTest {

	@BeforeMethod
	public void init() {
		String jsonString = "{\"name\":\"Vĩ Khang\",\"username\":\"khangchoi\",\"password\":\"12345678\",\"uID\":\"vcC4ngvss1lWWmM8TCQA\",\"email\":\"dkhang709@gmail.com\",\"role\":\"user\"}";
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
	public void TC_CHAT_01() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String titleName = m.getHome().getChat().getChatTitle().getLblName().getText();
		assertEquals("hoan", titleName);
	}

	@Test
	public void TC_CHAT_02() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText("");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	public void TC_CHAT_03() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText("Hello world");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	public void TC_CHAT_04() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText("Hello world");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(false);
	}

	@Test
	public void TC_CHAT_05() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText("Hello world");

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		enterPress();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText("12345678");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	public void TC_CHAT_07() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);

		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText("😃😗😘😝");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	public void TC_CHAT_21() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getTxt().setText(
				"Đợt đầu tiên sẽ có 100 cái áo. Vì sản xuất với quy mô nhỏ nên tụi mình quyết định không ship, không nhận pre order, ít nhất là trong đợt đầu tiên. Địa điểm thu mua sẽ được cập nhật trong thời gian tới. Size áo sẽ gồm M L XL XXL. Tỉ lệ như thế nào thì quên rồi. Nhưng nói chung là có 4 size. Cập nhật ngắn gọn cho mọi người. Cảm ơn!");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(true);
	}

	@Test
	public void TC_CHAT_15() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		m.getHome().getMenu_left().getMenuMess().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Item_People friend = (Item_People) m.getHome().getMenu_left().getMenuList().getComponent(0);

		PublicEvent.getInstance().getEventMain().selectUser(friend.getFriend());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getBtnMore().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		m.getHome().getChat().getChatBottom().getBtn().doClick();

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(false);
	}

	private void enterPress() {
		try {
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
