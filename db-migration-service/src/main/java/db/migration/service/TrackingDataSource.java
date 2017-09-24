package db.migration.service;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface TrackingDataSource extends DataSource{
    DataSource getDataSource();
    void setDataSource(DataSource dataSource);
    void setUrl(String url);
    String getUrl();
    void setDatabaseName(String databaseName);
    String getDatabaseName();
    DBChangeTracker getTracker();
    void setTracker(DBChangeTracker tracker);
    void setState(DBState state);
    DBState getState();
    TrackingConnection getTrackingConnection() throws SQLException;
    TrackingConnection getTrackingConnection(String username, String password) throws SQLException;
}
