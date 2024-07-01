package net.lostteku.manager;

import net.lostteku.enums.DefaultConf;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLManager {

    private Connection connection = null;

    public void connect(){
        if(DefaultConf.getCustomValue(DefaultConf.MYSQL_ENABLED) == "false") return;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DefaultConf.getCustomValue(DefaultConf.MYSQL_HOST) + ":"
            + DefaultConf.getCustomValue(DefaultConf.MYSQL_PORT) + "/" + DefaultConf.getCustomValue(DefaultConf.MYSQL_DATABASE_NAME), DefaultConf.getCustomValue(DefaultConf.MYSQL_USERNAME), DefaultConf.getCustomValue(DefaultConf.MYSQL_PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cMySQL-Verbindung konnte nicht hergestellt werden!");
        }
    }

    public void createTable(String sql) {
        try {
            if(connection == null) connect();
            if(connection.isClosed()) connect();

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        if(connection == null) connect();
        try {
            if(connection.isClosed()) connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
