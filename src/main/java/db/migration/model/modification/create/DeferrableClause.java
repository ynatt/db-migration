package db.migration.model.modification.create;

public enum DeferrableClause {
    NOT_DEFERRABLE("NOT DEFERRABLE"),
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
