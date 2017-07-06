package db.migration.model.modification;

public interface DBChange extends Trackable {
    String getChangeType();
}
