package net.lostteku;

import net.lostteku.commands.*;
import net.lostteku.events.*;
import net.lostteku.manager.BanManager;
import net.lostteku.manager.ConfigManager;
import net.lostteku.manager.LoggingManager;
import net.lostteku.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TheChosenOne extends JavaPlugin {

    private static TheChosenOne one;
    private ConfigManager configManager = new ConfigManager();
    private MySQLManager mySQLManager = new MySQLManager();
    private BanManager banManager = new BanManager();
    private LoggingManager loggingManager = new LoggingManager();

    @Override
    public void onLoad() {
        one = this;
        configManager.loadFiles();
        mySQLManager.connect();
        banManager.init();
        loggingManager.init();
    }

    //String, Integer, Float, void

    @Override
    public void onEnable() {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerChatListener(), this);
        pm.registerEvents(new PlayerCommandListener(), this);
        pm.registerEvents(new PlayerLoginListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new CPermissionCheckListener(), this);


        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("permission").setExecutor(new PermissionCommand());


        Bukkit.getConsoleSender().sendMessage("§aTheChosenOne has loaded!");

    }

    public static TheChosenOne getChosenOne(){
        return one;
    }
}
