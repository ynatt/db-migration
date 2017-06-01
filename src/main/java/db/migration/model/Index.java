package db.migration.model;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private String name;
    private String schemaName;
    private String tableName;
    private boolean isUnique;
    private boolean isClustered;
    private List<String> columns = new ArrayList<>();
    private List<String> includes = new ArrayList<>();

    public Index(){
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isClustered() {
        return isClustered;
    }

    public void setClustered(boolean clustered) {
        isClustered = clustered;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void addColumn(String column) {
        this.columns.add(column);
    }

    public List<String> getIncludes() {
        return includes;
    }

    public void addIncludes(String include) {
        this.includes.add(include);
    }
}
