package db.migration.model.modification.delete;

import db.migration.model.Column;
import db.migration.model.modification.DBChange;

public abstract class DeleteColumn implements DBChange {
    private String schemaName;
    private String tableName;
    private Column column;

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public Column getColumn() {
        return column;
    }
}
