/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.time;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.sql.Timestamp;
import my.test.SmartfoxCommand;

/**
 *
 * @author Creeper-PC
 */
public class GET_SERVER_TIME  extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
       trace("time? time.get   :::   " + params.toJson());
       Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
       Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
            
       Timestamp timer =  new Timestamp(System.currentTimeMillis());
        long tsTime2 = timer.getTime();
        SFSObject data1 = new SFSObject();
        data1.putLong("ct", 0);
        data1.putLong("st", tsTime2);
        SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("time.get",data1,penguin,room,false);
        
        SFSObject data2 = new SFSObject();
        data2.putUtfString("ek", "null");
        SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("encryption.get",data1,penguin,room,false);
    }
    
}
