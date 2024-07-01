package net.lostteku.events;

import net.lostteku.manager.LoggingManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private LoggingManager loggingManager = new LoggingManager();

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        loggingManager.update(event.getPlayer().getUniqueId().toString(), "online", false);
    }
}
