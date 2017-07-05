package db.migration.model;

import db.migration.model.modification.create.IndexedColumn;

import java.util.ArrayList;
import java.util.List;

public class Index extends DBObject implements MultiName {
    private IndexName indexName;
    private String indexType;
    private List<IndexedColumn> indexedColumns = new ArrayList<>();

    public Index() {

    }

    public Index(IndexName indexName) {
        this.indexName = indexName;
    }

    public Index(IndexName indexName, String indexType) {
        this.indexName = indexName;
        this.indexType = indexType;
    }

    public Index(IndexName indexName, String indexType, List<IndexedColumn> indexedColumns) {
        this.indexName = indexName;
        this.indexType = indexType;
        this.indexedColumns = indexedColumns;
    }

    public IndexName getIndexName() {
        return indexName;
    }

    public void setIndexName(IndexName indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public List<IndexedColumn> getIndexedColumns() {
        return indexedColumns;
    }

    public void setIndexedColumns(List<IndexedColumn> indexedColumns) {
        this.indexedColumns = indexedColumns;
    }

    @Override
    public String getFullName() {
        return indexName.getFullName();
    }
}
