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
    private List<IndexedColumn> indexedColumns;

    public Index() {

    }

    public Index(IndexName indexName) {
        this.indexName = indexName;
        this.indexedColumns = new ArrayList<>();
    }

    public Index(IndexName indexName, String indexType) {
        this(indexName);
        this.indexType = indexType;
    }

    public Index(IndexName indexName, String indexType, List<IndexedColumn> indexedColumns) {
        this(indexName,indexType);
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

    public void addIndexedColumn(IndexedColumn indexedColumn){
        if(indexedColumns!=null) {
            this.indexedColumns.add(indexedColumn);
        }
    }

    @Override
    public String getFullName() {
        return indexName.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Index)) return false;
        Index index = (Index) o;
        return (indexName != null ? indexName.equals(index.indexName) : index.indexName == null)
                && (indexType != null ? indexType.equals(index.indexType) : index.indexType == null)
                && (indexedColumns != null ? indexedColumns.equals(index.indexedColumns) : index.indexedColumns == null);
    }

    @Override
    public int hashCode() {
        int result = indexName != null ? indexName.hashCode() : 0;
        result = 31 * result + (indexType != null ? indexType.hashCode() : 0);
        result = 31 * result + (indexedColumns != null ? indexedColumns.hashCode() : 0);
        return result;
    }
}
