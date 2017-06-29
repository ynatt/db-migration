package db.migration.model.modification.delete;

import db.migration.model.Table;

public abstract class DeleteFromTable {
    private Table table;
    private String whereExpression;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getWhereExspression() {
        return whereExpression;
    }

    public void setWhereExspression(String whereExspression) {
        this.whereExpression = whereExspression;
    }
}
