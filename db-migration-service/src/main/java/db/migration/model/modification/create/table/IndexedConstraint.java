package db.migration.model.modification.create.table;

import db.migration.model.Column;
import db.migration.model.modification.create.table.enums.ConflictDecision;
import db.migration.model.modification.create.table.enums.ConstraintType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@XmlRootElement
public class IndexedConstraint extends TableConstraint {
    private List<Column> indexedColumns;
    private ConflictDecision conflictDecision;
    private ConstraintType type;

    public IndexedConstraint() {
        indexedColumns = new ArrayList<>();
    }

    public IndexedConstraint(ConstraintType type){
        this();
        if(checkType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("type can be only ConstraintType.PRIMARY_KEY or ConstraintType.UNIQUE");
        }
    }

    public IndexedConstraint(ConstraintType type,List<Column> columns){
        this(type);
        this.indexedColumns=columns;
    }

    public IndexedConstraint(ConstraintType type,List<Column> columns,ConflictDecision conflictDecision){
        this(type,columns);
        this.conflictDecision=conflictDecision;
    }

    public IndexedConstraint(String name,ConstraintType type) {
        super(name);
        if(checkType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("type can be only ConstraintType.PRIMARY_KEY or ConstraintType.UNIQUE");
        }
        indexedColumns = new ArrayList<>();
    }

    public IndexedConstraint(String name,ConstraintType type,List<Column> columns) {
        this(name,type);
        this.indexedColumns=columns;
    }

    public IndexedConstraint(String name,ConstraintType type,List<Column> columns,ConflictDecision conflictDecision) {
        this(name,type,columns);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndexedConstraint)) return false;

        IndexedConstraint that = (IndexedConstraint) o;

        return (indexedColumns != null ? indexedColumns.equals(that.indexedColumns) : that.indexedColumns == null)
                && conflictDecision == that.conflictDecision
                && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = indexedColumns != null ? indexedColumns.hashCode() : 0;
        result = 31 * result + (conflictDecision != null ? conflictDecision.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
