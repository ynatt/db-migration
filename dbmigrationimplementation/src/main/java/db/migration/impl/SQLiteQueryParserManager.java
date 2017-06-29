package db.migration.impl;

import db.migration.model.modification.DBChange;
import db.migration.service.SQLParserException;
import db.migration.service.SQLQueryParser;
import javacc.sqlitequeryparser.CCSQLiteQueryParser;
import javacc.sqlitequeryparser.ParseException;

import java.io.StringReader;

public class SQLiteQueryParserManager implements SQLQueryParser{

    @Override
    public DBChange parseSQLQuery(String sql) throws SQLParserException{
        CCSQLiteQueryParser parser = new CCSQLiteQueryParser(new StringReader(sql));
        try {
            return parser.analiseQuery();
        } catch (ParseException e) {
            throw  new SQLParserException(e);
        }
    }
}
