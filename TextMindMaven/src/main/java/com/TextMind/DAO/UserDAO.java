/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.DAO;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.entity.User;
import com.TextMind.entity.UserReported;
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
    private ArrayList<UserReported> listReport = new ArrayList<>();

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

    public ArrayList<UserReported> getListReport() {
        return listReport;
    }

    public void setListReport(ArrayList<UserReported> listReport) {
        this.listReport = listReport;
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

    public void fillList() {
        listFriend.clear();

        if(Auth.isAdmin()){
            getSocket().emit("getListReported", Auth.user.getuID());
            getSocket().on("pushListReported"+Auth.user.getuID(), new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    String jsonString = os[0].toString();
                    try {                  
                        JSONArray jsonArray = new JSONArray(jsonString);
                        System.out.println(jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.optString("name");
                            String uIDfrom = jsonObject.optString("uIDfrom");
                            String uIDto = jsonObject.optString("uIDto");
                            String detail = jsonObject.optString("detail");
                            if(!checkDeducate(uIDfrom)){
                                continue;
                            }
                            UserReported user = new UserReported();
                            user.setDetail(detail);
                            user.setIsOnline(false);
                            user.setuIDfrom(uIDfrom);
                            user.setName(name);
                            user.setuIDto(uIDto);
                            listReport.add(user);
                            if (listUpdateListener != null) {
                                listUpdateListener.onListUpdated();
                            }
                            System.out.println(name);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return;
        }
        else{
            getSocket().emit("getListFriend", Auth.user.getuID());
            getSocket().on("pushListFriend"+Auth.user.getuID(), new Emitter.Listener() {
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
        if(Auth.isAdmin()){
            for(UserReported friend : listReport){
                if(uID.equalsIgnoreCase(friend.getuIDfrom())){
                    return false;
                }
            }
        }
        else{
            for(User friend : listFriend){
                if(uID.equalsIgnoreCase(friend.getuID())){
                    return false;
                }
            }
        }
        return true;
    }
    

}
