package db.migration.model.modification.create;

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
