package db.migration.model.modification.create.table.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ConstraintType")
@XmlEnum
public enum ConstraintType {
    PRIMARY_KEY("PRIMARY KEY"),
    UNIQUE("UNIQUE"),
    FOREIGN_KEY("FOREIGN KEY");

    private String typeName;

    ConstraintType(String typeName){
        this.typeName=typeName;
    }
    @Override
    public String toString() {
        return typeName;
    }
}
