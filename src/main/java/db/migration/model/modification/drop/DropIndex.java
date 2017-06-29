package db.migration.model.modification.drop;

import db.migration.model.Index;

public abstract class DropIndex {
    private Index index;

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}
