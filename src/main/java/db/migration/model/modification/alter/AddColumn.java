package db.migration.model.modification.alter;

import db.migration.model.modification.create.ColumnDefinition;

public class AddColumn extends AbstractAlterType {
    private ColumnDefinition newColumnDefinition;

    AddColumn(AlterTableType alterTypeName) {
        super(alterTypeName);
    }

    public AddColumn(AlterTableType alterTypeName, ColumnDefinition newColumnDefinition) {
        super(alterTypeName);
        this.newColumnDefinition = newColumnDefinition;
    }

    public ColumnDefinition getNewColumnDefinition() {
        return newColumnDefinition;
    }

    public void setNewColumnDefinition(ColumnDefinition newColumnDefinition) {
        this.newColumnDefinition = newColumnDefinition;
    }

    @Override
    public String toString() {
        return "ADD COLUMN "+newColumnDefinition;
    }
}
