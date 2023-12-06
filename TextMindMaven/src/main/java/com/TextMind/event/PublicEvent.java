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
    private EventChatBody eventChatBody;
    private EventMenuRight eventMenuRight;
    private EventChatBottom eventChatBottom;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {
    	
    }

    public EventMenuRight getEventMenuRight() {
        return eventMenuRight;
    }

    public void addEventMenuRight(EventMenuRight eventMenuRight) {
        this.eventMenuRight = eventMenuRight;
    }

    public EventChatBottom getEventChatBottom() {
        return eventChatBottom;
    }

    public void setEventChatBottom(EventChatBottom eventChatBottom) {
        this.eventChatBottom = eventChatBottom;
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

    public EventChatBody getEventChatBody() {
        return eventChatBody;
    }

    public void addEventChatBody(EventChatBody eventChatBody) {
        this.eventChatBody = eventChatBody;
    }
    
    
}