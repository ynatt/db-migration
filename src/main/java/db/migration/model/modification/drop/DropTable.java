package db.migration.model.modification.drop;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DropTable implements DBChange {
    private Table table;
    private boolean ifExists = false;

    public DropTable() {

    }

    public DropTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
    @XmlElement
    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isIfExists() {
        return ifExists;
    }
    @XmlElement
    public void setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    @Override
    public String getChangeType() {
        return "DROP TABLE";
    }
}
