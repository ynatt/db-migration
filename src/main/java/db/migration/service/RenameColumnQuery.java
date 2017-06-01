package db.migration.service;

import db.migration.model.Column;

public abstract class RenameColumnQuery implements DBChange{
    private String schemaName;
    private String tableName;
    private Column oldColumn;
    private Column newColumn;
}
