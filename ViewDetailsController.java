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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import static qmskillsfinal.LoginQMSkillsController.username;

/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
   
    


  


public class ViewDetailsController extends LoginQMSkillsController implements Initializable {

    @FXML
    private Button tutorpage;
    @FXML
    private Button detail;
    @FXML
    private TableView<Details> viewtable;
    @FXML
    private TableColumn<Details,String> userNameColumn;
    @FXML
    private TableColumn<Details,String> studentIDColumn;
    @FXML
    private TableColumn<Details,String> passwordColumn;
    @FXML
    private TableColumn<Details,String> yearOfStudyColumn;
    @FXML
    private TableColumn<Details,String> degreeColumn;
    @FXML
    private TableColumn<Details,String> emailColumn;
    @FXML
    private TableColumn<Details,String> phoneNumberColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void dataadd(ActionEvent event) throws IOException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        ObservableList data = FXCollections.observableArrayList();
        try {
            jdbc.addUserData(data,LoginQMSkillsController.username,userNameColumn,studentIDColumn,passwordColumn,yearOfStudyColumn,degreeColumn,emailColumn,phoneNumberColumn,viewtable);
        } catch (SQLException ex) {
            Logger.getLogger(ViewsessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        try {
            if(jdbc.checker4(username) == true){
                Parent home = FXMLLoader.load(getClass().getResource("TutorPage.fxml"));
                Scene homepage = new Scene(home);
                Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
                homestage.setScene(homepage);
                homestage.show();
            }
            else{
                Parent home = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
                Scene homepage = new Scene(home);
                Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
                homestage.setScene(homepage);
                homestage.show();

            }}
                 catch (SQLException ex) {
            Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    }
    

