package oracle.takapc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGetter {

    private SQLBase MySQL;
    private String username;

    public SQLGetter(SQLBase base, String username) {
        this.username = username;
        this.MySQL = base;
    }

    public SQLGetter(SQLBase base) {
        this.MySQL = base;
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("create table if not exists account"
                    + "(NAME VARCHAR(100),PW VARCHAR(100),UUID VARCHAR(100),CONNECT BOOLEAN,PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(String pw, String uuid, Boolean connect) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("select * from account where uuid=?");
            ps.setString(1, username);
            ResultSet result = ps.executeQuery();
            result.next();
            if(!exists()) {
                PreparedStatement ps2 = MySQL.getConnection().prepareStatement("insert into account"
                        + "(NAME, PW, UUID, CONNECT) values (?,?,?,?)");
                ps2.setString(2, pw);
                ps2.setString(3, uuid);
                ps2.setBoolean(4, connect);
                ps2.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public boolean exists() {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("select * from account where UUID=?");
            ps.setString(1, "takap_c");

            ResultSet result = ps.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUUID() {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("select * from account where uuid=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("uuid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
