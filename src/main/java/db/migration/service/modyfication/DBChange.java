package db.migration.service.modyfication;

public interface DBChange extends SQLQuery {
    void execute();
}
