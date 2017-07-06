package db.migration.provider.model;

public class ExecutableUpdateTable extends AbstractExecutableDBChange{

    public ExecutableUpdateTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "UPDATE TABLE";
    }
}
