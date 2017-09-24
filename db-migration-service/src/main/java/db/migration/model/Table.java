package db.migration.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Table extends DBObject implements MultiName {
    private String schemaName;
    private String tableName;

    public Table() {

    }

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public Table(String schemaName, String tableName) {
        this(tableName);
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }
    @XmlAttribute
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    @XmlAttribute
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
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
        if (tableName != null){
            fullName+=tableName;
        }
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return (schemaName != null ? schemaName.equals(table.schemaName) : table.schemaName == null)
                && (tableName != null ? tableName.equals(table.tableName) : table.tableName == null);
    }

    @Override
    public int hashCode() {
        int result = schemaName != null ? schemaName.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        return result;
    }
}
