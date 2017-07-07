package db.migration.model.modification.create.table;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class ColumnDefinition {
    private String columnName;
    private String columnDataType;
    private List<String> columnSpecs;

    public ColumnDefinition() {
        columnSpecs = new ArrayList<>();
    }

    public ColumnDefinition(String columnName, String columnDataType) {
        this.columnName = columnName;
        this.columnDataType = columnDataType;
    }

    public String getColumnName() {
        return columnName;
    }
    @XmlElement
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDataType() {
        return columnDataType;
    }
    @XmlElement
    public void setColumnDataType(String columnDataType) {
        this.columnDataType = columnDataType;
    }

    public List<String> getColumnSpecs() {
        return columnSpecs;
    }
    @XmlElement
    public void setColumnSpecs(List<String> columnSpecs) {
        this.columnSpecs = columnSpecs;
    }

    public void addColumnSpec(String spec){
        columnSpecs.add(spec);
    }

    @Override
    public String toString() {
        return columnName+" "+columnDataType+columnSpecs();
    }

    private String columnSpecs(){
        StringBuilder result = new StringBuilder();
        for(String spec: columnSpecs){
            result.append(" ");
            result.append(spec);
        }
        return result.toString();
    }
}
