
package phonebook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ViewController implements Initializable {
    
    @FXML
    TableView table;
    @FXML
    TextField inputLastname;
    @FXML
    TextField inputFirstname;
    @FXML
    TextField inputEmail;
    @FXML
    Button addNewContactButton;
    @FXML
    StackPane menuPane;
    @FXML
    Pane contactPane;
    @FXML
    Pane exportPane;
    
    private final ObservableList<Person> data = 
            FXCollections.observableArrayList(
            new Person("Szabó", "Gyula", "gyuszi.teszt@example.com"),
            new Person("Bourne", "Jason", "gyuszi.teszt@example.com"),
            new Person("Scott", "Michael", "thatswahtshesaid@example.com"));
    
    
    public void setTableData() {
        TableColumn lastNameCol = new TableColumn("Vezetéknév");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName")); // Person objektumban keresd a lastName-et, String-ként jelenítjük meg
        
        lastNameCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());          
                }
            }
        );
        
        TableColumn firstNameCol = new TableColumn("Keresztnév");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        
        firstNameCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());       
                }
            }
        );
        
        TableColumn emailCol = new TableColumn("Email cím");
        emailCol.setMinWidth(200);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        
        emailCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());          
                }
            }
        );
        
        table.getColumns().addAll(lastNameCol, firstNameCol, emailCol);
        table.setItems(data);
    }
    
    private void setMenuData() {
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
        TreeView<String> treeView = new TreeView<>(treeItemRoot1);
        treeView.setShowRoot(false);
        
        TreeItem<String> nodeItemA = new TreeItem<>("Kontaktok");
        TreeItem<String> nodeItemB = new TreeItem<>("Kilépés");
        
        TreeItem<String> nodeItemA1 = new TreeItem<>("Lista");
        TreeItem<String> nodeItemA2 = new TreeItem<>("Exportálás");
        
        nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2);
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB);
        
        menuPane.getChildren().add(treeView);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData();
        setMenuData();
    }    


    
}
