package net.lostteku.events;

import net.lostteku.manager.LoggingManager;
import net.lostteku.permissions.PermissionPlayer;
import net.lostteku.permissions.cevents.PermissionCheckEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private LoggingManager loggingManager = new LoggingManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        loggingManager.update(event.getPlayer().getUniqueId().toString(), "online", true);
    }
}
