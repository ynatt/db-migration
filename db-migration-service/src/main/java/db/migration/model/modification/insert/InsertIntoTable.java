package db.migration.model.modification.insert;

import db.migration.model.Column;
import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement
public class InsertIntoTable implements DBChange {
    private Table table;
    private List<Column> columns;
    private List<String> values;

    public InsertIntoTable() {

    }

    public InsertIntoTable(Table table) {
        this.table = table;
    }

    public InsertIntoTable(Table table, List<Column> columns) {
        this.table = table;
        this.columns = columns;
    }

    public InsertIntoTable(Table table, List<Column> columns, List<String> values) {
        this.table = table;
        this.columns = columns;
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
}
