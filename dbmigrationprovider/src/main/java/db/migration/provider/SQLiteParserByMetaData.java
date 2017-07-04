package db.migration.provider;

import db.migration.model.Column;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;
import db.migration.model.modification.create.*;
import db.migration.service.SQLParserException;
import db.migration.service.SQLQueryParser;
import org.sqlite.SQLiteDataSource;
import org.sqlite.core.CoreDatabaseMetaData;

import javax.activation.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteParserByMetaData implements SQLQueryParser {

    DatabaseMetaData metaData;

    SQLiteParserByMetaData(DatabaseMetaData metaData){
        this.metaData=metaData;
    }

    @Override
    public DBChange parseSQLQuery(String sql) throws SQLParserException {
        DBChange change = null;
        change = new CreateTableParser().parseSQLQuery(sql);
        return change;
    }

    public static void main(String[] args) throws SQLParserException, SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:test.db");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Create Table if not exists tbl (id integer primary key autoincrement unique not null, unique(id) )";
        statement.execute(sql);
        statement.execute("create index if not exists pop on tbl (id)");
        CreateTable createTable =(CreateTable) new SQLiteParserByMetaData(connection.getMetaData()).
                parseSQLQuery(sql);
        System.out.println(createTable.isIfNotExists());
        System.out.println(createTable.getTable().getFullName());
        System.out.println(createTable.getColumnDefinitions());
        System.out.println(createTable.getTableConstraints());
        System.out.println(new SQLiteExecutor(connection).makeExecutable(createTable).getQuery());
    }

    private class CreateTableParser implements SQLQueryParser{

        @Override
        public DBChange parseSQLQuery(String sql) throws SQLParserException {
            DBChange change = null;
            Pattern createTablePattern = Pattern.compile("create table (?<ifNotExists>if not exists)? (?<tableName>(\\w|\\d)+)( |\\().+",Pattern.CASE_INSENSITIVE);
            Matcher matcher = createTablePattern.matcher(sql);
            if(matcher.find()) {
                String tableName = matcher.group("tableName");
                CreateTable createTable = new CreateTable(new Table(tableName));
                createTable.setIfNotExists(matcher.group("ifNotExists").equalsIgnoreCase("if not exists"));
                try {
                    List<ColumnDefinition> columnDefinitions = new ArrayList<>();
                    ColumnDefinition columnDefinition = new ColumnDefinition();
                    Connection connection = metaData.getConnection();
                    ResultSet columns = metaData.getColumns(null,null,tableName,null);
                    while (columns.next()){
                        columnDefinition.setColumnName(columns.getString("COLUMN_NAME"));
                        columnDefinition.setColomnDataType(columns.getString("TYPE_NAME"));
                        if(columns.getString("COLUMN_DEF")!=null){
                            columnDefinition.addColumnSpec("DEFAULT '"+columns.getString("COLUMN_DEF")+"'");
                        }
                        if(columns.getString("IS_NULLABLE").equalsIgnoreCase("no")){
                            columnDefinition.addColumnSpec("NOT NULL");
                        }
                        columnDefinitions.add(columnDefinition);
                    }
                    //Сделано так, потому что getColumns почему-то не определяет правильно является ли
                    //колонка autoincrement. Да и вообще в некоторых моментах DatabaseMetaData не дает
                    //правильную информацию. Например, не может определить название первичного ключа
                    //в table constraint. Возможно, проблема в драйвере.
                    Statement statement = connection.createStatement();
                    ResultSet autoincrementedColumns = statement.executeQuery("SELECT * FROM "+tableName);
                    int i = 1;
                    boolean hasAutoincrementedPrimaryKey = false;
                    for(ColumnDefinition definition : columnDefinitions){
                        if(autoincrementedColumns.getMetaData().isAutoIncrement(i)){
                            definition.addColumnSpec("PRIMARY KEY AUTOINCREMENT");
                            hasAutoincrementedPrimaryKey=true;
                            break;
                        }
                    }
                    createTable.setColumnDefinitions(columnDefinitions);
                    ArrayList<TableConstraint> tableConstraints =new ArrayList<>();
                    createTable.setTableConstraints(tableConstraints);
                    if(!hasAutoincrementedPrimaryKey){
                        IndexedConstraint primaryKey = new IndexedConstraint(ConstraintType.PRIMARY_KEY);
                        ResultSet primaryKeys = metaData.getPrimaryKeys(null,null,tableName);
                        while(primaryKeys.next()){
                            primaryKey.setName(primaryKeys.getString("PK_NAME"));
                            primaryKey.addIndexedColumn(new Column(primaryKeys.getString("COLUMN_NAME")));
                        }
                        createTable.addTableConstraint(primaryKey);
                    }
                    IndexedConstraint uniqueConstraint = new IndexedConstraint(ConstraintType.UNIQUE);
                    ResultSet uniqueColumns = metaData.getIndexInfo(null,null,tableName,true,false);
                    while (uniqueColumns.next()){
                        uniqueConstraint.addIndexedColumn(new Column(uniqueColumns.getString("COLUMN_NAME")));
                    }
                    createTable.addTableConstraint(uniqueConstraint);
                    change=createTable;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return change;
            }else {
                return change;
            }
        }


    }
}
