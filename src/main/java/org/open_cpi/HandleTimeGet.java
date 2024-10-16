package org.open_cpi;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import java.time.Instant;

public class HandleTimeGet extends BaseClientRequestHandler {
    @Override
    public void handleClientRequest(User user, ISFSObject isfsObject) {
        long ct = isfsObject.getLong("ct");
        long st = Instant.now().toEpochMilli();
        ISFSObject returnObject = new SFSObject();
        returnObject.putLong("ct", ct);
        returnObject.putLong("st", st);
        send("time.get", returnObject, user);
    }
}
