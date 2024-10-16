package org.open_cpi;

import com.smartfoxserver.bitswarm.sessions.Session;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSConstants;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import org.json.JSONObject;

public class HandleLogin extends BaseServerEventHandler {
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
        trace("[OpenCPI] Started login...");
        Class obj = event.getParameter(SFSEventParam.LOGIN_IN_DATA).getClass();
        Session session = (Session) event.getParameter(SFSEventParam.SESSION);

        ISFSObject outData = (ISFSObject) event.getParameter(SFSEventParam.LOGIN_OUT_DATA);
        ISFSObject inData = (ISFSObject) event.getParameter(SFSEventParam.LOGIN_IN_DATA);

        String formattedInData = inData.toJson().replace("\\\"", "\"");
        trace(formattedInData);
        JSONObject jsonInData = new JSONObject(inData.toJson());
        JSONObject jsonRoomData = new JSONObject(jsonInData.getString("joinRoomData"));

        session.setProperty("SessionId", jsonRoomData.getJSONObject("data").getLong("sessionId"));
        session.setProperty("swid", jsonRoomData.getJSONObject("data").getString("swid"));
        session.setProperty("tube", jsonRoomData.getJSONObject("data").getInt("selectedTubeId"));
        session.setProperty("colour", jsonRoomData.getJSONObject("data").getJSONObject("playerRoomData").getJSONObject("profile").getInt("colour"));
        session.setProperty("outfit", jsonRoomData.getJSONObject("data").getJSONObject("playerRoomData").getJSONObject("outfit"));
        outData.putLong(SFSConstants.DEFAULT_PLAYER_ID_GENERATOR, (long)session.getProperty("SessionId"));
        trace("[OpenCPI] User logged in, Login name: "+event.getParameter(SFSEventParam.LOGIN_NAME));
    }
}
