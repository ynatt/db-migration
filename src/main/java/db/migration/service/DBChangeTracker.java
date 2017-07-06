package db.migration.service;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.ExecutableDBChange;


public class DBChangeTracker {
    private DBState state;

    public DBChangeTracker(DBState state){
        this.state=state;
    }

    public DBState getState(){
        return state;
    }

    public void setState(DBState state) {
        this.state = state;
    }

    public void trackChange(DBChange change){
        state.addChange(change);
        change.showTrackingMessage();
        if(change instanceof ExecutableDBChange){
            System.out.println(((ExecutableDBChange) change).getQuery());
        }
    }
}

