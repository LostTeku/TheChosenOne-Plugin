package net.lostteku.permissions;

import net.lostteku.manager.ConfigManager;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PermissionPlayer {


    //Gruppe, Rang
    private String uuid;
    private String username;
    private Player player;
    private ArrayList<PlayerRank> rank;
    private ArrayList<String> inpermission;
    private ConfigManager configManager = new ConfigManager();


    public PermissionPlayer(Player player) {
        this.uuid = player.getUniqueId().toString();
        this.username = player.getName();
        this.player = player;
        this.rank = rank;
        this.inpermission = (ArrayList<String>) configManager.getConfigFile("user-permissions").getList(player.getUniqueId().toString());
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<PlayerRank> getPlayerRanks() {
        return rank;
    }

    public ArrayList<String> getPlayerPermissions() {
        return inpermission;
    }

    public class PlayerRank {
        private static Player player;
        private String rankname;
        private ArrayList<String> permissions = new ArrayList<>();

        public PlayerRank(Player player, String rankname, ArrayList<String> permissions) {
            this.player = player;
            this.rankname = rankname;
            this.permissions = permissions;
        }

        public static Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public String getRankname() {
            return rankname;
        }

        public void setRankname(String rankname) {
            this.rankname = rankname;
        }

        public ArrayList<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(ArrayList<String> permissions) {
            this.permissions = permissions;
        }
    }
}
