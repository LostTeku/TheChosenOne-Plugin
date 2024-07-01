package net.lostteku.commands;

import net.lostteku.TheChosenOne;
import net.lostteku.enums.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command label, String s, String[] args) {
        if(!(sender instanceof Player)) { Bukkit.getConsoleSender().sendMessage("§cDu solltest ein Spieler sein!"); return false; }
        Player player = (Player) sender;

        switch (args.length - 1){
            case -1:
                player.setFoodLevel(20);
                player.sendMessage(Messages.getCustomMessage(Messages.FOOD));
                return true;
            case 0:
                if(Bukkit.getPlayer(args[0]) == null) {
                    player.sendMessage(Messages.getCustomMessage(Messages.NO_PLAYER));
                    return false;
                }
                if(!Bukkit.getPlayer(args[0]).isOnline()) {
                    player.sendMessage(Messages.getCustomMessage(Messages.OFFLINE_PLAYER));
                    return false;
                }
                Bukkit.getPlayer(args[0]).setFoodLevel(20);
                Bukkit.getPlayer(args[0]).sendMessage(Messages.getCustomMessage(Messages.FOOD));
                player.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                return true;

            default:
                player.sendMessage(Messages.getCustomMessage(Messages.WRONG_COMMAND_ARGUMENTS) + " " + TheChosenOne.getChosenOne().getCommand(label.getName()).getUsage());
                return true;
        }
    }
}
