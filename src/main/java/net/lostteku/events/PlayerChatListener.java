package net.lostteku.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(event.getPlayer() == null) return;
        if(event.getPlayer().isOp()){
            event.setFormat("§cOwner | " + event.getPlayer().getDisplayName() + " -> §a" + event.getMessage());
        }
    }
}
