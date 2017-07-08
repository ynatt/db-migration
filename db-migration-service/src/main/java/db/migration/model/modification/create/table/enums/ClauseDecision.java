package db.migration.model.modification.create.table.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ClauseDecision")
@XmlEnum
public enum ClauseDecision {
    SET_NULL("SET NULL"),
    SET_DEFAULT("SET DEFAULT"),
    CASCADE("CASCADE"),
    RESTRICT("RESTRICT"),
    NO_ACTION("NO ACTION");

    private String decisionName;
    ClauseDecision(String decisionName){
        this.decisionName=decisionName;
    }

    @Override
    public String toString() {
        return decisionName;
    }
}

