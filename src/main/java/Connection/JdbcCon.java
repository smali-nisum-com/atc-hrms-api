package Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by smali on 08/01/2018.
 */
public class JdbcCon {
    private static final Logger log = LoggerFactory.getLogger(JdbcCon.class);


    public void connectSql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://10.4.10.108:3306", "hrms", "hrms");
            //here is database name, username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nisumhrdb.employees;");
            while (rs.next())
                log.info(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
            con.close();
        } catch (Exception e) {
            log.info("exception" + e);
        }
    }
}
