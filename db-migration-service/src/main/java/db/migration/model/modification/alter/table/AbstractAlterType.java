package db.migration.model.modification.alter.table;

import db.migration.model.modification.alter.table.enums.AlterTableType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class AbstractAlterType {
    private AlterTableType alterTypeName;

    public AbstractAlterType() {
    }

    AbstractAlterType(AlterTableType alterTypeName){
        this.alterTypeName=alterTypeName;
    }

    public AlterTableType getAlterTypeName() {
        return alterTypeName;
    }
    @XmlElement
    public void setAlterTypeName(AlterTableType alterTypeName) {
        this.alterTypeName = alterTypeName;
    }

    @Override
    public String toString() {
        return alterTypeName.toString();
    }
}
