package db.migration.model.modification.create.table;

import db.migration.model.Column;
import db.migration.model.modification.create.table.enums.ConflictDecision;
import db.migration.model.modification.create.table.enums.ConstraintType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@XmlRootElement
public class IndexedConstraint extends TableConstraint {
    private List<Column> indexedColumns;
    private ConflictDecision conflictDecision;
    private ConstraintType type;

    public IndexedConstraint() {

    }

    public IndexedConstraint(ConstraintType type){
        this.type=type;
        indexedColumns = new ArrayList<>();
    }

    public IndexedConstraint(ConstraintType type,List<Column> columns){
        if(checkType(type)) {
            this.type = type;
        } else {
            generateException();
        }
        this.indexedColumns=columns;
    }

    public IndexedConstraint(ConstraintType type,List<Column> columns,ConflictDecision conflictDecision){
        if(checkType(type)) {
            this.type = type;
        } else {
            generateException();
        }
        this.indexedColumns=columns;
        this.conflictDecision=conflictDecision;
    }

    public IndexedConstraint(String name,ConstraintType type) {
        super(name);
        if(checkType(type)) {
            this.type = type;
        } else {
            generateException();
        }
        indexedColumns = new ArrayList<>();
    }

    public IndexedConstraint(String name,ConstraintType type,List<Column> columns) {
        super(name);
        if(checkType(type)) {
            this.type = type;
        } else {
            generateException();
        }
        this.indexedColumns=columns;
    }

    public IndexedConstraint(String name,ConstraintType type,List<Column> columns,ConflictDecision conflictDecision) {
        super(name);
        if(checkType(type)) {
            this.type = type;
        } else {
            generateException();
        }
        this.indexedColumns=columns;
        this.conflictDecision=conflictDecision;
    }
    @XmlElement
    public void setType(ConstraintType type) {
        this.type = type;
    }

    public List<Column> getIndexedColumns() {
        return indexedColumns;
    }
    @XmlElement
    public void setIndexedColumns(List<Column> indexedColumns) {
        this.indexedColumns = indexedColumns;
    }

    public ConflictDecision getConflictDecision() {
        return conflictDecision;
    }
    @XmlElement
    public void setConflictDecision(ConflictDecision conflictDecision) {
        this.conflictDecision = conflictDecision;
    }

    public void addIndexedColumn(Column column){
        this.indexedColumns.add(column);
    }

    private boolean checkType(ConstraintType type){
        return ConstraintType.PRIMARY_KEY==type || ConstraintType.UNIQUE==type;
    }

    private void generateException(){
        throw new IllegalArgumentException("type can be only ConstraintType.PRIMARY_KEY or ConstraintType.UNIQUE");
    }
    @Override
    public ConstraintType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(this.getName()!=null){
            result.append(super.toString());
            result.append(" ");
        }
        result.append(getType());
        result.append(" ( ");
        Column column;
        for(Iterator<Column> iterator = indexedColumns.iterator();iterator.hasNext(); ){
            column=iterator.next();
            result.append(column.getColumnName());
            if(iterator.hasNext()){
                result.append(" , ");
            }
        }
        result.append(" ) ");
        if(this.conflictDecision!=null){
            result.append("ON CONFLICT ");
            result.append(conflictDecision.toString());
        }
        return result.toString();
    }
}
