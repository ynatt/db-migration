import db.migration.model.Column;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;
import db.migration.model.modification.create.table.*;
import db.migration.model.modification.create.table.enums.ClauseDecision;
import db.migration.model.modification.create.table.enums.ConflictDecision;
import db.migration.model.modification.create.table.enums.ConstraintType;
import db.migration.model.modification.create.table.enums.DeferrableClause;
import db.migration.model.modification.drop.DropTable;
import db.migration.provider.SQLiteExecutor;
import db.migration.provider.SQLiteQueryParserManager;
import db.migration.provider.SQLiteTrackingDataSource;
import db.migration.service.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTrackingStatement {
    private static String dataSourceUrl ="jdbc:sqlite:test.db";

    private static DBState dbState = new DBState("Test State");

    private static DBChangeTracker tracker = new DBChangeTracker(dbState);

    private static TrackingDataSource trackingDataSource;

    static {
        trackingDataSource = new SQLiteTrackingDataSource(dataSourceUrl);
        trackingDataSource.setState(dbState);
        trackingDataSource.setTracker(tracker);
    }

    private static final String[] SEQUENCE_OF_QUERIES = {"DROP TABLE IF EXISTS users;",
            "DROP TABLE IF EXISTS carts",
            "CREATE TABLE IF NOT EXISTS carts(id INTEGER PRIMARY KEY AUTOINCREMENT, cartNumber TEXT UNIQUE)",
            "CREATE TABLE IF NOT EXISTS users ( id INTEGER," +
                    " nickname TEXT NOT NULL," +
                    " age INTEGER NOT NULL, " +
                    " cardId INTEGER UNIQUE," +
                    " CONSTRAINT \"Reference to pay card\" FOREIGN KEY (cardId) references carts (id) ON DELETE CASCADE NOT DEFERRABLE," +
                    " CONSTRAINT \"PK\" PRIMARY KEY (id) on conflict rollback," +
                    " UNIQUE(nickname));"};

    private final File dbStateXML = new File("dbStateXMLTest.xml");
    @BeforeClass
    public static void setUp() throws SQLException {
        TrackingStatement trackingStatement = getTrackingStatement();
        for(String query : SEQUENCE_OF_QUERIES){
            trackingStatement.execute(query);
        }
    }

    @Test
    public void sizeTest() throws SQLException {
        assertTrue(dbState.getChanges().size()==4);
    }

    @Test
    public void dbChangesTest(){
        assertTrue(dbState.getChanges().get(0) instanceof DropTable);
        assertTrue(dbState.getChanges().get(1) instanceof DropTable);
        assertTrue(dbState.getChanges().get(2) instanceof CreateTable);
        assertTrue(dbState.getChanges().get(3) instanceof CreateTable);
    }

    @Test
    public void firstDropTableIsRightTest(){
        DropTable expectedDropTable = new DropTable(new Table("users"));
        expectedDropTable.setIfExists(true);
        assertEquals(expectedDropTable,dbState.getChanges().get(0));
    }

    @Test
    public void secondDropTableIsRightTest(){
        DropTable expectedDropTable = new DropTable(new Table("carts"));
        expectedDropTable.setIfExists(true);
        assertEquals(expectedDropTable,dbState.getChanges().get(1));
    }

    @Test
    public void firstCreateTableIsRightTest(){
        CreateTable expectedCreateTable = getCreateTableInstance("carts",true);
        addColumnDefinitionTo(expectedCreateTable,"id","INTEGER","PRIMARY KEY AUTOINCREMENT");
        addColumnDefinitionTo(expectedCreateTable,"cartNumber","TEXT","UNIQUE");
        assertEquals(expectedCreateTable,dbState.getChanges().get(2));
    }

    @Test
    public void secondCreateTableIsRightTest(){
        CreateTable expectedCreateTable = getCreateTableInstance("users",true);
        addColumnDefinitionTo(expectedCreateTable,"id","INTEGER");
        addColumnDefinitionTo(expectedCreateTable,"nickname","TEXT","NOT NULL");
        addColumnDefinitionTo(expectedCreateTable,"age","INTEGER","NOT NULL");
        addColumnDefinitionTo(expectedCreateTable,"cardId","INTEGER","UNIQUE");
        addForeignKeyConstraintTo(expectedCreateTable,"\"reference to pay card\"",wrapColumns("cardId"),
                getForeignKeyClause("carts",wrapColumns("id"),ClauseDecision.CASCADE,DeferrableClause.NOT_DEFERRABLE));
        addIndexedConstraintTo(expectedCreateTable,"\"PK\"",ConstraintType.PRIMARY_KEY,
                wrapColumns("id"), ConflictDecision.ROLLBACK);
        addIndexedConstraintTo(expectedCreateTable,null,ConstraintType.UNIQUE,wrapColumns("nickname"),ConflictDecision.ROLLBACK);
        assertEquals(expectedCreateTable,dbState.getChanges().get(3));
    }

    private CreateTable getCreateTableInstance(String tableName,boolean ifNotExists){
        CreateTable result = new CreateTable(new Table(tableName));
        result.setIfNotExists(ifNotExists);
        return result;
    }

    private ForeignKeyClause getForeignKeyClause(String tableName,List<Column> columns,
                                                 ClauseDecision onDeleteClause,DeferrableClause deferrable){
        ForeignKeyClause foreignKeyClause = new ForeignKeyClause(new Table(tableName));
        foreignKeyClause.setColumns(columns);
        foreignKeyClause.setOnDeleteClause(new OnDeleteClause(onDeleteClause));
        foreignKeyClause.setForeignKeyDeferrable(new ForeignKeyDeferrable(deferrable));
        return foreignKeyClause;
    }

    private CreateTable addColumnDefinitionTo(CreateTable createTable, String columnName, String type, String... specs){
        List<String> specList = new ArrayList<>(Arrays.asList(specs));
        createTable.addColumnDefinition(new ColumnDefinition(columnName,type,specList));
        return createTable;
    }

    private CreateTable addForeignKeyConstraintTo(CreateTable createTable,String fkConstraintName,
                                                  List<Column> constraintColumns,ForeignKeyClause foreignKeyClause){
        ForeignKeyConstraint foreignKeyConstraint = new ForeignKeyConstraint(fkConstraintName,constraintColumns,foreignKeyClause);
        createTable.addTableConstraint(foreignKeyConstraint);
        return createTable;
    }

    private CreateTable addIndexedConstraintTo(CreateTable createTable,String constraintName,
                                               ConstraintType constraintType, List<Column> columns,
                                               ConflictDecision conflictDecision){
        IndexedConstraint indexedConstraint = new IndexedConstraint(constraintName,constraintType,columns,conflictDecision);
        createTable.addTableConstraint(indexedConstraint);
        return createTable;
    }

    private List<Column> wrapColumns(String... columns){
        List<Column> result = new ArrayList<>();
        for(String columnName:columns){
            result.add(new Column(columnName));
        }
        return result;
    }

    @Test
    public void exportDBStateTest(){
        try {
            dbState.exportChanges(new FileWriter(dbStateXML));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void importDBStateTest(){
        try {
            List<DBChange> changes = dbState.getChanges();
            dbState.setChanges(null);
            dbState.importChanges(new FileReader(dbStateXML));
            assertEquals(changes,dbState.getChanges());
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private static TrackingStatement getTrackingStatement() throws SQLException {
        TrackingConnection trackingConnection = trackingDataSource.getTrackingConnection();
        return trackingConnection.createTrackingStatement(new SQLiteQueryParserManager());
    }
}
