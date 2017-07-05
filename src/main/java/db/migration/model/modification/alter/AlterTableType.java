package db.migration.model.modification.alter;

public enum AlterTableType {
    RENAME("RENAME"),
    ADD("ADD");

    private String typeName;

    AlterTableType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
