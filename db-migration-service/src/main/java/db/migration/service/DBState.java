package db.migration.service;

import db.migration.model.executable.*;
import db.migration.model.modification.DBChange;
import db.migration.model.modification.alter.table.AlterTable;
import db.migration.model.modification.create.index.CreateIndex;
import db.migration.model.modification.create.table.CreateTable;
import db.migration.model.modification.drop.DropIndex;
import db.migration.model.modification.drop.DropTable;
import db.migration.model.modification.insert.InsertIntoTable;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name="DBState")
public class DBState implements DBChangeExportImport {
    private String stateTitle;
    private DBExecutor executor;
    private List<DBChange> changes;

    public DBState() {
        changes = new ArrayList<>();
    }

    public DBState(String title){
        this();
        stateTitle=title;
    }

    public DBState(String title,DBExecutor executor){
        this(title);
        this.executor=executor;
    }

    public DBState(String title,DBExecutor executor, List<DBChange> changes){
        this(title, executor);
        this.changes = changes;
    }

    public DBExecutor getExecutor() {
        return executor;
    }
    @XmlTransient
    public void setExecutor(DBExecutor executor) {
        this.executor = executor;
    }

    public String getStateTitle(){
        return stateTitle;
    }

    public List<DBChange> getChanges(){
        return changes;
    }

    @XmlElements({
            @XmlElement(name = "CreateTable",type=CreateTable.class),
            @XmlElement(name = "InsertIntoTable",type=InsertIntoTable.class),
            @XmlElement(name = "ExecutableUpdateTable",type=ExecutableUpdateTable.class),
            @XmlElement(name = "ExecutableDeleteFromTable",type=ExecutableDeleteFromTable.class),
            @XmlElement(name = "ExecutableAlterTable",type=ExecutableAlterTable.class),
            @XmlElement(name = "ExecutableCreateIndex",type=ExecutableCreateIndex.class),
            @XmlElement(name = "ExecutableDropTable",type=ExecutableDropTable.class),
            @XmlElement(name = "ExecutableDropIndex",type=ExecutableDropIndex.class),
            @XmlElement(name = "ExecutableUpdateTable",type=ExecutableUpdateTable.class),
            @XmlElement(name = "DropTable",type=DropTable.class),
            @XmlElement(name = "DropIndex",type=DropIndex.class),
            @XmlElement(name = "AlterTable",type=AlterTable.class),
            @XmlElement(name = "CreateIndex",type=CreateIndex.class)}
    )
    public void setChanges(List<DBChange> changes) {
        this.changes = changes;
    }
    @XmlAttribute
    public void setStateTitle(String stateTitle) {
        this.stateTitle = stateTitle;
    }

    public void addChange(DBChange change){
        if(change!=null) {
            changes.add(change);
        }
    }

    void applyChanges(){
        for(DBChange change : changes){
            if(change!=null) {
                executor.execute(executor.makeExecutable(change));
            }
        }
    }

    void applyChanges(DBExecutor executor){
        for(DBChange change : changes){
            if(change!=null) {
                executor.execute(executor.makeExecutable(change));
            }
        }
    }

    @Override
    public void exportChanges(Writer writer) throws JAXBException {
        convertObjectToXml(this,writer);
    }

    @Override
    public void importChanges(Reader reader) throws JAXBException {
        DBState newState = convertXMLToDBState(reader);
        if(newState!=null) {
            this.setChanges(newState.getChanges());
        }
    }

    private static void convertObjectToXml(DBState state,Writer Writer) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(DBState.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(state,Writer);
    }

    private static DBState convertXMLToDBState(Reader reader) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(DBState.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (DBState) unmarshaller.unmarshal(reader);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBState)) return false;

        DBState dbState = (DBState) o;

        return (stateTitle != null ? stateTitle.equals(dbState.stateTitle) : dbState.stateTitle == null)
                && (executor != null ? executor.equals(dbState.executor) : dbState.executor == null)
                && (changes != null ? changes.equals(dbState.changes) : dbState.changes == null);
    }

    @Override
    public int hashCode() {
        int result = stateTitle != null ? stateTitle.hashCode() : 0;
        result = 31 * result + (executor != null ? executor.hashCode() : 0);
        result = 31 * result + (changes != null ? changes.hashCode() : 0);
        return result;
    }
}
