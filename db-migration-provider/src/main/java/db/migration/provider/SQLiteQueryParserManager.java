package db.migration.provider;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.UndefinedDBChange;
import db.migration.service.SQLParserException;
import db.migration.service.SQLQueryParser;
import javacc.sqlitequeryparser.CCSQLiteQueryParser;
import javacc.sqlitequeryparser.ParseException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SQLiteQueryParserManager implements SQLQueryParser{

    private List<SQLParserException> exceptions = new ArrayList<>();

    private CCSQLiteQueryParser ccsqLiteQueryParser;

    private SQLQueryParser simpleParser;

    public SQLiteQueryParserManager(){
        simpleParser = new SimpleSQLiteQueryParser();
    }

    @Override
    public DBChange parseSQLQuery(String sql) throws SQLParserException{
        ccsqLiteQueryParser = new CCSQLiteQueryParser(new StringReader(sql));
        DBChange dbChange;
        try {
            dbChange = ccsqLiteQueryParser.analiseQuery();
            if(dbChange!=null){
                return dbChange;
            }
        } catch (ParseException e) {
            exceptions.add(new SQLParserException(e));
        }
        dbChange = simpleParser.parseSQLQuery(sql);
        if(dbChange!=null){
            return dbChange;
        } else {
            return new UndefinedDBChange();
        }
    }
}
