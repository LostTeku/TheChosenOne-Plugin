package net.lostteku.commands;

import com.google.protobuf.Message;
import net.lostteku.TheChosenOne;
import net.lostteku.enums.DefaultConf;
import net.lostteku.enums.Messages;
import net.lostteku.manager.BanManager;
import net.lostteku.manager.LoggingManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BanCommand implements CommandExecutor {


    private LoggingManager loggingManager = new LoggingManager();
    private BanManager banManager = new BanManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(loggingManager.getUser(args[0]) == null) {
            sender.sendMessage(Messages.getCustomMessage(Messages.NO_PLAYER));
            return false;
        }
        LoggingManager.LoggedUser loggedUser = loggingManager.getUser(args[0]);
        switch (args.length){
            case 1:
                banManager.insert(new BanManager.ServerUser(loggedUser.getUuid(), loggedUser.getUsername(), "Nicht angegeben", 0L, "CONSOLE", new Timestamp(System.currentTimeMillis()), BanManager.UserType.BANUSER));

                if(loggedUser.isOnline()) {
                    Bukkit.getPlayer(loggedUser.getUsername()).kickPlayer(DefaultConf.getCustomValue(DefaultConf.BAN_SCREEN));
                }
                sender.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                return true;
                //Spielername dauer grund
            case 3:
                if(args[1].endsWith("d") || args[1].endsWith("D")){

                }
            default:
                sender.sendMessage(Messages.getCustomMessage(Messages.WRONG_COMMAND_ARGUMENTS) + " " + TheChosenOne.getChosenOne().getCommand(command.getName()).getUsage());
                return true;
        }
    }
}
