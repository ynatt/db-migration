package db.migration.model.modification.create.table;

import db.migration.model.modification.create.table.enums.DeferrableClause;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ForeignKeyDeferrable {
    private DeferrableClause deferrableClause;

    public ForeignKeyDeferrable() {
    }

    public DeferrableClause getDeferrableClause() {
        return deferrableClause;
    }
    @XmlAttribute
    public void setDeferrableClause(DeferrableClause deferrableClause) {
        this.deferrableClause = deferrableClause;
    }

    public ForeignKeyDeferrable(DeferrableClause deferrableClause) {
        this.deferrableClause = deferrableClause;
    }

    @Override
    public String toString() {
        if(deferrableClause!=DeferrableClause.NOT_DEFERRABLE) {
            return "DEFERRABLE " + deferrableClause;
        } else {
            return deferrableClause.toString();
        }
    }
}
