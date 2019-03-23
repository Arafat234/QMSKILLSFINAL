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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */

public class LoginQMSkillsController  implements Initializable {
    @FXML public TextField userName;
    @FXML public PasswordField password;
    public static String username;
   
    
    
    
    
    @FXML
    public void switchToHome(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("homeQMSkills.fxml"));
        Scene homepage = new Scene(home);
        Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homestage.setScene(homepage);
        homestage.show();
    }
    
    @FXML
    public void clearFields(){ // to be done properly
        if (userName.getText() != null || password.getText() != null ){
        userName.clear();
        password.clear();
        }
    }
    
    @FXML
    private void verifyLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        try{
        AccountsDirectory jdbc= AccountsDirectory.getInstance(); 
        if(jdbc.checker5(userName.getText())==true){
            Alert a = new Alert(Alert.AlertType.NONE); 
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You are banned please contact the admin."); 
            a.show();
            return;
        }
        if (userName.getText().equals("Admin") && password.getText().equals("Admin") ){
            Parent admin = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
            Scene adminpage = new Scene(admin);
            Stage adminstage = (Stage)((Node) event.getSource()).getScene().getWindow();
            adminstage.setScene(adminpage);
            adminstage.show();
        }
        else if (jdbc.login(userName.getText(), password.getText()) == true){
            if(jdbc.checker8(userName.getText())==false){
            Alert a = new Alert(Alert.AlertType.NONE); 
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Your account has not been verified please wait for at least 24 hours."); 
            a.show();
            return;
            }
            if (jdbc.loginchecker(userName.getText()) == true){
            username = userName.getText();
            Parent home = FXMLLoader.load(getClass().getResource("TutorPage.fxml"));
            Scene homepage = new Scene(home);
            Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
            homestage.setScene(homepage);
            homestage.show();
            }
            else{
            username = userName.getText();
            Parent home = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
            Scene homepage = new Scene(home);
            Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
            homestage.setScene(homepage);
            homestage.show();
            }
        }
        else
        {   
                   Alert a = new Alert(Alert.AlertType.NONE); 
                   a.setAlertType(Alert.AlertType.ERROR);
                   a.setContentText("You have entered in the wrong username or password"); 
                   a.show();
            
             System.out.println("Incorrect username and password");    
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}  


