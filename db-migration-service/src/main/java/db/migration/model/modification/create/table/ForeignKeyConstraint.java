package db.migration.model.modification.create.table;

import db.migration.model.Column;
import db.migration.model.modification.create.table.enums.ConstraintType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;
@XmlRootElement
public class ForeignKeyConstraint extends TableConstraint {
    private List<Column> columns;

    private ForeignKeyClause clause;

    public ForeignKeyConstraint(){

    }

    public ForeignKeyConstraint(List<Column> columns){
        this.columns=columns;
    }

    public ForeignKeyConstraint(List<Column> columns,ForeignKeyClause foreignKeyClause){
        this.columns=columns;
        this.clause=foreignKeyClause;
    }

    public ForeignKeyConstraint(String name,List<Column> columns) {
        super(name);
        this.columns=columns;
    }

    public ForeignKeyConstraint(String name,List<Column> columns,ForeignKeyClause foreignKeyClause) {
        super(name);
        this.columns=columns;
        this.clause=foreignKeyClause;
    }

    public List<Column> getColumns() {
        return columns;
    }
    @XmlElement
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public ForeignKeyClause getClause() {
        return clause;
    }
    @XmlElement
    public void setClause(ForeignKeyClause clause) {
        this.clause = clause;
    }

    public void addColumn(Column column){
        this.columns.add(column);
    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.FOREIGN_KEY;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(this.getName()!=null){
            result.append(super.toString());
            result.append(" ");
        }
        result.append(getType());
        result.append(" ");
        result.append(" ( ");
        Column column;
        for(Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); ){
            column=iterator.next();
            result.append(column.getColumnName());
            if(iterator.hasNext()){
                result.append(" , ");
            }
        }
        result.append(" ) ");
        result.append(clause);
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForeignKeyConstraint)) return false;
        ForeignKeyConstraint that = (ForeignKeyConstraint) o;
        return (columns != null ? columns.equals(that.columns) : that.columns == null)
                && (clause != null ? clause.equals(that.clause) : that.clause == null);
    }

    @Override
    public int hashCode() {
        int result = columns != null ? columns.hashCode() : 0;
        result = 31 * result + (clause != null ? clause.hashCode() : 0);
        return result;
    }
}
