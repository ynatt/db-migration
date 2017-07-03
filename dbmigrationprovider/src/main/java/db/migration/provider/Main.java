package db.migration.provider;

import db.migration.service.DBState;
import db.migration.service.TrackingConnection;
import db.migration.service.TrackingDataSource;
import db.migration.service.TrackingStatement;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        TrackingDataSource ds = new SQLiteTrackingDataSource("jdbc:sqlite:sample.db");
        try {
            DBState state = new DBState("Test State");
            TrackingConnection connection = ds.getTrackingConnection(state);
            TrackingStatement statement = connection.createTrackingStatement(new SQLiteQueryParserManager());
            statement.executeUpdate("create table if not EXISTS user (id integer, name string)");
            statement.executeUpdate("drop table user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
