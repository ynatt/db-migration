package db.migration.model.modification.create;

public enum ConstraintType {
    PRIMARY_KEY("PRIMARY KEY"),
    UNIQUE("UNIQUE"),
    FOREIGN_KEY("FOREIGN KEY");

    private String typeName;

    ConstraintType(String typeName){
        this.typeName=typeName;
    }
    @Override
    public String toString() {
        return typeName;
    }
}
