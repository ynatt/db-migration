package db.migration.provider;

import db.migration.model.modification.ExecutableDBChange;
import db.migration.model.modification.create.ColumnDefinition;
import db.migration.model.modification.create.CreateTable;
import db.migration.service.SQLParserException;

public class SQLParseTest {
    public static void main(String[] args) {
        String sql = "create table if not exists site.users ( id text primary key autoincrement " +
                ", name text not null default 'anon'" +
                ", account integer not null unique" +
                ", constraint \"loop\" foreign key (account) references accounts (id) on delete cascade on update no action " +
                "not deferrable, primary key (id,name) on conflict rollback, unique(name))";
        String sql1 = "create table if not exists accounts ( id integer primary key, cash integer not null)";
        SQLiteQueryParserManager parserManager = new SQLiteQueryParserManager();
        try {
            CreateTable createTable = (CreateTable) parserManager.parseSQLQuery(sql);
            System.out.println(createTable.getTable().getFullName());
            System.out.println(createTable.isIfNotExists());
            for (ColumnDefinition columnDef:createTable.getColumnDefinitions()){
                System.out.println(columnDef.getColumnName());
                System.out.println(columnDef.getColomnDataType());
                System.out.println(columnDef.getColumnSpecs());
                System.out.println("--------");
            }
            SQLiteExecutor sqLiteExecutor = new SQLiteExecutor(null);
            ExecutableDBChange executableCreateTable = sqLiteExecutor.makeExecutable(createTable);
            System.out.println(executableCreateTable.getQuery());
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
    }
}
