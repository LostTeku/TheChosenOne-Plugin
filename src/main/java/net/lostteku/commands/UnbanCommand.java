package net.lostteku.commands;

import net.lostteku.TheChosenOne;
import net.lostteku.enums.Messages;
import net.lostteku.manager.BanManager;
import net.lostteku.manager.LoggingManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    private BanManager banManager = new BanManager();
    private LoggingManager loggingManager = new LoggingManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        switch (args.length){
            case 1:
                if(loggingManager.getUser(args[0]) == null) {
                    sender.sendMessage(Messages.getCustomMessage(Messages.NO_PLAYER));
                    return false;
                }
                LoggingManager.LoggedUser user = loggingManager.getUser(args[0]);
                if(banManager.get(user.getUuid(), "id", BanManager.UserType.BANUSER) == null) {
                    sender.sendMessage(Messages.getCustomMessage(Messages.NOT_BANNED));
                    return false;
                }
                banManager.remove(user.getUuid(), BanManager.UserType.BANUSER);
                sender.sendMessage(Messages.getCustomMessage(Messages.ACTION_SUCESS));
                return true;
            default:
                sender.sendMessage(Messages.getCustomMessage(Messages.WRONG_COMMAND_ARGUMENTS) + " " + TheChosenOne.getChosenOne().getCommand(command.getName()).getUsage());
                return true;
        }
    }
}
