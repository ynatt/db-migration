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
        this.schemaName = schemaName;
        this.tableName = tableName;
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
}
