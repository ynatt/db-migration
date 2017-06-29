package db.migration.service;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface TrackingDataSource extends DataSource{
    DataSource getDataSource();
    void setUrl(String url);
    String getUrl();
    void setDatabaseName(String databaseName);
    String getDatabaseName();
    TrackingConnection getTrackingConnection(DBState state) throws SQLException;
}
