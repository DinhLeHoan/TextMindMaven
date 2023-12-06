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

public class P_RegisterTest {
	Login login = new Login();

    @BeforeMethod
    public void init() {
        login.setVisible(true);
        P_Login loginTest = (P_Login) login.getSlide().getList().get(0);

        loginTest.getBtnRegister().doClick();
    }
    
    @AfterMethod
    public void logout() {
        login.dispose();
    }
    
    @Test
    public void test1() {
    	P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
        registerTest.getTxtName().setText("");
        registerTest.getTxtEmail().setText("");
        registerTest.getTxtUsername().setText("");
        registerTest.getTxtPassword().setText("");
        registerTest.getTxtConfirm().setText("");
        registerTest.getTxtEmailConfirm().setText("");
        
        try {
			registerTest.validateInfor();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
    }
    
    @Test
    public void TC_QLKH_REGISTER_01() {
    	P_Login login;
//    	login.getBtnRegister().doClick();
        
    	
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
//        assertEquals(registerTest.getLblError().getText(), "Please fill all input field");
    }
    
    @Test
    public void TC_QLKH_REGISTER_03() {
    	P_Register registerTest = (P_Register) login.getSlide().getList().get(1);
       
    	registerTest.getBtnSend().doClick();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertEquals(registerTest.getLblError().getText(), "Mail is wrong format or being blank");
    }
    
    
    
}
