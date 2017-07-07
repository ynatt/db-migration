package db.migration.model.executable;

public class ExecutableDropTable extends AbstractExecutableDBChange{

    public ExecutableDropTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "DROP TABLE";
    }
}
