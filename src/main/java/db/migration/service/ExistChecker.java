package db.migration.service;

import db.migration.model.DBObject;

public abstract class ExistChecker {
    abstract boolean isExist(DBObject dbObject);
}
