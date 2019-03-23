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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterTutorController extends RegistrationController implements Initializable {

    
     @FXML ChoiceBox<String> module;
     ObservableList availableChoiceModule = FXCollections.observableArrayList(); 
     
     @FXML private TextField grade;
     
     private int mark = 70;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             // TODO
             Loadsmoduledata(module,availableChoiceModule);
         } catch (SQLException ex) {
             Logger.getLogger(RegisterTutorController.class.getName()).log(Level.SEVERE, null, ex);
         }

    }    

    @FXML
    private void addmoduleChoice(ActionEvent event) throws SQLException {
       try{
            AccountsDirectory jdbc= AccountsDirectory.getInstance(); 
            
            
           // jdbc.updateTutorRegistration(module.getValue(),RegistrationController.userIdentity);
           // mark = grade.getText();
            if (module.getValue()==null){
                Alert b = new Alert(Alert.AlertType.NONE); 
                b.setAlertType(Alert.AlertType.ERROR);
                b.setContentText("Please select a module"); 
                b.show();
            }
            if (Integer.parseInt(grade.getText()) < 70 || Integer.parseInt(grade.getText()) > 100 || !grade.getText().matches(".*[0-9].*")){
                Alert b = new Alert(Alert.AlertType.NONE); 
                b.setAlertType(Alert.AlertType.ERROR);
                b.setContentText("You will not be able to register into the system as you may have entered in the incorrect input or your grades may be to low, please note all details will be reviewed."); 
                b.show();
            }
            else{
            jdbc.updateTutorRegistration(module.getValue(),RegistrationController.userIdentity);
            jdbc.updateTutorRegistrationGrade(grade.getText(),RegistrationController.userIdentity);
            Parent home = FXMLLoader.load(getClass().getResource("homeQMSkills.fxml"));
            Scene homepage = new Scene(home);
            Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
            homestage.setScene(homepage);
            homestage.show(); 
            }  
       }
       catch(Exception e){
           Alert b = new Alert(Alert.AlertType.NONE); 
           b.setAlertType(Alert.AlertType.ERROR);
           b.setContentText("Please provide numerical input"); 
           b.show();
           System.out.println(e);
       }
    }  
    
   /* @FXML
     public void Dataload(){
        availableChoices4.removeAll(availableChoices4);
        String g = "Maths for computing";
        String h = "Organic Chemistry";
        availableChoices4.addAll(g,h);
        module.getItems().addAll(availableChoices4);  
 
    } */
     
     
    
    
    
    public void Loadsmoduledata(ChoiceBox module,ObservableList availableChoiceSubject) throws SQLException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.loadModuleData(module,availableChoiceModule);
        
    }
    
} 

