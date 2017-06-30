package db.migration.provider;

import db.migration.service.*;
import org.sqlite.SQLiteDataSource;
import db.migration.service.TrackingDataSource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class SQLiteTrackingDataSource implements TrackingDataSource {
    private DataSource dataSource;
    private String url;
    private String databaseName;

    public SQLiteTrackingDataSource(){

    }

    public SQLiteTrackingDataSource(String url){
        SQLiteDataSource sqliteDataSource = new SQLiteDataSource();
        sqliteDataSource.setUrl(url);
        dataSource=sqliteDataSource;
    }

    public SQLiteTrackingDataSource(String url,String databaseName){
        SQLiteDataSource sqliteDataSource = new SQLiteDataSource();
        sqliteDataSource.setUrl(url);
        sqliteDataSource.setDatabaseName(databaseName);
        dataSource=sqliteDataSource;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void setUrl(String url) {
        this.url=url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setDatabaseName(String databaseName) {
        this.databaseName=databaseName;
    }

    @Override
    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public TrackingConnection getTrackingConnection(DBState state) throws SQLException {
        return new SQLiteTrackingConnection(this.getConnection(),new DBChangeTracker(state));
    }

    @Override
    public TrackingConnection getTrackingConnection(String username,String password, DBState state) throws SQLException {
        return new SQLiteTrackingConnection(this.getConnection(username,password),new DBChangeTracker(state));
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return dataSource.getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }
}
