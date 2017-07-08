import db.migration.model.Table;
import db.migration.model.modification.drop.DropTable;
import db.migration.provider.SQLiteExecutor;
import db.migration.provider.SQLiteQueryParserManager;
import db.migration.provider.SQLiteTrackingDataSource;
import db.migration.service.DBState;
import db.migration.service.TrackingConnection;
import db.migration.service.TrackingDataSource;
import db.migration.service.TrackingStatement;
import org.junit.Test;

import java.sql.SQLException;
import static org.junit.Assert.assertEquals;

public class TestTrackingStatement {
    private String dataSourceUrl ="jdbc:sqlite:test.db";

    private DBState dbState = new DBState("Test State",new SQLiteExecutor());

    private TrackingDataSource trackingDataSource = new SQLiteTrackingDataSource(dataSourceUrl);


    @Test
    public void trackingStatementTest() throws SQLException {
        TrackingStatement trackingStatement = getTrackingStatement();
        trackingStatement.execute("drop table if exists users");
        DropTable expectedDropTable = new DropTable(new Table("users"));
        expectedDropTable.setIfExists(true);
        assertEquals(expectedDropTable,dbState.getChanges().get(0));
    }

    private TrackingStatement getTrackingStatement() throws SQLException {
        TrackingConnection trackingConnection = trackingDataSource.getTrackingConnection(dbState);
        return trackingConnection.createTrackingStatement(new SQLiteQueryParserManager());
    }
}
