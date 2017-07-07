package db.migration.model.modification.create.table;

import db.migration.model.modification.create.table.enums.ClauseDecision;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement
public class OnUpdateClause {
    private ClauseDecision clauseDecision;

    public OnUpdateClause() {
    }

    public OnUpdateClause(ClauseDecision clauseDecision) {
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
        return "ON UPDATE "+ clauseDecision;
    }
}
