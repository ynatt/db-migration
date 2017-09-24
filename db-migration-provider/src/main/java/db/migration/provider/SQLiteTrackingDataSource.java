package db.migration.provider;

import db.migration.service.*;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;
import db.migration.service.TrackingDataSource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class SQLiteTrackingDataSource implements TrackingDataSource {
    private SQLiteDataSource sqLiteDataSource;
    private DBChangeTracker tracker;
    private DBState state;

    public SQLiteTrackingDataSource(){
        sqLiteDataSource = new SQLiteDataSource();
    }

    public SQLiteTrackingDataSource(Properties properties){
        sqLiteDataSource = new SQLiteDataSource(new SQLiteConfig(properties));
    }

    public SQLiteTrackingDataSource(String url){
        this();
        sqLiteDataSource.setUrl(url);
    }

    public SQLiteTrackingDataSource(String url,String databaseName){
        this(url);
        sqLiteDataSource.setDatabaseName(databaseName);
    }

    public void setSqLiteDataSource(SQLiteDataSource sqLiteDataSource) {
        this.sqLiteDataSource = sqLiteDataSource;
    }

    public DBChangeTracker getTracker() {
        return tracker;
    }

    public void setTracker(DBChangeTracker tracker) {
        this.tracker = tracker;
    }

    public DBState getState() {
        return state;
    }

    public void setState(DBState state) {
        this.state = state;
    }

    @Override
    public DataSource getDataSource() {
        return sqLiteDataSource;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.sqLiteDataSource=(SQLiteDataSource) dataSource;
    }

    @Override
    public void setUrl(String url) {
        sqLiteDataSource.setUrl(url);
    }

    @Override
    public String getUrl() {
        return sqLiteDataSource.getUrl();
    }

    @Override
    public void setDatabaseName(String databaseName) {
        sqLiteDataSource.setDatabaseName(databaseName);
    }

    @Override
    public String getDatabaseName() {
        return sqLiteDataSource.getDatabaseName();
    }

    @Override
    public TrackingConnection getTrackingConnection() throws SQLException {
        return new SQLiteTrackingConnection(this.getConnection(),giveTracker());
    }

    private DBChangeTracker giveTracker(){
        if(tracker!=null & state!=null){
            return tracker;
        }
        if(state!=null){
            return new DBChangeTracker(state);
        }
        if(tracker!=null){
            tracker.setState(new DBState());
            return tracker;
        } else {
            return new DBChangeTracker(new DBState());
        }
    }

    @Override
    public TrackingConnection getTrackingConnection(String username,String password) throws SQLException {
        return new SQLiteTrackingConnection(this.getConnection(username,password),giveTracker());
    }

    @Override
    public Connection getConnection() throws SQLException {
        return sqLiteDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return sqLiteDataSource.getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return sqLiteDataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        sqLiteDataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        sqLiteDataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return sqLiteDataSource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return sqLiteDataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return sqLiteDataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return sqLiteDataSource.isWrapperFor(iface);
    }
}
