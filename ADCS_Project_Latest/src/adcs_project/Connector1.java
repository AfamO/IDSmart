/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */
import java.sql.*;
import javax.sql.DataSource;
import javax.swing.*;
import java.util.Properties;
//import javax.servlet.http.HttpServlet;
import javax.naming.*;
public class Connector1 {
    public  Connection dbconn;
    private static DataSource ds;
    private static String  msgout;
    private static Boolean IsConnected=false;
    public static int poolcount=0;
    private Properties prop;
    /** Creates a new instance of Connector */
    public Connector1() {
    }
    
    public void databaseConnect() {
        
        try {
       prop=AppConfig.LoadFromConfigFiles();
       String host=prop.getProperty("dbhost");
      // String host2=prop.getProperty("db2host");
       String port=prop.getProperty("dbport");
       String dbname=prop.getProperty("dbname");
       String dbuser=prop.getProperty("dbuser");
       String dbpass=prop.getProperty("dbpassword");
       if(host!=null || port!=null || dbname!=null || dbuser!=null || dbpass!=null  )
        {
            Class.forName("com.mysql.jdbc.Driver");
           // dbconn = DriverManager.getConnection("jdbc:mysql://192.168.2.16:3306/biometric_database", "teledom", "caint");
            
            
            dbconn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname, dbuser, dbpass);
            //dbconn = DriverManager.getConnection("jdbc:mysql://"+host2+":"+port+"/"+dbname, dbuser, dbpass);
       }
       else
     {
            display("Make Sure that your database configuration file has not been tampered with");
           dbconn=null;
       }
            
           
              

            if(dbconn!=null)
            {
                IsConnected=true;
               // display("The network time out is: "+DriverManager.getLoginTimeout());
            }
            else
            {
                IsConnected=false;
            }
            
        }
        
        catch ( ClassNotFoundException cnfex ) {
            // process ClassNotFoundExceptions here
            cnfex.printStackTrace();
            msgout =  "Connection to MAIN SERVER is unsuccessful.Please make sure that the Server is on and that \nproper configurations(like the right username, databasename and password) have been made.\nThank you.\n";
            //cnfex.toString() ;
            display(msgout);
        }
        catch ( SQLException sqlex ) {
            // process SQLExceptions here
            sqlex.printStackTrace();
             msgout =  "Connection to MAIN SERVER is unsuccessful.Please make sure that the Server is on and that \nproper configurations(like the right username, databasename and password) have been made.\nThank you.\n";
            //sqlex.toString() ;
            display(msgout);
        }
         
        catch ( Exception excp ) {
            // process remaining Exceptions here
            excp.printStackTrace();
            msgout =  excp.toString() ;
            display(msgout);
        }
         
    }
    public static Boolean getIsConnected()
    {
        return IsConnected;
    }
    
    public Statement create (){
        Statement stmt = null;
         try {
            stmt = dbconn.createStatement();
        }catch (SQLException ex) {
            display(ex.getMessage( ));
            System.out.println("The Stack Trace is:"+ex.getMessage());
            ex.printStackTrace();
        }
        return stmt;
    }
    public void display( String msg ) {
        JOptionPane.showMessageDialog(null, msg,"CONNECTION FAILURE",1);
    }
    
    }
   
