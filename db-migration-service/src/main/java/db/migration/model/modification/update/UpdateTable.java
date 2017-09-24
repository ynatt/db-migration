package db.migration.model.modification.update;

import db.migration.model.Column;
import db.migration.model.DBType;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import java.util.Map;

public class UpdateTable implements DBChange{
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

    @Override
    public String getChangeType() {
        return "UPDATE TABLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateTable)) return false;

        UpdateTable that = (UpdateTable) o;

        return (table != null ? table.equals(that.table) : that.table == null)
                && (newValues != null ? newValues.equals(that.newValues) : that.newValues == null)
                && (whereExpression != null ? whereExpression.equals(that.whereExpression) : that.whereExpression == null);
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (newValues != null ? newValues.hashCode() : 0);
        result = 31 * result + (whereExpression != null ? whereExpression.hashCode() : 0);
        return result;
    }
}
