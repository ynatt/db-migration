package db.migration.service;

import db.migration.service.modyfication.DBChange;

import java.util.List;

public interface DBState {
    List<DBChange> getChanges();
    void applyChanges(List<DBChange> changes);
}
