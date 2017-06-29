package db.migration.model.modification.drop;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

public abstract class DropTable implements DBChange {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
