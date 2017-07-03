package db.migration.model.modification.create;

import db.migration.model.Column;

import java.util.Iterator;
import java.util.List;

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

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public ForeignKeyClause getClause() {
        return clause;
    }

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
}
