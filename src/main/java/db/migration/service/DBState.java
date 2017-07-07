package db.migration.service;

import db.migration.model.modification.DBChange;
import db.migration.model.modification.alter.table.AddColumn;
import db.migration.model.modification.alter.table.AlterTable;
import db.migration.model.modification.alter.table.RenameTo;
import db.migration.model.modification.create.index.CreateIndex;
import db.migration.model.modification.create.table.CreateTable;
import db.migration.model.modification.delete.DeleteFromTable;
import db.migration.model.modification.drop.DropIndex;
import db.migration.model.modification.drop.DropTable;
import db.migration.model.modification.insert.InsertIntoTable;
import db.migration.model.executable.ExecutableDeleteFromTable;
import db.migration.model.executable.ExecutableUpdateTable;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name="DBState")
public class DBState implements DBChangeExportImport {
    @XmlAttribute
    private String stateTitle;
    @XmlTransient
    private DBExecutor executor;
    @XmlElements({
            @XmlElement(name = "CreateTable",type=CreateTable.class),
            @XmlElement(name = "InsertIntoTable",type=InsertIntoTable.class),
            @XmlElement(name = "ExecutableUpdateTable",type=ExecutableUpdateTable.class),
            @XmlElement(name = "ExecutableDeleteFromTable",type=ExecutableDeleteFromTable.class),
            @XmlElement(name = "DropTable",type=DropTable.class),
            @XmlElement(name = "DropIndex",type=DropIndex.class),
            @XmlElement(name = "DeleteFromTable",type=DeleteFromTable.class),
            @XmlElement(name = "AlterTable",type=AlterTable.class),
            @XmlElement(name = "CreateIndex",type=CreateIndex.class)}
    )
    private List<DBChange> changes;

    public DBState() {
    }

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
    @XmlTransient
    public DBExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(DBExecutor executor) {
        this.executor = executor;
    }

    public String getStateTitle(){
        return stateTitle;
    }

    public List<DBChange> getChanges(){
        return changes;
    }

    public void setChanges(List<DBChange> changes) {
        this.changes = changes;
    }

    public void setStateTitle(String stateTitle) {
        this.stateTitle = stateTitle;
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
        convertObjectToXml(this,filePath);
    }

    @Override
    public void importChanges(String filePath) {
        DBState newState = convertXMLToDBState(filePath);
        if(newState!=null) {
            this.setChanges(newState.getChanges());
        }
    }

    public static void convertObjectToXml(DBState state, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(DBState.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(state, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static DBState convertXMLToDBState(String filePath){
        try {
            JAXBContext context = JAXBContext.newInstance(DBState.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (DBState) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
