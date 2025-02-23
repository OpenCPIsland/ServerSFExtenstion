package my.test;

import my.test.sfs2x.ZoneEventHandler;
import my.test.sfs2x.Login;
import my.test.sfs2x.RoomAdded;
import my.test.sfs2x.UpdateUserVaraibles;

public class MyExtension extends com.smartfoxserver.v2.extensions.SFSExtension
{
    @Override
    public void init()
    {
        trace("Hello, this is my first SFS2X Extension!");
        addEventHandler(com.smartfoxserver.v2.core.SFSEventType.USER_LOGIN, Login.class);
        addEventHandler(com.smartfoxserver.v2.core.SFSEventType.USER_JOIN_ZONE, ZoneEventHandler.class);
        addEventHandler(com.smartfoxserver.v2.core.SFSEventType.USER_JOIN_ROOM, RoomAdded.class);
        addEventHandler(com.smartfoxserver.v2.core.SFSEventType.USER_VARIABLES_UPDATE, UpdateUserVaraibles.class);
    }
 
    @Override
    public void destroy()
    {
        trace("Destroy is called!");
    }
}
