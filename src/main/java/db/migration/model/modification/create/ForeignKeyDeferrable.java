package db.migration.model.modification.create;

public class ForeignKeyDeferrable {
    private DeferrableClause deferrableClause;

    public ForeignKeyDeferrable(DeferrableClause deferrableClause) {
        this.deferrableClause = deferrableClause;
    }

    @Override
    public String toString() {
        return "DEFERRABLE "+deferrableClause;
    }
}
