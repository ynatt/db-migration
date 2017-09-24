package db.migration.model.modification.create.table;

import db.migration.model.modification.create.table.enums.ClauseDecision;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
    @XmlAttribute
    public void setClauseDecision(ClauseDecision clauseDecision) {
        this.clauseDecision = clauseDecision;
    }

    @Override
    public String toString() {
        return "ON DELETE "+ clauseDecision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OnDeleteClause)) return false;

        OnDeleteClause that = (OnDeleteClause) o;

        return clauseDecision == that.clauseDecision;
    }

    @Override
    public int hashCode() {
        return clauseDecision != null ? clauseDecision.hashCode() : 0;
    }
}
