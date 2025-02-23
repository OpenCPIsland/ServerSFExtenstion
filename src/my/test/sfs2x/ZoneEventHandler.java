package my.test.sfs2x;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.mmo.IMMOItemVariable;
import com.smartfoxserver.v2.mmo.MMOItem;
import com.smartfoxserver.v2.mmo.MMOItemVariable;
import com.smartfoxserver.v2.mmo.Vec3D;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.Random;

public class ZoneEventHandler extends com.smartfoxserver.v2.extensions.BaseServerEventHandler
    {
    
    private static final int MAX_NPC = 5;
    private static final int MAX_MAP_X = 100;
    private static final int MAX_MAP_Z = 100;
   

    private ScheduledFuture<?> npcRunnerTask;
    private List<User> allNpcs;
    
      
    
        @Override
        public void handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent event) throws com.smartfoxserver.v2.exceptions.SFSException
        {
                        
            Zone theZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        //    Zone zone1 =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
          //  Room room1 = zone1.getRoomByName("Alpine:en_US:Town::1.13.0");
            User user1 = (User) event.getParameter(SFSEventParam.USER);
            
                 // String penguinname = user1.getName();
            
		//IDBManager dbManager = getParentExtension().getParentZone().getDBManager();
	//	Connection connection;
                
     //   try
     //   {
        	// Grab a connection from the DBManager connection pool
	    //    connection = dbManager.getConnection();
	        
	        // Build a prepared statement
	   //     PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username=?");
	   //     stmt.setString(1, penguinname);
                
	    //    ResultSet res = stmt.executeQuery();
                
            //    res.next();
                
		String ASWISSID = "2ade2ef5-1712-4ebc-ac68-20886511914a"+Math.random();//res.getString("aswid");
                
                trace("this aswid is:: "+ ASWISSID);
                
               // PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM accounts WHERE aswid=?");
              //  stmt2.setString(1, ASWISSID);
                
	      //  ResultSet res2 = stmt2.executeQuery();
                
              //  res2.next();
                
                String SWISSID = "233434bedew"+Math.random(); //res2.getString("swid");
                
                trace("this swid is:: "+ SWISSID);
                
              //  PreparedStatement stmt1 = connection.prepareStatement("SELECT * FROM stats WHERE swid=?");
               // stmt1.setString(1, SWISSID);
	       // ResultSet res1 = stmt1.executeQuery();
                
               // res1.next();
                
                Random r = new Random();
                int low = 100000;
                int high = 9999999;
                int result = r.nextInt(high-low) + low;
                long sessionid = (long)user1.getSession().getProperty("SessionId");//res1.getLong("sessionid2");
                trace("this sessionid is:: "+ sessionid);
                
                int tubeid = 0;//res1.getInt("selectedTubeId");
                int colour = 1;//res1.getInt("colour");

                
		//user1.setProperty("SessionId", sessionid);
                
                //user1.setProperty("Id", Login.SessionId);
                //user1.setProperty("PlayerId", Login.SessionId);
                
            //    user1.setProperty("Room", Login.SetRoom);
                
                trace("Welcome new users id: " + user1.getId());
                 trace("Welcome new users id2: " + user1.getPlayerId());
        Zone zone =  SmartFoxServer.getInstance().getZoneManager().getZoneByName("ClubPenguin");
            Room room = zone.getRoomByName(user1.getSession().getProperty("CurrentRoom").toString());
          //  com.smartfoxserver.v2.entities.User user = (com.smartfoxserver.v2.entities.User) event.getParameter(com.smartfoxserver.v2.core.SFSEventParam.USER);
            /*List<UserVariable> vars = new ArrayList<UserVariable>();
            vars.add(new SFSUserVariable("sess", String.valueOf(user1.getSession().getProperty("SessionId"))));
            vars.add(new SFSUserVariable("outfit", user1.getSession().getProperty("outfit").toString()));//"{\"parts\":[]}"));
            vars.add(new SFSUserVariable("colour", (int)user1.getSession().getProperty("colour")));
            vars.add(new SFSUserVariable("tube", (int)user1.getSession().getProperty("tube")));
            vars.add(new SFSUserVariable("on_quest", ""));
            vars.add(new SFSUserVariable("con", "{}"));*/
            
            UserVariable sess = new SFSUserVariable("sess", String.valueOf(user1.getSession().getProperty("SessionId")));
            UserVariable outfit = new SFSUserVariable("outfit", user1.getSession().getProperty("outfit").toString());
            UserVariable colour2 = new SFSUserVariable("colour", (int)user1.getSession().getProperty("colour"));
         //   UserVariable tube = new SFSUserVariable("tube", (int)user1.getSession().getProperty("tube"));
        //    UserVariable on_quest = new SFSUserVariable("on_quest", "");
        //    UserVariable con = new SFSUserVariable("con", "{}");
            getApi().setUserVariables(user1, Arrays.asList(outfit, colour2, sess));//, tube));// on_quest,con));
           // SmartFoxServer.getInstance().getAPIManager().getSFSApi().setUserVariables(user1, vars);
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().joinRoom(user1, room);
            trace("Current Room is : " + room.getName());

            Timestamp timer =  new Timestamp(System.currentTimeMillis());
            long tsTime2 = timer.getTime();
            SFSObject data1 = new SFSObject();
            data1.putLong("c", 1);
            data1.putLong("st", tsTime2);
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("time.get",data1,user1,room,false);
            
            SFSObject data = new SFSObject();
            data.putUtfString("ek", "MPnWDRuAeGmxJKjnvInuznjGvp46XY/f59KEeSudDNGELgiv9macvbupaMFBhqh+80YvGYko8ZfEh2kQg3C3Ks5RgVMA+byaNeJaOwPe/pliNq0cTGYwlQZBu9Kpi2fzOvVo0c9ljlEebFfcLuN7MxQ1n5hd8Fiq4YOh3rAlFos=");
          //  data.putInt("r", 38420);
            // data.putUtfString("c", "encryption.get");
            SmartFoxServer.getInstance().getAPIManager().getSFSApi().sendExtensionResponse("encryption.get",data,user1,room,false);
           // SmartFoxServer.getInstance().getAPIManager().getMMOApi().setUserPosition(user, vec3d, room);
            trace("Welcome new user: " + user1.getName());
            
         //   trace("Welcome new user: " + user1.getProperty("Id"));
            
            List<IMMOItemVariable> variables = new LinkedList<>();
            variables.add( new MMOItemVariable("type", "bonus") );
            variables.add( new MMOItemVariable("points", 250) );
            variables.add( new MMOItemVariable("active", true) );

            MMOItem mmoItem = new MMOItem(variables);
            
            float x = Float.valueOf("10.68");
            float y = Float.valueOf("0.3953104");
            float z = Float.valueOf("-13.38");
            //SmartFoxServer.getInstance().getAPIManager().getMMOApi().setMMOItemPosition(mmoItem, new Vec3D(x, y, z), room);
            SmartFoxServer.getInstance().getAPIManager().getMMOApi().setUserPosition(user1, new Vec3D(x, y, z), room);
          
               
      //          stmt.close();
      //          stmt2.close();
      //          stmt1.close();
       //         res.close();
       //         res2.close();
       //         res1.close();
      //          connection.close();
             
       // }
        
        // User name was not found
      //  catch (SQLException e)
      //  {
      //      trace("mysql error mom: " + e.getMessage());
      //  }
            

        }
    }
