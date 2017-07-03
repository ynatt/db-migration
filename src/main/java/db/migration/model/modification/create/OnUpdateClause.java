package db.migration.model.modification.create;

public class OnUpdateClause {
    private ClauseDecision clauseDecision;

    public OnUpdateClause(ClauseDecision clauseDecision) {
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
        return "ON UPDATE "+ clauseDecision;
    }
}
