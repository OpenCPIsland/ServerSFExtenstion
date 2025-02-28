package my.test.sfs2x;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.config.ZoneSettings.BadWordsFilterSettings;
import com.smartfoxserver.v2.config.ZoneSettings.ExtensionSettings;
import com.smartfoxserver.v2.config.ZoneSettings.MMOSettings;
import com.smartfoxserver.v2.config.ZoneSettings.RoomPermissions;
import com.smartfoxserver.v2.config.ZoneSettings.RoomSettings;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.mmo.Vec3D;
import java.util.Arrays;
import java.util.List;


public class ZoneEventHandler extends com.smartfoxserver.v2.extensions.BaseServerEventHandler
    {
    
        @Override
        public void handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent event) throws com.smartfoxserver.v2.exceptions.SFSException
        {
                        
            User user1 = (User) event.getParameter(SFSEventParam.USER);

                long sessionid = (long)user1.getSession().getProperty("SessionId");//res1.getLong("sessionid2");
                trace("this sessionid is:: "+ sessionid);
                
                trace("Welcome new users id: " + user1.getId());
                 trace("Welcome new users id2: " + user1.getPlayerId());
        Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
            Room room = zone.getRoomByName(user1.getSession().getProperty("CurrentRoom").toString());
			if (zone != null) {
            List<Room> rooms = zone.getRoomList();
            boolean roomExists = false;
            for (Room r2 : rooms) {
                if (r2.getName().equals(room)) {
                    trace("Room " + room + " already exists.");
                    roomExists = true;
                    break;
                }
            }

            if (!roomExists) {
                try {
                    RoomSettings settings2 = new RoomSettings(user1.getSession().getProperty("CurrentRoom").toString());
                    settings2.groupId = "default";
                    settings2.password = "";
                    settings2.maxSpectators = 100;
                    settings2.isDynamic = false;
                    settings2.isHidden = false;
                    settings2.isGame= false;
                    settings2.maxUsers = 39;
                    settings2.autoRemoveMode = "NEVER_REMOVE";
                    RoomPermissions roomperset = new RoomPermissions();
                    roomperset.flags = "PUBLIC_MESSAGES";
                    roomperset.maxRoomVariablesAllowed = 50;
                    settings2.permissions = roomperset;
                    BadWordsFilterSettings  badWordsFilterSettings = new BadWordsFilterSettings();
                    badWordsFilterSettings.isActive = false;
                    settings2.badWordsFilter = badWordsFilterSettings;
                    settings2.events = "USER_VARIABLES_UPDATE_EVENT,USER_ENTER_EVENT,USER_EXIT_EVENT,USER_COUNT_CHANGE_EVENT";
                    ExtensionSettings extensionsset = new ExtensionSettings();
                    extensionsset.name = "MyFirstExtension";
                    extensionsset.type = "JAVA";
                    extensionsset.file = "my.test.MyRoomExtension";
                    extensionsset.propertiesFile = "";
                    extensionsset.reloadMode = "AUTO";
                    settings2.extension = extensionsset;
                    settings2.allowOwnerInvitation = false;
                    settings2.suppressUserList = false;
                    MMOSettings mMOSettings = new MMOSettings();
                    mMOSettings.isActive = true;
                    mMOSettings.defaultAOI = "900,900,900";
                    mMOSettings.lowerMapLimit = "-900.00,-900.00,-900.00";
                    mMOSettings.higherMapLimit = "900.00,900.00,900.00";
                    mMOSettings.forceFloats = true;
                    mMOSettings.userMaxLimboSeconds = 120;
                    mMOSettings.proximityListUpdateMillis = 500;
                    mMOSettings.sendAOIEntryPoint = true;
                    settings2.mmoSettings = mMOSettings;

                    Room newRoom = SmartFoxServer.getInstance().getZoneManager().createRoom(SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin"), settings2);
                    
                    trace("Created new room: " + newRoom.getName());
                } catch (SFSException e) {
                    trace("Error creating new room: " + e.getMessage());
                }
            }
        } else {
            trace("Zone not found.");
        }
			
            Room room2 = zone.getRoomByName(user1.getSession().getProperty("CurrentRoom").toString());
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().joinRoom(user1, room2);

            UserVariable sess = new SFSUserVariable("sess", String.valueOf(user1.getSession().getProperty("SessionId")));
            UserVariable outfit = new SFSUserVariable("outfit", user1.getSession().getProperty("outfit").toString());
            UserVariable colour2 = new SFSUserVariable("colour", (int)user1.getSession().getProperty("colour"));
            UserVariable tube = new SFSUserVariable("tube", (int)user1.getSession().getProperty("tube"));
        //    UserVariable on_quest = new SFSUserVariable("on_quest", "");
        //    UserVariable con = new SFSUserVariable("con", "{}");
            getApi().setUserVariables(user1, Arrays.asList(outfit, colour2, sess, tube));//, tube));// on_quest,con));
            trace("Welcome new user: " + user1.getName());
            
            float x = Float.valueOf("10.68");
            float y = Float.valueOf("0.3953104");
            float z = Float.valueOf("-13.38");
            SmartFoxServer.getInstance().getAPIManager().getMMOApi().setUserPosition(user1, new Vec3D(x, y, z), room2);
            
           
        }
    }
