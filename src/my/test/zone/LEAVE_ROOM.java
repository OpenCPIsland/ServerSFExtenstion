/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.zone;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.Base64;

/**
 *
 * @author Creeper-PC
 */
public class LEAVE_ROOM extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
        Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
        SmartFoxServer.getInstance().getAPIManager().getSFSApi().logout(penguin);
       trace("zone? zone.logout   :::   " + params.toJson());
       trace("zone? zone.logout   :::   " + Base64.getEncoder().encodeToString(params.toBinary()));
       trace("zone? zone.logout   :::   " + params.getDump(true));
       
           Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
        if (room.getUserList().isEmpty()) {
            // Delete the room
           // getApi().emptyRoom(room, true);
            zone.removeRoom(room);
            //  getApi().deleteRoom(room, true);
            trace("Deleted empty room: " + room.getName());
        }
    }
    
}
