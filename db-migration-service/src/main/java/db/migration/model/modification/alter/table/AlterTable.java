package db.migration.model.modification.alter.table;

import db.migration.model.Table;
import db.migration.model.modification.DBChange;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlterTable implements DBChange {
    private Table table;
    private AbstractAlterType alterType;

    public AlterTable() {

    }

    public AlterTable(Table table) {
        this.table = table;
    }

    public AlterTable(Table table, AbstractAlterType alterType) {
        this.table = table;
        this.alterType = alterType;
    }

    public Table getTable() {
        return table;
    }
    @XmlElement
    public void setTable(Table table) {
        this.table = table;
    }

    public AbstractAlterType getAlterType() {
        return alterType;
    }
    @XmlElements({
            @XmlElement(name = "AddColumn",type=AddColumn.class),
            @XmlElement(name = "RenameTo",type=RenameTo.class)
    })
    public void setAlterType(AbstractAlterType alterType) {
        this.alterType = alterType;
    }

    @Override
    public String getChangeType() {
        return "ALTER TABLE";
    }
}
