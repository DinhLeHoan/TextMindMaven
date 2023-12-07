package testing;

import static com.TextMind.Socket.SocketManager.getSocket;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
import com.TextMind.event.PublicEvent;
import com.TextMind.form.P_Login;
import com.TextMind.form.P_Register;
import com.TextMind.main.Login;
import com.TextMind.swing.PanelSlide;

public class P_RegisterTest {
	Login login = new Login();

	@BeforeMethod
	public void init() {
		login.setVisible(true);
	}

	@AfterMethod
	public void logout() {
		login.dispose();
	}

	@Test
	public void TC_QLKH_REGISTER_01() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(login.getSlide().getCurrentShowing(), 1);
	}

	@Test
	public void TC_QLKH_REGISTER_02() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
		loginTest.getBtnRegister().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Chuyển slide Login
		P_Register regisTest = (P_Register) login.getSlide().getList().get(1);
		regisTest.getBtnLogin().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(login.getSlide().getCurrentShowing(), 0);
	}

	@Test
	public void TC_QLKH_REGISTER_03() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);

		registerTest.getBtnSend().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}

	@Test
	public void TC_QLKH_REGISTER_04() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("DucAnh");
		registerTest.getTxtEmail().setText("");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		try {
			registerTest.getBtnSend().doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}

	@Test
	public void TC_QLKH_REGISTER_05() {

		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");

		registerTest.getBtnSend();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		registerTest.getTxtEmailConfirm().setText(registerTest.getCode());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getCode(), null);
	}

	@Test
	public void TC_QLKH_REGISTER_06() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("");
		registerTest.getTxtEmail().setText("");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}

	@Test
	public void TC_QLKH_REGISTER_07() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("");
		registerTest.getTxtEmail().setText("");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}

	@Test
	public void TC_QLKH_REGISTER_08() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("");
		registerTest.getTxtEmail().setText("");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("ducanh123");
		registerTest.getTxtEmailConfirm().setText("");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}

	@Test
	public void TC_QLKH_REGISTER_09() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");
		
		registerTest.getBtnSend().doClick();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertNotNull(code);
	}

	@Test
	public void TC_QLKH_REGISTER_10() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");
		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}

	@Test
	public void TC_QLKH_REGISTER_11() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");
		registerTest.getBtnSend().doClick();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertNotNull(code);
	}

	@Test
	public void TC_QLKH_REGISTER_12() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("ducanh123");
		registerTest.getBtnSend().doClick();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertNotNull(code);
	}

	@Test
	public void TC_QLKH_REGISTER_13() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("");
		registerTest.getBtnSend().doClick();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertNotNull(code);
	}

	@Test
	public void TC_QLKH_REGISTER_14() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);

		registerTest.getBtnRegister().doClick();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
	}

	@Test
	public void TC_QLKH_REGISTER_15() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		try {
			registerTest.getBtnRegister().doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
	}
	
	@Test
	public void TC_QLKH_REGISTER_16() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		try {
			registerTest.getBtnRegister().doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
	}
	
	@Test
	public void TC_QLKH_REGISTER_17() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		try {
			registerTest.getBtnRegister().doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
	}
	
	@Test
	public void TC_QLKH_REGISTER_18() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		try {
			registerTest.getBtnRegister().doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
	}
	
	@Test
	public void TC_QLKH_REGISTER_19() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");
		registerTest.getTxtEmailConfirm().setText("");

		try {
			registerTest.getBtnRegister().doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
	}
	@Test
	public void TC_QLKH_REGISTER_20() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynhgmail.com");
		registerTest.getTxtUsername().setText("");
		registerTest.getTxtPassword().setText("");
		registerTest.getTxtConfirm().setText("");
		registerTest.getTxtEmailConfirm().setText("");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
	}
	@Test
	public void TC_QLKH_REGISTER_21() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("duc");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(), "<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
	}

	@Test
	public void TC_QLKH_REGISTER_22() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123@");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(), "<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
	}
	@Test
	public void TC_QLKH_REGISTER_23() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("duca");
		registerTest.getTxtConfirm().setText("ducanh123");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(),
				"<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
	}

	@Test
	public void TC_QLKH_REGISTER_24() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123@");
		registerTest.getTxtConfirm().setText("ducanh123");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(),
				"<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
	}

	@Test
	public void TC_QLKH_REGISTER_25() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123@");
		registerTest.getTxtConfirm().setText("ducanh123@");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(),
				"<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
	}

	@Test
	public void TC_QLKH_REGISTER_26() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh124");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText(code);
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(), "Password do not match with confirm");
	}
	
	@Test
	public void TC_QLKH_REGISTER_27() {
		// Chuyển slide Register
		P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

		loginTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
		registerTest.getTxtName().setText("Duc Anh");
		registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
		registerTest.getTxtUsername().setText("ducanh123");
		registerTest.getTxtPassword().setText("ducanh123");
		registerTest.getTxtConfirm().setText("ducanh123");

		registerTest.getBtnSend().doClick();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String code = registerTest.getCode();
		registerTest.getTxtEmailConfirm().setText("111111111111111111");
		registerTest.getBtnRegister().doClick();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(registerTest.getLblError().getText(), "Verify Code wrong");
	}
    @Test
    public void TC_QLKH_REGISTER_28() {
    	//Chuyển slide Register
    	P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
    	
	    loginTest.getBtnRegister().doClick();
	    try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    
    	P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
    	
        registerTest.getTxtName().setText("DucAnh");
        registerTest.getTxtEmail().setText("ducan.hthienbinh.suhuynh@gmail.com");
        registerTest.getTxtUsername().setText("ducanh123");
        registerTest.getTxtPassword().setText("ducanh123");
        registerTest.getTxtConfirm().setText("ducanh123");
        registerTest.getBtnSend().doClick();;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String code = registerTest.getCode();
        System.out.println(code);
        registerTest.getTxtEmailConfirm().setText(code);
        
        try {
			registerTest.validateInfor();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        registerTest.getBtnLogin().doClick();
        
        loginTest.getTxtUsername().setText("ducanh123");
        loginTest.getTxtPassword().setText("ducanh123");
        loginTest.getBtnLogin().doClick();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertEquals( Auth.user.getUsername(), "ducanh123");
    }	

}
