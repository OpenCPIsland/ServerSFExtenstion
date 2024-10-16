package org.open_cpi;

import com.smartfoxserver.v2.extensions.SFSExtension;

public class RoomExtension extends SFSExtension {
    @Override
    public void init() {
        addRequestHandler("time.get", HandleTimeGet.class);
        addRequestHandler("encryption.get", HandleEncryptionGet.class);
        addRequestHandler("l.a", HandleLocomotion.class);
    }
}
