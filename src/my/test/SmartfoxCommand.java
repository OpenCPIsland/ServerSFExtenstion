/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.test;

/**
 *
 * @author Creeper-PC
 */
    
public enum SmartfoxCommand{
    CHAT_ACTIVITY("chat.activity"),
    CHAT_ACTIVITY_CANCEL("chat.activity_cancel"),
    CHAT("chat.msg"),
    GET_SERVER_TIME("time.get"),
    SERVER_TIME_ERROR("time.error"),
    GET_ROOM_ENCRYPTION_KEY("encryption.get"),
    ENCRYPTION_KEY_ERROR("encryption.error"),
    PROTOTYPE("proto"),
    COMPLETE_OBJECTIVE("quest.c_obj"),
    SET_QUEST_STATES("quest.set"),
    SERVER_OBJECTIVE_COMPLETED("quest.s_obj"),
    SERVER_QUEST_ERROR("quest.error"),
    SET_CONSUMABLE_INVENTORY("con.set"),
    USE_CONSUMABLE("con.use"),
    REUSE_CONSUMABLE("con.reuse"),
    REUSE_FAILED("con.reuse_error"),
    CONSUMABLE_MMO_DEPLOYED("con.mmoitemdeployed"),
    SET_CONSUMABLE_PARTIAL_COUNT("con.partial"),
    RECEIVED_REWARDS("reward.add"),
    RECEIVED_REWARDS_DELAYED("reward.add_delayed"),
    RECEIVED_ROOOM_REWARDS("reward.room"),
    PROGRESSION_LEVELUP("reward.levelup"),
    SEND_EARNED_REWARDS("reward.broadcast"),
    IGLOO_UPDATED("room.igloo_updated"),
    FORCE_LEAVE("room.force_leave"),
    LEAVE_ROOM("zone.logout"),
    ROOM_TRANSIENT_DATA("zone.transfer"),
    GET_PLAYER_LOCATION("zone.player_location"),
    PLAYER_NOT_FOUND("zone.player_notfound"),
    LOCOMOTION_ACTION("l.a"),
    PICKUP("task.p"),
    TASK_COUNT("task.c"),
    TASK_PROGRESS("task.r"),
    MEMBERSHIP_REFRESH("membership.refresh"),
    GET_SIGNED_STATE("validate.state"),
    EQUIP_DURABLE("durable.equip"),
    EQUIP_DISPENSABLE("dispensable.equip"),
    WEBSERVICE_EVENT("wsevent.{0}"),
    PARTY_GAME_START("pgame.start"),
    PARTY_GAME_START_V2("pgame.start.v2"),
    PARTY_GAME_END("pgame.end"),
    PARTY_GAME_MESSAGE("pgame.message");
     
    private String value;

    SmartfoxCommand(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
             
}


    
