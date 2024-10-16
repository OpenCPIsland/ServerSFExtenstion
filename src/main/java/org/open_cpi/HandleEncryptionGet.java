package org.open_cpi;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class HandleEncryptionGet extends BaseClientRequestHandler {
    @Override
    public void handleClientRequest(User user, ISFSObject isfsObject) {
        ISFSObject returnObject = new SFSObject();
        returnObject.putUtfString("ek", "lalalallala");
        send("encryption.get", returnObject, user);
    }
}
