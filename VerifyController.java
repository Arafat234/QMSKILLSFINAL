/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmskillsfinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
public class VerifyController implements Initializable {
    
    @FXML ChoiceBox<String> username;
    ObservableList availableChoicesUsername = FXCollections.observableArrayList();
    
    @FXML ChoiceBox<String> verifychoice;
    ObservableList availableChoice = FXCollections.observableArrayList();
    
    @FXML
    Button home;
    
    
    @FXML
    Button complete;
    /**
     * Initializes the controller class.
     */
    
    /**
     * Initializes the controller class.
     * @param username
     * @param availableChoiceUsername
     * @throws java.sql.SQLException
     */
    @FXML
     public void LoadUsernamedata(ChoiceBox username,ObservableList availableChoiceUsername) throws SQLException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.loadUsernameData2(username,availableChoiceUsername);  
    }
    
    @FXML
    public void verify1(ActionEvent event) throws IOException, SQLException{
        if(username.getValue() == null || verifychoice.getValue() == null){
             Alert b = new Alert(Alert.AlertType.NONE); 
             b.setAlertType(Alert.AlertType.ERROR);
             b.setContentText("Please make sure you have chosen all options"); 
             b.show();
             return;
        }
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.updateBanStatus2(username.getValue(),verifychoice.getValue());
        
        Parent home = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
        Scene homepage = new Scene(home);
        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homestage.setScene(homepage);
        homestage.show();
    }
    
    
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
        Scene homepage = new Scene(home);
        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homestage.setScene(homepage);
        homestage.show();
    }
    
    
    
    @FXML
    private void loadData(){ // load from database
    availableChoice.removeAll(availableChoice);
    String e = "yes";
    String f = "no";
    availableChoice.addAll(e,f);
    verifychoice.getItems().addAll(availableChoice);
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        try {
            LoadUsernamedata(username,availableChoicesUsername);
        } catch (SQLException ex) {
            Logger.getLogger(VerifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
