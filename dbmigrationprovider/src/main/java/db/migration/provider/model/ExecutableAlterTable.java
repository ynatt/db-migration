package db.migration.provider.model;

public class ExecutableAlterTable extends AbstractExecutableDBChange {


    public ExecutableAlterTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "ALTER TABLE";
    }

}
