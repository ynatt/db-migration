package db.migration.model.modification.create;

import db.migration.model.Column;
import db.migration.model.modification.DBChange;

public abstract class CreateColumn implements DBChange {
    private String schemaName;
    private String tableName;
    private Column column;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }
}
