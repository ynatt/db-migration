package db.migration.model.executable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExecutableDeleteFromTable extends AbstractExecutableDBChange{

    public ExecutableDeleteFromTable(){
        super(null);
    }

    public ExecutableDeleteFromTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "DELETE FROM TABLE";
    }
}
