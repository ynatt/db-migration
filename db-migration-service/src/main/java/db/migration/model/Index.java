package db.migration.model;

import db.migration.model.modification.create.table.IndexedColumn;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
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
    @XmlElement
    public void setIndexName(IndexName indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }
    @XmlAttribute
    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public List<IndexedColumn> getIndexedColumns() {
        return indexedColumns;
    }
    @XmlElement
    public void setIndexedColumns(List<IndexedColumn> indexedColumns) {
        this.indexedColumns = indexedColumns;
    }

    @Override
    public String getFullName() {
        return indexName.getFullName();
    }
}
