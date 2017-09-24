package db.migration.model.modification.alter.table;

import db.migration.model.Table;
import db.migration.model.modification.alter.table.enums.AlterTableType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RenameTo extends AbstractAlterType {
    private Table newTable;

    public RenameTo() {

    }

    public RenameTo(AlterTableType alterTypeName) {
        super(alterTypeName);
    }

    public RenameTo(AlterTableType alterTypeName, Table newTable) {
        super(alterTypeName);
        this.newTable = newTable;
    }

    public Table getNewTable() {
        return newTable;
    }
    @XmlElement
    public void setNewTable(Table newTable) {
        this.newTable = newTable;
    }

    @Override
    public String toString() {
        return "RENAME TO " + newTable.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenameTo)) return false;
        if (!super.equals(o)) return false;
        RenameTo renameTo = (RenameTo) o;
        return newTable != null ? newTable.equals(renameTo.newTable) : renameTo.newTable == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (newTable != null ? newTable.hashCode() : 0);
        return result;
    }
}
