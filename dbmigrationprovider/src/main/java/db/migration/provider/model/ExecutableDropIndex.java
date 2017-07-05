package db.migration.provider.model;

import db.migration.model.modification.ExecutableDBChange;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutableDropIndex implements ExecutableDBChange {
    private String sql;
    private SQLException exception;

    public ExecutableDropIndex(String sql) {
        this.sql = sql;
    }

    @Override
    public String getChangeType() {
        return "DROP INDEX";
    }

    @Override
    public String getQuery() {
        return sql;
    }

    public SQLException getException() {
        return exception;
    }

    @Override
    public boolean execute(Connection connection) {
        try(Statement statement = connection.createStatement()){
            return statement.execute(sql);
        } catch (SQLException e) {
            exception=e;
            return false;
        }
    }
}
