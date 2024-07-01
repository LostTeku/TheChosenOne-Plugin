package net.lostteku.manager;

import javafx.beans.property.ReadOnlySetProperty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BanManager {

    // UUID, Username, Reason, Duration, BannedBy, Timestamp
    // Banuser, Muteuser, Teamuser

    private MySQLManager mySQLManager = new MySQLManager();

    public enum UserType {
        BANUSER,
        MUTEUSER,
    }

    public static class ServerUser {

        private String uuid;
        private String username;
        private String reason;
        private Long duration;
        private String bannedby;
        private Timestamp timestamp;
        private UserType type;

        public ServerUser(String uuid, String username, String reason, Long duration, String bannedby, Timestamp timestamp, UserType type) {
            this.uuid = uuid;
            this.username = username;
            this.reason = reason;
            this.duration = duration;
            this.bannedby = bannedby;
            this.timestamp = timestamp;
            this.type = type;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Long getDuration() {
            return duration;
        }

        public void setDuration(Long duration) {
            this.duration = duration;
        }

        public String getBannedby() {
            return bannedby;
        }

        public void setBannedby(String bannedby) {
            this.bannedby = bannedby;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        public UserType getType() {
            return type;
        }

        public void setType(UserType type) {
            this.type = type;
        }
    }

    public void init() {
        mySQLManager.createTable("CREATE TABLE IF NOT EXISTS `bans` (`id` BIGINT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(255) NOT NULL , `username` VARCHAR(255) NOT NULL , `reason` VARCHAR(255) NOT NULL , `duration` BIGINT NOT NULL , `bannedby` VARCHAR(255) NOT NULL , `timestamp` TIMESTAMP NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
        mySQLManager.createTable("CREATE TABLE IF NOT EXISTS `mute` (`id` BIGINT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(255) NOT NULL , `username` VARCHAR(255) NOT NULL , `reason` VARCHAR(255) NOT NULL , `duration` BIGINT NOT NULL , `mutedby` VARCHAR(255) NOT NULL , `timestamp` TIMESTAMP NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
    }

    //Insert, update, Get,

    public void insert(ServerUser user) {
        PreparedStatement statement = null;
        try {
            switch (user.getType()) {
                case BANUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("INSERT INTO `bans` (`id`, `uuid`, `username`, `reason`, `duration`, `bannedby`, `timestamp`) VALUES (NULL, ?, ?, ?, ?, ?, current_timestamp());");
                case MUTEUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("INSERT INTO `mutes` (`id`, `uuid`, `username`, `reason`, `duration`, `mutedby`, `timestamp`) VALUES (NULL, ?, ?, ?, ?, ?, current_timestamp());");
            }
            statement.setString(1, user.getUuid());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getReason());
            statement.setLong(4, user.getDuration());
            statement.setTimestamp(5, user.getTimestamp());
            statement.executeUpdate();
            statement.close();
            mySQLManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String uuid, String column, Object value, UserType type) {
        PreparedStatement statement = null;
        try {
            switch (type) {
                case BANUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("UPDATE ´bans´ SET `" + column + "` = ? WHERE ´uuid´ = ?");
                case MUTEUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("UPDATE ´mutes´ SET `" + column + "` = ? WHERE ´uuid´ = ?");
            }
            statement.setObject(1, value);
            statement.setString(2, uuid);
            statement.executeUpdate();
            statement.close();
            mySQLManager.getConnection().close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String get(String uuid, String column, UserType type) {
        PreparedStatement statement = null;
        try {
            switch (type) {
                case BANUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("SELECT " + column + " FROM `bans` WHERE `uuid` = ?");
                case MUTEUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("SELECT " + column + " FROM `mutes` WHERE `uuid` = ?");
            }
            statement.setString(1, uuid);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return set.getString(column);
            }

            set.close();
            statement.close();
            mySQLManager.getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(String uuid, UserType type){
        PreparedStatement statement = null;
        try {
            switch (type) {
                case BANUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("DELETE FROM `bans` WHERE `uuid` = ?");
                case MUTEUSER ->
                        statement = mySQLManager.getConnection().prepareStatement("DELETE FROM `mutes` WHERE `uuid` = ?");
            }
            statement.setString(1, uuid);
            statement.executeUpdate();
            statement.close();
            mySQLManager.getConnection().close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
