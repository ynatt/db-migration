package db.migration.model.modification;

public class UndefinedDBChange implements DBChange{
    @Override
    public String getChangeType() {
        return "UNDEFINED TYPE";
    }
}
