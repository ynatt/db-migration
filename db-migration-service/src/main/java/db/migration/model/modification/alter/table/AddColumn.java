package db.migration.model.modification.alter.table;

import db.migration.model.modification.alter.table.enums.AlterTableType;
import db.migration.model.modification.create.table.ColumnDefinition;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddColumn extends AbstractAlterType {
    private ColumnDefinition newColumnDefinition;

    public AddColumn(){

    }

    public AddColumn(AlterTableType alterTypeName) {
        super(alterTypeName);
    }

    public AddColumn(AlterTableType alterTypeName, ColumnDefinition newColumnDefinition) {
        super(alterTypeName);
        this.newColumnDefinition = newColumnDefinition;
    }

    public ColumnDefinition getNewColumnDefinition() {
        return newColumnDefinition;
    }
    @XmlElement
    public void setNewColumnDefinition(ColumnDefinition newColumnDefinition) {
        this.newColumnDefinition = newColumnDefinition;
    }

    @Override
    public String toString() {
        return "ADD COLUMN "+newColumnDefinition;
    }
}
