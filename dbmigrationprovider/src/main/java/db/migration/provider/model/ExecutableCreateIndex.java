package db.migration.provider.model;

import db.migration.model.modification.ExecutableDBChange;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutableCreateIndex implements ExecutableDBChange{
    private String sql;
    private SQLException exception;

    public ExecutableCreateIndex(String sql) {
        this.sql = sql;
    }

    public SQLException getException() {
        return exception;
    }

    @Override
    public String getChangeType() {
        return "CREATE INDEX";
    }

    @Override
    public String getQuery() {
        return sql;
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
