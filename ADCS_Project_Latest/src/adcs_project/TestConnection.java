/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

/**
 *
 * @author Charles
 */
public class TestConnection {
    
    
    public Connection getConnection() 
  { 
      Connection connection = null; 
  try { 
      Class.forName("com.mysql.jdbc.Driver"); 
 // connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric_database", "root", ""); 
 //connection = DriverManager.getConnection("jdbc:mysql://192.168.2.16:3306/biometric_database", "teledom", "caint");
 //JOptionPane.showMessageDialog(null,"Coneected to database  Thank u Jesus");
  } 
  catch (Exception e) 
  { 
      System.out.println("Error Occured While Getting the Connection: - " + e); 
  } 
  return connection; 
  } 
    
    
    
   public void SaveClientBiometricInfor(String client_id,byte [] img,byte [] img1,byte [] img2){
     //FileInputStream pix;
     //FileInputStream sign;
    // FileInputStream finger;
Connection connection = null; 
PreparedStatement statement = null;
InputStream pix;
InputStream sign;
InputStream finger;
//FileInputStream inputStream = null; 
try { 
 //File image = new File("C:/honda.jpg"); 
//inputStream = new FileInputStream(image); 
//pix=new FileInputStream(img);
//sign=new FileInputStream(s2);
//finger=new FileInputStream(f3);
   // ByteArrayOutputStream pixout = new ByteArrayOutputStream();
    //ImageIO.write(img,"png", pixout);
   // byte [] pixbyte=pixout.toByteArray();
    pix =new ByteArrayInputStream(img); 
            //new ByteArrayInputStream(pixbyte); 
   
    
     //ByteArrayOutputStream sigout = new ByteArrayOutputStream();
    //ImageIO.write(s2,"png", sigout);
   // byte [] sigbyte=sigout.toByteArray();
    sign =new ByteArrayInputStream(img1); 
            //new ByteArrayInputStream(sigbyte); 
   
     
    // ByteArrayOutputStream finout = new ByteArrayOutputStream();
    //ImageIO.write(f3,"png", finout);
    //byte [] finbyte=finout.toByteArray();
     finger =new ByteArrayInputStream(img2); 
            // new ByteArrayInputStream(finbyte); 
   
connection = getConnection(); 
//statement = connection .prepareStatement("insert into trn_imgs(img_title, img_data) " + "values(?,?)"); 
//String sql = "INSERT INTO client_biometric  (client_id,picture_img,sign_img,finger_img) VALUES (?,?,?,?)";
statement = connection .prepareStatement("INSERT INTO client_biometric_infor  (client_id,picture_img,sign_img,finger_img) " + "VALUES (?,?,?,?)");
statement.setString(1,client_id); 
//statement.setBinaryStream(2, (InputStream) inputStream, (int) (image.length())); 
//statement.setBinaryStream(2, (InputStream)pix, pixbyte.length);
//statement.setBinaryStream(3, (InputStream)sign, sigbyte.length);
//statement.setBinaryStream(4, (InputStream)finger, finbyte.length);
statement.setBinaryStream(2,pix,img.length);
statement.setBinaryStream(3, sign,img1.length);
statement.setBinaryStream(4, finger,img2.length);

statement.executeUpdate();
//System.out.println("Thank u Jesus Christ it is working");
//JOptionPane.showMessageDialog(null,"Information Sent to succssfully  Thank u Jesus !!!!!!!!");
JOptionPane.showMessageDialog(null, "Information was sent to database successfully");

} //catch (FileNotFoundException e) { 
//System.out.println("FileNotFoundException: - " + e); 
//} 
//catch (IOException e) { 
//System.out.println("FileNotFoundException: -  Image can be write" + e); 
//} 
catch (SQLException e) { System.out.println("SQLException: - " + e); 
} finally { 
try {
connection.close(); 
statement.close();
} 
catch (SQLException e) { 
System.out.println("SQLException Finally: - " + e); 
} 
} 
} 
  
   
/* public void RetriveImage(){
     try{
     Statement stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DB.images");
            int count = 0;
            while (rs.next()) {
                InputStream is = rs.getBinaryStream("photo");
                BufferedImage image = ImageIO.read(is); //the image is read in
                //store it back again as a file
                ImageIO.write(image, "jpg", new FileOutputStream("recived" + count + ".jpg"));
                count++;
            }
 
            System.out.println(count+" images saved on disk");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 }  
 */
   
   
   
}


