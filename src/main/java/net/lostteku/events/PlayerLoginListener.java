package net.lostteku.events;

import net.lostteku.enums.DefaultConf;
import net.lostteku.manager.BanManager;
import net.lostteku.manager.LoggingManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerLoginListener implements Listener {

    private BanManager banManager = new BanManager();
    private LoggingManager loggingManager = new LoggingManager();

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event){
        if(banManager.get(event.getUniqueId().toString(), "uuid", BanManager.UserType.BANUSER) != null){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, DefaultConf.getCustomValue(DefaultConf.BAN_SCREEN));
            return;
        }

        if(loggingManager.getUser(event.getName()) == null) loggingManager.insert(new LoggingManager.LoggedUser(event.getUniqueId().toString(), event.getName(), true));
    }
}
