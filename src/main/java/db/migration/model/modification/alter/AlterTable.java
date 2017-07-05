package db.migration.model.modification.alter;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

public class AlterTable implements DBChange {
    private Table table;
    private AbstractAlterType alterType;

    public AlterTable() {
    }

    public AlterTable(Table table) {
        this.table = table;
    }

    public AlterTable(Table table, AbstractAlterType alterType) {
        this.table = table;
        this.alterType = alterType;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public AbstractAlterType getAlterType() {
        return alterType;
    }

    public void setAlterType(AbstractAlterType alterType) {
        this.alterType = alterType;
    }

    @Override
    public String getChangeType() {
        return "ALTER TABLE";
    }
}
