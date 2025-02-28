/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.sfs2x;

import com.smartfoxserver.bitswarm.sessions.Session;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSConstants;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import org.json.JSONObject;



/**
 *
 * @author Creeper-PC
 */
public class Login extends BaseServerEventHandler {
    
   // public static int SessionId = 0;
    
  //  public static String SetRoom = "";
    
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
       Class obj = event.getParameter(SFSEventParam.LOGIN_IN_DATA).getClass();
       Session test = (Session) event.getParameter(SFSEventParam.SESSION);
       
       ISFSObject  outData = (ISFSObject)  event.getParameter(SFSEventParam.LOGIN_OUT_DATA);
       
       ISFSObject customData = (ISFSObject) event.getParameter(SFSEventParam.LOGIN_IN_DATA);

       String jsonFormattedString = customData.toJson().replace("\\\"", "\"");
       JSONObject object = new JSONObject(customData.toJson());
       
       JSONObject DataOfRoom = new JSONObject(object.getString("joinRoomData"));
       
       test.setProperty("SessionId", DataOfRoom.getJSONObject("data").getLong("sessionId"));
       test.setProperty("swid", DataOfRoom.getJSONObject("data").getString("swid"));
       test.setProperty("tube", DataOfRoom.getJSONObject("data").getInt("selectedTubeId"));
       test.setProperty("colour", DataOfRoom.getJSONObject("data").getJSONObject("playerRoomData").getJSONObject("profile").getInt("colour"));
       test.setProperty("outfit", DataOfRoom.getJSONObject("data").getJSONObject("playerRoomData").getJSONObject("outfit"));//.getJSONArray("parts"));
       outData.putLong(SFSConstants.DEFAULT_PLAYER_ID_GENERATOR, (long)test.getProperty("SessionId"));
       trace("sfs? User.Login   ::: Login Name "+event.getParameter(SFSEventParam.LOGIN_NAME) +"  Login In Data " + jsonFormattedString+"  Session " + event.getParameter(SFSEventParam.SESSION)+"  Zone " + event.getParameter(SFSEventParam.ZONE)+"  Login Password " + event.getParameter(SFSEventParam.LOGIN_PASSWORD)+"  Login Out Data " + event.getParameter(SFSEventParam.LOGIN_OUT_DATA));
       
       char testt = ';';
       String testtt = String.valueOf(testt);
       String[] test2 = DataOfRoom.getJSONObject("data").getString("room").split(testtt);
      // SetRoom = test2[0];
       test.setProperty("CurrentRoom", DataOfRoom.getJSONObject("data").getString("room"));
       //trace(" upcoming? "+test2[0]);

    }
    
}
