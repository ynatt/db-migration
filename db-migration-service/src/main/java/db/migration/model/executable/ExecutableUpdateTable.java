package db.migration.model.executable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExecutableUpdateTable extends AbstractExecutableDBChange{

    public ExecutableUpdateTable(){
        super(null);
    }

    public ExecutableUpdateTable(String sql) {
        super(sql);
    }

    @Override
    public String getChangeType() {
        return "UPDATE TABLE";
    }
}
