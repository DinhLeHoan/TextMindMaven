package testing;

import static com.TextMind.Socket.SocketManager.getSocket;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.awt.Component;
import java.awt.Robot;
import java.awt.event.InputEvent;
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
		String jsonString = "{\"name\":\"hoan\",\"username\":\"hoanledinh123\",\"password\":\"hoanledinh123\",\"uID\":\"3MDzySWIpuANDTMDfadG\",\"email\":\"dinhlehoan21101@gmail.com\",\"role\":\"user\"}";
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
	public void TC_MODULE_LOGIN_05() {
		main m = new main();
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		m.getHome().getMenu_left().getMenuList();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Component[] components = m.getHome().getMenu_left().getMenuList().getComponents();
		if (components.length > 0 && components[0] instanceof Item_People) {
			Item_People firstItem = (Item_People) components[0];

			simulateMouseClick(firstItem);

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			assertEquals(m.getHome().getChat().getChatTitle().getLblName().getText(), firstItem.getLblName().getText());
		}
	}

	private void simulateMouseClick(Item_People item) {
		SwingUtilities.invokeLater(() -> {
			try {
				Robot robot = new Robot();

				robot.mouseMove(item.getLocationOnScreen().x + item.getWidth() / 2,
						item.getLocationOnScreen().y + item.getHeight() / 2);

				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
