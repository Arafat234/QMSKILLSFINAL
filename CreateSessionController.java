/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmskillsfinal;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import qmskillsfinal.AccountsDirectory;
import qmskillsfinal.LoginQMSkillsController;
import static qmskillsfinal.LoginQMSkillsController.username;

/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
public class CreateSessionController extends  LoginQMSkillsController  implements Initializable {

    @FXML ChoiceBox<String> module;
    ObservableList availableChoiceA = FXCollections.observableArrayList();
    
    @FXML ChoiceBox<String> time;
    ObservableList availableChoicetime = FXCollections.observableArrayList(); 
    
    @FXML ChoiceBox<String> tutor;
    ObservableList availableChoiceC = FXCollections.observableArrayList();
    
    @FXML ChoiceBox<String> payment;
    ObservableList available2 = FXCollections.observableArrayList();
    
    
    @FXML ChoiceBox<String> day;
    ObservableList available1 = FXCollections.observableArrayList();
    
   // @FXML private TextField sessionID;
    private int ID;
    
    
    private Button cancelbutton;
   
    
    /**
     * Initializes the controller class.
     * @param module
     * @param time
     * @param tutor
     * @param day
     * @param payment
     * @param url
     * @param rb
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    
        @FXML
     private void hello(ActionEvent event) throws SQLException, IOException{      
            if(module.getValue() == null || time.getValue() == null || tutor.getValue() == null || payment.getValue() == null || day.getValue() == null){
                Alert c = new Alert(Alert.AlertType.NONE); 
                c.setAlertType(Alert.AlertType.ERROR);
                c.setContentText("Please ensure all options have been selected"); 
                c.show();
                return;
            }
            
            AccountsDirectory jdbc= AccountsDirectory.getInstance(); 
            Random rand = new Random(); 
            //ID = rand.nextInt();
            ID = rand.nextInt(10000);
            
            while(jdbc.checker3(ID) == true){
                ID = rand.nextInt(10000);
            }
            
            String sessionID =String.valueOf(ID);
            jdbc.fullnamereciever(LoginQMSkillsController.username);
            jdbc.emailreciever(LoginQMSkillsController.username);
            jdbc.insertsession(sessionID,module.getValue(),time.getValue(),tutor.getValue(),day.getValue(),payment.getValue(),LoginQMSkillsController.username,"","","");
            Parent home = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
            Scene homepage = new Scene(home);
            Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
            homestage.setScene(homepage);
            homestage.show();
           }
    
        @FXML
       private void studentpage(ActionEvent event) throws IOException{      

                   Parent home = FXMLLoader.load(getClass().getResource("StudentPage.fxml"));
                   Scene homepage = new Scene(home);
                   Stage homestage = (Stage)((Node) event.getSource()).getScene().getWindow();
                   homestage.setScene(homepage);
                   homestage.show();
        } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadOptionData();
        try { 
            LoadModulesdata(module,availableChoiceA,tutor,availableChoiceC);
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       /* try {
            Loadtutordata(tutor,availableChoiceC);
        } catch (SQLException ex) {
            Logger.getLogger(CreateSessionController.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }    

  
    
    @FXML
    private void loadOptionData(){
    availableChoicetime.removeAll(availableChoicetime);
    String a = "Morning (8:00am - 12:00pm)";
    String b = "Afternoon (12:00pm - 6:00pm)";
    String c = "Evening (6:00pm - 8:00pm) ";
    
    availableChoicetime.addAll(a,b,c);
    time.getItems().addAll(availableChoicetime);
    
    available1.removeAll(available1);
    String d = "Monday";
    String e = "Tuesday";
    String f = "Wednesday";
    String g = "Thursday";
    String h = "Friday";
    
    available1.addAll(d,e,f,g,h);
    day.getItems().addAll(available1);
    
    
    available2.removeAll(available2);
    String i = "Paypal";
    String j = "Cash";
    
    available2.addAll(i,j);
    payment.getItems().addAll(available2);
    
    } 
    
    
    @FXML
    public void LoadModulesdata(ChoiceBox<String> module,ObservableList availablechoiceA,ChoiceBox<String> tutor,ObservableList availablechoiceC) throws SQLException{      
            AccountsDirectory jdbc= AccountsDirectory.getInstance(); 
            jdbc.moduleLoad(module, availablechoiceA,tutor,availablechoiceC);
    } 
    
 
    
    
    
    
    
}
    
    
