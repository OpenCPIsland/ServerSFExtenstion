/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.test;
import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.controllers.system.Logout;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
/**
 *
 * @author Creeper
 */
public class PingHandler extends BaseClientRequestHandler
{
    @Override
    public void handleClientRequest(User user, ISFSObject isfso)
    {
        trace("it does now ping prevent being afk?   :::   " + isfso.toJson());
    }
}
