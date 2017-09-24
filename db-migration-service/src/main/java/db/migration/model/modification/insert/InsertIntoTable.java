package db.migration.model.modification.insert;

import db.migration.model.Column;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class InsertIntoTable implements DBChange {
    private Table table;
    private List<Column> columns;
    private List<String> values;

    public InsertIntoTable() {
        columns = new ArrayList<>();
        values = new ArrayList<>();
    }

    public InsertIntoTable(Table table) {
        this();
        this.table = table;
    }

    public InsertIntoTable(Table table, List<Column> columns) {
        this(table);
        this.columns = columns;
    }

    public InsertIntoTable(Table table, List<Column> columns, List<String> values) {
        this(table,columns);
        this.values = values;
    }

    public Table getTable() {
        return table;
    }
    @XmlElement
    public void setTable(Table table) {
        this.table = table;
    }

    public List<String> getValues() {
        return values;
    }
    @XmlElement
    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<Column> getColumns() {
        return columns;
    }
    @XmlElement
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String getChangeType() {
        return "INSERT INTO";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertIntoTable)) return false;

        InsertIntoTable that = (InsertIntoTable) o;

        return (table != null ? table.equals(that.table) : that.table == null)
                && (columns != null ? columns.equals(that.columns) : that.columns == null)
                && (values != null ? values.equals(that.values) : that.values == null);
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (columns != null ? columns.hashCode() : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }
}
