package db.migration.model.executable;

public class ExecutableAlterTable extends AbstractExecutableDBChange {


    public ExecutableAlterTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "ALTER TABLE";
    }

}
