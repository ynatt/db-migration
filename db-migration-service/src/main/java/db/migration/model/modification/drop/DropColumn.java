package db.migration.model.modification.drop;

import db.migration.model.Column;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

public class DropColumn implements DBChange {
    private Table table;
    private Column column;

    public DropColumn() {
    }

    public DropColumn(Table table) {
        this.table = table;
    }

    public DropColumn(Table table, Column column) {
        this(table);
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
