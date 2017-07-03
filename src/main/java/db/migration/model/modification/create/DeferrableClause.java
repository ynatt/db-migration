package db.migration.model.modification.create;

public enum DeferrableClause {
    INITIALLY_DEFERRED("INITIALLY DEFERRED"),
    INITIALLY_IMMEDIATE("INITIALLY IMMEDIATE");

    private String clauseName;
    DeferrableClause(String clauseName){
        this.clauseName=clauseName;
    }

    @Override
    public String toString() {
        return clauseName;
    }
}
