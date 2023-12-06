package com.TextMind.form;

import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.event.EventMessage;
import com.TextMind.event.PublicEvent;
import com.TextMind.model.Model_Message;
import com.TextMind.model.Model_Register;
import com.TextMind.swing.MyPasswordField;
import com.TextMind.swing.MyTextField;
import io.socket.emitter.Emitter;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author KHOA
 */
public class P_Register extends javax.swing.JPanel {

    MyTextField txtName = new MyTextField();
    MyTextField txtEmail = new MyTextField();
    MyTextField txtEmailConfirm = new MyTextField();
    MyTextField txtUsername = new MyTextField();
    MyPasswordField txtPassword = new MyPasswordField();
    MyPasswordField txtConfirm = new MyPasswordField();
    
    private int countdown = 30; 
    private String code = null;
    private String validateMail = null;

    Button cmd = new Button();
    
    /**
     * Creates new form P_Login
     */
    public P_Register() {
        initComponents();
        initLogin();
        login.start();
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "5[]10[]10[]10[]10[]10[]10[]20[]5[]push"));
        JLabel label = new JLabel("Register");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(204, 255, 255));
        login.add(label);

        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        txtName.setHint("Name");
        login.add(txtName, "w 90%");
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 90%");
        txtUsername.setHint("Username");
        login.add(txtUsername, "w 90%");
        txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/images/user.png")));
        txtName.setPrefixIcon(new ImageIcon(getClass().getResource("/images/user.png")));
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));
        txtPassword.setHint("Password");
        login.add(txtPassword, "w 90%");
        txtConfirm.setHint("Password confirm");
        login.add(txtConfirm, "w 90%");
        txtConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/images/pass.png")));

        txtEmailConfirm.setHint("Verify code");
        txtEmailConfirm.setPrefixIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        login.add(txtEmailConfirm, "w 90%");

        btnSend.setText("Send Verify Code");
        btnSend.setBackground(new Color(0, 102, 204));
        btnSend.setForeground(new Color(250, 250, 250));
        
        login.add(btnSend, "w 40%, h 40");
        
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                String email = txtEmail.getText().trim();
                if(EmailValidator.getInstance().isValid(email) && !email.isBlank()) {
                    try {
                        // Your code to send the verify code
                        sendVerifyCode();
                        btnSend.setEnabled(false);

                        // Start the countdown
                        startCountdown();
                    } catch (JSONException ex) {
                        Logger.getLogger(P_Register.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    lblError.setText("Mail is wrong format or being blank");
                    txtEmail.grabFocus();
                    return;
                }
            }
        });
        
        lblError.setText("");
        lblError.setHorizontalAlignment(JLabel.CENTER);
        lblError.setVerticalAlignment(JLabel.CENTER);
        login.add(lblError, "w 80%, h 40");
        
        btnRegister.setText("Register");
        btnRegister.setBackground(new Color(0, 102, 204));
        btnRegister.setForeground(new Color(250, 250, 250));
//        btnRegister.setBorder(new RoundedBorder(10));
        login.add(btnRegister, "w 40%, h 40");
        
        btnLogin.setText("Login");
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(new Color(250, 250, 250));
        login.add(btnLogin, "w 40%, h 40");
        
        enterDown(txtName) ;
        enterDown(txtEmail) ;
        enterDown(txtEmailConfirm) ;
        enterDown(txtUsername) ;
        enterDown(txtPassword) ;
        enterDown(txtConfirm) ;        
    }

    public void validateInfor() throws JSONException {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = (new String(txtPassword.getPassword())).trim();
        String username = txtUsername.getText().trim();
        String confirmPassword = (new String(txtConfirm.getPassword())).trim();
        String pattermPassword = "^[A-Za-z0-9]{8,}$";
        
        String validateCode = txtEmailConfirm.getText().trim();
        
        if (name.isBlank() || email.isBlank() || password.isBlank() || username.isBlank() || confirmPassword.isBlank() || validateCode.isBlank()) {
            lblError.setText("Please fill all input field");
            return;
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            lblError.setText("Mail is wrong format");
            txtEmail.grabFocus();
            return;
        }

        if (!password.matches(pattermPassword) || !username.matches(pattermPassword)) {
            lblError.setText("<html>Password or Username is at least 8 word <br>and contain only alpha bet and number</html>");
            txtPassword.grabFocus();
            return;
        }

        if (!username.matches(pattermPassword)) {
            lblError.setText("<html>Username is at least 8 word and <br>contain only alpha bet and number</html>");
            txtUsername.grabFocus();
            return;
        }

        if (!password.equals(confirmPassword) ) {
            lblError.setText("Password do not match with confirm");
            txtConfirm.grabFocus();
            return;
        }
        
        if(!validateCode.equals(code)){
            lblError.setText("Verify Code wrong");
            txtEmailConfirm.grabFocus();
            return;
        }
        
        if(!validateMail.equals(email)){
            lblError.setText("Email does not match the verified email");
            txtEmailConfirm.grabFocus();
            return;
        }
        

        JSONObject data = new JSONObject();
        String randomString = RandomStringUtils.randomAlphanumeric(6);
        data.put("username", username);
        data.put("password", password);
        data.put("email", email);
        data.put("name", name);
        data.put("random", randomString);

        getSocket().emit("signUpCheck", data);
        getSocket().once("signUpValidate" + randomString, new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                boolean isSignUpValid = (boolean) os[0];
                // Handle the logic based on the received boolean value
                if (!isSignUpValid) {
                    lblError.setText("<html>Sign up error, username or email <br>already exist in database</html>");
                    return;
                } else {
                    lblError.setText("Sign up success");                    
                    resetField();
                    PublicEvent.getInstance().getEventLogin().goLogin();
                    return;
                }
            }
        });

        return;
    }

    private void resetField() {
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtUsername.setText("");
        txtConfirm.setText("");
    }
    
    private void enterDown(MyTextField txt) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == 10) {
                    try {
                        validateInfor() ;
                    } catch (JSONException ex) {
                        lblError.setText("Error");
                    }
                }
            }
        });
    }
    
    private void enterDown(MyPasswordField txt) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == 10) {
                    try {
                        validateInfor() ;
                    } catch (JSONException ex) {
                        lblError.setText("Error");
                    }
                }
            }
        });
    }
    
    private void sendVerifyCode() throws JSONException {
        String email = txtEmail.getText().trim();
        if(!EmailValidator.getInstance().isValid(email) || email.isBlank()) {
            lblError.setText("Mail is wrong format or being blank");
            txtEmail.grabFocus();
            return;
        }
        else{
            JSONObject data = new JSONObject();
            String randomString = RandomStringUtils.randomAlphanumeric(6);
            data.put("email", email);
            data.put("random", randomString);
            data.put("type", "verification");
            getSocket().emit("getValicateEmail", data);
            getSocket().once("verificationCodeSent"+randomString, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String jsonString = args[0].toString();
                try {                  

                        JSONObject jsonObject = new JSONObject(jsonString);
                        
                        String mailCode = jsonObject.optString("code");
                        String mailOfCode = jsonObject.optString("mailOfThis");
                        validateMail = mailOfCode;
                        code = mailCode;
                    }
                catch (Exception e) {
                    System.out.println(e);
                }
                }
            });
        
        }
    }
    
     private void startCountdown() {
        SwingWorker<Void, Void> countdownWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (countdown > 0) {
                    // Update the button text with the countdown message
                    SwingUtilities.invokeLater(() -> {
                        btnSend.setText("Wait for " + countdown + "s to resend");
                        btnSend.setForeground(Color.BLACK);
                    });

                    Thread.sleep(1000); // Sleep for 1 second
                    countdown--;
                }
                return null;
            }

            @Override
            protected void done() {
                // Re-enable the button and set the default text
                btnSend.setEnabled(true);
                btnSend.setText("Send Verify Code");
                countdown = 30; // Reset countdown
            }
        };

        countdownWorker.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new com.TextMind.Helper.CurvesPanel();
        lblError = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();

        lblError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setText("#Error");
        lblError.setToolTipText("");

        btnRegister.setForeground(new java.awt.Color(46, 163, 0));
        btnRegister.setText("Register");
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnLogin.setForeground(new java.awt.Color(0, 132, 245));
        btnLogin.setText("Back to login");
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 89, Short.MAX_VALUE))
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addGap(40, 40, 40)
                .addComponent(btnSend)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        try {

            validateInfor();
        } catch (JSONException ex) {
            Logger.getLogger(P_Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        PublicEvent.getInstance().getEventLogin().goLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSendActionPerformed

    
    public MyTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(MyTextField txtName) {
		this.txtName = txtName;
	}

	public MyTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(MyTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public MyTextField getTxtEmailConfirm() {
		return txtEmailConfirm;
	}

	public void setTxtEmailConfirm(MyTextField txtEmailConfirm) {
		this.txtEmailConfirm = txtEmailConfirm;
	}

	public MyTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(MyTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public MyPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(MyPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public MyPasswordField getTxtConfirm() {
		return txtConfirm;
	}

	public void setTxtConfirm(MyPasswordField txtConfirm) {
		this.txtConfirm = txtConfirm;
	}

	public javax.swing.JLabel getLblError() {
		return lblError;
	}

	public void setLblError(javax.swing.JLabel lblError) {
		this.lblError = lblError;
	}

	

	public javax.swing.JButton getBtnSend() {
		return btnSend;
	}




	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel lblError;
    private com.TextMind.Helper.CurvesPanel login;
    // End of variables declaration//GEN-END:variables
}
