/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.test;

import my.test.penguin.reward.SEND_EARNED_REWARDS;
import my.test.room.party.PARTY_GAME_MESSAGE;
import my.test.room.GET_ROOM_ENCRYPTION_KEY;
import my.test.room.IGLOO_UPDATED;
import my.test.penguin.GET_SIGNED_STATE;
import my.test.quest.task.PICKUP;
import my.test.quest.SET_QUEST_STATES;
import my.test.quest.COMPLETE_OBJECTIVE;
import my.test.penguin.membership.MEMBERSHIP_REFRESH;
import my.test.penguin.LOCOMOTION_ACTION;
import my.test.zone.GET_PLAYER_LOCATION;
import my.test.zone.LEAVE_ROOM;
import my.test.props.USE_CONSUMABLE;
import my.test.props.REUSE_CONSUMABLE;
import my.test.props.SET_CONSUMABLE_INVENTORY;
import my.test.gear.EQUIP_DURABLE;
import my.test.gear.EQUIP_DISPENSABLE;
import my.test.chat.CHAT_ACTIVITY;
import my.test.chat.CHAT_ACTIVITY_CANCEL;
import my.test.chat.CHAT;
import my.test.time.GET_SERVER_TIME;
import com.smartfoxserver.v2.extensions.SFSExtension;
/**
 *
 * @author Creeper
 */
public class MyRoomExtension extends SFSExtension{
    
    @Override
    public void init(){
        trace("My First Room Extension...");
        //Smartfox Default Packets
        addRequestHandler("PingPong", PingHandler.class);
        
        addRequestHandler(SmartfoxCommand.CHAT_ACTIVITY.toString(), CHAT_ACTIVITY.class);
        addRequestHandler(SmartfoxCommand.CHAT_ACTIVITY_CANCEL.toString(), CHAT_ACTIVITY_CANCEL.class);
        addRequestHandler(SmartfoxCommand.CHAT.toString(), CHAT.class);
		
        addRequestHandler(SmartfoxCommand.LEAVE_ROOM.toString(), LEAVE_ROOM.class);
        addRequestHandler(SmartfoxCommand.GET_PLAYER_LOCATION.toString(), GET_PLAYER_LOCATION.class);
        
        addRequestHandler(SmartfoxCommand.LOCOMOTION_ACTION.toString(), LOCOMOTION_ACTION.class);

        addRequestHandler(SmartfoxCommand.PROTOTYPE.toString(), PROTOTYPE.class);
        
        addRequestHandler(SmartfoxCommand.COMPLETE_OBJECTIVE.toString(), COMPLETE_OBJECTIVE.class);
        addRequestHandler(SmartfoxCommand.SET_QUEST_STATES.toString(), SET_QUEST_STATES.class);
        
        addRequestHandler(SmartfoxCommand.EQUIP_DISPENSABLE.toString(), EQUIP_DISPENSABLE.class);
        addRequestHandler(SmartfoxCommand.EQUIP_DURABLE.toString(), EQUIP_DURABLE.class);
        
        addRequestHandler(SmartfoxCommand.SET_CONSUMABLE_INVENTORY.toString(), SET_CONSUMABLE_INVENTORY.class);
        addRequestHandler(SmartfoxCommand.USE_CONSUMABLE.toString(), USE_CONSUMABLE.class);
        addRequestHandler(SmartfoxCommand.REUSE_CONSUMABLE.toString(), REUSE_CONSUMABLE.class);
        
        addRequestHandler(SmartfoxCommand.MEMBERSHIP_REFRESH.toString(), MEMBERSHIP_REFRESH.class);
        
        addRequestHandler(SmartfoxCommand.PICKUP.toString(), PICKUP.class);
        
        addRequestHandler(SmartfoxCommand.GET_SIGNED_STATE.toString(), GET_SIGNED_STATE.class);
        
        addRequestHandler(SmartfoxCommand.SEND_EARNED_REWARDS.toString(), SEND_EARNED_REWARDS.class);
        
        addRequestHandler(SmartfoxCommand.WEBSERVICE_EVENT.toString(), WEBSERVICE_EVENT.class);
        
        addRequestHandler(SmartfoxCommand.PARTY_GAME_MESSAGE.toString(), PARTY_GAME_MESSAGE.class);
        
        addRequestHandler(SmartfoxCommand.IGLOO_UPDATED.toString(), IGLOO_UPDATED.class);
        
        addRequestHandler(SmartfoxCommand.GET_SERVER_TIME.toString(), GET_SERVER_TIME.class);
        addRequestHandler(SmartfoxCommand.GET_ROOM_ENCRYPTION_KEY.toString(), GET_ROOM_ENCRYPTION_KEY.class);

    }
    
}
