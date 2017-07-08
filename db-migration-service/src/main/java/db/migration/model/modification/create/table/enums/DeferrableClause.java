package db.migration.model.modification.create.table.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DeferrableClause")
@XmlEnum
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
