package db.migration.model.modification.create;

public class OnDeleteClause {
    private ClauseDecision clauseDecision;

    public OnDeleteClause(ClauseDecision clauseDecision) {
        this.clauseDecision = clauseDecision;
    }

    public ClauseDecision getClauseDecision() {
        return clauseDecision;
    }

    public void setClauseDecision(ClauseDecision clauseDecision) {
        this.clauseDecision = clauseDecision;
    }

    @Override
    public String toString() {
        return "ON DELETE "+ clauseDecision;
    }
}
