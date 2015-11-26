/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class StudentCapturedInformation extends JFrame {
    
    //private  String pix="";
    //private  String sign="";
    //private String finger="";
      private BiometricSet1 bs=null;
    
     private BufferedImage pix=null;
      private  BufferedImage sign=null;
      private BufferedImage finger=null;
      private Boolean Iswritten=false;
    
    private String bioinfo[]=BiometricMain.getDB_Biodata();
    private  BufferedImage display_Captured[]=BiometricMain.getCaptured_Biodata();
    private JPanel ImagePane;
    public JPanel studentPane;
    private  File pixpath ;
    private  File sigpath;
    private  File finpath;
    private String reg;
    private String InfoLogged="";
      
   Reconsoft data=new Reconsoft();
   //Reconsoft2 dat2=new Reconsoft2();
    public StudentCapturedInformation ()
    {
        if(ImageCapturing.isWebcamFirst)
        {
             pix=display_Captured[0];
             sign=display_Captured[1];
             finger=display_Captured[2];
             
             System.out.println("The pix at webcam is stored on index 0:"); 
             System.out.println("The sig at is stored on index 1");
           
             //System.out.println("The pix at webcam first link is:"+pix);
             //System.out.println("The sig at webcam first link is:"+sign);
             //System.out.println("The finger print 1 link is:"+finger);
        }
        else if(!ImageCapturing.isWebcamFirst)
        {
             pix=display_Captured[1];
             sign=display_Captured[0];
             finger=display_Captured[2];
             
             System.out.println("The pix at webcam is stored on index 1:"); 
             System.out.println("The sig at is stored on index 0");
           
            // System.out.println("The pix at sig first link is:"+pix);
             //System.out.println("The sig sig first link is:"+sign);
            // System.out.println("The finger print 2 link is:"+finger);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Biometric Information not Captured");
          // pix="img_1376906652231.jpg";
           // sign="sig_1226180967083.jpg";
            //finger="img_1377100513559.jpg";
        }
        bs=data.RetriveImage(bioinfo[2]);
    }
   
    public void WriteRegToSmartCard(String reg){
         Iswritten=false;
        AfamSmartCard jsc=new AfamSmartCard();
        
            byte id=(byte)0x02;
            byte DATA=(byte)0xF0;
            if(jsc.IsCardPresent())
            {
                if(jsc.Format_PersonalizeSmartCard()){
               int lent=0;
               if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
               {
                   lent=9;
               }
               else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               {
                   lent=6;
               }
             //reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No to write to \nyour SmartIDCard for Personalization");
           if(!reg.equals(""))
           {
               if(reg.length()!=lent){
                   if(lent==6){
                        javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Staff No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                   }
                   else if(lent==9)
                   {
                       javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Matric. No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                   }
                   
                    
               }
               else
               {
                   jsc.ConnectToSmartCard();
                   jsc.WriteToSmartCard(DATA, id,reg);
                   Iswritten=true;
                   
                   JOptionPane.showMessageDialog(null,"Your Information was successfully written to ACS ACOS SMARTCARD \n"+"Thank you");
               }
               
           }
           else
           {
               javax.swing.JOptionPane.showMessageDialog(null, "You have NOT entered anything\n"+"Thank you.");
               return;
           }
                  
            }
                else if(jsc.IsSmartCardFormated_Personalized() && (jsc.ReadFromSmartCard(DATA, id,9).matches("SP 139") ||jsc.ReadFromSmartCard(DATA, id,9).matches("0262/2009")))
                {
                    
                    
                    int lent=0;
               if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
               {
                   lent=9;
               }
               else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               {
                   lent=6;
               }
             //reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No to write to \nyour SmartIDCard for Personalization");
           if(!reg.equals(""))
           {
               if(reg.length()!=lent){
                   if(lent==6){
                        javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Staff No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                   }
                   else if(lent==9)
                   {
                       javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Matric. No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                   }
                   
                    
               }
               else
               {
                    jsc.ConnectToSmartCard();
                  // jsc.WriteToSmartCard(DATA, id,reg);
                   JOptionPane.showMessageDialog(null,"This ACS ACOS SMARTCARD has been registered ALREADY \n"+"Thank you");
                   //Iswritten=true;
               }
               
           }
           else
           {
               javax.swing.JOptionPane.showMessageDialog(null, "You have NOT entered anything\n"+"Thank you.");
               return;
           }
                    
                    
                }
           
        
        
        else
        {
            JOptionPane.showMessageDialog(null,"Your SmartCard is either tampered with or damgaged OR NOT SUPPORTED:Please Insert a good one\nThank you\nBye!!!!!");
             return;
        }
    }
         else
            {
                    JOptionPane.showMessageDialog(null,"SmartCard is Absent:Please Insert your card in the Reader\nBye!!!!!");
           //System.exit(1);
                return;
         }
       
       
    //String info=jsc.ReadFromSmartCard(DATA, id);
       
    //JOptionPane.showMessageDialog(null,"The red Info is:\n"+info);
    }

    public void AddStudentsBiometricInfo()
    {
        
        
        // File imagepath = new File("");
        // System.out.println(this.CheckNewRegistration());
      
       /*********************************************
      // fill i the background image for display
     **********************************************/ 
        // final ImageIcon icon = new ImageIcon(getClass().getResource("/ADCS_PROJECT/mbg3.png"));
         ImagePane = new JPanel();//{			
           // protected void paintComponent(Graphics g){			
           // g.drawImage(icon.getImage(), 0,0, null);
           // super.paintComponent(g);
            //super.paintComponent(g);
            
        //}	
        //};
        ImagePane.setLayout(null);
        
        ImagePane.setOpaque(false);
        
              reg=bioinfo[2];
              
               JLabel conf = new JLabel("STUDENT BIOMETRIC SUMMARY INFORMATION" );
	           conf.setBounds( 40,30,450, 20 );
                conf.setForeground(Color.BLUE);
                conf.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(conf);
                 
                 JLabel id = new JLabel("Student Matric/NO:" );
	           id.setBounds( 50,90,200, 20 );
                id.setForeground(Color.BLUE);
                id.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(id);
                 
                 JLabel id2 = new JLabel(bioinfo[2]);
		 id2.setBounds( 210, 90, 150, 20 );
                id2.setForeground(Color.BLUE);
                id2.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(id2);
                 
                JLabel pass = new JLabel("PASSPORT:" );
		 pass.setBounds( 20, 200, 150, 20 );
                pass.setForeground(Color.BLUE);
                pass.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(pass);
                 
              /*********************************************
              // fill i the background image for display
              // Fill in the path to an existing image
               // \\src\\adcs_project\\std_pix\\img"   sig_1373555799433.jpg
              **********************************************/ 
                 
             //pixpath = new File(imagepath.getAbsolutePath()+"\\src\\adcs_project\\std_pix\\"+pix);    
                 
              // ImageIcon icon3 = new ImageIcon(getClass().getResource("/adcs_project/std_pix/"+pix));  
                 
                        JLabel label = new JLabel();
                        label.setBounds( 150,125,270,240 );
                       if(pix==null){
                            pix=bs.getBufferedPix();
                        }
                        label.setIcon(new ImageIcon(pix));
                       //label.setIcon(icon3);
                      ImagePane.add(label);
                 
                 
                      
                 JLabel signa = new JLabel("SIGNATURE:" );
		 signa.setBounds( 20,440, 150, 20 );
                signa.setForeground(Color.BLUE);
                signa.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(signa);
                 
                 
    //sigpath = new File(imagepath.getAbsolutePath()+"\\src\\adcs_project\\std_sig\\"+sign);
                
      //ImageIcon sigicon = new ImageIcon(getClass().getResource("/ADCS_PROJECT/std_sig/"+sign)); 
          //"sig_1373987334932.jpg"
                 JLabel sig= new JLabel();
		 sig.setBounds( 150,420, 200,70 );
                 if(sign==null){
                            sign=bs.getBufferedSig();
                        }
                  sig.setIcon(new ImageIcon(sign));
                 //sig.setIcon(sigicon);
		 ImagePane.add(sig);
                 
                 
                
                 JLabel fingerLabel = new JLabel("FINGERPRINT:" );
		 fingerLabel.setBounds( 20,530, 150, 20 );
                fingerLabel.setForeground(Color.BLUE);
                fingerLabel.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(fingerLabel);
          
        //   ImageIcon finicon = new ImageIcon(imagepath.getAbsolutePath()+"\\src\\adcs_project\\finger_img\\"+finger);
                 
           // finpath = new File(imagepath.getAbsolutePath()+"\\src\\adcs_project\\finger_img\\"+finger);
                 
      // ImageIcon finicon = new ImageIcon(getClass().getResource("/adcs_project/finger_img/"+finger));  
                 JLabel fin= new JLabel( );
		 fin.setBounds( 150,480,250,120 );
                 if(finger==null){
                            finger=bs.getBufferedFingerPrint();
                        }
                 fin.setIcon(new ImageIcon(finger));
		 ImagePane.add(fin);
                 
                 //sig_1373555799433.jpg printtest.bmp
                 
                 JButton subB= new JButton("Complete All Capturing");
		 subB.setBounds( 50,580,220, 20 );
                subB.setForeground(Color.BLUE);
                subB.setFont(new Font("Verdana",Font.BOLD,12));
                 subB.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
         // Reconsoft rs=new Reconsoft();
          //TestConnection test = new TestConnection();
          WriteRegToSmartCard(bioinfo[2]);
          if(Iswritten==true)
          {
        // test.SaveClientBiometricInfor(bioinfo[2],convertPixtobyte(pix), convertPixtobyte(sign),convertPixtobyte(finger));
           
    
          //rs.AddStudentBiometricInfor(bioinfo[2], pix, sign,finger);
           //test.SaveClientBiometricInfor(bioinfo[2], pixpath, sigpath, finpath);
         // test.SaveClientBiometricInfor(bioinfo[2], pix, sign,finger);
      // System.out.println(finger);
       //System.out.println(pixpath);
      // System.out.println(sigpath);
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "COMPLETED THE OVERALL REGISTRATION  OF BASIC AND BIOMETRIC INFORMATION OF STUDENT WITH MATRIC. NO: "+reg);
       AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged); 
      // System.out.println(finger);
      // System.out.println("Information was sent to database successfully");
       JOptionPane.showMessageDialog(null, "Please Close the page or click Ok to Print your ID");
        PrintableStudentidcard();
          data.UpdateUserStatus("student_registration", "client_id", "Completed", reg);
         // dat2.UpdateUserStatus("student_registration", "client_id", "Completed", reg);
        
         InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO PRINT THE SMART IDCARD IMMEDIATELY AFTER COMPLETING THE REGISTRATION OF STUDENT WITH MATRIC. NO: "+bioinfo[2]);
       AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
          }
          else
          {
              JOptionPane.showMessageDialog(null, "Please fix the problem you encountered with formatting or writing in your SmartCard ID\n, before finally saving your biometric data to database\nThank you");
            
               }
               }
              
               });
		 ImagePane.add(subB);
                 
                 JButton exitB= new JButton("Exit");
		 exitB.setBounds(320,580, 100, 20 );
                exitB.setForeground(Color.BLUE);
                exitB.setFont(new Font("Verdana",Font.BOLD,12));
                
                exitB.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          // System.out.println("Thank you, about to close");
            BiometricMain.getStudObj().setVisible(false);
            
            
           
    
        }
    });
		// ImagePane.add(exitB);
      
                 
                 
       // this.BioInform();
       studentPane = new JPanel();
        studentPane.setLayout(new BorderLayout());
       // getContentPane().add(staffidPane);
        
         studentPane.add(ImagePane,BorderLayout.CENTER );
         JFrame student = new JFrame();
         student.add(studentPane);
         student.setSize(500,650);
         student.setLocationRelativeTo(null);
         student.setVisible(true);
         student.setResizable(false);
        
       //  this.setSize(450,500);
        
        
       
                  
                 
                 
    } 
   
  
        
        
          
 public byte [] convertPixtobyte(BufferedImage m)
   {
      // byte [] pixbyte= new byte[5000];
       //int byteread=0;
      // ByteArrayOutputStream pixout = new ByteArrayOutputStream();
       
     try{
       ByteArrayOutputStream pixout = new ByteArrayOutputStream();
    ImageIO.write(m,"png", pixout);
    byte [] pixbyte=pixout.toByteArray();
  //ByteArrayInputStream  pix =new ByteArrayInputStream(pixout.toByteArray(),0,pixout.size()); 
  return pixbyte;
       }catch(IOException n){
           n.printStackTrace();
           return null;
       }
       
   }
    




 public boolean  CheckNewRegistration()
   {
       String id =bioinfo[2];
               //"555";
               //bioinfo[2];
        if(id==null || id.equals(""))
        {
         return true;   
        }else
        {
          return false;  
        }
       
       }
   
   
   
   public boolean  CheckwebcamImage()
   {
       if(pix==null || pix.equals(""))
       {
           return true;
       }else
       {
           return false;
       }
   }
   
   public boolean  CheckSignatureImage()
   {
       if(sign==null || sign.equals(""))
       {
           return true;
       }else 
       {
           return false;
       }
   }
   
   public Boolean  CheckfingerImage()
   {
       if(finger ==null || finger.equals(""))
       {
           return true;
       }else
       {
          return false; 
       }
   }
   
   
   
   
   

   
  /*
    public static void main(String[] args) {
        // TODO code application logic here
  StudentCapturedInformation sta = new StudentCapturedInformation();
  sta.AddStudentsBiometricInfo();
        //staff.setResizable(false);
     // staff.setLocationRelativeTo(null);
       // staff.setVisible(true); 
        
         
     }*/
    
   

   
   
   
    public void PrintableStudentidcard()
    {
       PrintID  b = new PrintID  (reg);
        b.setResizable(false);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.setResizable(false);
        ScalePrintableFormat test = new ScalePrintableFormat();
        test.printCard(b.idcardPan);  
    }
    
    
}
   