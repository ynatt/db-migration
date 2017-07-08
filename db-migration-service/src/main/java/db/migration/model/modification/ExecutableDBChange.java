package db.migration.model.modification;


import java.sql.Connection;

public interface ExecutableDBChange extends DBChange,SQLQuery{
    boolean execute(Connection connection);
}
