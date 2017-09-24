package db.migration.model.modification.create.table;

import db.migration.model.modification.create.table.enums.ConstraintType;

import javax.xml.bind.annotation.XmlElement;

public abstract class TableConstraint {

    private String name;

    TableConstraint(){

    }

    TableConstraint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public abstract ConstraintType getType();

    @Override
    public String toString() {
        if(name!=null){
            return "CONSTRAINT "+name;
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableConstraint)) return false;
        TableConstraint that = (TableConstraint) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
