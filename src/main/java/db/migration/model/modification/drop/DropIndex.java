package db.migration.model.modification.drop;

import db.migration.model.IndexName;
import db.migration.model.modification.DBChange;

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
    @XmlElement
    public void setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    @Override
    public String getChangeType() {
        return "DROP INDEX";
    }
}
