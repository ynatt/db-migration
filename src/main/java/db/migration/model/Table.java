package db.migration.model;

import java.util.ArrayList;
import java.util.List;

public class Table extends DBObject {
    private String name;
    private String schemaName;
    private List<Column> columns = new ArrayList<>();
    private List<ForeignKey> foreignKeys = new ArrayList<>();
    private List<Index> indexes = new ArrayList<>();

    public Table(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void addForeignKey(ForeignKey foreignKey) {
        this.foreignKeys.add(foreignKey);
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public void addIndexes(Index index) {
        this.indexes.add(index);
    }
}
