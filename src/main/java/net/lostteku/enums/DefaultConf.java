package net.lostteku.enums;

import net.lostteku.manager.ConfigManager;
import org.bukkit.ChatColor;

public enum DefaultConf {

    MYSQL_ENABLED("general.mysql.enabled", "true"),
    MYSQL_USERNAME("general.mysql.username", "Testuser"),
    MYSQL_PASSWORD("general.mysql.password", "useasecurepasswordhere"),
    MYSQL_DATABASE_NAME("general.mysql.database", "databasename"),
    MYSQL_HOST("general.mysql.host", "localhost"),
    MYSQL_PORT("general.mysql.port", "3306"),
    KICK_BAN_SCREEN("general.banmanagement.kickbanscreen", "&cDu wurdest permanent gebannt! #Auf nimmer wiedersehen!"),
    BAN_SCREEN("general.banmanagement.banscreen", "&cDu bist gebannt!");
    private String path;
    private String value;
    private static ConfigManager manager = new ConfigManager();

    DefaultConf(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return value;
    }

    public static String getCustomValue(DefaultConf conf){
        return ChatColor.translateAlternateColorCodes('&', manager.getConfigFile("config").getString(conf.getPath()).replace('#', '\n'));
    }
}
