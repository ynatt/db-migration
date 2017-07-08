package db.migration.model.modification;

public interface Trackable {
    default void showTrackingMessage(){
        System.out.println(this.getClass().getSimpleName()+" was tracked");
    }
}
