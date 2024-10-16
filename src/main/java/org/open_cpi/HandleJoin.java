package org.open_cpi;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.api.ISFSMMOApi;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.mmo.Vec3D;

import java.util.ArrayList;
import java.util.List;

public class HandleJoin extends BaseServerEventHandler {
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
        Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
        Room room = zone.getRoomByName("Alpine:en_US:Town::1.13.0;offline;2024-07-30;NONE");
        User user = (User) event.getParameter(SFSEventParam.USER);
        User theUser = (User) event.getParameter(SFSEventParam.USER);

        trace("-- HANDLING NEW JOIN REQUEST --");
        trace("Room: " + room);
        trace("User: " + user);

        ISFSApi smartfox = SmartFoxServer.getInstance().getAPIManager().getSFSApi();
        ISFSMMOApi mmo = SmartFoxServer.getInstance().getAPIManager().getMMOApi();
        ISFSArray sfsa = new SFSArray();
        ISFSArray sfsb = new SFSArray();

        List<UserVariable> theVars = new ArrayList<UserVariable>();
        theVars.add( new SFSUserVariable("colour", user.getSession().getProperty("colour")) );
        theVars.add( new SFSUserVariable("outfit", user.getSession().getProperty("outfit").toString()) );
        theVars.add( new SFSUserVariable("tube", user.getSession().getProperty("tube")) );
        theVars.add( new SFSUserVariable("sess", String.valueOf(user.getSession().getId())) );

        getApi().setUserVariables(theUser, theVars);

        smartfox.joinRoom(user, room);

        mmo.setUserPosition(user, new Vec3D(10f, 0f, 0f), room);
    }
}
