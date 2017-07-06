package db.migration.provider.model;

public class ExecutableDeleteFromTable extends AbstractExecutableDBChange{

    public ExecutableDeleteFromTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "DELETE FROM TABLE";
    }
}
