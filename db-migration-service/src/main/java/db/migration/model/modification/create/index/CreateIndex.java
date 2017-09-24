package db.migration.model.modification.create.index;

import db.migration.model.Index;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class CreateIndex implements DBChange {
    private Table table;
    private boolean ifNotExists = false;
    private boolean isUnique = false;
    private Index index;

    public CreateIndex() {

    }

    public CreateIndex(Index index) {
        this.index = index;
    }

    public CreateIndex(Table table, Index index) {
        this(index);
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
    @XmlElement
    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }
    @XmlAttribute
    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }

    public boolean isUnique() {
        return isUnique;
    }
    @XmlAttribute
    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public Index getIndex() {
        return index;
    }
    @XmlElement
    public void setIndex(Index index) {
        this.index = index;
    }

    @Override
    public String getChangeType() {
        return "CREATE INDEX";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateIndex)) return false;
        CreateIndex that = (CreateIndex) o;
        return ifNotExists == that.ifNotExists
                && isUnique == that.isUnique
                && (table != null ? table.equals(that.table) : that.table == null)
                && (index != null ? index.equals(that.index) : that.index == null);
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (ifNotExists ? 1 : 0);
        result = 31 * result + (isUnique ? 1 : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }
}
