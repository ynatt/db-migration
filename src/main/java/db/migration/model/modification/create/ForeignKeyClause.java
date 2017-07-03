package db.migration.model.modification.create;

import db.migration.model.Column;
import db.migration.model.Table;

import java.util.Iterator;
import java.util.List;

public class ForeignKeyClause {
    private Table foreignTable;
    private List<Column> columns;
    private OnDeleteClause onDeleteClause;
    private OnUpdateClause onUpdateClause;
    private ForeignKeyDeferrable foreignKeyDeferrable;

    public ForeignKeyClause(Table foreignTable) {
        this.foreignTable = foreignTable;
    }

    public Table getForeignTable() {
        return foreignTable;
    }

    public void setForeignTable(Table foreignTable) {
        this.foreignTable = foreignTable;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public OnDeleteClause getOnDeleteClause() {
        return onDeleteClause;
    }

    public void setOnDeleteClause(OnDeleteClause onDeleteClause) {
        this.onDeleteClause = onDeleteClause;
    }

    public OnUpdateClause getOnUpdateClause() {
        return onUpdateClause;
    }

    public void setOnUpdateClause(OnUpdateClause onUpdateClause) {
        this.onUpdateClause = onUpdateClause;
    }

    public ForeignKeyDeferrable getForeignKeyDeferrable() {
        return foreignKeyDeferrable;
    }

    public void setForeignKeyDeferrable(ForeignKeyDeferrable foreignKeyDeferrable) {
        this.foreignKeyDeferrable = foreignKeyDeferrable;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("REFERENCES ");
        result.append(foreignTable.getFullName());
        if(columns!=null) {
            result.append(" ( ");
            Column column;
            for (Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); ) {
                column = iterator.next();
                result.append(column.getColumnName());
                if (iterator.hasNext()) {
                    result.append(" , ");
                }
            }
            result.append(" ) ");
        }
        if(onDeleteClause!=null) {
            result.append(onDeleteClause.toString());
        }
        if(onUpdateClause!=null) {
            result.append(onUpdateClause.toString());
        }
        if(foreignKeyDeferrable!=null) {
            result.append(foreignKeyDeferrable.toString());
        } else{
            result.append("NOT DEFERRABLE");
        }
        return result.toString();
    }
}
