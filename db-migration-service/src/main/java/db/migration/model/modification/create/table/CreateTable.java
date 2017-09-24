package db.migration.model.modification.create.table;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class CreateTable implements DBChange {
    private Table table;
    private List<ColumnDefinition> columnDefinitions;
    private boolean ifNotExists=false;
    private List<TableConstraint> tableConstraints;

    public CreateTable() {
        columnDefinitions = new ArrayList<>();
        tableConstraints = new ArrayList<>();
    }

    public CreateTable(Table table) {
        this();
        this.table = table;
    }

    public CreateTable(Table table, List<ColumnDefinition> columnDefinitions) {
        this(table);
        this.columnDefinitions = columnDefinitions;
    }

    public CreateTable(Table table, List<ColumnDefinition> columnDefinitions, List<TableConstraint> tableConstraints) {
        this(table,columnDefinitions);
        this.tableConstraints = tableConstraints;
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
    @XmlAttribute
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

    public void addColumnDefinition(ColumnDefinition columnDefinition){
        if(this.columnDefinitions!=null) {
            this.columnDefinitions.add(columnDefinition);
        }
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
        }
    }

    @Override
    public String getChangeType() {
        return "CREATE TABLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTable)) return false;

        CreateTable that = (CreateTable) o;

        return ifNotExists == that.ifNotExists
                && (table != null ? table.equals(that.table) : that.table == null)
                && (columnDefinitions != null ? columnDefinitionsEquals(columnDefinitions,that.columnDefinitions) : that.columnDefinitions == null)
                && (tableConstraints != null ? tableConstraintEquals(tableConstraints,that.tableConstraints) : that.tableConstraints == null);
    }

    private boolean tableConstraintEquals(List<TableConstraint> firstConstr, List<TableConstraint> secondConstr){
        if(firstConstr==null | secondConstr==null){
            return false;
        }
        Set<TableConstraint> sortedFirstConstr = new TreeSet<>(new Comparator<TableConstraint>() {
            @Override
            public int compare(TableConstraint o1, TableConstraint o2) {
                return (o1.getName()+o1.getType()).compareTo(o2.getName()+o2.getType());
            }
        });
        sortedFirstConstr.addAll(firstConstr);
        Set<TableConstraint> sortedSecondConstr = new TreeSet<>(new Comparator<TableConstraint>() {
            @Override
            public int compare(TableConstraint o1, TableConstraint o2) {
                return (o1.getName()+o1.getType()).compareTo(o2.getName()+o2.getType());
            }
        });
        sortedSecondConstr.addAll(secondConstr);
        if(sortedFirstConstr.size()!=sortedSecondConstr.size()){
            return false;
        }
        Iterator<TableConstraint> firstIterator = sortedFirstConstr.iterator();
        Iterator<TableConstraint> secondIterator = sortedSecondConstr.iterator();
        while (firstIterator.hasNext() & secondIterator.hasNext()){
            if(!firstIterator.next().equals(secondIterator.next())){
                return false;
            }
        }
        return true;
    }

    private boolean columnDefinitionsEquals(List<ColumnDefinition> firstColDefs, List<ColumnDefinition> secondColDefs){
        if(firstColDefs==null | secondColDefs==null){
            return false;
        }
        Set<ColumnDefinition> sortedFirstColDefs = new TreeSet<>(new Comparator<ColumnDefinition>() {
            @Override
            public int compare(ColumnDefinition o1, ColumnDefinition o2) {
                return o1.getColumnName().compareToIgnoreCase(o2.getColumnName());
            }
        });
        sortedFirstColDefs.addAll(firstColDefs);
        Set<ColumnDefinition> sortedSecondColDefs = new TreeSet<>(new Comparator<ColumnDefinition>() {
            @Override
            public int compare(ColumnDefinition o1, ColumnDefinition o2) {
                return o1.getColumnName().compareToIgnoreCase(o2.getColumnName());
            }
        });
        sortedSecondColDefs.addAll(secondColDefs);
        if(sortedFirstColDefs.size()!=sortedSecondColDefs.size()){
            return false;
        }
        Iterator<ColumnDefinition> firstIterator = sortedFirstColDefs.iterator();
        Iterator<ColumnDefinition> secondIterator = sortedSecondColDefs.iterator();
        while (firstIterator.hasNext() & secondIterator.hasNext()){
            if(!firstIterator.next().equals(secondIterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (columnDefinitions != null ? columnDefinitions.hashCode() : 0);
        result = 31 * result + (ifNotExists ? 1 : 0);
        result = 31 * result + (tableConstraints != null ? tableConstraints.hashCode() : 0);
        return result;
    }
}
