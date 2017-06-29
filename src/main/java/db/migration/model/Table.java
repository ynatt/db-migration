package db.migration.model;


public class Table extends DBObject implements MultiName {
    private String schemaName;
    private String tableName;
//    private List<Column> columns = new ArrayList<>();
//    private List<ForeignKey> foreignKeys = new ArrayList<>();
//    private List<Index> indexes = new ArrayList<>();


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

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

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

//    public List<Column> getColumns() {
//        return columns;
//    }
//
//    public void addColumn(Column column) {
//        this.columns.add(column);
//    }
//
//    public List<ForeignKey> getForeignKeys() {
//        return foreignKeys;
//    }
//
//    public void addForeignKey(ForeignKey foreignKey) {
//        this.foreignKeys.add(foreignKey);
//    }
//
//    public List<Index> getIndexes() {
//        return indexes;
//    }
//
//    public void addIndexes(Index index) {
//        this.indexes.add(index);
//    }

}
