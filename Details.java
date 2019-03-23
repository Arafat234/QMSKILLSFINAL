/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmskillsfinal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mohammedarafatamin
 */
public class Details {
    private final StringProperty username;
    private final StringProperty StudentID;
    private final StringProperty password;
    private final StringProperty yearOfStudy;
    private final StringProperty degree;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    
    
    public Details(String username,String StudentID,String password,String yearOfStudy,String degree,String email,String phoneNumber){
        this.username = new SimpleStringProperty(username);
        this.StudentID = new SimpleStringProperty(StudentID);
        this.password = new SimpleStringProperty(password);
        this.yearOfStudy = new SimpleStringProperty(yearOfStudy);
        this.degree= new SimpleStringProperty(degree);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
}
    
   public String  getUsername (){return username.get(); }
   public String  getStudentID (){return StudentID.get(); }
   public String  getPassword (){return password.get(); }
   public String  getYearOfStudy (){return yearOfStudy.get(); }
   public String  getDegree (){return degree.get(); }
   public String  getEmail (){return email.get(); }
   public String  getPhoneNumber (){return phoneNumber.get(); }
    
   public void setUsername(String value){
       username.setValue(value);
   }
   
   public void setStudentID(String value){
       StudentID.setValue(value);
   }
   
   public void setPassword(String value){
       password.setValue(value);
   }
   
   public void setYearOfStudy(String value){
       yearOfStudy.setValue(value);
   }
   
    public void setDegree(String value){
       degree.setValue(value);
   } 
    
    public void setEmail(String value){
       email.setValue(value);
   }
    
    public void setPhoneNumber(String value){
       phoneNumber.setValue(value);
   }
   
   public StringProperty usermameProperty(){return username ;}
   public StringProperty StudentIDProperty(){return StudentID;}
   public StringProperty PasswordProperty(){return password;}
   public StringProperty yearOfStudyProperty(){return yearOfStudy;}
   public StringProperty degreeProperty(){return degree;}
   public StringProperty emailProperty(){return email;}
   public StringProperty phoneNumberProperty(){return phoneNumber;}
  
    
}

