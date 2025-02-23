/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.sfs2x;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;

/**
 *
 * @author Creeper-PC
 */
public class RoomAdded  extends BaseServerEventHandler {
    
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
       User user1 = (User) event.getParameter(SFSEventParam.USER);
       Zone theZone = (Zone) event.getParameter(SFSEventParam.ZONE);
       Room theRoom = (Room) event.getParameter(SFSEventParam.ROOM);
       String roomName = theRoom.getName();
       trace("sfs? User.RoomJoin   :::   user " + user1+ "   :::   zone "+ theZone + "   :::   room "+theRoom);
    }
    
}
