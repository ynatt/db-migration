package db.migration.model;

import java.util.ArrayList;
import java.util.List;

public class Index extends DBObject {
    private String indexName;
    private String indexType;
//    private String schemaName;
//    private String tableName;
//    private boolean isUnique;
//    private boolean isClustered;
    private List<String> columnNames = new ArrayList<>();
//    private List<String> includes = new ArrayList<>();


    public Index() {

    }

    public Index(String indexName) {
        this.indexName = indexName;
    }

    public Index(String indexName, String indexType) {
        this.indexName = indexName;
        this.indexType = indexType;
    }

    public Index(String indexName, String indexType, List<String> columnNames) {
        this.indexName = indexName;
        this.indexType = indexType;
        this.columnNames = columnNames;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    //    public String getSchemaName() {
//        return schemaName;
//    }
//
//    public void setSchemaName(String schemaName) {
//        this.schemaName = schemaName;
//    }
//
//    public String getTableName() {
//        return tableName;
//    }
//
//    public void setTableName(String tableName) {
//        this.tableName = tableName;
//    }

//    public boolean isUnique() {
//        return isUnique;
//    }
//
//    public void setUnique(boolean unique) {
//        isUnique = unique;
//    }
//
//    public boolean isClustered() {
//        return isClustered;
//    }
//
//    public void setClustered(boolean clustered) {
//        isClustered = clustered;
//    }


    public void setColumnNames(List<String> columns) {
        this.columnNames = columns;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

//    public List<String> getIncludes() {
//        return includes;
//    }
//
//    public void addIncludes(String include) {
//        this.includes.add(include);
//    }
}
