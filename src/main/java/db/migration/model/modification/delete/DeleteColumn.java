package db.migration.model.modification.delete;

import db.migration.model.Column;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

public class DeleteColumn implements DBChange {
    private Table table;
    private Column column;

    public DeleteColumn() {
    }

    public DeleteColumn(Table table) {
        this.table = table;
    }

    public DeleteColumn(Table table, Column column) {
        this.table = table;
        this.column = column;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    @Override
    public String getChangeType() {
        return null;
    }
}
