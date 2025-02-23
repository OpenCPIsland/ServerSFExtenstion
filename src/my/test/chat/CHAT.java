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
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.mmo.MMOItemVariable;
import com.smartfoxserver.v2.mmo.Vec3D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Creeper-PC
 */
public class CHAT  extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
        Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
        Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
            

       
       trace("chat? chat.msg   :::   " + params.getUtfString("msg"));
         JSONObject object = new JSONObject(params.toJson());
       
         JSONObject DataOfMsg = new JSONObject(object.getString("msg"));
         SFSObject data = new SFSObject();
         JSONObject object2 = new JSONObject();
         
         String cmd = DataOfMsg.getJSONObject("data").getString("message");
       String[] args = cmd.split(" ");
       
       if(cmd.contains("!test")){
           
           trace("my first command is "+ args[1]);
           
       }else if(cmd.contains("!mmo")){
           
           float ix = Float.valueOf(args[1]);
           float iy = Float.valueOf(args[2]);
           float iz = Float.valueOf(args[3]);
           
            
            List<UserVariable> variables = new ArrayList<UserVariable>();

            variables.add( new SFSUserVariable("colour", 9) );
            variables.add( new SFSUserVariable("tube", 2) );//next array?
            variables.add( new SFSUserVariable("outfit", "{\"parts\":[]}") );
            
            SFSObject data2 = new SFSObject();
            data2.putUtfString("name", "GearSword");
            data2.putUtfString("type", "durable");
            variables.add( new SFSUserVariable("con", data2) );
            variables.add( new SFSUserVariable("loco", 1) );
         //   variables.add( new MMOItemVariable("sess", "1899131575") );
            variables.add( new SFSUserVariable("on_quest", "Rockhopper") );

          //  MMOItem mmoItem = new MMOItem(variables);
            
  
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().setUserVariables(penguin, variables);
            
                       // SmartFoxServer.getInstance().getAPIManager().getMMOApi().setMMOItemVariables(mmoItem, variables);
            //SmartFoxServer.getInstance().getAPIManager().getMMOApi().setMMOItemPosition(mmoItem, new Vec3D(ix, iy, iz), room);
          
            
            SmartFoxServer.getInstance().getAPIManager().getMMOApi().setUserPosition(penguin, new Vec3D(ix, iy, iz), room);
            trace("mmo packet has been send!   " +  ix + "    " + iy + "    " + iz);
       }else if(cmd.contains("!alert")){
           
  
      SFSObject replay = new SFSObject();
      //replay.putSFSArray("sender", null);
      replay.putUtfString("message", args[1]);
      //replay.putSFSObject("data", null);
      SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendAdminMessage(penguin, null, replay, null);
              
       }
       else{
       //  int sessionid =  Integer.parseInt(penguin.getProperty("SessionId").toString());
       
         object2.put("senderSessionId", (long)penguin.getSession().getProperty("SessionId"));
         object2.put("emotion", DataOfMsg.getJSONObject("data").getInt("emotion"));
         object2.put("moderated", false);
         object2.put("message", DataOfMsg.getJSONObject("data").getString("message"));
         data.putUtfString("msg", object2.toString());
        //data.putInt("senderId", penguin.getId());
         data.putUtfString("ep", "msg");
         
       //  trace("the senderId is? : "+ SmartFoxServer.getInstance().getUserManager().getUserCount());
       
       SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("chat.msg",data,room.getUserList(),room,false);
       }
       
       trace("chat? chat.msg   :::   " + params.toJson());
       
       trace("chat? chat.msg   :::   " + room.getUserList());
    }
}
