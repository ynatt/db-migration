package db.migration.service;

import java.util.List;

public interface DBState {
    List<DBChange> getChanges();
    void applyChanges(List<DBChange> changes);
}
