/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test.sfs2x;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Creeper-PC
 */
public class UpdateUserVaraibles extends BaseServerEventHandler {
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
        User user = (User) event.getParameter(SFSEventParam.USER);
        trace("UpdateUserVaraibles :::: "+event.getParameter(SFSEventParam.VARIABLES).toString());
         
         if(event.getParameter(SFSEventParam.VARIABLES).toString().contains("N: con")){
             
         } else if(event.getParameter(SFSEventParam.VARIABLES).toString().contains("N: bubble")){
            List<UserVariable> variables = (List<UserVariable>) event.getParameter(SFSEventParam.VARIABLES);
            getApi().setUserVariables(user, variables, true, false);
         }
         else{
        JSONArray objarr = new JSONArray(event.getParameter(SFSEventParam.VARIABLES).toString());
        List<UserVariable> vars2 = new ArrayList<UserVariable>();
        int n = 0;
        
        if(objarr.getJSONObject(0).get("T") == null){
               vars2.add(new SFSUserVariable(objarr.getJSONObject(0).getString("N"), null));
               getApi().setUserVariables(user, vars2, true, false);
       } else{
            
  outerwhileloop:
       while(n < objarr.length()){
           switch(objarr.getJSONObject(n).getString("T")){
               case "INT":
               vars2.add(new SFSUserVariable(objarr.getJSONObject(n).getString("N"), objarr.getJSONObject(n).getInt("V")));
               trace("is "+objarr.getJSONObject(n).getString("T")+" "+n);
               trace("data of name: "+objarr.getJSONObject(n).getString("N")+" info: "+objarr.getJSONObject(n).get("V").toString()+" nr."+n);
               getApi().setUserVariables(user, vars2, true, false);
               break;
               case "NULL":
               vars2.add(new SFSUserVariable(objarr.getJSONObject(n).getString("N"), null));
               trace("is "+objarr.getJSONObject(n).getString("T")+" "+n);
               trace("data of name: "+objarr.getJSONObject(n).getString("N")+" info: "+objarr.getJSONObject(n).get("V").toString()+" nr."+n);
               getApi().setUserVariables(user, vars2, true, false);
               break;
               case "STRING":
               trace("is "+objarr.getJSONObject(n).getString("T")+" "+n);
               trace("data of name: "+objarr.getJSONObject(n).getString("N")+" info: "+objarr.getJSONObject(n).get("V").toString()+" nr."+n);
               if(objarr.getJSONObject(n).getString("N").contains("outfit")){
                   JSONObject outfitData = new JSONObject(objarr.getJSONObject(n).get("V").toString());
                   if(outfitData.toString().contains("data")){
                      vars2.add(new SFSUserVariable("outfit", outfitData.getJSONObject("data").toString())); 
                      getApi().setUserVariables(user, vars2, true, false);
                   }
               } else if(objarr.getJSONObject(n).getString("N").contains("colour")){
                   JSONObject outfitData = new JSONObject(objarr.getJSONObject(n).get("V").toString());
                   vars2.add(new SFSUserVariable("colour", outfitData.getJSONObject("data").getInt("colour")));
                   getApi().setUserVariables(user, vars2, true, false);
               } else if(objarr.getJSONObject(n).getString("N").contains("tube")){
                   JSONObject outfitData = new JSONObject(objarr.getJSONObject(n).get("V").toString());
                   vars2.add(new SFSUserVariable("tube", outfitData.getJSONObject("data").getInt("tubeId")));
                   getApi().setUserVariables(user, vars2, true, false);
               }
               break;
           }
          
           n++;
           }
        }
      }

               /*  List<UserVariable> variables = (List<UserVariable>) event.getParameter(SFSEventParam.VARIABLES);
        getApi().setUserVariables(user, variables, true, false);*/
      
          //  SmartFoxServer.getInstance().getAPIManager().getSFSApi().setUserVariables(user, vars2, true, false);
       /* List<UserVariable> variables = (List<UserVariable>) event.getParameter(SFSEventParam.VARIABLES);
        getApi().setUserVariables(user, variables, true, false);*/

      
    /*  @SuppressWarnings("unchecked")
            List<UserVariable> variables = (List<UserVariable>) event.getParameter(SFSEventParam.VARIABLES);
         
         // Make a map of the variables list
         Map<String, UserVariable> varMap = new HashMap<String, UserVariable>();
         for (UserVariable var : variables)
         {
            varMap.put(var.getName(), var);
         }
         
        if(varMap.toString().contains("awayfromkeyboard")){
             
             UserVariable Datat = new SFSUserVariable("awayfromkeyboard", varMap.get("awayfromkeyboard").getIntValue());
             getApi().setUserVariables(user, Arrays.asList(Datat));
             
        }*/
    }
}

