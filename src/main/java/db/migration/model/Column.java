package db.migration.model;

public class Column extends DBObject implements MultiName{
    private Table table;
    private String columnName;
//    private DBType type;
//    private DBType defaultValue;
//    private boolean isPrimaryKey;
//    private boolean isForeignKey;
//    private boolean isNullable;
//    private boolean isUnique;


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

//    public DBType getDefaultValue() {
//        return defaultValue;
//    }
//
//    public DBType getType(){
//        return type;
//    }
//
//    public void setType(DBType type) {
//        this.type = type;
//    }
//
//    public void setDefaultValue(DBType defaultValue) {
//        this.defaultValue = defaultValue;
//    }
//
//    public boolean isPrimaryKey() {
//        return isPrimaryKey;
//    }
//
//    public void setPrimaryKey(boolean primaryKey) {
//        isPrimaryKey = primaryKey;
//    }
//
//    public boolean isForeignKey() {
//        return isForeignKey;
//    }
//
//    public void setForeignKey(boolean foreignKey) {
//        isForeignKey = foreignKey;
//    }
//
//    public boolean isNullable() {
//        return isNullable;
//    }
//
//    public void setNullable(boolean nullable) {
//        isNullable = nullable;
//    }
//
//    public boolean isUnique() {
//        return isUnique;
//    }
//
//    public void setUnique(boolean unique) {
//        isUnique = unique;
//    }
}
