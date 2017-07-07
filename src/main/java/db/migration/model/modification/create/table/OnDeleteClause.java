package db.migration.model.modification.create.table;

import db.migration.model.modification.create.table.enums.ClauseDecision;

import javax.xml.bind.annotation.XmlElement;

public class OnDeleteClause {
    private ClauseDecision clauseDecision;

    public OnDeleteClause() {
    }

    public OnDeleteClause(ClauseDecision clauseDecision) {
        this.clauseDecision = clauseDecision;
    }

    public ClauseDecision getClauseDecision() {
        return clauseDecision;
    }
    @XmlElement
    public void setClauseDecision(ClauseDecision clauseDecision) {
        this.clauseDecision = clauseDecision;
    }

    @Override
    public String toString() {
        return "ON DELETE "+ clauseDecision;
    }
}
