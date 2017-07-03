package db.migration.model.modification.create;

public enum ConflictDecision {
    ROLLBACK("ROLLBACK"),
    ABORT("ABORT"),
    FAIL("FAIL"),
    IGNORE("IGNORE"),
    REPLACE("REPLACE");

    private String decisionName;

    ConflictDecision(String decisionName){
        this.decisionName=decisionName;
    }

    @Override
    public String toString() {
        return decisionName;
    }
}
