/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author fupre1
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

 
public class AppConfig 
{
    static File fileName=new File("Config");
    static  String userHome = System.getProperty("user.home",".");
     static String FILE_SEPARATOR = System.getProperty("file.separator","/"); 
     //static String configFileName = fileName+FILE_SEPARATOR+"config.properties";
     static File configFileName=new File("config.properties");
     static File setupFileName=new File("setupconfig.properties");
     static String logFileName = userHome+FILE_SEPARATOR+"applicationLog.log";
    public static void LoadIntoConfigFiles()
    {
        //(String dbhost,String port,String dbname,String dbuser,String dbpass )
    	Properties prop = new Properties();
 ///*
    	try {
    		//set the properties value
           
                prop.setProperty("comp_name", "Teledom International Ltd");
                prop.setProperty("com_city", "Lagos");
    		prop.setProperty("State", "Lagos");
    		prop.setProperty("logo_con", "logo.png");
    		prop.setProperty("front_frame", "front.png");
                prop.setProperty("back_frame", "back.png");
               
                
                
 
    		//save properties to project root folder
    		prop.store(new FileOutputStream(setupFileName), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
       // */
        
        
        
    }
     public static Properties LoadFromConfigFiles( )
    {
    	Properties prop = new Properties();
 
        
        try {
               //load a properties file
    		prop.load(new FileInputStream(configFileName));
  
               //get the property value and print it out
                /*
                System.out.println(prop.getProperty("dbhost"));
                System.out.println(prop.getProperty("dbport"));
                System.out.println(prop.getProperty("dbname"));
    		System.out.println(prop.getProperty("dbuser"));
    		System.out.println(prop.getProperty("dbpassword"));
                System.out.println(prop.getProperty("logLimit"));
                */
                
                
                
 
    	} catch (IOException ex) {
        }
        return prop;
        
    }
     
     public static void main(String[] args)
     {
        // System.out.println("My configFileName path is:"+configFileName);
         //  AppConfig.LoadFromConfigFiles();//fileName.getAbsolutePath()
         AppConfig.LoadIntoConfigFiles();
         //configFileName
     }
     
}
