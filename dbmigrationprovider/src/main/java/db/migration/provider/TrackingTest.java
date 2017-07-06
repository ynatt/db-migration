package db.migration.provider;

import db.migration.service.DBState;
import db.migration.service.TrackingConnection;
import db.migration.service.TrackingDataSource;
import db.migration.service.TrackingStatement;

import java.sql.SQLException;

public class TrackingTest {
    public static void main(String[] args) {
        TrackingDataSource ds = new SQLiteTrackingDataSource("jdbc:sqlite:sample.db");
        try {
            DBState state = new DBState("Test State");
            TrackingConnection connection = ds.getTrackingConnection(state);
            TrackingStatement statement = connection.createTrackingStatement(new SQLiteQueryParserManager());
            statement.executeUpdate("create table if not EXISTS user (id integer, name string)");
            statement.execute("insert into user (id,name) values(1,'ad')");
            statement.execute("update user set name = 'qwe' where id = 1");
            statement.execute("delete from user where id = 1");
            statement.executeUpdate("drop table if exists user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
