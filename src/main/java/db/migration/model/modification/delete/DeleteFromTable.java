package db.migration.model.modification.delete;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

public class DeleteFromTable implements DBChange {
    private Table table;
    private String whereExpression;

    public DeleteFromTable() {
    }

    public DeleteFromTable(Table table) {
        this.table = table;
    }

    public DeleteFromTable(Table table, String whereExpression) {
        this.table = table;
        this.whereExpression = whereExpression;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getWhereExspression() {
        return whereExpression;
    }

    public void setWhereExspression(String whereExpression) {
        this.whereExpression = whereExpression;
    }



    @Override
    public String getChangeType() {
        return "DELETE FROM TABLE";
    }
}
