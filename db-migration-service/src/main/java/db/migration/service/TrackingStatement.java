package db.migration.service;

import java.sql.Statement;

public interface TrackingStatement extends Statement{
    void setStatement(Statement statement);
    Statement getStatement();
    void setDBChangeTracker(DBChangeTracker dbChangeTracker);
    DBChangeTracker getDBChangeTracker();
}
