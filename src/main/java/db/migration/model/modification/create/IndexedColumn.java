package db.migration.model.modification.create;

import db.migration.model.Column;

public class IndexedColumn {
    private Column column;
    private Order order;

    public IndexedColumn() {
    }

    public IndexedColumn(Column column) {
        this.column = column;
    }

    public IndexedColumn(Column column, Order order) {
        this.column = column;
        this.order = order;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        String result = column.getColumnName();
        if(order!=null){
            result+=order.toString();
        }
        return result;
    }
}
