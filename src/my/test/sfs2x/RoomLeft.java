/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.sfs2x;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

/**
 *
 * @author Creeper-PC
 */
public class RoomLeft extends BaseServerEventHandler {
    
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
       User user1 = (User) event.getParameter(SFSEventParam.USER);
       Zone theZone = (Zone) event.getParameter(SFSEventParam.ZONE);
       Room theRoom = (Room) event.getParameter(SFSEventParam.ROOM);
       String roomName = theRoom.getName();
	   
	   Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
       Room room = zone.getRoomByName(user1.getSession().getProperty("CurrentRoom").toString());
	   
        if (room.getUserList().isEmpty()) {
            // Delete the room
            getApi().emptyRoom(room, true);
            //  getApi().deleteRoom(room, true);
            trace("Deleted empty room: " + room.getName());
        }
		
       trace("sfs? User.RoomLeave   :::   user " + user1+ "   :::   zone "+ theZone + "   :::   room "+theRoom);
    }
}
