package db.migration.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Column extends DBObject implements MultiName{
    private Table table;
    private String columnName;

    public Column() {

    }

    public Column(String columnName) {
        this.columnName = columnName;
    }

    public Column(Table table, String columnName) {
        this(columnName);
        this.table=table;
    }

    public Table getTable() {
        return table;
    }
    @XmlElement
    public void setTable(Table table) {
        this.table = table;
    }

    public String getColumnName() {
        return columnName;
    }
    @XmlAttribute
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getFullName() {
        String fullName = "";
        if (table != null){
            fullName+=table.getFullName();
            if(!fullName.isEmpty()){
                fullName+=".";
            }
        }
        if(columnName != null){
            fullName+=columnName;
        }
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column column = (Column) o;
        return (table != null ? table.equals(column.table) : column.table == null)
                && (columnName != null ? columnName.equals(column.columnName) : column.columnName == null);
    }

    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (columnName != null ? columnName.hashCode() : 0);
        return result;
    }
}
