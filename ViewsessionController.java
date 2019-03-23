/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmskillsfinal;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
public class ViewsessionController extends LoginQMSkillsController implements Initializable {

    /**
     * Initializes the controller class.
     * @param event
     * @param data
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @FXML
    private TableView<sessionDetails> sessionstable;
    
    @FXML
    private TableColumn<sessionDetails,String> sessionIDColumn;
    
    @FXML
    private TableColumn<sessionDetails,String> fullnameColumn;
    
    @FXML
    private TableColumn<sessionDetails,String> timeColumn;
    
    @FXML
    private TableColumn<sessionDetails,String> paymentmethodColumn;
    
    @FXML
    private TableColumn<sessionDetails,String> dayColumn;
    
    @FXML
    private TableColumn<sessionDetails,String> emailColumn;
    
     @FXML
    private TextField idsession;
    
    
   
    
    

    
    
    @FXML
    public void tutorpage(ActionEvent event) throws IOException{
        Parent c = FXMLLoader.load(getClass().getResource("TutorPage.fxml"));
        Scene cpage = new Scene(c);
        Stage cstage = (Stage)((Node) event.getSource()).getScene().getWindow();
        cstage.setScene(cpage);
        cstage.show();
    }
    
    @FXML
    public void adddata(ActionEvent event) throws IOException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        ObservableList data = FXCollections.observableArrayList();
        try {
            jdbc.addSessionData(data,LoginQMSkillsController.username,sessionIDColumn,fullnameColumn,timeColumn,paymentmethodColumn,dayColumn,emailColumn,sessionstable);
        } catch (SQLException ex) {
            Logger.getLogger(ViewsessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancelSessionData(ActionEvent event) throws IOException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        ObservableList data = FXCollections.observableArrayList();
        try {
            jdbc.cancelSessionData(idsession.getText());
            jdbc.addSessionData(data,LoginQMSkillsController.username,sessionIDColumn,fullnameColumn,timeColumn,paymentmethodColumn,dayColumn,emailColumn,sessionstable);
        } catch (SQLException ex) {
            Logger.getLogger(ViewsessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
