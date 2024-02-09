package com.adamk33n3r.runelite.watchdog;

import net.runelite.api.ChatMessageType;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GameMessageType implements Displayable {
    ANY("Any", "Any message", (ChatMessageType[]) null),
    BROADCAST("Broadcast", "Broadcast from the server", ChatMessageType.BROADCAST),
    DIALOG("Dialog", "Dialog messages", ChatMessageType.DIALOG, ChatMessageType.MESBOX),
    DUEL("Duel", "Duel messages", ChatMessageType.CHALREQ_TRADE, ChatMessageType.CHALREQ_FRIENDSCHAT, ChatMessageType.CHALREQ_CLANCHAT),
    EXAMINE("Examine", "Examine text", ChatMessageType.ITEM_EXAMINE, ChatMessageType.NPC_EXAMINE, ChatMessageType.OBJECT_EXAMINE),
    GAME_MESSAGE("Game Message", "Game messages", ChatMessageType.GAMEMESSAGE, ChatMessageType.CONSOLE, ChatMessageType.ENGINE),
    LOGIN_LOGOUT("Login/Logout", "Friend Login/Logout messages", ChatMessageType.LOGINLOGOUTNOTIFICATION),
    TRADE("Trade", "Trade messages", ChatMessageType.TRADE, ChatMessageType.TRADE_SENT, ChatMessageType.TRADEREQ),
    SPAM("Spam", "Filtered game messages", ChatMessageType.SPAM),
    ;

    private final String name;
    private final String tooltip;
    private final ChatMessageType[] chatMessageTypes;

    GameMessageType(String name, String tooltip, ChatMessageType... chatMessageTypes) {
        this.name = name;
        this.tooltip = tooltip;
        this.chatMessageTypes = chatMessageTypes;
    }

    boolean isOfType(ChatMessageType chatMessageType) {
        return this.chatMessageTypes != null && Arrays.asList(this.chatMessageTypes).contains(chatMessageType);
    }
}
