package db.migration.service;

import db.migration.model.modification.DBChange;

import java.util.ArrayList;
import java.util.List;

public class DBState implements DBChangeExportImport {
    private String stateTitle;
    private DBExecutor executor;

    private List<DBChange> changes;

    public DBState(String title){
        stateTitle=title;
        changes = new ArrayList<>();
    }

    public DBState(String title,DBExecutor executor){
        stateTitle=title;
        this.executor=executor;
        changes = new ArrayList<>();
    }

    public DBState(String title,DBExecutor executor, List<DBChange> changes){
        stateTitle=title;
        this.executor=executor;
        this.changes = changes;
    }

    public String getStateTitle(){
        return stateTitle;
    }

    public List<DBChange> getChanges(){
        return changes;
    }

    public void addChange(DBChange change){
        changes.add(change);
    }

    void applyChanges(DBExecutor executor){
        for(DBChange change : changes){
            executor.execute(executor.makeExecutable(change));
        }
    }

    @Override
    public void exportChanges(String filePath) {
        //TODO:this
    }

    @Override
    public void importChanges(String filePath) {
        //TODO:this
    }
}
