package db.migration.model.modification.create.table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

public class ColumnDefinition {
    private String columnName;
    private String columnDataType;
    private List<String> columnSpecs;

    public ColumnDefinition() {
        columnSpecs = new ArrayList<>();
    }

    public ColumnDefinition(String columnName) {
        this();
        this.columnName = columnName;
    }

    public ColumnDefinition(String columnName, String columnDataType) {
        this(columnName);
        this.columnDataType = columnDataType;
    }

    public ColumnDefinition(String columnName, String columnDataType, List<String> columnSpecs) {
        this(columnName,columnDataType);
        this.columnSpecs = columnSpecs;
    }

    public String getColumnName() {
        return columnName;
    }
    @XmlAttribute
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDataType() {
        return columnDataType;
    }
    @XmlAttribute
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnDefinition)) return false;
        ColumnDefinition that = (ColumnDefinition) o;
        return (columnName != null ? columnName.equalsIgnoreCase(that.columnName) : that.columnName == null)
                && (columnDataType != null ? columnDataType.equalsIgnoreCase(that.columnDataType) : that.columnDataType == null)
                && (columnSpecs != null ? columnSpecsEquals(columnSpecs, that.columnSpecs) : that.columnSpecs == null);
    }

    private boolean columnSpecsEquals(List<String> firstSpecs,List<String> secondSpecs){
        if(firstSpecs==null | secondSpecs==null){
            return false;
        }
        Set<String> sortedFirstSpecs = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        sortedFirstSpecs.addAll(firstSpecs);
        Set<String> sortedSecondSpecs = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        sortedSecondSpecs.addAll(secondSpecs);
        if(sortedFirstSpecs.size()!=sortedSecondSpecs.size()){
            return false;
        }
        Iterator<String> firstIterator = sortedFirstSpecs.iterator();
        Iterator<String> secondIterator = sortedSecondSpecs.iterator();
        while (firstIterator.hasNext() & secondIterator.hasNext()){
            if(!firstIterator.next().equalsIgnoreCase(secondIterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = columnName != null ? columnName.hashCode() : 0;
        result = 31 * result + (columnDataType != null ? columnDataType.hashCode() : 0);
        result = 31 * result + (columnSpecs != null ? columnSpecs.hashCode() : 0);
        return result;
    }
}
