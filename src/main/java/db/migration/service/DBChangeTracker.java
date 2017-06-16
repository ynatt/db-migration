package db.migration.service;

import db.migration.service.modyfication.DBChange;

/**
 * @author VYZH
 * @since 16.06.2017
 */
public interface DBChangeTracker {
    void trackChange(DBChange change);
}
