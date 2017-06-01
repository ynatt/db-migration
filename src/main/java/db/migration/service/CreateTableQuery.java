package db.migration.service;

import db.migration.model.Column;

import java.util.List;

public abstract class CreateTableQuery implements DBChange {
    private String schemaName;
    private String tableName;
    private List<Column> columns;
}
