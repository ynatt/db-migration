package db.migration.model.modification.create;

public abstract class TableConstraint {
    private String name;

    TableConstraint(){

    }

    TableConstraint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract ConstraintType getType();

}
