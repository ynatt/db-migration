package db.migration.model.modification.drop;

import db.migration.model.IndexName;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DropIndex implements DBChange {
    private boolean ifExists = false;
    private IndexName indexName;

    public DropIndex() {
    }

    public DropIndex(IndexName indexName) {
        this.indexName = indexName;
    }

    public IndexName getIndexName() {
        return indexName;
    }
    @XmlElement
    public void setIndexName(IndexName indexName) {
        this.indexName = indexName;
    }

    public boolean isIfExists() {
        return ifExists;
    }
    @XmlAttribute
    public void setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    @Override
    public String getChangeType() {
        return "DROP INDEX";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DropIndex)) return false;
        DropIndex dropIndex = (DropIndex) o;
        return ifExists == dropIndex.ifExists
                && (indexName != null ? indexName.equals(dropIndex.indexName) : dropIndex.indexName == null);
    }

    @Override
    public int hashCode() {
        int result = (ifExists ? 1 : 0);
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        return result;
    }
}
