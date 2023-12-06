package testing;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TextMind.Auth.Auth;
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
    	if(Auth.user!=null) {
            getSocket().emit("signOutStatus", Auth.user.getuID());
    	}
        Auth.user = null;
        login.dispose();
    }

    @Test
    public void test1() {
        P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
        loginTest.getTxtUsername().setText("hoanledinh123");
        loginTest.getTxtPassword().setText("hoanledinh123");
        loginTest.login();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotNull(Auth.user);
    }
    
    @Test
    public void test2() {
        P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
        loginTest.getTxtUsername().setText("hoanledinh123");
        loginTest.getTxtPassword().setText("hoanledinh12");
        loginTest.login();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNull(Auth.user);
    }
    
    @Test 
    public void test3() {
        P_Login loginTest = (P_Login) login.getSlide().getList().get(0);
        loginTest.getTxtUsername().setText("");
        loginTest.getTxtPassword().setText("");
        loginTest.login();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNull(Auth.user);
    }
}
