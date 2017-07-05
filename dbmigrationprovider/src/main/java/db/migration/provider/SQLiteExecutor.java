package db.migration.provider;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;
import db.migration.model.modification.alter.AddColumn;
import db.migration.model.modification.alter.AlterTable;
import db.migration.model.modification.alter.RenameTo;
import db.migration.model.modification.create.*;
import db.migration.model.modification.drop.DropIndex;
import db.migration.model.modification.drop.DropTable;
import db.migration.provider.model.*;
import db.migration.service.DBExecutor;

import java.sql.Connection;
import java.util.Iterator;

public class SQLiteExecutor implements DBExecutor {
    private Connection connection;

    public SQLiteExecutor(Connection connection){
        this.connection=connection;
    }

    @Override
    public void execute(ExecutableDBChange executableDBChange) {
        executableDBChange.execute(connection);
    }

    @Override
    public ExecutableDBChange makeExecutable(DBChange dbChange) {
        if(dbChange instanceof CreateTable){
            String sql = makeSQLQueryCreateTable((CreateTable) dbChange);
            return new ExecutableCreateTable(sql);
        }
        if(dbChange instanceof DropTable){
            String sql = makeSQLQueryDropTable((DropTable) dbChange);
            return new ExecutableDropTable(sql);
        }
        if(dbChange instanceof DropIndex){
            String sql = makeSQLQueryDropIndex((DropIndex ) dbChange);
            return new ExecutableDropIndex(sql);
        }
        if(dbChange instanceof CreateIndex){
            String sql = makeSQLQueryCreateIndex((CreateIndex) dbChange);
            return new ExecutableCreateIndex(sql);
        }
        if(dbChange instanceof AlterTable){
            String sql = makeSQLQueryAlterTable((AlterTable) dbChange);
            return new ExecutableAlterTable(sql);
        }

        return null;
    }

    private String makeSQLQueryCreateTable(CreateTable createTable){
        StringBuilder sql = new StringBuilder(createTable.getChangeType());
        if(createTable.isIfNotExists()){
            sql.append(" "+ "IF NOT EXISTS");
        }
        sql.append(" ");
        sql.append(createTable.getTable().getFullName());
        sql.append(" ( ");
        ColumnDefinition columnDefinition;
        for (Iterator<ColumnDefinition> iter = createTable.getColumnDefinitions().iterator(); iter.hasNext();) {
            columnDefinition = iter.next();
            sql.append(columnDefinition.getColumnName());
            sql.append(" ");
            sql.append(columnDefinition.getColomnDataType());
            if (columnDefinition.getColumnSpecs() != null) {
                for (String spec : columnDefinition.getColumnSpecs()) {
                    sql.append(" ");
                    sql.append(spec.toUpperCase());
                }
            }
            if (iter.hasNext()) {
                sql.append(", ");
            }
        }
        if(createTable.getTableConstraints()!=null & !createTable.getTableConstraints().isEmpty()){
            sql.append(" , ");
            TableConstraint tableConstraint;
            for(Iterator<TableConstraint> iterator = createTable.getTableConstraints().iterator(); iterator.hasNext();){
                tableConstraint=iterator.next();
                sql.append(tableConstraint);
                if(iterator.hasNext()){
                    sql.append(" , ");
                }
            }
        }
        sql.append(" ) ");
        return sql.toString();
    }

    private String makeSQLQueryDropTable(DropTable dropTable){
        StringBuilder sql = new StringBuilder(dropTable.getChangeType());
        if(dropTable.isIfExists()){
            sql.append(" IF EXISTS");
        }
        sql.append(" ");
        sql.append(dropTable.getTable().getFullName());
        return sql.toString();
    }

    private String makeSQLQueryDropIndex(DropIndex dropIndex){
        StringBuilder sql = new StringBuilder(dropIndex.getChangeType());
        if(dropIndex.isIfExists()){
            sql.append(" IF EXISTS");
        }
        sql.append(" ");
        sql.append(dropIndex.getIndexName().getFullName());
        return sql.toString();
    }

    private String makeSQLQueryCreateIndex(CreateIndex createIndex){
        StringBuilder sql = new StringBuilder();
        if(createIndex.isUnique()){
            sql.append("CREATE UNIQUE INDEX");
        } else {
            sql.append(createIndex.getChangeType());
        }
        if(createIndex.isIfNotExists()){
            sql.append(" IF NOT EXISTS");
        }
        sql.append(" ");
        sql.append(createIndex.getIndex().getFullName());
        sql.append(" ON ");
        sql.append(createIndex.getTable().getFullName());
        sql.append(" ( ");
        IndexedColumn indexedColumn;
        for(Iterator<IndexedColumn> iterator = createIndex.getIndex().getIndexedColumns().iterator();iterator.hasNext();){
            indexedColumn=iterator.next();
            sql.append(indexedColumn.getColumn().getFullName());
            if(indexedColumn.getOrder()!=null){
                sql.append(" ");
                sql.append(indexedColumn.getOrder());
            }
            if(iterator.hasNext()){
                sql.append(" ,");
            }
        }
        sql.append(" );");
        return sql.toString();
    }

    private String makeSQLQueryAlterTable(AlterTable alterTable){
        StringBuilder sql = new StringBuilder(alterTable.getChangeType());
        sql.append(" ");
        sql.append(alterTable.getTable().getFullName());
        if(alterTable.getAlterType() instanceof RenameTo) {
            RenameTo renameTo = (RenameTo) alterTable.getAlterType();
            sql.append(" ");
            sql.append(renameTo);
        }
        if(alterTable.getAlterType() instanceof AddColumn){
            AddColumn addColumn = (AddColumn) alterTable.getAlterType();
            sql.append(" ");
            sql.append(addColumn);
        }
        return sql.toString();
    }
    @Override
    public Connection getConnection() {
        return connection;
    }
}
