package db.migration.impl;

import db.migration.service.DBState;
import db.migration.service.TrackingConnection;
import db.migration.service.TrackingDataSource;
import db.migration.service.TrackingStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        TrackingDataSource ds = new SQLiteTrackingDataSource("jdbc:sqlite:sample.db");
        try {
            DBState state = new DBState("Test State");
            TrackingConnection connection = ds.getTrackingConnection(state);
            TrackingStatement statement = connection.createTrackingStatement();
            statement.executeUpdate("create table if not EXISTS user (id integer, name string)");
            statement.executeUpdate("drop table user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
