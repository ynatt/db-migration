package db.migration.provider;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;
import db.migration.provider.model.*;
import db.migration.service.SQLParserException;
import db.migration.service.SQLQueryParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleSQLiteQueryParser implements SQLQueryParser{
    private final static String CREATE_TABLE_PATTERN = "^create (temp|temporary)? table .*$";

    private final static String CREATE_INDEX_PATTERN = "^create (unique)? index .*$";

    private final static String DROP_TABLE_PATTERN = "^drop table .*$";

    private final static String DROP_INDEX_PATTERN = "^drop index .*$";

    private final static String ALTER_TABLE_PATTERN = "^alter table .*$";

    private final static String INSERT_INTO_TABLE_PATTERN = "(insert|replace) (or .{1,8})? into .*";

    private final static String DELETE_FROM_TABLE_PATTERN = "delete from .*";

    private final static String UPDATE_TABLE_PATTERN = "update .*";

    private static String[] patterns = {CREATE_TABLE_PATTERN,
                                        CREATE_INDEX_PATTERN,
                                        DROP_TABLE_PATTERN,
                                        DROP_INDEX_PATTERN,
                                        ALTER_TABLE_PATTERN,
                                        INSERT_INTO_TABLE_PATTERN,
                                        DELETE_FROM_TABLE_PATTERN,
                                        UPDATE_TABLE_PATTERN};
    @Override
    public DBChange parseSQLQuery(String sql) throws SQLParserException {
        Pattern pattern;
        Matcher matcher;
        for(String queryPattern : patterns){
            pattern = Pattern.compile(queryPattern,Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(sql);
            if(matcher.matches()){
                return createExecutableDBChange(queryPattern,sql);
            }
        }
        return null;
    }

    private ExecutableDBChange createExecutableDBChange(String pattern, String sql){
        switch (pattern){
            case CREATE_TABLE_PATTERN : return new ExecutableCreateTable(sql);
            case CREATE_INDEX_PATTERN : return new ExecutableCreateIndex(sql);
            case DROP_TABLE_PATTERN : return new ExecutableDropTable(sql);
            case DROP_INDEX_PATTERN : return new ExecutableDropIndex(sql);
            case ALTER_TABLE_PATTERN : return new ExecutableAlterTable(sql);
            case INSERT_INTO_TABLE_PATTERN : return new ExecutableInsertIntoTable(sql);
            case DELETE_FROM_TABLE_PATTERN : return new ExecutableDeleteFromTable(sql);
            case UPDATE_TABLE_PATTERN : return new ExecutableUpdateTable(sql);
            default:
                return null;
        }
    }
}
