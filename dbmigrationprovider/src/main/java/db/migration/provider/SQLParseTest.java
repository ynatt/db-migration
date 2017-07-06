package db.migration.provider;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;
import db.migration.model.modification.alter.AlterTable;
import db.migration.model.modification.create.ColumnDefinition;
import db.migration.model.modification.create.CreateIndex;
import db.migration.model.modification.create.CreateTable;
import db.migration.model.modification.drop.DropIndex;
import db.migration.model.modification.drop.DropTable;
import db.migration.service.SQLParserException;

public class SQLParseTest {
    public static void main(String[] args) {
        String createTableSql = "create table if not exists site.users ( id integer primary key autoincrement " +
                ", name text not null default 'anon'" +
                ", account integer not null unique" +
                ", constraint \"loop\" foreign key (account) references accounts (id) on delete cascade " +
                "not deferrable, primary key (id,name) on conflict rollback, unique(name))";
        String dropTableSql = "drop table if exists schwd.inwdx";
        String createIndexSql = "create unique index if not exists sdd.asd on asd.fa (pop asc, asq desc)";
        String dropIndex = "Drop index if exists daw.awd";
        String alterTableRenameTo = "alter table asd.wq rename to adw.ad";
        String alterTableAddColumn = "alter table asd.wq add column id integer not null primary key autoincrement";
        String insert = "insert into asd.ad (id) values (14.4,4,\"asd\",'dawd',NULL) , (141),('add'),(x'415');";
        String delete = "Delete from wda.awd where id = 90";
        String update = "Update asda.ad set id = 1 where name = \"\"";
        SQLiteQueryParserManager parserManager = new SQLiteQueryParserManager();
        try {
            DBChange dbChange = parserManager.parseSQLQuery(update);
            SQLiteExecutor sqLiteExecutor = new SQLiteExecutor(null);
            ExecutableDBChange executableCreateTable = sqLiteExecutor.makeExecutable(dbChange);
            System.out.println(executableCreateTable.getQuery());
        } catch (SQLParserException e) {
            e.printStackTrace();
        }
    }
}
