/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.room;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.security.NoSuchAlgorithmException;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import my.test.utils.RSAHelper;

/**
 *
 * @author Creeper-PC
 */
public class GET_ROOM_ENCRYPTION_KEY  extends BaseClientRequestHandler  {
    
    private static RSAHelper rsaHelper;
    
    @Override
    public void handleClientRequest(User penguin, ISFSObject params)
    {
       trace("room? encryption.get   :::   " + params.toJson());
               
       Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
       Room room = zone.getRoomByName(penguin.getSession().getProperty("CurrentRoom").toString());
       
        try {
        rsaHelper = new RSAHelper();
            
        String publicKeyModulus = params.getUtfString("pkm");
        String publicKeyExponent = params.getUtfString("pke");

        byte[] modulus = Base64.getDecoder().decode(publicKeyModulus);
        byte[] exponent = Base64.getDecoder().decode(publicKeyExponent);
        PublicKey key = RSAHelper.getPublicKey(modulus, exponent);

        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);
        SecretKey aesSecret = generator.generateKey();

        byte[] encryptedCipher = rsaHelper.encryptBytes(aesSecret.getEncoded(), key);

        String encodedKey = Base64.getEncoder().encodeToString(encryptedCipher);
       
        SFSObject data1 = new SFSObject();
        data1.putUtfString("ek", encodedKey);
        SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("encryption.get",data1,penguin,room,false);
        
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("encryption.error",null,penguin,room,false);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(GET_ROOM_ENCRYPTION_KEY.class.getName()).log(Level.SEVERE, null, ex);
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("encryption.error",null,penguin,room,false);
        } catch (Exception ex) {
            Logger.getLogger(GET_ROOM_ENCRYPTION_KEY.class.getName()).log(Level.SEVERE, null, ex);
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("encryption.error",null,penguin,room,false);
        }
    }
    
}
