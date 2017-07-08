package db.migration.service;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;

import java.sql.Connection;

public interface DBExecutor {
    void execute(ExecutableDBChange executableDBChange);
    ExecutableDBChange makeExecutable(DBChange dbChange);
    Connection getConnection();
}
