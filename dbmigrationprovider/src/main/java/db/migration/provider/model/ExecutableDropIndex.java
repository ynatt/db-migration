package db.migration.provider.model;

public class ExecutableDropIndex extends AbstractExecutableDBChange {


    public ExecutableDropIndex(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "DROP INDEX";
    }

}
