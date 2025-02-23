/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.room.party;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author Creeper-PC
 */
public class PARTY_GAME_MESSAGE  extends BaseClientRequestHandler  {
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
       trace("room.party? pgame.message   :::   " + params.toJson());
    }
    
}
