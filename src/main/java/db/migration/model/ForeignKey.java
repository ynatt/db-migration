package db.migration.model;

import java.util.ArrayList;
import java.util.List;

public class ForeignKey {
    private String name;
    private String foreignReference;
    private String primaryReference;
    private List<String> foreignColumns = new ArrayList<>();
    private List<String> primaryColumns = new ArrayList<>();

    public ForeignKey(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForeignReference() {
        return foreignReference;
    }

    public void setForeignReference(String foreignReference) {
        this.foreignReference = foreignReference;
    }

    public String getPrimaryReference() {
        return primaryReference;
    }

    public void setPrimaryReference(String primaryReference) {
        this.primaryReference = primaryReference;
    }

    public List<String> getForeignColumns() {
        return foreignColumns;
    }

    public void addForeignColumn(String foreignColumn) {
        this.foreignColumns.add(foreignColumn);
    }

    public List<String> getPrimaryColumns() {
        return primaryColumns;
    }

    public void addPrimaryColumns(String primaryColumn) {
        this.primaryColumns.add(primaryColumn);
    }
}
