package db.migration.provider;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;
import db.migration.service.DBState;
import db.migration.service.TrackingConnection;
import db.migration.service.TrackingDataSource;
import db.migration.service.TrackingStatement;

import java.sql.SQLException;
import java.util.List;

public class TrackingTest {
    public static void main(String[] args) {
        TrackingDataSource ds = new SQLiteTrackingDataSource("jdbc:sqlite:sample.db");
        try {
            DBState state = new DBState("Test State");
            TrackingConnection connection = ds.getTrackingConnection(state);
            TrackingStatement statement = connection.createTrackingStatement(new SQLiteQueryParserManager());
            statement.executeUpdate("drop table if exists user");
            statement.executeUpdate("create table if not EXISTS user (id integer, name string)");
            statement.executeUpdate("drop table if exists users");
            statement.executeUpdate("create table if not exists users ( id integer " +
                    ", name text not null default 'anon'" +
                    ", account integer not null unique" +
                    ", constraint \"loop\" foreign key (account) references accounts (id) on delete cascade " +
                    "not deferrable,constraint \"ad\" primary key (id,name) on conflict rollback, unique(name))");
            statement.execute("insert into user (id,name) values(1,'ad'),(4,'dw')");
            statement.execute("update user set name = 'qwe' where id = 1");
            statement.execute("alter table user add column id2 integer default 'ad' ");
//            statement.execute("alter table user rename to user");
            statement.execute("delete from user1 where id = 1");
            statement.execute("drop index if exists sd");
            statement.executeUpdate("create index sd on user2 (name)");
            state.exportChanges("state.xml");
            DBState dbState = new DBState("123",new SQLiteExecutor());
            dbState.importChanges("state.xml");
            if(dbState.getChanges()!=null){
                List<DBChange> dbChangeList = dbState.getChanges();
                SQLiteExecutor executor = new SQLiteExecutor();
                for(DBChange change : dbChangeList){
                    if(change instanceof ExecutableDBChange){
                        System.out.println(((ExecutableDBChange) change).getQuery());
                    }else {
                        System.out.println(executor.makeExecutable(change).getQuery());
                    }
                }
            }else {
                System.out.println("fufa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
