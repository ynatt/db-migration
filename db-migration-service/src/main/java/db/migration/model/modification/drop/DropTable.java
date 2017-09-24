package db.migration.model.modification.drop;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlAttribute;
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
    @XmlAttribute
    public void setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    @Override
    public String getChangeType() {
        return "DROP TABLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DropTable)) return false;

        DropTable dropTable = (DropTable) o;

        return ifExists == dropTable.ifExists
                && (table != null ? table.equals(dropTable.table) : dropTable.table == null);
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (ifExists ? 1 : 0);
        return result;
    }
}
