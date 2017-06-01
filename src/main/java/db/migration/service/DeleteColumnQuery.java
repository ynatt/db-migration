package db.migration.service;

import db.migration.model.Column;

public abstract class DeleteColumnQuery implements DBChange{
    private String schemaName;
    private String tableName;
    private Column column;
}
