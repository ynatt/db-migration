package db.migration.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface TrackingConnection extends Connection {
     void setTracker(DBChangeTracker tracker);
     DBChangeTracker getTracker();
     void setConnection(Connection connection);
     Connection getConnection();
     TrackingStatement createTrackingStatement() throws SQLException;
}
