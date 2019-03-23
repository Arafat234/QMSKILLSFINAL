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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
public class UpdateDetailsController extends  LoginQMSkillsController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private TextField yearofstudy;
    @FXML
    private TextField email;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField studentid;
    
    @FXML ChoiceBox<String> degreechoice;
    ObservableList availableChoices = FXCollections.observableArrayList(); 
    
    @FXML
    private TextField fullname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            //loadData();
            Loadsubjectdata(degreechoice,availableChoices);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void clearAllFields(ActionEvent event) {
        if(password.getText()!=null){
        password.clear();
        }
        if(yearofstudy.getText()!=null){
        yearofstudy.clear();
        }
        if(email.getText()!=null){
        email.clear();
        }
        if(phonenumber.getText()!=null){
        phonenumber.clear();
        }
        if(fullname.getText()!=null){
        fullname.clear();
        }
        if(degreechoice.getValue()!= null){
        degreechoice.setValue(null);
        }
    }

   /* @FXML
    private void loadData(){
    availableChoices.removeAll(availableChoices);
    String a = "CS";
    String b = "Math";
    String c = "Chem";
    String d = "Eng";
    availableChoices.addAll(a,b,c,d);
    degreechoice.getItems().addAll(availableChoices);
    } */
    
    @FXML
    private void updates(ActionEvent event) throws SQLException{
    try{
    AccountsDirectory jdbc = AccountsDirectory.getInstance();
    if(degreechoice.getValue() == null && yearofstudy.getText().equals("") && password.getText().equals("") && email.getText().equals("") && phonenumber.getText().equals("") && fullname.getText().equals("")){
       Alert c = new Alert(Alert.AlertType.NONE); 
       c.setAlertType(Alert.AlertType.ERROR);
       c.setContentText("Please provide some data"); 
       c.show();
    }
    else{
    if(degreechoice.getValue() != null){
    jdbc.updatedegreechoice(LoginQMSkillsController.username,degreechoice.getValue() );
    }
    if(!yearofstudy.getText().equals("")){
    jdbc.updateyearofstudychoice(LoginQMSkillsController.username,yearofstudy.getText());
    }
    if(!password.getText().equals("")){
    jdbc.updatepasswordchoice(LoginQMSkillsController.username,password.getText());
    }
    if(!email.getText().equals("")){
    jdbc.updateemailchoice(LoginQMSkillsController.username,email.getText());
    }
    if(!phonenumber.getText().equals("")){
    jdbc.updatephonenumberchoice(LoginQMSkillsController.username,phonenumber.getText());
    }
    if(!fullname.getText().equals("")){
    jdbc.updatefullnamechoice(LoginQMSkillsController.username,fullname.getText());
    }
   
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

            }
   
    }
    }
    catch(Exception e){
           System.out.println(e);
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
    
    @FXML
    public void Loadsubjectdata(ChoiceBox degreechoice,ObservableList availableChoiceSubject) throws SQLException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.loadSubjectData(degreechoice,availableChoiceSubject);
        
    }
    
   
    
}


