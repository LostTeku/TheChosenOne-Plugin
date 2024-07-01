package net.lostteku.commands;

import com.google.protobuf.Message;
import net.lostteku.TheChosenOne;
import net.lostteku.enums.Messages;
import net.lostteku.manager.ConfigManager;
import net.lostteku.manager.LoggingManager;
import net.lostteku.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.List;

public class PermissionCommand implements CommandExecutor {

    private ConfigManager configManager = new ConfigManager();
    private LoggingManager loggingManager = new LoggingManager();
    private PermissionManager permissionManager = new PermissionManager();



    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length >= 3) {
                switch (args[0]) {
                    case "add":
                        if (loggingManager.getUser(args[1]) == null) {
                            sender.sendMessage(Messages.getCustomMessage(Messages.NO_PLAYER));
                            return false;
                        }
                        PermissionAttachment attachment = Bukkit.getOfflinePlayer(loggingManager.getUser(args[1]).getUsername()).getPlayer().addAttachment(TheChosenOne.getChosenOne());
                        //if (configManager.getConfigFile("user-permissions").get(loggingManager.getUser(args[1]).getUuid()) == null) {
                        if(permissionManager.getPlayerPermissions(loggingManager.getUser(args[1]).getUuid()) == null){
                            configManager.setConfigurationToFile("user-permissions", loggingManager.getUser(args[1]).getUuid() + ".permissions",  List.of(args[2].split(" ")));
                            sender.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                            return true;
                        }
                        ArrayList<String> permissionlist = permissionManager.getPlayerPermissions(loggingManager.getUser(args[1]).getUuid());
                        if(!permissionlist.contains(args[2])){
                            permissionlist.add(args[2]);
                            Bukkit.getPluginManager().addPermission(new Permission(args[2]));
                            attachment.setPermission(args[2], true);
                            configManager.setConfigurationToFile("user-permissions", loggingManager.getUser(args[1]).getUuid() + ".permissions",  permissionlist);
                            sender.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                            return true;
                        }
                        sender.sendMessage(Messages.getCustomMessage(Messages.PERMISSION_ALREADY_THERE));
                        return true;

                    case "remove":
                        if (loggingManager.getUser(args[1]) == null) {
                            sender.sendMessage(Messages.getCustomMessage(Messages.NO_PLAYER));
                            return false;
                        }

                        if(permissionManager.getPlayerPermissions(loggingManager.getUser(args[1]).getUuid()) == null){
                            configManager.setConfigurationToFile("user-permissions", loggingManager.getUser(args[1]).getUuid() + ".permissions",  List.of(args[2].split(" ")));
                            sender.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                            return true;
                        }
                        ArrayList<String> permissionlist2 = permissionManager.getPlayerPermissions(loggingManager.getUser(args[1]).getUuid());
                        if(permissionlist2.contains(args[2])) {
                            permissionlist2.remove(args[2]);
                            Bukkit.getPluginManager().removePermission(args[2]);
                            configManager.setConfigurationToFile("user-permissions", loggingManager.getUser(args[1]).getUuid() + ".permissions",  permissionlist2);
                            sender.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                            return true;
                        }
                        sender.sendMessage(Messages.getCustomMessage(Messages.PERMISSION_NOT_FOUND));
                        return true;

                }
        }
        return true;
    }
}
