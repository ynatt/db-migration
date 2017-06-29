package db.migration.model.modification.create;

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

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColomnDataType() {
        return columnDataType;
    }

    public void setColomnDataType(String colomnDataType) {
        this.columnDataType = colomnDataType;
    }

    public List<String> getColumnSpecs() {
        return columnSpecs;
    }

    public void setColumnSpecs(List<String> columnSpecs) {
        this.columnSpecs = columnSpecs;
    }
}
