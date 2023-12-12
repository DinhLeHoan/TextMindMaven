package testing;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.component.Forgot_Password;
import com.TextMind.form.P_Login;
import com.TextMind.main.Login;

import static com.TextMind.Socket.SocketManager.getSocket;

public class P_LoginTest {
	Login login = new Login();

	@BeforeMethod
	public void init() {
		login.setVisible(true);
	}

	@AfterMethod
	public void logout() {

		if (Auth.user != null) {
			getSocket().emit("signOutStatus", Auth.user.getuID());

		}
		Auth.user = null;
		login.dispose();
	}

	@Test(priority =1)
	public void TC_MODULE_LOGIN_05() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("hoanledinh123");
		loginTest.getTxtPassword().setText("hoanledinh123");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(Auth.user.getUsername(), "hoanledinh123");
	}

	@Test(priority =2)
	public void TC_MODULE_LOGIN_14() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("hoanledinh123");
		loginTest.getTxtPassword().setText("hoanledinh12");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("Invalid password", loginTest.getLblError().getText());
	}

	@Test(priority =3)
	public void TC_MODULE_LOGIN_13() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("hoanledinh12");
		loginTest.getTxtPassword().setText("hoanledinh123");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("User not found", loginTest.getLblError().getText());
	}

	@Test(priority =4)
	public void TC_MODULE_LOGIN_04() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("");
		loginTest.getTxtPassword().setText("");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals("Invalid username or password!", loginTest.getLblError().getText());
	}

	@Test(priority =5)
	public void TC_MODULE_LOGIN_06() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("");
		loginTest.getTxtPassword().setText("hoanledinh123");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("Invalid username or password!", loginTest.getLblError().getText());
	}

	@Test(priority =6)
	public void TC_MODULE_LOGIN_07() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("hoanledinh123");
		loginTest.getTxtPassword().setText("");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("Invalid username or password!", loginTest.getLblError().getText());
	}

	@Test(priority =7)
	public void TC_MODULE_LOGIN_11() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("#%#%(*$%$*%");
		loginTest.getTxtPassword().setText("hoanledinh123");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("Invalid username or password!", loginTest.getLblError().getText());
	}

	@Test(priority =8)
	public void TC_MODULE_LOGIN_12() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getTxtUsername().setText("hoanledinh123");
		loginTest.getTxtPassword().setText("@#%#*(^$*(");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loginTest.login();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("Invalid username or password!", loginTest.getLblError().getText());
	}

	@Test(priority = 9)
	public void TC_MODULE_LOGIN_02() {
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getBtnRegister().doClick();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(login.getSlide().getCurrentShowing(), 1);

	}

}
