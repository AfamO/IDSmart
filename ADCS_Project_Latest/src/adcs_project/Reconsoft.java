package adcs_project;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Charles
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.sql.*;
import javax.swing.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

//import
//import org.apache.commons.lang.*;
//import org.apache.commons.lang.StringEscapeUtils;


//import org.apache.common
//import java.rmi.server.UnicastRemoteObject;

public class Reconsoft {
    //private URL u=new URL("index.html");
    private Statement statement;
    private  Connector1 dbconnector;
    //private static Connector1 con1=new Connector1();
    private Vector row;
    private String[] record;
    private String n1,n2,n3;
    private static int smscount=0;
    private static int totalcount=0;
    private static Reconsoft rs1;
    private Properties snosprop=new Properties();
    private static Properties RegisterdUserprop=new Properties();
    private Properties usersensorprop=new Properties();
    private List sensorList=new ArrayList<String>();
    private List clients_contactsList=new ArrayList<String>();
    private List clients_contactsId=new ArrayList<String>();
    private String [] mail;
    private String convertTosnos;
    
    /** Creates a new instance of ProjectDatabase */ 
      
    public Reconsoft(){

            dbconnector=new Connector1 ();
            
        //querysmsList();
        String htmlsource = "The less than sign (<) and ampersand (&) must be escaped before using them in HTML";
        String sqlsource="''select ''from snos_table where snos_type=''34'' OR snos_type=''SNOS1''";
        String javasource="import javax.swing.*; String val=new String();";
        //String escpsql=StringEscapeUtils.escapeSql(sqlsource);
        //String escphtml=StringEscapeUtils.escapeHtml(htmlsource);
        //String escpjava=StringEscapeUtils.escapeJava(javasource);
        //System.out.println("The eSCAPED result is:"+escpjava);
    }
     public Reconsoft(String m1,String m2,String m3){
        this.n1=m1;
        this.n2=m2;
        this.n3=m3;
        //getTextMessageAndDisplay(n1,n2,n3);
    }
    
      public void AddStudentsBasicInfor(String reg,String fname,String mname,String oname,String gen,String fac,String dept,String lev,String sess,String add,String fone,String group,String state,String lga,String bdate){
        dbconnector.databaseConnect();
        
        try {
            
           statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO student_registration" +
            "(client_id,firstname,middlename,othernames,gender,faculty,department,level,session,address,phoneno,b_group,state,lga,bdate)" +
             "VALUES('" + reg + "','" + fname+ "','" + mname +"','" + oname +"','" + gen + "','" + fac+ "','" + dept +"','" + lev +"','" + sess+"','" + add+"','" + fone+"','" + group+"','" +state+"','" + lga+"','" + bdate +"')";
             int result = statement.executeUpdate( query );
            
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        finally{
        try{
            dbconnector.dbconn.close();
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
    }
    }
      
    public void AddStudentBiometricInfor(String student_staff_id,String std_pix,String std_sig,String std_fpnt){
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
       // Blob blo  = dbconnector.dbconn.createBlob();
                       
            String query;
            query = "INSERT INTO biometric" +
     "(client_id,picture_link,signature_link,fingerprint_link)" +
      "VALUES('" + student_staff_id + "','" + std_pix + "','" + std_sig+ "', '" + std_fpnt+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
    }
    
    
    
    
    public void AddStaffNextKinInfor(String staff_id,String sname,String fname,String fone,String addres,String rela){
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query;
            query = "INSERT INTO staff_kin_reg" +
     "(kinstaffNum,surname,fname,fone,kinaddress,relation)" +
      "VALUES('" + staff_id + "','" + sname + "','" + fname+ "','" + fone+ "','" + addres+ "','" +rela+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
    } 
    
     public void AddStudentNextKinInfor(String staff_id,String sname,String fname,String fone,String addres,String rela){
        dbconnector.databaseConnect();
        
        try {
            
            statement = dbconnector.dbconn.createStatement();
                       
            String query;
            query = "INSERT INTO staff_kin_reg" +
     "(kinstaffNum,surname,fname,fone,kinaddress,relation)" +
      "VALUES('" + staff_id + "','" + sname + "','" + fname+ "','" + fone+ "','" + addres+ "','" +rela+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
    } 
    
    
    
    
    
    
     public void AddUsers(String userid,String pass,String status){
        dbconnector.databaseConnect();
        
        try {
            
           statement = dbconnector.dbconn.createStatement();
                       
            String query = "INSERT INTO Admin" +
            "(user_id,pass_word,status)" +
             "VALUES('" + userid + "','" + pass+ "','" + status  +"')";
             int result = statement.executeUpdate( query );
            
             statement.close();
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        finally{
        try{
            dbconnector.dbconn.close();
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
    }
    }
    
    
  /*****************************************************************************
   * Store Setup Image into database
   *****************************************************************************/
    
    public void Addsetup_Inform(String name2, String city, File log2, File id_frame, File back) {
        FileInputStream logo;
        FileInputStream front_id;
        FileInputStream back_id;
        dbconnector.databaseConnect();
        try {
            statement = dbconnector.dbconn.createStatement();
            logo = new FileInputStream(log2);
            front_id = new FileInputStream(id_frame);
            back_id = new FileInputStream(back);

            String sql = "INSERT INTO setup_tab (co_name,co_city,logo,front,back) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = dbconnector.dbconn.prepareStatement(sql);

            stmt.setString(1, name2);
            stmt.setString(2, city);
            stmt.setBinaryStream(3, (InputStream) logo, (int) log2.length());
            stmt.setBinaryStream(4, (InputStream) front_id, (int) id_frame.length());
            stmt.setBinaryStream(5, (InputStream) back_id, (int) back.length());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Information Submitted succssfully");

            stmt.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

}
     
 
   public void Update_setup_Inform(String name2,String city,File log2,File id_frame,File back)
   {
       FileInputStream logo;
       FileInputStream front_id;
       FileInputStream back_id;
       dbconnector.databaseConnect();
       try {
           statement = dbconnector.dbconn.createStatement();
           logo = new FileInputStream(log2);
           front_id = new FileInputStream(id_frame);
           back_id = new FileInputStream(back);
                                                  
           String query = "UPDATE setup_tab SET co_name=? ,co_city=? ,logo=? ,front=? ,back=?  WHERE sid =1";
           PreparedStatement stmt = dbconnector.dbconn.prepareStatement(query);

           stmt.setString(1, name2);
           stmt.setString(2, city);
           stmt.setBinaryStream(3, (InputStream) logo, (int) log2.length());
           stmt.setBinaryStream(4, (InputStream) front_id, (int) id_frame.length());
           stmt.setBinaryStream(5, (InputStream) back_id, (int) back.length());
           stmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Customization Information updated succssfully\n Click Exit Button and Restart the Software From Desktop.");

           stmt.close();

       } catch (Exception ex) {
           System.out.println(ex.getMessage());
       }

    }
    
    
  public void SaveClientBiometricInfor(String client_id,File img,File s2,File f3){
     FileInputStream pix;
     FileInputStream sign;
     FileInputStream finger;
     dbconnector.databaseConnect();
        try {
            statement = dbconnector.dbconn.createStatement();
            pix=new FileInputStream(img);
            sign=new FileInputStream(s2);
            finger=new FileInputStream(f3);
     //(client_id,picture_img,sign_img,finger_img)
String sql = "INSERT INTO client_biometric  (client_id,picture_img,sign_img,finger_img) VALUES (?,?,?,?)";
PreparedStatement stmt = dbconnector.dbconn.prepareStatement(sql);
//PreparedStatement Idstmt = dbconnector.dbconn.prepareStatement("SELECT IMG_ID FROM finger_image");
//stmt.setBinaryStream(1, be);
//stmt.setBinaryStream(1, f);
   // inp.read();
    //System.out.println(inp.toString());
   
      stmt.setString(1, client_id);
      //stmt.setBlob(smscount, sign, smscount);
      stmt.setBlob(2, (InputStream)pix, (int)img.length());
      stmt.setBlob(3, (InputStream)sign, (int)s2.length());
      stmt.setBlob(4, (InputStream)finger, (int)f3.length());
     // statement.setBinaryStream(2, (InputStream) inputStream, (int) (image.length())); 
       stmt.executeUpdate();

  JOptionPane.showMessageDialog(null,"Information Sent to succssfully  Thank u Jesus");
//Picks the ID generated for it. 
//           ResultSet rs = Idstmt.executeQuery();
         //  rs.next();
//System.out.println("Fingerprint enrolled with id = "+Integer.toString(rs.getInt(1)));
stmt.close();

}
catch(Exception ex) {
System.out.println(ex.getMessage());
}
}  
    
    
 
  /******************************************************************
   * Query Front and back ID card Framework
   * @param client_id
   * @return 
   *****************************************************************/
  
  public BiometricSet1 QueryFrontID_CardFrame(){
    BiometricSet1 front_id=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT front FROM setup_tab where sid='1'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           
            while (rs.next()) 
            {

                front_id.SetFront_pix(ImageIO.read(rs.getBinaryStream("front")));
                 
                //System.out.println("The Name of the Staff is "+rs.getString("firstname"));

            }


          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  front_id;
  }
  
  
  
  public BiometricSet1 QueryBackID_CardFrame(){
    BiometricSet1 back_id=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT back FROM setup_tab where sid='1'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           
           if (rs.next()) 
            {

                back_id.SetBack_pix(ImageIO.read(rs.getBinaryStream("back")));
                 
                //System.out.println("The Name of the Staff is "+rs.getString("firstname"));
             }


          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  back_id;
  }
  
  
  
  
  
  public BiometricSet1 QueryHome_Logo_Card(){
    BiometricSet1 home_id=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT co_name,co_city,logo FROM setup_tab where sid='1'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           
           if (rs.next()) 
            {
                home_id.SetCo_name(rs.getString("co_name"));
                home_id.SetCo_City(rs.getString("co_city"));
                home_id.SetBack_pix(ImageIO.read(rs.getBinaryStream("logo")));
                 
                //System.out.println("The Name of the Staff is "+rs.getString("firstname"));
             }


          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
           finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  home_id;
  }
   
    
    public BiometricSet1 QueryStudentsInfo(String client_id){
    BiometricSet1 bs=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT a.client_id, a.firstname,a.middlename,a.othernames,a.gender,a.faculty,a.department,a.level,a.session,b.client_id,a.b_group," +
                    "b.picture_link,b.signature_link,b.fingerprint_link FROM student_registration a,biometric b " +
                    "where a.client_id = b.client_id and b.client_id='"+client_id+"'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
            System.out.println("The Student's Surname starts here");

            while (rs.next()) {

            //System.out.println("The Student's Surname is "+bs.getStudentSurname());
                

                bs.StudSurname(rs.getString("firstname"));
                bs.StudMiddlename(rs.getString("middlename"));
                bs.studOname(rs.getString("othernames"));
                bs.Sex(rs.getString("gender"));bs.Fac(rs.getString("faculty"));
                bs.Depart(rs.getString("department"));bs.Level(rs.getString("level"));
                bs.SetSesion(rs.getString("session"));
                bs.SetBloodGrp(rs.getString("b_group"));
                        
                bs.SetPix(rs.getString("picture_link"));
                bs.SetSig(rs.getString("signature_link"));
                bs.SetFingerPrint(rs.getString("fingerprint_link"));
                
                System.out.println("The Student's Surname is "+bs.getStudentSurname());
                 System.out.println("The Student's Sig is "+bs.getSig());
                System.out.println("The Name of the Student is "+rs.getString("firstname"));



               }


             
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return bs;
  }
    
    
    
    
    public BiometricSet1 QueryStudentsDatabaseInfo(String client_id){
    BiometricSet1 bs=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT a.client_id, a.firstname,a.middlename,a.othernames,a.gender,a.faculty,a.department,a.level,a.session,a.b_group," +
                    "b.client_id,b.picture_img,b.sign_img,b.finger_img FROM student_registration a,client_biometric_infor b " +
                    "where a.client_id = b.client_id and b.client_id='"+client_id+"'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
            System.out.println("The Student's Surname starts here");

            while (rs.next()) {

            //System.out.println("The Student's Surname is "+bs.getStudentSurname());
                
                

                bs.StudSurname(rs.getString("firstname"));
                bs.StudMiddlename(rs.getString("middlename"));
                bs.studOname(rs.getString("othernames"));
                bs.Sex(rs.getString("gender"));bs.Fac(rs.getString("faculty"));
                bs.Depart(rs.getString("department"));bs.Level(rs.getString("level"));
                bs.SetSesion(rs.getString("session"));
                bs.SetBloodGrp(rs.getString("b_group"));
                        
                bs.SetBufferPix(ImageIO.read(rs.getBinaryStream("picture_img")));
                 bs.SetBufferSign(ImageIO.read(rs.getBinaryStream("sign_img")));
                 bs.SetBufferFinger(ImageIO.read(rs.getBinaryStream("finger_img")));
               
                
                System.out.println("The Student's Surname is "+bs.getStudentSurname());
                 System.out.println("The Student's Sig is "+bs.getSig());
                System.out.println("The Name of the Student is "+rs.getString("firstname"));



               }


             
          }catch ( IOException exc) {
              exc.printStackTrace();

            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
      catch (SQLException  exc) {
              exc.printStackTrace();

            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return bs;
  } 
    
    
    
    
    
    
    
    
    
    
    
    public BiometricSet1 RetriveImage(String staff_id)
    {
        BiometricSet1 bs=new BiometricSet1();
           dbconnector.databaseConnect();
    
     try{
         statement = dbconnector.dbconn.createStatement();
     
         String query = "SELECT client_id,picture_img,sign_img,finger_img FROM client_biometric_infor " +
                    "where client_id='"+staff_id+"'";
           ResultSet rs = statement.executeQuery(query);
          while (rs.next()) {

           
                InputStream is = rs.getBinaryStream("picture_img");
                BufferedImage image = ImageIO.read(is); //the image is read in
                 bs.SetBufferPix(image);
                 bs.SetBufferSign(ImageIO.read(rs.getBinaryStream("sign_img")));
                 bs.SetBufferFinger(ImageIO.read(rs.getBinaryStream("finger_img")));
                //store it back again as a file
               // ImageIO.write(image, "jpg", new FileOutputStream("recived" + count + ".jpg"));
                //count++;
            }
 
            System.out.println(" images saved on disk");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     return bs;
 }  
    
    

    public BiometricSet1 QueryStaffDatabaseInformation(String staff_id){
    BiometricSet1 staff=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT a.firstname, a.lastname,a.title,a.othernames,a.bdate,a.department,a.staffNum,a.b_group,a.designation," +
                    "b.client_id,b.picture_img,b.sign_img,b.finger_img FROM staff_registration a,client_biometric_infor b " +
                    "where b.client_id = a.staffNum and a.staffNum='"+staff_id+"'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

           
                 staff.FirstName(rs.getString("lastname"));
                 staff.StaffSurname(rs.getString("firstname"));
                  staff.SetTitle(rs.getString("title"));
                 staff.MiddleName(rs.getString("othernames"));
                 staff.SetDesign(rs.getString("designation"));
                 staff.SetBloodGrp(rs.getString("b_group"));
                 staff.Depart(rs.getString("department"));
                 staff.StaffNum(rs.getString("staffNum"));
                 staff.SetBufferPix(ImageIO.read(rs.getBinaryStream("picture_img")));
                 staff.SetBufferSign(ImageIO.read(rs.getBinaryStream("sign_img")));
                 staff.SetBufferFinger(ImageIO.read(rs.getBinaryStream("finger_img")));
                System.out.println("The Name of the Staff is "+rs.getString("firstname"));



               }


             
          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  staff;
  }
   
    
   public BiometricSet1 QueryStaffInformation(String staff_id){
    BiometricSet1 staff=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT a.firstname, a.lastname,a.othernames,a.bdate,a.department,a.staffNum,a.b_group,a.designation," +
                    "b.client_id,b.picture_link,b.signature_link,b.fingerprint_link FROM staff_registration a,biometric b " +
                    "where b.client_id = a.staffNum and a.staffNum='"+staff_id+"'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

           
                 staff.FirstName(rs.getString("lastname"));
                 staff.StaffSurname(rs.getString("firstname"));
                 staff.MiddleName(rs.getString("othernames"));
                 staff.SetDesign(rs.getString("designation"));
                 staff.SetBloodGrp(rs.getString("b_group"));
                 staff.Depart(rs.getString("department"));
                 staff.StaffNum(rs.getString("staffNum"));
                 staff.SetPix(rs.getString("picture_link"));
                 staff.SetSig(rs.getString("signature_link"));
                 staff.SetFingerPrint(rs.getString("fingerprint_link"));
                System.out.println("The Name of the Staff is "+rs.getString("firstname"));



               }


             
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  staff;
  } 
    
    
    
   
   
   public BiometricSet1 QueryStaffBasicInformation(String staff_id){
    BiometricSet1 dstaff=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0; ,c.staffNum=a.staffNum
          //,c.surname,c.fname,c.fone,c.relation,c.staffNum
          //,staff_kin_reg c
           statement = dbconnector.dbconn.createStatement();
            //String query = "SELECT a.firstname, a.lastname,a.othernames,a.address,a.phoneno,a.bdate,a.department,a.lga,a.State,a.staffNum,a.gender,a.b_group,a.designation," +
                 //   "b.client_id,b.picture_link FROM staff_registration a,client_biometric_infor b " +
                   // "where b.client_id = a.staffNum and a.staffNum='"+staff_id+"'";
           
           String query = "SELECT a.firstname,a.title, a.lastname,a.othernames,a.address,a.phoneno,a.bdate,a.department,a.lga,a.State,a.staffNum,a.gender,a.b_group,a.designation," +
                    "b.client_id,b.picture_img,c.surname,c.fname,c.fone,c.relation,c.kinaddress,c.kinstaffNum FROM staff_registration a,client_biometric_infor b,staff_kin_reg c " +
                    "where b.client_id = a.staffNum and c.kinstaffNum = a.staffNum and a.staffNum='"+staff_id+"'";
          
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

           
                 dstaff.StaffSurname(rs.getString("lastname"));
                 dstaff.FirstName(rs.getString("firstname"));
                 dstaff.SetTitle(rs.getString("title"));
                 dstaff.MiddleName(rs.getString("othernames"));
                 dstaff.SetDesign(rs.getString("designation"));
                 dstaff.SetBloodGrp(rs.getString("b_group"));
                 dstaff.Depart(rs.getString("department"));
                 dstaff.StaffNum(rs.getString("staffNum"));
                 dstaff.Address(rs.getString("address"));
                 dstaff.Sex(rs.getString("gender"));
                 dstaff.SetPhone(rs.getString("phoneno"));
                 dstaff.SetBdate(rs.getString("bdate"));
                 dstaff.SetLga(rs.getString("lga"));
                 dstaff.SetState(rs.getString("State"));
                // dstaff
                 dstaff.SetBufferPix(ImageIO.read(rs.getBinaryStream("picture_img")));
                 dstaff.SetFone(rs.getString("fone"));
                 dstaff.SetKinSname(rs.getString("surname"));
                 dstaff.SetKinFname(rs.getString("fname"));
                 dstaff.SetKinaddress(rs.getString("kinaddress"));
                 dstaff.SetRelation(rs.getString("relation"));
                 
                System.out.println("The Name of the Staff is "+rs.getString("firstname"));



               }


             
          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  dstaff;
  }  
   
   
   
  public BiometricSet1 QueryStaffBioID(String staff_id){
    BiometricSet1 ustaff=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
          String query = "SELECT b.bio_id,b.client_id FROM client_biometric_infor b where b.client_id='"+staff_id+"'";
          
          // String query = "SELECT a.staff_id,a.firstname, a.lastname,a.othernames,a.address,a.phoneno,a.bdate,a.department,a.lga,a.State,a.staffNum,a.gender,a.b_group,a.designation," +
              //   "c.kid,c.kinstaffNum FROM staff_registration a,client_biometric_infor b,staff_kin_reg c " +
               //   "where   a.staffNum='"+staff_id+"'";
        
                   
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

                 ustaff.SetSelectedStaffbioid(rs.getString("bio_id"));
                 ustaff.SetSelectedStudentbioid(rs.getString("bio_id"));


               }

                    

             
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,"Record Not Found"+exc);
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  ustaff;
  } 
  
   
   
   
   
   
   
   public BiometricSet1 QueryStaffNextofKinInformation(String staff_id){
    BiometricSet1 dstaff=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0; ,c.staffNum=a.staffNum
          //,c.surname,c.fname,c.fone,c.relation,c.staffNum
          //,staff_kin_reg c
           statement = dbconnector.dbconn.createStatement();
            //String query = "SELECT a.firstname, a.lastname,a.othernames,a.address,a.phoneno,a.bdate,a.department,a.lga,a.State,a.staffNum,a.gender,a.b_group,a.designation," +
                 //   "b.client_id,b.picture_link FROM staff_registration a,client_biometric_infor b " +
                   // "where b.client_id = a.staffNum and a.staffNum='"+staff_id+"'";
           
           String query = "SELECT * from staff_kin_reg where kinstaffNum ='"+staff_id+"'";
          
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

           
                
                
                 dstaff.SetPhone(rs.getString("fone"));
                 dstaff.SetKinSname(rs.getString("surname"));
                 dstaff.SetKinFname(rs.getString("fname"));
                 dstaff.SetKinaddress(rs.getString("kinaddress"));
                 dstaff.SetRelation(rs.getString("relation"));
                 
               // System.out.println("The Name of the Staff is "+rs.getString("firstname"));



               }


             
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,"Record Not Found");
            exc.printStackTrace();
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  dstaff;
  }  
   
   public BiometricSet1 QueryStudentOnlyBasicInformation(String student_mat){
    BiometricSet1 dstudent=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
            statement = dbconnector.dbconn.createStatement();
            
           String query = "SELECT * FROM student_registration where client_id='"+student_mat+"'";
          
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

           
                dstudent.StudSurname(rs.getString("firstname"));
                dstudent.StudMiddlename(rs.getString("middlename"));
                dstudent.studOname(rs.getString("othernames"));
                dstudent.Sex(rs.getString("gender"));
                dstudent.Fac(rs.getString("faculty"));
                dstudent.Depart(rs.getString("department"));
                dstudent.Level(rs.getString("level"));
                dstudent.SetSesion(rs.getString("session"));
                dstudent.SetBloodGrp(rs.getString("b_group"));
                dstudent.Address(rs.getString("address"));
                dstudent.SetFone(rs.getString("phoneno"));
                dstudent.Matric(rs.getString("client_id"));
                //dstudent.
                 
                 dstudent.SetBdate(rs.getString("bdate"));
                 dstudent.SetLga(rs.getString("lga"));
                 dstudent.SetState(rs.getString("State"));
               


               }


             
          }catch (SQLException  exc) {

            JOptionPane.showMessageDialog(null,"Record Not Found, there is a problem somwhere");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  dstudent;
  }  
   
   public BiometricSet1 QueryAllStudentInformation(String student_mat){
    BiometricSet1 dstudent=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
           statement = dbconnector.dbconn.createStatement();
           
           String query = "SELECT a.client_id, a.firstname,a.middlename,a.othernames,a.gender,a.faculty,a.department,a.level,a.session,a.address,a.phoneno,a.State,a.lga,a.b_group,a.bdate," +
                     "b.client_id,b.picture_img,c.surname,c.fname,c.fone,c.relation,c.kinaddress,c.kinstaffNum FROM student_registration a,client_biometric_infor b,staff_kin_reg c " +
                    "where a.client_id = b.client_id and c.kinstaffNum = a.client_id and a.client_id='"+student_mat+"'";
          
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

           
                dstudent.StudSurname(rs.getString("firstname"));
                dstudent.StudMiddlename(rs.getString("middlename"));
                dstudent.studOname(rs.getString("othernames"));
                dstudent.Sex(rs.getString("gender"));
                dstudent.Fac(rs.getString("faculty"));
                dstudent.Depart(rs.getString("department"));
                dstudent.Level(rs.getString("level"));
                dstudent.SetSesion(rs.getString("session"));
                dstudent.SetBloodGrp(rs.getString("b_group"));
                dstudent.Address(rs.getString("address"));
                dstudent.SetPhone(rs.getString("phoneno"));
                dstudent.Matric(rs.getString("client_id"));
                //dstudent.
                 
                 dstudent.SetBdate(rs.getString("bdate"));
                 dstudent.SetLga(rs.getString("lga"));
                 dstudent.SetState(rs.getString("State"));
               
                 dstudent.SetBufferPix(ImageIO.read(rs.getBinaryStream("picture_img")));
                 dstudent.SetFone(rs.getString("fone"));
                 dstudent.SetKinSname(rs.getString("surname"));
                 dstudent.SetKinFname(rs.getString("fname"));
                 dstudent.SetKinaddress(rs.getString("kinaddress"));
                 dstudent.SetRelation(rs.getString("relation"));
                 
              //  System.out.println("The Name of the Staff is "+rs.getString("firstname"));



               }


             
          }catch (SQLException  exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
       catch (IOException exc) {
           exc.printStackTrace();
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  dstudent;
  }
   
   
   public void UpdateEditable_Staff_Bio_Id(String sid,String Client_num) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE client_biometric_infor SET client_id = '" +Client_num +"' WHERE bio_id= '" + sid + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }    
   
   
   
   
   
   
    
    
    public void AddStaffInformationTodb(BiometricSet1 d,String sex,String bdate,String status){ 
         dbconnector.databaseConnect();
        try {
              
             statement = dbconnector.dbconn.createStatement();
             
            String query = "INSERT INTO staff_registration" +
            "(firstname,title,lastname,othernames,address,phoneno,bdate,department,lga,State,staffNum,gender,b_group,designation,status)" +
             "VALUES('" +d.getFirstName() + "','" +d.getTitle() + "','" + d.getStaffSurname()+ "','" + d.getMiddleName()+ "','" + d.getAddress() 
                    + "','" + d.getFone()+ "','" + bdate+ "','" + d.getDepart()+ "','" + d.getLga() + "','" + d.getState()+ "','" + d.getStaffNum()+ "','" + sex +"','" + d.getBloodgrp()+"','" + d.getDesign()+ "','" + status+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }
        
        catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        
    }
     public void UpdateStatus(BiometricSet1 d,String sex,String bdate,String status){ 
         dbconnector.databaseConnect();
        try {
              
             statement = dbconnector.dbconn.createStatement();
             
            String query = "INSERT INTO staff_registration" +
            "(firstname,lastname,othernames,address,phoneno,bdate,department,lga,State,staffNum,gender,b_group,designation,status)" +
             "VALUES('" +d.getFirstName() + "','" + d.getStaffSurname()+ "','" + d.getMiddleName()+ "','" + d.getAddress() 
                    + "','" + d.getFone()+ "','" + bdate+ "','" + d.getDepart()+ "','" + d.getLga() + "','" + d.getState()+ "','" + d.getStaffNum()+ "','" + sex +"','" + d.getBloodgrp()+"','" + d.getDesign()+ "','" + status+ "')";
             int result = statement.executeUpdate( query );
             statement.close();
        }
        
        catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        
    }
    
   
      
   
    
    
    
    
    
    
    
    
    public String[] queryListofLga(String state) 
    {
       dbconnector.databaseConnect();
       
          try {
                statement = dbconnector.dbconn.createStatement();

                String query = "SELECT * FROM lga_tab WHERE State = '" +state + "'";
                ResultSet rs = statement.executeQuery(query);
                int num = 0;
                num = CountLga(state);
                num = num + 1;
                String record[] = new String[num];
                record[0] = "Choose one";
                for (int i = 1; i < num; i++){
                    rs.next();
                    record[i] = (rs.getString(3));
                    if (i == num-1){
                        return record;
                    }
                }
                
                return record;

          }catch (SQLException exc) {
           
           JOptionPane.showMessageDialog(null,exc.toString( ));
            String[] record = {"No Information"};
            return record;
        }
        finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }
    
    } 
    
    
    public int CountLga(String stat) 
    {
       dbconnector.databaseConnect();
       
       try {
                 statement = dbconnector.dbconn.createStatement();
                String query = "SELECT * FROM lga_tab  WHERE State = '" +
                stat + "'";

                ResultSet rs1 = statement.executeQuery(query);
                int num = 0;
                String ans = "yes";
                while (ans.equals("yes")){
                    num ++;
                    if(rs1.next()){
                        ans = "yes";
                    }else{
                        ans = "n";
                        return num - 1; 
                    }
                }
                
                return 0;
          }catch (SQLException exc) {
          JOptionPane.showMessageDialog(null,exc.toString( ));
            return 0;
        }  
    }
    
    public Boolean CheckIfItemExist(String table,String field,String item)
      {
          boolean IsItemExist=false;
          dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = " select * FROM "+table+" where "+field+" ='"+item+"' " ;
              ResultSet rs = statement.executeQuery(query);
              if(rs.next())
              {
                  IsItemExist=true;
              }
              else
              {
                  IsItemExist=false;
              }
           
            //dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
          
           finally{
                 try{
                     dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                    // JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }
           

     return IsItemExist;



      }
    
    

 public boolean CheckExistingStudent(String matric){
       
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM student_registration WHERE client_id = '" +
            matric + "'";
            ResultSet rs1 = statement.executeQuery( query );


            if(!rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    }
   
    
    public boolean CheckExistingStaff(String staffid){
       
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM staff_registration WHERE staffNum = '" +
            staffid + "'";
            ResultSet rs1 = statement.executeQuery( query );


            if(!rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    }
    
    
    
    public boolean CheckExistingBiometric(String Client_id){
       
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT client_id FROM client_biometric_infor WHERE client_id = '" +
            Client_id + "'";
            ResultSet rs1 = statement.executeQuery( query );


            if(!rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
          
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    }
    
    
    
    public boolean CheckExistingUserID(String user_id){
       
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT user_id FROM Admin WHERE user_id = '" +
            user_id + "'";
            ResultSet rs1 = statement.executeQuery( query );


            if(!rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    } 
    
    
    
   public void DeleteUsers(String userid){
        dbconnector.databaseConnect();
        
        try {
            
           statement = dbconnector.dbconn.createStatement();
                       
            String query = "DELETE FROM Admin WHERE user_id = '" +
            userid + "'";
            int result = statement.executeUpdate(query);
            
             statement.close();
         JOptionPane.showMessageDialog(null,"user information deteted successfully");
        }catch ( SQLException sqlex ) {
                JOptionPane.showMessageDialog(null, sqlex.toString());
            
        }
        finally{
        try{
            dbconnector.dbconn.close();
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
    }
    }  
   /******************************************************
    * Check if setup exits
    * @param id
    * @param pass
    * @param sta
    * @return 
    */
    
     public boolean ChecksetupInform(){
        
     dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            
            String query = "SELECT * FROM setup_tab where sid='1'";
            
            ResultSet rs1 = statement.executeQuery( query );


            while (rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    } 
    
    
    
    
    
    
    
    
    
    
    
    
 public boolean CheckregisteredID(String id,String pass,String sta){
        
     dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM Admin WHERE user_id = '" +
            id + "'" + "AND pass_word = '" +pass + "' AND status = '" + sta + "'" ;
            ResultSet rs1 = statement.executeQuery( query );


            while (rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    } 
    
    
    public String myID(String userid,String pass,String sta){
       
        dbconnector.databaseConnect();
        try {
            
            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM Admin WHERE user_id = '" +
            userid + "'" + "AND pass_word = '" +pass + "' AND status = '" + sta + "'" ;
            ResultSet rs1 = statement.executeQuery( query );
            
           rs1.next();
           String id = rs1.getString (2);
           dbconnector.dbconn.close();
            return id;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return "";
        }
        
    } 
    
  
    
   public String Showmystatus(String userid){
       
        dbconnector.databaseConnect();
        try {
            
            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT status FROM Admin WHERE user_id = '" +
            userid + "'" ;
            ResultSet rs1 = statement.executeQuery( query );
            
           rs1.next();
           String id = rs1.getString("status");
           dbconnector.dbconn.close();
            return id;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return "";
        }
        
    }   
    
    
    
    
    
 public void updateAdmin(String userid,String pass,String status) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE Admin SET pass_word = '" + pass +"',status= '"+status+ "'" +
            "WHERE user_id = '" + userid + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   
    
 
 public void UpdateAllEditableStaffInformation(String id,String ti,String lname,String fname,String mname,String des,String de,String g,String fone,String add,String bdate,String lga,String sta,String sex,String snum) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE staff_registration SET lastname = '" +lname +"',firstname= '"+fname +"',othernames= '"+mname+"',designation= '"+des+"',department= '"+de+"',b_group= '"+
                    g+"',phoneno= '"+fone+"',address= '"+add+"',bdate= '"+bdate+"',lga= '"+lga+"',state= '"+sta+"',title= '"+ti+"',staffNum= '"+snum+"',gender= '"+sex+"'" + "WHERE staff_id= '" + id + "'";
            int result = statement.executeUpdate( query );
            
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   
 
 
 
   public void UpdateEditableStaffNextOfKinsInformation(String sid,String snum,String sname,String fname,String fone,String add,String rela) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE staff_kin_reg SET kinstaffNum = '" +snum +"',surname= '"+sname +"',fname= '"+fname+"',fone= '"+fone+"',kinaddress= '"+add+"',relation= '"+
                    rela+"' WHERE kid= '" + sid + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   








public void UpdateEditStudentBasicInformation(String id,String fname,String mname,String Oname,String fac,String depart,String lev,String sess,String bloodg,String clientid,String bdate,String add,String fone,String sex,String sta,String lga) {
        
        dbconnector.databaseConnect();
        
     //  client_id,firstname,middlename,othernames,,faculty,department,level,session,,,b_group,,,
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE student_registration SET firstname = '" +fname +"',middlename= '"+mname +"',othernames= '"+Oname+"',faculty= '"+fac+"',department= '"+depart
                    +"',level= '"+lev+"',session= '"+sess+"',client_id= '"+clientid+"',b_group= '"+bloodg+"',bdate= '"+bdate+"',address= '"+add+"',phoneno= '"+fone+"',gender= '"+sex+"',state= '"+sta+"',lga= '"+ lga+"'" + "WHERE student_id= '" + id + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }    
    
   
 
 public BiometricSet1 QueryAllStaffInformation(String staff_id){
    BiometricSet1 ustaff=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT * FROM staff_registration where staffNum='"+staff_id+"'";
                   
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           

            while (rs.next()) {

                ustaff.SetSelectedStaffid(rs.getString("staff_id"));
                 ustaff.StaffSurname(rs.getString("lastname"));
                 ustaff.SetTitle(rs.getString("title"));
                 ustaff.FirstName(rs.getString("firstname"));
                 ustaff.MiddleName(rs.getString("othernames"));
                 ustaff.SetDesign(rs.getString("designation"));
                 ustaff.SetBloodGrp(rs.getString("b_group"));
                 ustaff.Depart(rs.getString("department"));
                 ustaff.StaffNum(rs.getString("staffNum"));
                 ustaff.Address(rs.getString("address"));
                 ustaff.SetState(rs.getString("state"));
                 ustaff.SetLga(rs.getString("lga"));
                 ustaff.Sex(rs.getString("gender"));
                 ustaff.SetBdate(rs.getString("bdate"));
                 ustaff.SetFone(rs.getString("phoneno"));
                 ustaff.StaffNum(rs.getString("staffNum"));
                    // System.out.println("The Name of the Staff is "+rs.getString("firstname"));



               }


             
          }catch (SQLException exc) {

            JOptionPane.showMessageDialog(null,"Record Not Found"+exc);
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return  ustaff;
  } 
 
 
 
 
 public void StaffInformation(String id,String lname,String fname,String mname,String des,String depart,String bloodg,String staffid) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE staff_registration SET lastname = '" +lname +"',firstname= '"+fname +"',othernames= '"+mname+"',designation= '"+des+"',department= '"+depart+"',staffNum= '"+staffid+"',b_group= '"+
                    bloodg+"'" + "WHERE staff_id= '" + id + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   
 
 public void UpdateAllStaffInformation(BiometricSet1 bs) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE staff_registration SET lastname = '" +bs.getStaffSurname() +"',firstname= '"+bs.getFirstName() +"',title= '"+bs.getTitle() +"',othernames= '"+bs.getMiddleName()+"',designation= '"+bs.getDesign()+"',department= '"+bs.getDepart()+"',b_group= '"+
                    bs.getBloodgrp()+"',phoneno= '"+bs.getFone()+"',address= '"+bs.getAddress()+"',bdate= '"+bs.getBdate()+"',lga= '"+bs.getLga()+"',state= '"+bs.getState()+"',gender= '"+bs.getSex()+"'" + "WHERE staffNum= '" + bs.getStaffNum() + "'";
            int result = statement.executeUpdate( query );
            
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   
 public void UpdateStaffNextOfKinsInformation(BiometricSet1 bs) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE staff_kin_reg SET kinstaffNum = '" +bs.getStaffNum() +"',surname= '"+bs.getKinSname() +"',fname= '"+bs.getKinFname()+"',fone= '"+bs.getFone()+"',kinaddress= '"+bs.getKinAddress()+"',relation= '"+
                    bs.getRelation()+"' WHERE kinstaffNum= '" + bs.getStaffNum() + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   
 
 
 
 
 
 
 
 public BiometricSet1 updateStudentsIDcard(String client_id){
    BiometricSet1 ubs=new BiometricSet1();
           dbconnector.databaseConnect();
    
      try {
         // min=0;
           statement = dbconnector.dbconn.createStatement();
      String query = "SELECT client_id,firstname,middlename,othernames,gender,faculty,department,level,session,student_id,b_group FROM student_registration WHERE client_id='"+client_id+"'";
           ResultSet rs = statement.executeQuery(query);
            int num=0;
           // System.out.println("The Student's Surname starts here");

            while (rs.next()) {

                //ubs.SetSelectedStudentid(rs.getString("student_id"));
                ubs.SetSelectedStudentid(rs.getString("student_id"));
                ubs.StudSurname(rs.getString("firstname"));
                ubs.StudMiddlename(rs.getString("middlename"));
                ubs.studOname(rs.getString("othernames"));
                //bs.Sex(rs.getString("gender"));
                ubs.Fac(rs.getString("faculty"));
                ubs.Depart(rs.getString("department"));
                ubs.Level(rs.getString("level"));
                ubs.SetSesion(rs.getString("session"));
                ubs.SetBloodGrp(rs.getString("b_group"));
                        
                


               }


             
          }catch (SQLException  exc) {
              exc.printStackTrace();

            JOptionPane.showMessageDialog(null,"Record Not Found");
            exc.printStackTrace();
         }
    finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }

    return ubs;
  } 
 
 
 public void StudentInformation(String id,String fname,String mname,String Oname,String fac,String depart,String lev,String sess,String bloodg,String clientid) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE student_registration SET firstname = '" +fname +"',middlename= '"+mname +"',othernames= '"+Oname+"',faculty= '"+fac+"',department= '"+depart
                    +"',level= '"+lev+"',session= '"+sess+"',client_id= '"+clientid+"',b_group= '"+ bloodg+"'" + "WHERE student_id= '" + id + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }   
 
 public void UpdateAllStudentInformation(BiometricSet1 bs) {
        
        dbconnector.databaseConnect();
        
       
          try {
            statement = dbconnector.dbconn.createStatement();
            String query = "UPDATE student_registration SET firstname = '" +bs.getStudentSurname() +"',middlename= '"+bs.getStudMiddlename() +"',othernames= '"+bs.getStudentOname()+"',faculty= '"+bs.getFac()+"',department= '"+bs.getDepart()
                    +"',level= '"+bs.getLevel()+"',session= '"+bs.getSess()+"',b_group= '"+ bs.getBloodgrp()+"',address= '"+bs.getAddress()+"',phoneno= '"+bs.getFone()+"',gender= '"+ bs.getSex()+"',state= '"+ bs.getState()+"',lga= '"+ bs.getLga()+"',bdate= '"+ bs.getBdate()+"'" + "WHERE client_id= '" + bs.getMatric() + "'";
            int result = statement.executeUpdate( query );
             statement.close();                
           
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString());
        }  
    }
 public void queryStudentRegistrationList(){
   
          dbconnector.databaseConnect();
    row = new Vector ();   
      try {
          
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT *  FROM student_registration" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;
            while (rs.next()) {
                
                
                String Surname = rs.getString("firstname");
                String nam1=rs.getString("middlename");
                String nam2=rs.getString("othernames");
                String other=nam2+" "+nam1;
                String sex2=rs.getString("gender");
                String faculty=rs.getString("faculty");
                String depart=rs.getString("department");
                String lev=rs.getString("level");
                String session=rs.getString("session");
                String bgroup=rs.getString("b_group");
                String add=rs.getString("address");
                String fone=rs.getString("phoneno");
                String matric=rs.getString("client_id");
                String bdate=rs.getString("bdate");
                String lga=rs.getString("lga");
                String state=rs.getString("State");
                String status=rs.getString("status");
                if(status.equalsIgnoreCase("completed"))
                {
                    status="Complete";
                }
                else
                {
                    status="InComplete";
                }
                  
                
                String no2=Integer.toString(num);
                
                //String Tex = rs.getString (3).toUpperCase();
                       
               // String date1 =rs.getString (4); 
               // String status=rs.getString("status");
// "firstname","middlename","othernames","Matric Number","Faculty","Department","gender","level","session","address","phoneno","b_group","state","lga","bdate","Registration Status"   
                        
               
                record=new String[] {no2,Surname,other,matric,faculty,depart,lev,session,fone,status};
                //record=new String[] {Surname,nam1,nam2,matric,faculty,depart,sex2,lev,session,add,fone,bgroup,state,lga,bdate,status};
              
                row.addElement(record);
               num++;
                
               }
          
            
            dbconnector.dbconn.close();
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }  
  }
 
 
 
 
 
 
public void queryStaffRegistrationList(){
   
          dbconnector.databaseConnect();
    row = new Vector ();   
      try {
          
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT *  FROM staff_registration" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;
            while (rs.next()) {
                
               
                String Surname = rs.getString("lastname");
                String nam1=rs.getString("firstname");
                String nam2=rs.getString("othernames");
                String other=nam1+" "+nam2;
                String sex2=rs.getString("gender");
                String des=rs.getString("designation");
                String depart=rs.getString("department");
                 String bgroup=rs.getString("b_group");
                String add=rs.getString("address");
                String fone=rs.getString("phoneno");
                String staffNum=rs.getString("staffNum");
                String bdate=rs.getString("bdate");
                String lga=rs.getString("lga");
                String state=rs.getString("State");
                String status=rs.getString("status");
                 if(status.equalsIgnoreCase("completed"))
                {
                    status="Complete";
                }
                else
                {
                    status="InComplete";
                }
                String no2=Integer.toString(num);
                   
                record=new String[] {no2,Surname,other,staffNum,depart,des,bgroup,fone,status};
                
                row.addElement(record);
               
                num++;
               }
          
            
            dbconnector.dbconn.close();
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }  
  } 
 
 
 
 




public void queryOperatorsRegistrationList(){
   
          dbconnector.databaseConnect();
    row = new Vector ();   
      try {
          
           statement = dbconnector.dbconn.createStatement();
            String query = "SELECT *  FROM Admin" ;
           ResultSet rs = statement.executeQuery(query);
            int num=1;
            while (rs.next()) {
                
               
                String user = rs.getString("user_id");
                String stat=rs.getString("status");
                  
                String no2=Integer.toString(num);
                   
                record=new String[] {no2,user,stat};
                
                row.addElement(record);
               
                num++;
               }
          
            
            dbconnector.dbconn.close();
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,"Record Not Found");
         }  
  }
public void UpdateUserStatus(String table,String field,String status,String id_no)
      {
          
        dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = "update "+table+" set status='"+status+"' where "+field+" ='"+id_no+"' " ;
            statement.executeUpdate(query);
             
             
           
            //dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         }
          
           finally{
                 try{
                     dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                    // JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }
           

     



      }
public boolean CheckExistingNextofKin(String kin_Num){
       
        dbconnector.databaseConnect();
        try {

            statement = dbconnector.dbconn.createStatement();
            String query = "SELECT kinstaffNum FROM staff_kin_reg WHERE kinstaffNum = '" +
            kin_Num + "'";
            ResultSet rs1 = statement.executeQuery( query );


            if(!rs1.next()) {
                return true;
            }

          dbconnector.dbconn.close();
          
           return false;
            
        }catch (SQLException exc){
            JOptionPane.showMessageDialog (null,exc.toString());
            return false;
        }
        
    } 
 public String[] queryListofDeparts(String college) 
    {
       dbconnector.databaseConnect();
       
          try {
                statement = dbconnector.dbconn.createStatement();

                String query = "SELECT * FROM depart_tab WHERE collage = '" +college + "'";
                ResultSet rs = statement.executeQuery(query);
                int num = 0;
                num = CountDeparts(college);
                num = num + 1;
                String record[] = new String[num];
                record[0] = "Choose one";
                for (int i = 1; i < num; i++){
                    rs.next();
                    record[i] = (rs.getString(3));
                    if (i == num-1){
                        return record;
                    }
                }
                
                return record;

          }catch (SQLException exc) {
           
           JOptionPane.showMessageDialog(null,exc.toString( ));
            String[] record = {"No Information"};
            return record;
        }
        finally{
                 try{
                      dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }
    
    } 
 public int CountDeparts(String col) 
    {
       dbconnector.databaseConnect();
       
       try {
                 statement = dbconnector.dbconn.createStatement();
                String query = "SELECT * FROM depart_tab  WHERE collage = '" +
                col + "'";

                ResultSet rs1 = statement.executeQuery(query);
                int num = 0;
                String ans = "yes";
                while (ans.equals("yes")){
                    num ++;
                    if(rs1.next()){
                        ans = "yes";
                    }else{
                        ans = "n";
                        return num - 1; 
                    }
                }
                
                return 0;
          }catch (SQLException exc) {
          JOptionPane.showMessageDialog(null,exc.toString( ));
            return 0;
        }  
    }
    
    


  public String RetrieveItem(String table,String field,String item,String Col)
      {
          String Item="";
          //if(this.CheckIfItemExist(table, field, item) || this.CheckExistingSNOS(item))
         // {
             dbconnector.databaseConnect();
       // row = new Vector ();
        
      try {
            statement = dbconnector.dbconn.createStatement();
            
            String query = " select "+Col+" FROM "+table+" where "+field+" ='"+item+"' " ;
              ResultSet rs = statement.executeQuery(query);
              //rs.updateBinaryStream(Item, null);
              if(rs.next())
              {
                  Item=rs.getString(Col);
              }
              else
              {
                  //IsItemExist=false;
              }
           
            //dbconnector.dbconn.close();
            
          }catch (SQLException exc) {
            
            JOptionPane.showMessageDialog(null,exc.toString( ));
            
         } 
      finally{
                 try{
                     dbconnector.dbconn.close();
                      //JOptionPane.showMessageDialog(null,"dbcp connection has been closed");
                 }

                 catch (SQLException ex) {
                     ex.printStackTrace();
                    // JOptionPane.showMessageDialog(null,ex.getMessage());
                 }
             }
          //}
          //Item="No Item";
           
     return Item;
          
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
   
dbconnector.databaseConnect(); 
//statement = connection .prepareStatement("insert into trn_imgs(img_title, img_data) " + "values(?,?)"); 
//String sql = "INSERT INTO client_biometric  (client_id,picture_img,sign_img,finger_img) VALUES (?,?,?,?)";
statement =dbconnector.dbconn.prepareStatement("INSERT INTO client_biometric_infor  (client_id,picture_img,sign_img,finger_img) " + "VALUES (?,?,?,?)");
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
//JOptionPane.showMessageDialog(null, "Information was sent to database successfully");

} //catch (FileNotFoundException e) { 
//System.out.println("FileNotFoundException: - " + e); 
//} 
//catch (IOException e) { 
//System.out.println("FileNotFoundException: -  Image can be write" + e); 
//} 
catch (SQLException e) { 
    System.out.println("SQLException: - " + e); 
    e.printStackTrace();
} finally { 
try {
    statement.close();
 dbconnector.dbconn.close(); 

} 
catch (SQLException e) { 
System.out.println("SQLException Finally: - " + e); 
} 
} 
} 
  public void UpdateBiometricImageInfor(int pos,String field,String client_id,byte [] img){
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
 
    pix =new ByteArrayInputStream(img); 
    //this.s
    
            //new ByteArrayInputStream(pixbyte); 
   dbconnector.databaseConnect(); 
    String query = " select * FROM client_biometric_infor where client_id ='"+client_id+"' " ;
 // statement=dbconnector.dbconn .prepareStatement("update client_biometric_infor SET  "+field+"=? where client_id=?");
    statement=dbconnector.dbconn .prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
   //statement.

              //ResultSet rs = statement.executeQuery(query);
         ResultSet rs = (ResultSet) statement.executeQuery();
              
              //ResultSet.CONCUR_UPDATABLE;
              //rs.updateBinaryStream(Item, null);
  
              if(rs.next())
              {
                  System.out.println("Blob Information was successfully selected");
                 //JOptionPane.showMessageDialog(null, "Blob Information was successfully selected");
              }
              else
              {
                  //IsItemExist=false;
              }
              
//statement = connection .prepareStatement("insert into trn_imgs(img_title, img_data) " + "values(?,?)"); 
//String sql = "INSERT INTO client_biometric  (client_id,picture_img,sign_img,finger_img) VALUES (?,?,?,?)";
//statement = dbconnector.dbconn.prepareStatement("UPDATE client_biometric_infor  (client_id,picture_img,sign_img,finger_img) " + "VALUES (?,?,?,?)");
 
//rs.updateBinaryStream(Item, null);
 rs.updateBinaryStream(field, pix, img.length);
 //rs.updateBlob(field, pix);
 rs.updateRow();//commit change
//statement.setBinaryStream(2, (InputStream) inputStream, (int) (image.length())); 
//statement.setBinaryStream(2, (InputStream)pix, pixbyte.length);
//statement.setBinaryStream(3, (InputStream)sign, sigbyte.length);
//statement.setBinaryStream(4, (InputStream)finger, finbyte.length);
//statement.setString(1,client_id);
//statement.setBlob(pos, pix);
//statement.setBinaryStream(pos,pix,img.length);
//statement.executeUpdate();
//statement.

//statement.executeUpdate();
//System.out.println("Thank u Jesus Christ it is working");
//JOptionPane.showMessageDialog(null,"Information Sent to succssfully  Thank u Jesus !!!!!!!!");
//JOptionPane.showMessageDialog(null, "Blob Information was successfully updated");
 
 System.out.println("Blob Information was successfully updated");

} //catch (FileNotFoundException e) { 
//System.out.println("FileNotFoundException: - " + e); 
//} 
//catch (IOException e) { 
//System.out.println("FileNotFoundException: -  Image can be write" + e); 
//} 
catch (SQLException e) { 
    System.out.println("SQLException: - " + e); 
    e.printStackTrace();
} finally { 
try {
    statement.close();
 dbconnector.dbconn.close(); 

} 
catch (SQLException e) { 
System.out.println("SQLException Finally: - " + e); 
} 
} 
} 
 
 
 
 public Vector getRows (){
       return row;
   }  
 
 
 
 
    public static void main(String args[])
{
    StaffBasicRegSummary sbs=new StaffBasicRegSummary("SP 003");
    //new Reconsoft().QueryStudentOnlyBasicInformation("2013/1111");
    sbs.setVisible(true);
}

}
   
