package db.migration.service;

import java.io.Reader;
import java.io.Writer;

public interface DBChangeExportImport {
    void exportChanges(Writer writer) throws Exception;
    void importChanges(Reader reader) throws Exception;
}
