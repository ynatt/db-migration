package db.migration;

import db.migration.service.DBChangeTracker;
import db.migration.service.TrackingConnection;
import db.migration.service.modyfication.DBChange;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author VYZH
 * @since 16.06.2017
 */
public class SimpleTest {

    public static void main(String[] args) throws Exception {
        SimpleTest test = new SimpleTest();
        test.testCreateTableTracking();
    }

    public void testCreateTableTracking() throws Exception {
        DataSource ds = getSqLiteDataSource();

        DBChangeTracker tracker = new DBChangeTracker() {
            @Override
            public void trackChange(DBChange change) {
                System.out.println("New change = " + change);
            }
        };

        Connection connection = new TrackingConnection(ds.getConnection(), tracker);
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    private DataSource getSqLiteDataSource() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:target/sample.db");
        return ds;
    }
}
