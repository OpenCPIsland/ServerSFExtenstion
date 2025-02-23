/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class CHAT_ACTIVITY_CANCEL  extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
       SFSObject data = new SFSObject();
       Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
       Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
       data.putInt("senderId", penguin.getId());  
       SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("chat.activity_cancel",data,room.getUserList(),room,false);
       trace("chat? chat.activity_cancel   :::   " + params.toJson());
    }
    
}
