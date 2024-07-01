package net.lostteku.events;

import net.lostteku.enums.Messages;
import net.lostteku.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class PlayerCommandListener implements Listener {

    private ConfigManager configManager = new ConfigManager();
    private List<String> blockedCommandList = configManager.getConfigFile("config").getStringList("general.blockedcommands");

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (blockedCommandList.contains(event.getMessage())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Messages.getCustomMessage(Messages.BLOCKED_COMMAND));
        }
    }
}
