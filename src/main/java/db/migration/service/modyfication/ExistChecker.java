package db.migration.service.modyfication;

import db.migration.model.DBObject;

public abstract class ExistChecker {
    abstract boolean isExist(DBObject dbObject);
}
