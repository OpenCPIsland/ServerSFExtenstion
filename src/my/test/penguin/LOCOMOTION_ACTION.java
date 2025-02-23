/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.penguin;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.mmo.Vec3D;
import java.sql.Timestamp;
import java.util.Collection;

/**
 *
 * @author Creeper-PC
 */
public class LOCOMOTION_ACTION extends BaseClientRequestHandler {
   
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
        
         Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
         Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());

         Timestamp timer =  new Timestamp(System.currentTimeMillis());
         long tsTime2 = timer.getTime();
       //  int sessionid =  Integer.parseInt(penguin.getProperty("SessionId").toString());
       //  trace("penguin? l.a sessionID   :::   " + penguin.getProperty("SessionId"));
         SFSObject data = new SFSObject();
         data.putFloatArray("p", params.getFloatArray("p"));
         data.putByte("a", (byte)params.getByte("a"));
                
         data.putInt("senderId", penguin.getId());
         data.putLong("t", tsTime2);
         
        if (params.containsKey("d")) {
             data.putFloatArray("d", params.getFloatArray("d"));
        }
         
        if (params.containsKey("v")) {
            data.putFloatArray("v", params.getFloatArray("v"));
	}

	if (params.containsKey("o")) {
            data.putUtfString("o", params.getUtfString("o"));
	}
        
         
	Collection<Float> floatCollection = params.getFloatArray("p");

	if (floatCollection != null && floatCollection.size() >= 3) {
            float[] floatArray = new float[3];
            int i = 0;
	for (Float floatValue : floatCollection) {
            floatArray[i++] = floatValue;
	if (i >= 3) break; 
	}
            Vec3D position = new Vec3D(floatArray[0], floatArray[1], floatArray[2]);
            SmartFoxServer.getInstance().getAPIManager().getMMOApi().setUserPosition(penguin, position, room);
	}
        
       SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("l.a",data,room.getUserList(),room,false);
     //  SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendPublicMessage(room, penguin, "l.a", data);
   //    trace("penguin? l.a   :::   " + penguin.getProperty("Room").toString());
       
       
      //trace("penguin? l.a   :::   " + params.toJson());
    }
}
