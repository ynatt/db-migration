package db.migration.service.modyfication.delete;

import db.migration.model.Table;
import db.migration.service.modyfication.DBChange;

public abstract class DeleteTable implements DBChange {
    private Table table;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
