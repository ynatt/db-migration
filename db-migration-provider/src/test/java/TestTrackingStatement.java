import db.migration.model.modification.drop.DropTable;
import db.migration.provider.SQLiteExecutor;
import db.migration.provider.SQLiteQueryParserManager;
import db.migration.provider.SQLiteTrackingDataSource;
import db.migration.service.DBState;
import db.migration.service.TrackingConnection;
import db.migration.service.TrackingDataSource;
import db.migration.service.TrackingStatement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

public class TestTrackingStatement {
    private String dataSourceUrl ="jdbc:sqlite:target/test.db";

    private TrackingDataSource trackingDataSource;

    @Before
    public void setUp() throws Exception {
        trackingDataSource = new SQLiteTrackingDataSource(dataSourceUrl);
    }

    @Test
    public void trackingStatementTest() throws SQLException {
        DBState dbState =
                Mockito.spy(new DBState("Test State",new SQLiteExecutor()));

        TrackingConnection trackingConnection = trackingDataSource.getTrackingConnection(dbState);
        TrackingStatement statement = trackingConnection.createTrackingStatement(new SQLiteQueryParserManager());
        statement.execute("drop table if exists users");

        Mockito.verify(dbState).addChange(Mockito.isA(DropTable.class));

//        DropTable expectedDropTable = new DropTable(new Table("users"));
//        expectedDropTable.setIfExists(true);
//        assertEquals(expectedDropTable, this.dbState.getChanges().get(0));
    }

    @Test
    public void testExportToXml() throws Exception {
        DBState dbState = new DBState("Test State",new SQLiteExecutor());

        TrackingConnection trackingConnection = trackingDataSource.getTrackingConnection(dbState);
        TrackingStatement statement = trackingConnection.createTrackingStatement(new SQLiteQueryParserManager());
        statement.execute("drop table if exists users");
        statement.execute("create table t1 (a int)");

        dbState.exportChanges("target/export.xml");
    }

    @Test
    public void testImportToXml() throws Exception {
        DBState dbState = new DBState("Test State",new SQLiteExecutor());

        TrackingConnection trackingConnection = trackingDataSource.getTrackingConnection(dbState);
        TrackingStatement statement = trackingConnection.createTrackingStatement(new SQLiteQueryParserManager());
        statement.execute("drop table if exists users");

        dbState.exportChanges("target/export2.xml");

        dbState.getChanges().clear();

        dbState.importChanges("target/export2.xml");

        System.out.println(dbState.getChanges().get(0).getChangeType());
    }
}
