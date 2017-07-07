package db.migration.model.modification.create.table;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
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
    @XmlElement
    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }
    @XmlElement
    public void setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
    }
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }
    @XmlElement
    public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    public List<TableConstraint> getTableConstraints() {
        return tableConstraints;
    }
    @XmlElementWrapper(name = "TableConstraints")
    @XmlElements({
            @XmlElement(name = "IndexedConstraint",type=IndexedConstraint.class),
            @XmlElement(name = "ForeignKeyConstraint",type=ForeignKeyConstraint.class)}
    )
    public void setTableConstraints(List<TableConstraint> tableConstraints) {
        this.tableConstraints = tableConstraints;
    }

    public void addTableConstraint(TableConstraint tableConstraint){
        if(this.tableConstraints!=null) {
            this.tableConstraints.add(tableConstraint);
        } else {
            this.tableConstraints = new ArrayList<>();
            this.tableConstraints.add(tableConstraint);
        }
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
