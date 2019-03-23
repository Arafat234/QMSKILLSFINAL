package qmskillsfinal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohammedarafatamin
 */
public class HomeQMSkillsController implements Initializable {

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException{
        Parent login = FXMLLoader.load(getClass().getResource("loginQMSkills.fxml"));
        Scene loginpage = new Scene(login);
        Stage loginstage = (Stage)((Node) event.getSource()).getScene().getWindow();
        loginstage.setScene(loginpage);
        loginstage.show();
    }
    
    @FXML
    public void switchToRegister(ActionEvent event) throws IOException{
        Parent register = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene registerPage = new Scene(register);
        Stage registerstage = (Stage)((Node) event.getSource()).getScene().getWindow();
        registerstage.setScene(registerPage);
        registerstage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
