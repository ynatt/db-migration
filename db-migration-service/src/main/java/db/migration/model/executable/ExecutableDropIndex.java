package db.migration.model.executable;

public class ExecutableDropIndex extends AbstractExecutableDBChange {


    public ExecutableDropIndex(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "DROP INDEX";
    }

}
