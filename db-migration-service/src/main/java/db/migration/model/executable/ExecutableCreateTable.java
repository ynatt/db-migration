package db.migration.model.executable;

public class ExecutableCreateTable extends AbstractExecutableDBChange{

    public ExecutableCreateTable(String sql){
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "CREATE TABLE";
    }
}
