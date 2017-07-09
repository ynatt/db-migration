package db.migration.service;

public interface DBChangeExportImport {
    void exportChanges(String filePath);
    void importChanges(String filePath);
}
