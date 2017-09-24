package db.migration.model.modification.create.table;

import db.migration.model.Column;
import db.migration.model.Table;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;

public class ForeignKeyClause {
    private Table foreignTable;
    private List<Column> columns;
    private OnDeleteClause onDeleteClause;
    private OnUpdateClause onUpdateClause;
    private ForeignKeyDeferrable foreignKeyDeferrable;

    public ForeignKeyClause() {
    }

    public ForeignKeyClause(Table foreignTable) {
        this.foreignTable = foreignTable;
    }

    public Table getForeignTable() {
        return foreignTable;
    }

    public void setForeignTable(Table foreignTable) {
        this.foreignTable = foreignTable;
    }

    public List<Column> getColumns() {
        return columns;
    }
    @XmlElement
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public OnDeleteClause getOnDeleteClause() {
        return onDeleteClause;
    }
    @XmlElement
    public void setOnDeleteClause(OnDeleteClause onDeleteClause) {
        this.onDeleteClause = onDeleteClause;
    }

    public OnUpdateClause getOnUpdateClause() {
        return onUpdateClause;
    }
    @XmlElement
    public void setOnUpdateClause(OnUpdateClause onUpdateClause) {
        this.onUpdateClause = onUpdateClause;
    }

    public ForeignKeyDeferrable getForeignKeyDeferrable() {
        return foreignKeyDeferrable;
    }
    @XmlElement
    public void setForeignKeyDeferrable(ForeignKeyDeferrable foreignKeyDeferrable) {
        this.foreignKeyDeferrable = foreignKeyDeferrable;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("REFERENCES ");
        result.append(foreignTable.getFullName());
        if(columns!=null) {
            result.append(" ( ");
            Column column;
            for (Iterator<Column> iterator = columns.iterator(); iterator.hasNext(); ) {
                column = iterator.next();
                result.append(column.getColumnName());
                if (iterator.hasNext()) {
                    result.append(" , ");
                }
            }
            result.append(" )");
        }
        if(onDeleteClause!=null) {
            result.append(" ");
            result.append(onDeleteClause.toString());
        }
        if(onUpdateClause!=null) {
            result.append(" ");
            result.append(onUpdateClause.toString());
        }
        if(foreignKeyDeferrable!=null) {
            result.append(" ");
            result.append(foreignKeyDeferrable.toString());
        } else{
            result.append(" NOT DEFERRABLE");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForeignKeyClause)) return false;
        ForeignKeyClause that = (ForeignKeyClause) o;
        return (foreignTable != null ? foreignTable.equals(that.foreignTable) : that.foreignTable == null)
                && (columns != null ? columnsEquals(columns,that.columns) : that.columns == null)
                && (onDeleteClause != null ? onDeleteClause.equals(that.onDeleteClause) : that.onDeleteClause == null)
                && (onUpdateClause != null ? onUpdateClause.equals(that.onUpdateClause) : that.onUpdateClause == null)
                && (foreignKeyDeferrable != null ? foreignKeyDeferrable.equals(that.foreignKeyDeferrable) : that.foreignKeyDeferrable == null);
    }

    public boolean columnsEquals(List<Column> firstColumns,List<Column> secondColumns){
        if(firstColumns==null | secondColumns==null){
            return false;
        }
        Set<Column> sortedFirstColumns = new TreeSet<>(new Comparator<Column>() {
            @Override
            public int compare(Column o1, Column o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        sortedFirstColumns.addAll(firstColumns);
        Set<Column> sortedSecondColumns = new TreeSet<>(new Comparator<Column>() {
            @Override
            public int compare(Column o1, Column o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
        sortedSecondColumns.addAll(secondColumns);
        if(sortedFirstColumns.size()!=sortedSecondColumns.size()){
            return false;
        }
        Iterator<Column> firstIterator = sortedFirstColumns.iterator();
        Iterator<Column> secondIterator = sortedSecondColumns.iterator();
        while (firstIterator.hasNext() & secondIterator.hasNext()){
            if(!firstIterator.next().equals(secondIterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = foreignTable != null ? foreignTable.hashCode() : 0;
        result = 31 * result + (columns != null ? columns.hashCode() : 0);
        result = 31 * result + (onDeleteClause != null ? onDeleteClause.hashCode() : 0);
        result = 31 * result + (onUpdateClause != null ? onUpdateClause.hashCode() : 0);
        result = 31 * result + (foreignKeyDeferrable != null ? foreignKeyDeferrable.hashCode() : 0);
        return result;
    }
}
