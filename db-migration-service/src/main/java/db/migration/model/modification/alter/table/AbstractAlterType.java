package db.migration.model.modification.alter.table;

import db.migration.model.modification.alter.table.enums.AlterTableType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class AbstractAlterType {
    private AlterTableType alterTypeName;

    protected AbstractAlterType() {

    }

    protected AbstractAlterType(AlterTableType alterTypeName){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAlterType)) return false;
        AbstractAlterType that = (AbstractAlterType) o;
        return alterTypeName == that.alterTypeName;
    }

    @Override
    public int hashCode() {
        return alterTypeName != null ? alterTypeName.hashCode() : 0;
    }
}
