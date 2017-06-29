package db.migration.model.modification.rename;

import db.migration.model.modification.DBChange;

public abstract class RenameTable implements DBChange {
    private String schemaName;
    private String oldTableName;
    private String newTableName;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getOldTableName() {
        return oldTableName;
    }

    public void setOldTableName(String oldTableName) {
        this.oldTableName = oldTableName;
    }

    public String getNewTableName() {
        return newTableName;
    }

    public void setNewTableName(String newTableName) {
        this.newTableName = newTableName;
    }
}
