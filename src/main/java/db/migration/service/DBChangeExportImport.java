package db.migration.service;

public interface DBChangeExportImport {
    void exportChanges();
    void importChanges();
}
