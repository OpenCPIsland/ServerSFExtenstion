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
public class EQUIP_DISPENSABLE  extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
        Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
         Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
         
          Map<Integer, String> gearNames = new HashMap<>();
    gearNames.put(8, "FishDogMultiTool");
    gearNames.put(43, "Sword");
    gearNames.put(100, "AcousticGuitar");
    gearNames.put(102, "Microphone");
    gearNames.put(104, "BongoDrums");
    gearNames.put(31, "PirateKit");
    gearNames.put(5, "Drums");
    gearNames.put(26, "Keytar");
    gearNames.put(21, "Guitar");
    gearNames.put(7, "FirstAidKit");
    gearNames.put(36, "ScepterProp");
    gearNames.put(37, "Shield");
    gearNames.put(4, "Dance");
    gearNames.put(76, "GaryGear");
    gearNames.put(75, "PomPomsBlue");
    gearNames.put(74, "PomPomsRed");
    gearNames.put(78, "YetiRescueKit");
    gearNames.put(70, "CPSNCamera");
    gearNames.put(9, "FishingRod");
 
    
   long typeLong = params.getLong("type");
    int type = (int) typeLong;
     trace("Der dispensable gear hat propid: " + type);
    String gearName = gearNames.getOrDefault(type, "DefaultGearName");
        trace("Der dispensable gear name ist: " + gearName);
      
    SFSObject data2 = new SFSObject();
       //data2.putUtfString("name", "Sword");
      data2.putUtfString("name", gearName);
       data2.putUtfString("type", "durable");
       UserVariable con = new SFSUserVariable("con", data2);
     //  UserVariable con2 = new SFSUserVariable("outfit", "{}");
       
       getApi().setUserVariables(penguin, Arrays.asList(con));
        
        
        
       trace("gear? dispensable.equip   :::   " + params.toJson());
    }
    
}
