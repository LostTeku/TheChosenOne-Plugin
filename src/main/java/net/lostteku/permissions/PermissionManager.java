package net.lostteku.permissions;

import net.lostteku.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.Arrays;

public class PermissionManager {


    private ConfigManager configManager = new ConfigManager();
    private Player player;
    private PermissionAttachment attachment;


    private void init(){

    }

    public ArrayList<String> getPlayerPermissions(String uuid) {
        return (ArrayList<String>) configManager.getConfigFile("user-permissions").getList(uuid + ".permissions");
    }
}
