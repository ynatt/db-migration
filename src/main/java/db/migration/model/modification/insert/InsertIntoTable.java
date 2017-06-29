package db.migration.model.modification.insert;

import db.migration.model.Table;

public abstract class InsertIntoTable {
    private Table table;
    private TableValues values;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public TableValues getValues() {
        return values;
    }

    public void setValues(TableValues values) {
        this.values = values;
    }
}
