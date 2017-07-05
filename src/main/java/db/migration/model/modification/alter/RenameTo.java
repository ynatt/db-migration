package db.migration.model.modification.alter;

import db.migration.model.Table;

public class RenameTo extends AbstractAlterType{
    private Table newTable;

    RenameTo(AlterTableType alterTypeName) {
        super(alterTypeName);
    }

    public RenameTo(AlterTableType alterTypeName, Table newTable) {
        super(alterTypeName);
        this.newTable = newTable;
    }

    public Table getNewTable() {
        return newTable;
    }

    public void setNewTable(Table newTable) {
        this.newTable = newTable;
    }

    @Override
    public String toString() {
        return "RENAME TO " + newTable.getFullName();
    }
}
