package db.migration.model;

public class Column extends DBObject implements MultiName{
    private Table table;
    private String columnName;

    public Column() {

    }

    public Column(String columnName) {
        this.columnName = columnName;
    }

    public Column(Table table, String columnName) {
        this.table = table;
        this.columnName = columnName;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getColumnName() {
        return columnName;
    }

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
}
