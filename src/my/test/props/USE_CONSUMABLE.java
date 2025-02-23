/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.props;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import org.json.JSONObject;

/**
 *
 * @author Creeper-PC
 */
public class USE_CONSUMABLE   extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params) {
        Zone zone = SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
        Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());

        trace("prop? con.use   :::   " + params.toJson());

        JSONObject object = new JSONObject(params.toJson());
        String typeString = object.getString("type");
        JSONObject typeObject = new JSONObject(typeString);
        JSONObject dataObject = typeObject.getJSONObject("data");
        String cmd = dataObject.getString("type");

        SFSObject responseData = new SFSObject();
       responseData.putInt("senderId", penguin.getId());
         responseData.putInt("partialCount", 3);
        responseData.putUtfString("type", cmd); 

        trace("The type value is: " + cmd); 

        SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("con.use", responseData, room.getUserList(), room, false);
    }
    
}
