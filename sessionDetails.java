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
public class sessionDetails {
    private final StringProperty sessionID;
    private final StringProperty fullName;
    private final StringProperty time;
    private final StringProperty paymentMethod;
    private final StringProperty day;
    private final StringProperty email;
    
    
    public sessionDetails(String sessionID,String fullName,String time,String paymentMethod,String day,String email){
        this.sessionID = new SimpleStringProperty(sessionID);
        this.fullName = new SimpleStringProperty(fullName);
        this.time = new SimpleStringProperty(time);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.day = new SimpleStringProperty(day);
        this.email = new SimpleStringProperty(email);
}
    
   public String  getsessionID (){return sessionID.get(); }
   public String  getfullName (){return fullName.get(); }
   public String  getTime (){return time.get(); }
   public String  getpaymentMethod (){return paymentMethod.get(); }
   public String  getDay (){return day.get(); }
   public String  getEmail (){return email.get(); }
    
   public void setSessionID(String value){
       sessionID.setValue(value);
   }
   
   public void setfullName(String value){
       fullName.setValue(value);
   }
   
   public void setTime(String value){
       time.setValue(value);
   }
   
   public void setpaymentMethod(String value){
       paymentMethod.setValue(value);
   }
   
    public void setDayMethod(String value){
       day.setValue(value);
   } 
    
    public void setEmailMethod(String value){
       email.setValue(value);
   } 
    
   
   public StringProperty sessionIDProperty(){return sessionID;}
   public StringProperty fullNameProperty(){return fullName;}
   public StringProperty timeProperty(){return time;}
   public StringProperty paymentMethodProperty(){return paymentMethod;}
   public StringProperty dayMethodProperty(){return day;}
   public StringProperty emailMethodProperty(){return email;}
  
    
}

