package db.migration.model.modification.create.table.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ConflictDecision")
@XmlEnum
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
