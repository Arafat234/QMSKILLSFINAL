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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
public class ChangeStatusController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML ChoiceBox<String> username;
    ObservableList availableChoicesUsername = FXCollections.observableArrayList();
    
    @FXML ChoiceBox<String> status;
    ObservableList availableChoice = FXCollections.observableArrayList();
    
    
    @FXML
     public void LoadUsernamedata(ChoiceBox username,ObservableList availableChoiceUsername) throws SQLException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.loadUsernameData(username,availableChoiceUsername);  
    }
     
    @FXML
    public void ban(ActionEvent event) throws IOException, SQLException{
        if(username.getValue() == null || status.getValue() == null){
             Alert b = new Alert(Alert.AlertType.NONE); 
             b.setAlertType(Alert.AlertType.ERROR);
             b.setContentText("Please make sure you have chosen all options"); 
             b.show();
             return;
        }
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.updateBanStatus(username.getValue(),status.getValue());
        
        Parent home = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
        Scene homepage = new Scene(home);
        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homestage.setScene(homepage);
        homestage.show();
    }
     
    @FXML
    private void loadData(){ // load from database
    availableChoice.removeAll(availableChoice);
    String e = "Ban";
    String f = "Unban";
    availableChoice.addAll(e,f);
    status.getItems().addAll(availableChoice);
    } 
     
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
        Scene homepage = new Scene(home);
        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homestage.setScene(homepage);
        homestage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        try {
            // TODO
            LoadUsernamedata(username,availableChoicesUsername);
        } catch (SQLException ex) {
            Logger.getLogger(ChangeStatusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
