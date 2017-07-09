package db.migration.model.executable;

public class ExecutableInsertIntoTable extends AbstractExecutableDBChange  {

    public ExecutableInsertIntoTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "INSERT INTO";
    }

}
