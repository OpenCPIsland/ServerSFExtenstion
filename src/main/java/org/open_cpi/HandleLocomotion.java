package org.open_cpi;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class HandleLocomotion extends BaseClientRequestHandler {
    @Override
    public void handleClientRequest(User user, ISFSObject isfsObject) {
        isfsObject.putInt("senderId", user.getId());
        send("l.a", isfsObject, user.getCurrentMMORoom().getProximityList(user));
        send("l.a", isfsObject, user);
        trace(isfsObject.toJson());
    }
}