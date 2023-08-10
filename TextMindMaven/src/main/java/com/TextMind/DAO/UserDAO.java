/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.DAO;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.entity.User;
import io.socket.emitter.Emitter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hoanl
 */
public class UserDAO {
    private ArrayList<User> listFriend = new ArrayList<>();
    private ArrayList<String> listFriendOnline = new ArrayList<>();
    private ListUpdateListener listUpdateListener;

    public UserDAO() {
        fillList();
        getOnline();
    }

    public interface ListUpdateListener {
        void onListUpdated();
    }

    public ArrayList<String> getListFriendOnline() {
        return listFriendOnline;
    }

    public void setListFriendOnline(ArrayList<String> listFriendOnline) {
        this.listFriendOnline = listFriendOnline;
        if (listUpdateListener != null) {
            listUpdateListener.onListUpdated();
        }
    }

    public void setListUpdateListener(ListUpdateListener listener) {
        this.listUpdateListener = listener;
    }

    public ArrayList<User> getListFriend() {
        return listFriend;
    }

    private void fillList() {
        listFriend.clear();
        getSocket().emit("getListFriend", Auth.user.getuID());
        getSocket().on("pushListFriend", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                String jsonString = os[0].toString();
                try {                  
                    JSONArray jsonArray = new JSONArray(jsonString);
                    
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.optString("name");
                        String uID = jsonObject.optString("uID");
                        if(!checkDeducate(uID)){
                            continue;
                        }
                        listFriend.add(new User(uID,name,false));
                        if (listUpdateListener != null) {
                            listUpdateListener.onListUpdated();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void getOnline() {
        listFriendOnline.clear();
        getSocket().emit("signInStatus", Auth.user.getuID());
        getSocket().once("getSignInStatus", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                JSONArray jsonArray = (JSONArray) os[0];
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        String uIDFriend = jsonArray.getString(i);
                        if (!listFriendOnline.contains(uIDFriend)) {
                            listFriendOnline.add(uIDFriend);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (listUpdateListener != null) {
                    listUpdateListener.onListUpdated();
                }
            }
        });
    }
    
    public boolean checkDeducate(String uID){
        for(User friend : listFriend){
            if(uID.equalsIgnoreCase(friend.getuID())){
                return false;
            }
        }
        return true;
    }
    

}
