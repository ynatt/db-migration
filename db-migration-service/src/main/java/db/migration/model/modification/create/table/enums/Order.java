package db.migration.model.modification.create.table.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Order")
@XmlEnum
public enum Order {
    ASC("ASC"),
    DESC("DESC");

    private String orderName;

    Order(String orderName){
        this.orderName=orderName;
    }

    @Override
    public String toString() {
        return orderName;
    }
}
