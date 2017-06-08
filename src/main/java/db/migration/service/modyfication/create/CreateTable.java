package db.migration.service.modyfication.create;

import db.migration.model.Column;
import db.migration.service.modyfication.DBChange;

import java.util.List;

public abstract class CreateTable implements DBChange {
    private String schemaName;
    private String tableName;
    private List<Column> columns;

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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
