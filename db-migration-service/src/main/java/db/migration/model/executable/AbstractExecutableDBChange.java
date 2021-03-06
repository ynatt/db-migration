package db.migration.model.executable;

import db.migration.model.modification.ExecutableDBChange;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@XmlRootElement
public abstract class AbstractExecutableDBChange implements ExecutableDBChange{
    private String sql;
    private SQLException exception;

    public AbstractExecutableDBChange() {

    }

    protected AbstractExecutableDBChange(String sql) {
        this.sql = sql;
    }

    public SQLException getException() {
        return exception;
    }

    public String getSql() {
        return sql;
    }

    @XmlElement
    public void setSql(String sql) {
        this.sql = sql;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractExecutableDBChange)) return false;

        AbstractExecutableDBChange that = (AbstractExecutableDBChange) o;

        return sql != null ? sql.equals(that.sql) : that.sql == null;
    }

    @Override
    public int hashCode() {
        return sql != null ? sql.hashCode() : 0;
    }
}
