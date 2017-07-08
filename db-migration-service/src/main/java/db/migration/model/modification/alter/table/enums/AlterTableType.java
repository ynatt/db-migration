package db.migration.model.modification.alter.table.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlterTableType")
@XmlEnum
public enum AlterTableType {
    RENAME("RENAME"),
    ADD("ADD");

    private String typeName;

    AlterTableType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
