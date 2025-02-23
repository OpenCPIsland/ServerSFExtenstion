/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.chat;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
/**
 *
 * @author Creeper
 */
public class CHAT_ACTIVITY  extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
       SFSObject data = new SFSObject();
       Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
       Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
       data.putInt("senderId", penguin.getId());
       SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("chat.activity",data,room.getUserList(),room,false);
       trace("chat? chat.activity   :::   " + params.toJson());
    }
    
}
