package db.migration.provider;

import java.sql.Statement;

import db.migration.service.DBChangeTracker;
import db.migration.model.modification.create.CreateTable;
import db.migration.service.SQLParserException;
import db.migration.service.SQLQueryParser;
import db.migration.service.TrackingStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteTrackingStatement implements TrackingStatement {
    private Statement statement;
    private DBChangeTracker tracker;
    SQLQueryParser parser;

    public SQLiteTrackingStatement(){
        parser = new SQLiteQueryParserManager();
    }

    public SQLiteTrackingStatement(Statement statement, DBChangeTracker tracker) {
        this.statement = statement;
        this.tracker = tracker;
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);

        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        int result = statement.executeUpdate(sql);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        statement.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return statement.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        statement.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return statement.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        statement.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        statement.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return statement.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        statement.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        statement.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return statement.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        statement.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        statement.setCursorName(name);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        boolean result = statement.execute(sql);

        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return statement.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return statement.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return statement.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        statement.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return statement.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        statement.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return statement.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return statement.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return statement.getResultSetType();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        statement.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        statement.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return statement.executeBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return statement.getConnection();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return statement.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return statement.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        int result = statement.executeUpdate(sql, autoGeneratedKeys);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        int result = statement.executeUpdate(sql, columnIndexes);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        int result = statement.executeUpdate(sql, columnNames);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        boolean result = statement.execute(sql, autoGeneratedKeys);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        boolean result = statement.execute(sql, columnIndexes);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        boolean result = statement.execute(sql, columnNames);
        try {
            tracker.trackChange(parser.parseSQLQuery(sql));
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return statement.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return statement.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        statement.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return statement.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        statement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return statement.isCloseOnCompletion();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return statement.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return statement.isWrapperFor(iface);
    }

    @Override
    public void setStatement(Statement statement) {
        this.statement=statement;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public void setDBChangeTracker(DBChangeTracker dbChangeTracker) {
        this.tracker=dbChangeTracker;
    }

    @Override
    public DBChangeTracker getDBChangeTracker() {
        return tracker;
    }
}
