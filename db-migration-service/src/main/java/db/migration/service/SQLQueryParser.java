package db.migration.service;

import db.migration.model.modification.DBChange;

public interface SQLQueryParser {
    DBChange parseSQLQuery(String sql) throws SQLParserException;
}
