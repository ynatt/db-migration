package db.migration.model.modification.create;

import db.migration.model.Index;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

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

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }

    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    @Override
    public String getChangeType() {
        return "CREATE INDEX";
    }
}
