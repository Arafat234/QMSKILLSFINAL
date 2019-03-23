package qmskillsfinal;

    
import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountsDirectory {
        public String id;
        public String fullname;
        public String studentemail;
        // USED AS SPECIAL CASE to take values from one table to the next
        
        //Step 1  
         // create a AccountsDirectorySingleton class.  
        //static member holds only one instance of the AccountsDirectorySingleton class.  
             
        private static AccountsDirectory jdbc;  
           
        //JDBCSingleton prevents the instantiation from any other class.  
        private AccountsDirectory() {  }  
        
        //Now we are providing global point of access.  
        public static AccountsDirectory getInstance() {    
                                     if (jdbc==null)  
                                   {  
                                      jdbc= new AccountsDirectory();  
                                   }  
                         return jdbc;  
             }  
            
        // to get the connection from methods like insert, view etc.   
        private static Connection getConnection()throws ClassNotFoundException, SQLException  
          {  
                
              Connection con=null;  
              Class.forName("org.apache.derby.jdbc.ClientDriver");  
              con= DriverManager.getConnection("jdbc:derby://localhost:1527/SE", "root", "root");  
              return con;  
                
          }  
            
        //to insert into the registartion table of the database   
        public int insert(String degreechoice, String studentid,String fullname,String username,String password1,String yearofstudy,String email,String phonenumber,String userrolechoice) throws SQLException  
          {  
              Connection c=null;  
                
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
                
              try {  
                    
                      c=this.getConnection();  
                      ps=c.prepareStatement("insert into REGISTRATION(DEGREECHOICE,STUDENTID,FULLNAME,USERNAME,PASSWORD,YEAROFSTUDY,EMAIL,PHONENUMBER,USERROLECHOICE,BANSTATUS,VERIFY)values(?,?,?,?,?,?,?,?,?,?,?)");  
                       
                      ps.setString(1,degreechoice);
                      ps.setString(2,studentid);
                      ps.setString(3,fullname);
                      ps.setString(4,username);
                      ps.setString(5,password1);
                      ps.setString(6,yearofstudy);
                      ps.setString(7,email);
                      ps.setString(8,phonenumber);
                      ps.setString(9,userrolechoice);
                      ps.setBoolean(10,false);
                      ps.setBoolean(11,false);
                      recordCounter=ps.executeUpdate();  
                        
              } catch (Exception e) { e.printStackTrace();
                   Alert a = new Alert(Alert.AlertType.NONE); 
                   a.setAlertType(Alert.AlertType.ERROR);
                   a.setContentText("You have used a USERNAME or STUDENTID that already exist."); 
                   a.show();
              } finally{  
                    if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
              }  
             return recordCounter;  
          }  
  
        //to view the data from the database        
        public  void view(String name) throws SQLException  
        {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;  
                  
                try {  
                      
                        con=this.getConnection();  
                        ps=con.prepareStatement("select * from userdata where uname=?");  
                        ps.setString(1, name);  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                                  System.out.println("Name= "+rs.getString(2)+"\t"+"Paasword= "+rs.getString(3));      
                         
                        }  
                
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }  
        
        // to update the password for the given username  
        public int updateTutorRegistration(String module,String userIdentity) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET MODULE = ? where USERNAME='"+userIdentity+"'");  
                      ps.setString(1,module);
                      //ps.setString(11,grade);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }  
        
         // to update the password for the given username  
        public int updateTutorRegistrationGrade(String grade,String userIdentity) throws SQLException  {  
              Connection a=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      a=this.getConnection();  
                      ps=a.prepareStatement("Update REGISTRATION SET GRADE = ? where USERNAME='"+userIdentity+"'");  
                      ps.setString(1,grade);
                      //ps.setString(11,grade);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(a!=null){  
                      a.close();  
                  }   
               }  
             return recordCounter;  
          }  
              
        public int delete(int userid) throws SQLException
                {  
              Connection c=null;  
              PreparedStatement ps=null;  
              int recordCounter=0;  
              try {  
                       c=this.getConnection();  
                      ps=c.prepareStatement(" delete from userdata where uid='"+userid+"' ");  
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) { e.printStackTrace(); }   
              finally{  
              if (ps!=null){  
                      ps.close();  
             }if(c!=null){  
                      c.close();  
              }   
           }  
             return recordCounter;  
          }  
        
        public boolean login(String username, String password) throws SQLException, IOException  
                {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;  
                  
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select * from REGISTRATION");  
                        //ps.setString(1, name);  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                            if ((username.equals(rs.getString(4)) && password.equals(rs.getString(5))))
                            {                
                                return true;
                            }
                        
                            }       
                    }  
                
                catch (Exception e) { System.out.println(e);}  
                    finally{  
                        if(rs!=null){  
                            rs.close();  
                        }if (ps!=null){  
                          ps.close();  
                        }if(con!=null){  
                            con.close();  
                        }   
                    }
                return false;
            }
        
        public boolean loginchecker(String username) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select USERROLECHOICE from REGISTRATION where USERNAME='"+username+"'");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getString(1).equals("Tutor"))
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
        
        public boolean checker(String username) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select USERNAME from REGISTRATION");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getString(1).equalsIgnoreCase(username))
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
        
        public boolean checker2(String studentid) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select STUDENTID from REGISTRATION");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getString(1).equals(studentid))
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
        
        public boolean checker3(int id) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select SESSIONID from SESSION");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getString(1).equals(id))
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
        
         // used for navigating to tutor page or student page
        public boolean checker4(String username) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select USERROLECHOICE from REGISTRATION where  USERNAME ='"+username+"'");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getString(1).equals("Tutor"))
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                } 
        
        
        
        
        public int updatedegreechoice(String username,String degreechoice) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET DEGREECHOICE = ? where USERNAME='"+username+"'");  
                      ps.setString(1,degreechoice);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }             
           
        public int updateyearofstudychoice(String username,String yearofstudy) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET YEAROFSTUDY = ? where USERNAME='"+username+"'");  
                      ps.setString(1,yearofstudy);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }         
         
        public int updatepasswordchoice(String username,String password) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET PASSWORD = ? where USERNAME='"+username+"'");  
                      ps.setString(1,password);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }    
          
        public int updatefullnamechoice(String username,String fullname) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET FULLNAME = ? where USERNAME='"+username+"'");  
                      ps.setString(1,fullname);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }
  
        public int updateemailchoice(String username,String email) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET EMAIL = ? where USERNAME='"+username+"'");  
                      ps.setString(1,email);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }       
         
        public int updatephonenumberchoice(String username,String phonenumber) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET PHONENUMBER = ? where USERNAME='"+username+"'");  
                      ps.setString(1,phonenumber);
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }   
  
        public  void moduleLoad(ChoiceBox<String> module,ObservableList availableChoiceA,ChoiceBox<String> tutor,ObservableList availableChoiceC) throws SQLException  
        {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select DISTINCT MODULE from REGISTRATION");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                            if(rs.getString(1) != null){
                            availableChoiceA.addAll(rs.getString(1));
                            }
                        }  
                        
                    module.getItems().addAll(availableChoiceA);
                    
                     module.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                            try {
                                //System.out.println(module.getItems().get((Integer) number2));
                                tutorLoad(tutor,availableChoiceC,module.getItems().get((Integer) number2));
                            } catch (SQLException ex) {
                                Logger.getLogger(AccountsDirectory.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          
                        }
                      });
                
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
    

        public void tutorLoad(ChoiceBox<String> tutor,ObservableList availableChoiceC,String modulename) throws SQLException  
        {
            availableChoiceC.clear();
            tutor.getItems().clear();
            //System.out.println(tutor.getItems().size());
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select FULLNAME,EMAIL,GRADE,USERNAME from REGISTRATION WHERE USERROLECHOICE = 'Tutor' AND MODULE ='"+modulename+"'");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                           
                            id = rs.getString(4);
                            availableChoiceC.addAll(rs.getString(1) +" | Grade: "+ rs.getString(3)+" | Email: "+ rs.getString(2) );
                             //id = rs.getString(4);
                             
                            
                        }  
                        
                    tutor.getItems().addAll(availableChoiceC);
                
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }

        //to insert the record into the database   
        public int insertsession(String sessionid, String module,String time, String tutor, String day, String payment, String username,String tutorid,String studentfullname,String email) throws SQLException  
          {  
              Connection c=null;  
                
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              tutorid = id;
              studentfullname = fullname; 
              email = studentemail;
             
              try {  
                    
                      c=this.getConnection();  
                      ps=c.prepareStatement("insert into SESSION(SESSIONID,MODULE,TIME,TUTOR,DAY,PAYMENTMETHOD,USERNAME,TUTORUSERNAME,STUDENTFULLNAME,STUDENTEMAIL)values(?,?,?,?,?,?,?,?,?,?)");  
                       
                      ps.setString(1,sessionid);
                      ps.setString(2,module);
                      ps.setString(3,time);
                      ps.setString(4,tutor);
                      ps.setString(5,day);
                      ps.setString(6,payment);
                      ps.setString(7,username);
                      ps.setString(8,tutorid);
                      ps.setString(9,fullname);
                      ps.setString(10,studentemail);
                      
                      recordCounter=ps.executeUpdate();  
                        
              } catch (Exception e) { e.printStackTrace();
                   
              } finally{  
                    if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
              }  
             return recordCounter;  
          } 
          
        public void fullnamereciever(String username) throws SQLException  
        {
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select FULLNAME from REGISTRATION WHERE USERNAME ='"+username+"'");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {                        
                            fullname = rs.getString(1);
                        }  
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
       
        public void emailreciever(String username) throws SQLException  
        {
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select EMAIL from REGISTRATION WHERE USERNAME ='"+username+"'");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {                        
                            studentemail = rs.getString(1);
                        }  
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
       
        public void addSessionData(ObservableList data,String username,TableColumn sessionIDColumn,TableColumn fullnameColumn,TableColumn timeColumn,TableColumn paymentmethodColumn,TableColumn dayColumn,TableColumn emailColumn, TableView sessiontable ) throws SQLException  
        {
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;    
                
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select SESSIONID,STUDENTFULLNAME,TIME,PAYMENTMETHOD,DAY,STUDENTEMAIL FROM SESSION WHERE TUTORUSERNAME ='"+username+"'");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {                        
                            data.add(new sessionDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                        }  
                        
          } catch (Exception e) { System.out.println(e);  
                
          }
          finally{  
                sessionIDColumn.setCellValueFactory(new PropertyValueFactory<>("sessionID"));
                fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
                timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
                paymentmethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
                dayColumn.setCellValueFactory(new PropertyValueFactory<>("Day")); 
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email")); 
                
                sessiontable.setItems(null);
                sessiontable.setItems(data);
                    
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
            
        public int cancelSessionData(String sessionid) throws SQLException
        {  
              Connection c=null;  
              PreparedStatement ps=null;  
              int recordCounter=0;  
              try {  
                       c=this.getConnection();  
                      ps=c.prepareStatement(" delete from SESSION where SESSIONID='"+sessionid+"' ");  
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) { e.printStackTrace(); }   
              finally{  
              if (ps!=null){  
                      ps.close();  
             }if(c!=null){  
                      c.close();  
              }   
           }  
             return recordCounter;  
          }    
               
         public  void loadSubjectData(ChoiceBox<String> degreechoice,ObservableList availableChoiceSubject) throws SQLException  
        {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select DEGREECHOICES from INFO");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                            if(rs.getString(1) != null){
                            availableChoiceSubject.addAll(rs.getString(1));
                            }
                        }  
                        
                    degreechoice.getItems().addAll(availableChoiceSubject);
                   
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
        
         public  void loadUsernameData(ChoiceBox<String> username,ObservableList availableUserName) throws SQLException  
        {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select USERNAME from REGISTRATION");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                            if(rs.getString(1) != null){
                            availableUserName.addAll(rs.getString(1));
                            }
                        }  
                        
                    username.getItems().addAll(availableUserName);
                   
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
         
         public  void loadUsernameData2(ChoiceBox<String> username,ObservableList availableUserName) throws SQLException  
        {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select USERNAME from REGISTRATION where VERIFY = 'false'");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                            if(rs.getString(1) != null){
                            availableUserName.addAll(rs.getString(1));
                            }
                        }  
                        
                    username.getItems().addAll(availableUserName);
                   
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
         
     public int updateBanStatus2(String username,String choice) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET VERIFY = ? where USERNAME='"+username+"'"); 
                      if (choice.equals("yes")){
                      ps.setBoolean(1,true);
                      }
                      else{
                      ps.setBoolean(1,false);
                      }
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          }     
         
        
     public int updateBanStatus(String username,String choice) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement("Update REGISTRATION SET BANSTATUS = ? where USERNAME='"+username+"'"); 
                      if (choice.equals("Ban")){
                      ps.setBoolean(1,true);
                      }
                      else{
                      ps.setBoolean(1,false);
                      }
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
             return recordCounter;  
          } 
     
      public void addUserData(ObservableList data,String username,TableColumn usernameColumn,TableColumn studentIDColumn,TableColumn passwordColumn,TableColumn yearOfStudyColumn,TableColumn degreeColumn,TableColumn emailColumn,TableColumn phoneNumberColumn, TableView table ) throws SQLException  
        {
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;    
                
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select USERNAME,STUDENTID,PASSWORD,YEAROFSTUDY,DEGREECHOICE,EMAIL,PHONENUMBER FROM REGISTRATION WHERE USERNAME ='"+username+"'");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {                        
                            data.add(new Details(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
                        }  
                        
          } catch (Exception e) { System.out.println(e);  
                
          }
          finally{  
                usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
                studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
                passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
                yearOfStudyColumn.setCellValueFactory(new PropertyValueFactory<>("YearOfStudy")); 
                degreeColumn.setCellValueFactory(new PropertyValueFactory<>("Degree")); 
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email")); 
                phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber")); 
                
                
                table.setItems(null);
                table.setItems(data);
                    
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
     
      public  void loadModuleData(ChoiceBox<String> module,ObservableList availableChoiceModule) throws SQLException  
        {  
                Connection con = null;  
                PreparedStatement ps = null;  
                ResultSet rs = null;            
                try {  
                        con=this.getConnection();  
                        ps=con.prepareStatement("select DISTINCT MODULE from INFO");  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                            if(rs.getString(1) != null){
                            availableChoiceModule.addAll(rs.getString(1));
                            }
                        }  
                        
                    module.getItems().addAll(availableChoiceModule);
                   
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
        }
     
     
      
              public boolean checker5(String username) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select BANSTATUS from REGISTRATION where USERNAME='"+username+"'");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getBoolean(1) == true)
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
      
      public boolean checker6(String email) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select EMAIL from REGISTRATION");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getString(1).equalsIgnoreCase(email))
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
      
      public boolean checker8(String username) throws SQLException, IOException  
                {  
                    Connection con = null;  
                    PreparedStatement ps = null;  
                    ResultSet rs = null;  

                    try {  
                            con=this.getConnection();  
                            ps=con.prepareStatement("select VERIFY from REGISTRATION where USERNAME='"+username+"'");  
                            //ps.setString(1, name);  
                           
                            rs=ps.executeQuery();  
                            while (rs.next()) {  
                                if (rs.getBoolean(1) == true)
                                {                
                                    return true;
                                }
                                
                                
                                }       
                        }  

                    catch (Exception e) { System.out.println(e);}  
                        finally{  
                            if(rs!=null){  
                                rs.close();  
                            }if (ps!=null){  
                              ps.close();  
                            }if(con!=null){  
                                con.close();  
                            }   
                        }
                    return false;
                }
      
      
      
      
      
     
}
 // End of AccountsDirectory class }
