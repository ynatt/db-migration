package db.migration.provider.model;

public class ExecutableInsertIntoTable extends AbstractExecutableDBChange  {

    public ExecutableInsertIntoTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "INSERT INTO";
    }

}
