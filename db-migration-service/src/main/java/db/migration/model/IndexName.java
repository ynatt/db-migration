package db.migration.model;

import javax.xml.bind.annotation.XmlAttribute;

public class IndexName implements MultiName{
    private String schemaName;
    private String indexName;

    public IndexName() {

    }

    public IndexName(String indexName) {
        this.indexName = indexName;
    }

    public IndexName(String schemaName, String indexName) {
        this(indexName);
        this.schemaName = schemaName;
    }

    public String getSchemaName() {
        return schemaName;
    }
    @XmlAttribute
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getIndexName() {
        return indexName;
    }
    @XmlAttribute
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Override
    public String getFullName() {
        String fullName = "";
        if (schemaName != null){
            fullName+=schemaName;
            if(!fullName.isEmpty()){
                fullName+=".";
            }
        }
        if (indexName != null){
            fullName+=indexName;
        }
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndexName)) return false;
        IndexName indexName1 = (IndexName) o;
        return (schemaName != null ? schemaName.equals(indexName1.schemaName) : indexName1.schemaName == null)
                && (indexName != null ? indexName.equals(indexName1.indexName) : indexName1.indexName == null);
    }

    @Override
    public int hashCode() {
        int result = schemaName != null ? schemaName.hashCode() : 0;
        result = 31 * result + (indexName != null ? indexName.hashCode() : 0);
        return result;
    }
}
