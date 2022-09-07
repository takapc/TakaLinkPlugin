package oracle.takapc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public SQLBase SQL;

    public static Main main;

    @Override
    public void onEnable() {
        this.SQL = new SQLBase("localhost", "8889", "link_key", "root", "ROOT_PW");
        SQLGetter data = new SQLGetter(SQL);
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            getLogger().info("Database not connected...");
        }
        if (SQL.isConnected()) {
            getLogger().info("Database is connected!");
            data.createTable();
        }
        getCommand("link").setExecutor(new linkCommand());

        main = this;
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }

    public  SQLBase getSQL() {
        return SQL;
    }

    public static Main getMain() { return main; }
}
