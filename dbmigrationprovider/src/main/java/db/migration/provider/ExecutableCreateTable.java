package db.migration.provider;

import db.migration.model.modification.ExecutableDBChange;
import db.migration.model.modification.SQLQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecutableCreateTable implements ExecutableDBChange,SQLQuery{

    private String sql;
    private SQLException exception;

    ExecutableCreateTable(String sql){
        this.sql=sql;
        exception=null;
    }

    public SQLException getException() {
            return exception;
    }

    @Override
    public String getChangeType() {
        return "CREATE TABLE";
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
