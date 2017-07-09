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
        this.table = table;
        this.index = index;
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
}
