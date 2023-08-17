/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.event;

/**
 *
 * @author KHOA
 */
public class PublicEvent {
    private static PublicEvent instance;
    private EventImageView eventImageView;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventMain eventMain;
    private EventMenuLeft eventMenuLeft;
    private EventTitleChat eventTitleChat;
    
    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventImageView(EventImageView event) {
        this.eventImageView = event;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }
    
    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }
        
    public void addEventMain(EventMain event) {
        this.eventMain = event;
    }

    public void addEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }
        
    public EventImageView getEventImageView() {
        return eventImageView;
    }

    public EventChat getEventChat() {
        return eventChat;
    }
    
    public EventLogin getEventLogin() {
        return eventLogin;
    }
    
    public EventMain getEventMain() {
        return eventMain;
    }
    
    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }

    public EventTitleChat getEventTitleChat() {
        return eventTitleChat;
    }

    public void addEventTitleChat(EventTitleChat eventTitleChat) {
        this.eventTitleChat = eventTitleChat;
    }
    
}