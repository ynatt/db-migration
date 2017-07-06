package db.migration.provider.model;

public class ExecutableCreateIndex extends AbstractExecutableDBChange{

    public ExecutableCreateIndex(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "CREATE INDEX";
    }
}
