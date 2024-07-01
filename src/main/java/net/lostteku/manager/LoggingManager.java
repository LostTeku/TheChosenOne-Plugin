package net.lostteku.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoggingManager {

    private MySQLManager mySQLManager = new MySQLManager();

    public static class LoggedUser {
        private String uuid;
        private String username;
        private boolean onlinestatus;

        public LoggedUser(String uuid, String username, boolean onlinestatus) {
            this.uuid = uuid;
            this.username = username;
            this.onlinestatus = onlinestatus;
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

        public boolean isOnline() {
            return onlinestatus;
        }

        public void setOnlineStatus(boolean onlinestatus) {
            this.onlinestatus = onlinestatus;
        }
    }

    public void init(){
        mySQLManager.createTable("CREATE TABLE IF NOT EXISTS `loggedusers` (`id` BIGINT NOT NULL AUTO_INCREMENT , `uuid` VARCHAR(255) NOT NULL , `username` VARCHAR(255) NOT NULL , `online` BOOLEAN NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
    }

    public void insert(LoggedUser user) {
        try {
            PreparedStatement statement = mySQLManager.getConnection().prepareStatement("INSERT INTO `loggedusers` (`id`, `uuid`, `username`, `online`) VALUES (NULL, ?, ?, ?);");
            statement.setString(1, user.getUuid());
            statement.setString(2, user.getUsername());
            statement.setBoolean(3, user.isOnline());
            statement.executeUpdate();
            statement.close();
            mySQLManager.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LoggedUser getUser(String username){
        PreparedStatement statement = null;
        LoggedUser user = null;
        try {
            statement = mySQLManager.getConnection().prepareStatement("SELECT * FROM `loggedusers` WHERE `username` = ?");
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                 user = new LoggedUser(set.getString("uuid"), set.getString("username"), set.getBoolean("online"));
            }
            set.close();
            statement.close();
            mySQLManager.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void update(String uuid, String column, Object value){
        PreparedStatement statement = null;
        try {
            statement = mySQLManager.getConnection().prepareStatement("UPDATE `loggedusers` SET `" + column + "` = ? WHERE `uuid` = ?");
            statement.setObject(1, value);
            statement.setString(2, uuid);
            statement.executeUpdate();

            statement.close();
            mySQLManager.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
