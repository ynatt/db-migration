package db.migration.provider;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;
import db.migration.model.modification.create.ColumnDefinition;
import db.migration.model.modification.create.CreateTable;
import db.migration.provider.model.ExecutableCreateTable;
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
            CreateTable createTable = (CreateTable) dbChange;
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
                        sql.append(spec);
                    }
                }
                if (iter.hasNext()) {
                    sql.append(", ");
                }
            }
            sql.append(" ) ");
            return new ExecutableCreateTable(sql.toString());
        }

        return null;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
