/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.gear;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author Creeper-PC
 */
public class EQUIP_DURABLE   extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
         Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
         Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
         
       //  SFSObject data = new SFSObject();
        // data.putUtfString("con", "{}");
        // SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("con",data,room.getUserList(),room,false);
       
           trace("gear? durable.equip   :::   " + params.toJson());
        Map<Integer, String> gearNames = new HashMap<>();
    gearNames.put(66, "GearTrafficSign");
    gearNames.put(20, "GearSword");
    gearNames.put(55, "GlowstickPack");
    gearNames.put(15,"GearJackhammer");
    gearNames.put(86,"GearDance");
    gearNames.put(12,"GearFirstAidKit");
    gearNames.put(13,"GearFishDogMultiTool");
    gearNames.put(71,"GearPomPomsBlue");
    gearNames.put(72,"GearPomPomsRed");
    gearNames.put(18,"GearScepter");
    gearNames.put(19,"GearShield");
    gearNames.put(14,"GearGuitar");
    gearNames.put(16,"GearKeytar");
    gearNames.put(11,"GearDrums");
    gearNames.put(101,"AcousticGuitarGear");
    gearNames.put(103,"MicrophoneGear");
    gearNames.put(105,"BongoDrumsGear");
    gearNames.put(129,"GearFlowerWand");
    gearNames.put(154,"MedievalGear");
     
     
         String jsonString = params.getUtfString("type");
    JSONObject jsonObj = new JSONObject(jsonString);
    int propId = jsonObj.getJSONObject("data").getInt("propId");
    
    String gearName = gearNames.getOrDefault(propId, "GearSword"); // DefaultGearName
        
        
       SFSObject data2 = new SFSObject();
      // data2.putUtfString("name", "GearSword");
      data2.putUtfString("name", gearName);
       data2.putUtfString("type", "durable");
       UserVariable con = new SFSUserVariable("con", data2);
     //  UserVariable con2 = new SFSUserVariable("outfit", "{}");
       
       getApi().setUserVariables(penguin, Arrays.asList(con));
       
      
       
        // Define a private, global Room Variable; no one will be able to overwrite this value
      // Being a global Variable, its value will be visible from outside the Room
  /*   RoomVariable chatTopic = new SFSRoomVariable("con", data2);
     chatTopic.setPrivate(false);
     chatTopic.setGlobal(true);
  
     
     // Set Room Variables via the server side API
     // Passing null as the User parameter sets the ownership of the variables to the Server itself
     getApi().setRoomVariables(penguin, room, Arrays.asList(chatTopic));*/
     //  trace("gear? durable.equip   :::   " + params.toJson());
         trace("Der gear hat propid: " + propId);
    }
    
}
