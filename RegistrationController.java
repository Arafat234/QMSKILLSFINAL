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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
    

public class RegistrationController implements Initializable {
    
    
    @FXML private TextField studentid;
    
    @FXML private TextField fullname;
    
    @FXML public TextField username;
   
    @FXML private PasswordField password1;
    
    @FXML private PasswordField password2;
    
    @FXML private TextField yearofstudy;
    
    @FXML private TextField email;
    
    @FXML private TextField phonenumber;
    
    @FXML private CheckBox ATCcheckbox;
    
    /*@FXML ChoiceBox<String> degreechoice;
    ObservableList availableChoices = FXCollections.observableArrayList(); */
    
    @FXML ChoiceBox<String> degreechoice;
    ObservableList availableChoiceSubject = FXCollections.observableArrayList(); 
    
    @FXML ChoiceBox<String> userrolechoice;
    ObservableList availableChoices2 = FXCollections.observableArrayList();
   
    public static String userIdentity;
    
    
    
    @FXML
    public void switchToTutorRegister(ActionEvent event) throws IOException{
        Parent tutorRegister = FXMLLoader.load(getClass().getResource("RegisterTutor.fxml"));
        Scene tutorRegisterpage = new Scene(tutorRegister);
        Stage tutorRegisterstage = (Stage)((Node) event.getSource()).getScene().getWindow();
        tutorRegisterstage.setScene(tutorRegisterpage);
        tutorRegisterstage.show();
    }
    
    
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("homeQMSkills.fxml"));
        Scene homepage = new Scene(home);
        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homestage.setScene(homepage);
        homestage.show();
    }
    
    
    @FXML
    private void loadData(){ // load from database
    /*availableChoices.removeAll(availableChoices);
    String a = "CS";
    String b = "Math";
    String c = "Chem";
    String d = "Eng";
    availableChoices.addAll(a,b,c,d);
    degreechoice.getItems().addAll(availableChoices); */
    
    availableChoices2.removeAll(availableChoices2);
    String e = "Tutor";
    String f = "Student";
    availableChoices2.addAll(e,f);
    userrolechoice.getItems().addAll(availableChoices2);

    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadData();
         
          try {
            Loadsubjectdata(degreechoice,availableChoiceSubject);
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
    
    public void Loadsubjectdata(ChoiceBox degreechoice,ObservableList availableChoiceSubject) throws SQLException{
        AccountsDirectory jdbc = AccountsDirectory.getInstance();
        jdbc.loadSubjectData(degreechoice,availableChoiceSubject);
        
    }
    
    
    
    @FXML
    private void clearAllFields(ActionEvent event){ // validation to be done properly
    
    studentid.clear();
    
    
    fullname.clear();
    
    username.clear();
    
    
    password1.clear();
    
    
    password2.clear();
    
   
    yearofstudy.clear();
    
    
    email.clear();
    
    
    phonenumber.clear();
    
    
    userrolechoice.setValue(null);
    
    
    degreechoice.setValue(null);
    
    }
    
    @FXML
    private void StoreDetails(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        try{
        AccountsDirectory jdbc= AccountsDirectory.getInstance(); 
        boolean isSelected = ATCcheckbox.isSelected();
            // Condition not satisfied if none are empty
            if (degreechoice.getValue() == null || studentid.getText() == null || fullname.getText() == null || username.getText() == null || password1.getText()== null ||password2.getText() == null || yearofstudy.getText() == null || email.getText() == null || phonenumber.getText() == null || userrolechoice.getValue() == null){
                   Alert a = new Alert(AlertType.NONE); 
                   a.setAlertType(AlertType.ERROR);
                   a.setContentText("Please make sure all fields have been filled and numeric input has been placed appropriately in the releveant fields."); 
                   a.show();
                }
            else if (!password1.getText().equals(password2.getText())){
                   Alert c = new Alert(AlertType.NONE); 
                   c.setAlertType(AlertType.ERROR);
                   c.setContentText("Make sure passwords are the same."); 
                   c.show();
            
                }
            else if (isSelected == false){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have agreed to the terms and conditions as well as making sure all fields have been filled."); 
                    b.show();
                }
            else if (!phonenumber.getText().matches(".*[0-9].*")){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have used numeric input for phonenumber."); 
                    b.show();
                }
            else if (!studentid.getText().matches(".*[0-9].*")){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have used numeric input for studentid."); 
                    b.show();
                }
            else if (!yearofstudy.getText().matches(".*[0-9].*")){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have used numeric input for phone number."); 
                    b.show();
                }
            else if (Integer.parseInt(yearofstudy.getText()) <= 0 || Integer.parseInt(yearofstudy.getText()) > 5 ){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have entered in the your right year of study."); 
                    b.show();
                }
            else if (email.getText().contains("@") == false){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have entered in an email."); 
                    b.show();
                }
            else if (jdbc.checker6(email.getText()) == true){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Email already exists."); 
                    b.show();
                }
            else if(jdbc.checker(username.getText()) == true){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please try again as username already exist."); 
                    b.show();
            }
            else if(jdbc.checker2(studentid.getText()) == true){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please make sure you have used the correct student id."); 
                    b.show();
            }
            else if(username.getText().equalsIgnoreCase("Admin") ){
                    Alert b = new Alert(AlertType.NONE); 
                    b.setAlertType(AlertType.ERROR);
                    b.setContentText("Please try again as username already exis.t"); 
                    b.show(); 
            }
            else{    
                
                userIdentity = username.getText();
                
                jdbc.insert(degreechoice.getValue(),studentid.getText(),fullname.getText(),username.getText(),password1.getText(),yearofstudy.getText(),email.getText(),phonenumber.getText(),userrolechoice.getValue());
               
                    if (userrolechoice.getValue().equals("Student")){
                        Parent home = FXMLLoader.load(getClass().getResource("homeQMSkills.fxml"));
                        Scene homepage = new Scene(home);
                        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        homestage.setScene(homepage);
                        homestage.show(); 
                    }
                    else {
                        Parent tutorsss = FXMLLoader.load(getClass().getResource("RegisterTutor.fxml"));
                        Scene tutorssspage = new Scene(tutorsss);
                        Stage tutorsssstage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        tutorsssstage.setScene(tutorssspage);
                        tutorsssstage.show(); 
                    }
                     
                } 
            }
        catch(IOException e){
            Alert a = new Alert(AlertType.NONE); 
            a.setAlertType(AlertType.ERROR);
            a.setContentText("Username or StudentID already exist."); 
            a.show();
            System.out.println(e);
        } 
    }
    }

    

   
 
    


    
   
