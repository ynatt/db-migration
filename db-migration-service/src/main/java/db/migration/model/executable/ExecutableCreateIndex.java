package db.migration.model.executable;

public class ExecutableCreateIndex extends AbstractExecutableDBChange{

    public ExecutableCreateIndex(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "CREATE INDEX";
    }
}
