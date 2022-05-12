package dam.jkutkut.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDB {
    private String driver;
    private String url;

    public AccessDB(String db) {
        driver = "org.sqlite.JDBC";
        url = "jdbc:sqlite:" + db;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }
}
