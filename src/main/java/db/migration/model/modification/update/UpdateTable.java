package db.migration.model.modification.update;

import db.migration.model.Column;
import db.migration.model.DBType;
import db.migration.model.Table;

import java.util.Map;

public class UpdateTable {
    private Table table;
    private Map<Column,DBType> newValues;
    private String whereExpression;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Map<Column, DBType> getNewValues() {
        return newValues;
    }

    public void setNewValues(Map<Column, DBType> newValues) {
        this.newValues = newValues;
    }

    public String getWhereExpression() {
        return whereExpression;
    }

    public void setWhereExpression(String whereExpression) {
        this.whereExpression = whereExpression;
    }
}
