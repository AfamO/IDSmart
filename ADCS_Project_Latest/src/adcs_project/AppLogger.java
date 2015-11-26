/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author fupre1
 */
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
 
public class AppLogger {
     private Properties prop;
   static  String userHome = System.getProperty("user.home",".");
   //static File fileName1=new File("src\\adcs_project\\log");
    static File fileName=new File("log");
  // File f=fileName.getAbsoluteFile()+"\\";
   private String InfoLogged="";
    static String FILE_SEPARATOR = System.getProperty("file.separator","/"); 
   //static String logFileName = userHome+FILE_SEPARATOR+"applicationLog.log";
    static String logFileName = fileName+FILE_SEPARATOR+"applicationLog.log";
     //static String logFileName = fileName.getAbsolutePath()+"\\applicationLog.log";
   static private Logger logger=null;
    public AppLogger(){
         Logger logga = Logger.getLogger("MyLog");
         this.logger=logga;
          FileHandler fh;
          prop=AppConfig.LoadFromConfigFiles();
        
        try {
            /*
            System.out.println("My home :"+userHome);
            System.out.println("My absolute path is :"+fileName.getAbsolutePath());
            System.out.println("My file_separator is :"+FILE_SEPARATOR);
            System.out.println("My home file is:"+logFileName1);
            */
            // This block configure the logger with handler and formatter
            int limit=5000000;
            
            if( prop.getProperty("logLimit")==null|| prop.getProperty("logLimit").equals(""))
            {
                JOptionPane.showMessageDialog(null, "The Loglimit of the application is either empty or the configuration file is missing.\nPlease check your Configuration file and make sure that it is in the\n right place and that all the values are intact. \nThank you. ","LOGGING AND INITIALIZATION PROBLEM",2);
                    limit =5000000;
                   InfoLogged= InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "The Loglimit of the application has been altered and set empty,Please check your Configuration file-The default limit is : "+limit);
                   LogInfo(BiometricMain.LogerObj,InfoLogged);
            }
            else
            {
                limit=Integer.parseInt(prop.getProperty("logLimit"));
                InfoLogged= InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "The Loglimit of the application as red is : "+limit);
                 System.out.println("My LogLimit=:"+limit);
                
            }
            
           
          File  logFiName=new File("applicationLog.log");
          //logFileName=logFiName
            fh = new FileHandler(logFiName.toString(),limit,1,true);
            System.out.println("My Logfile path is=:"+logFiName.toString());
           // C:/temp/test/MyLogFile.log logger.info("Hi How r u?");
            this.logger.addHandler(fh);
            //logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            
    }
    }
     
    public static void main(String[] args) {
        
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;
        
        try {
           
            System.out.println("My home :"+userHome);
            System.out.println("My absolute path is :"+fileName.getAbsolutePath());
            System.out.println("My file_separator is :"+FILE_SEPARATOR);
            System.out.println("My home file is:"+logFileName);
             File  logFiName=new File("applicationLog.log");
            // This block configure the logger with handler and formatter
            int limit=5000000;
            //fh = new FileHandler(logFileName,limit,1,true);
             fh = new FileHandler(logFiName.toString(),limit,1,true);
             System.out.println("My Logfile path is:"+logFiName.toString());
           // C:/temp/test/MyLogFile.log logger.info("Hi How r u?");
            logger.addHandler(fh);
            //logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
            // the following statement is used to log any messages
            logger.info("My app log");
            
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //logger.info("Abba King, VC, SP 911, Registered Student");
        logger.info("Hi How do u do?");
        
    }

    public Logger getLogger() {
        
        
        return AppLogger.logger;
        
    }
    
     public static void LogInfo(Logger logger,String info) {
        
       // Logger logger = Logger.getLogger("MyLog");
       
            // the following statement is used to log any messages
            logger.info(info);
            
        
        }
        
        //logger.info("Abba King, VC, SP 911, Registered Student");
        //logger.info("Hi How do u do?");
        
    
     public static String InfoLogged(String name,String status,String action) {
        
        String detailInfo="[User: "+name+", Status: "+ status+", Action Taken: "+action+"]";
        return detailInfo;
    }
    
}
