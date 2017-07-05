package db.migration.model.modification.alter;

public abstract class AbstractAlterType {
    private AlterTableType alterTypeName;

    AbstractAlterType(AlterTableType alterTypeName){
        this.alterTypeName=alterTypeName;
    }

    @Override
    public String toString() {
        return alterTypeName.toString();
    }
}
