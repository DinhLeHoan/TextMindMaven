package testing;

import static com.TextMind.Socket.SocketManager.getSocket;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.entity.User;
import com.TextMind.main.Login;
import com.TextMind.main.main;
import com.TextMind.form.P_Login;

public class SignOutTest {
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
	public void TC_SIGN_OUT_01() {
		main m = new main();
		Login lg = new Login() ;
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_right().getBtnSignOut().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(lg.isVisible());
		
	}
	
	@Test
	public void TC_SIGN_OUT_02() {
		main m = new main();
		Login lg = new Login() ;
		m.setVisible(true);

		m.getHome().setUser(Auth.user);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.getHome().getMenu_right().getBtnSignOut().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		P_Login loginTest = (P_Login) lg.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("khangchoi");
		loginTest.getTxtPassword().setText("12345678");
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(Auth.user.getUsername(), "khangchoi");
		
	}
}