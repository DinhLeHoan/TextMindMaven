/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TextMind.event;

import com.TextMind.model.Model_Register;

/**
 *
 * @author KHOA
 */
public interface EventLogin {
    public void login();

//    public void register(Model_Register data, EventMessage message);
    public void register() ;

    public void goRegister();

    public void goLogin();
    
    public void reLogin();
}
