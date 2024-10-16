package org.open_cpi;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class ZoneExtension extends SFSExtension {
    @Override
    public void init() {
        trace("initing OpenCPI...");
        addEventHandler(SFSEventType.USER_JOIN_ZONE, HandleJoin.class);
        addEventHandler(SFSEventType.USER_LOGIN, HandleLogin.class);
    }
}