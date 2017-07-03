package db.migration.model.modification.create;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;
import db.migration.service.TrackingConnection;

import java.util.List;

public class CreateTable implements DBChange {
    private Table table;
    private List<ColumnDefinition> columnDefinitions;
    private boolean ifNotExists=false;
    private List<TableConstraint> tableConstraints;

    public CreateTable() {

    }

    public CreateTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }

    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    public List<TableConstraint> getTableConstraints() {
        return tableConstraints;
    }

    public void setTableConstraints(List<TableConstraint> tableConstraints) {
        this.tableConstraints = tableConstraints;
    }

    public void addTableConstraint(TableConstraint tableConstraint){
        this.tableConstraints.add(tableConstraint);
    }

    @Override
    public String getChangeType() {
        return "CREATE TABLE";
    }

    @Override
    public String toString() {
        return "Type: Create Table;";
    }
}
