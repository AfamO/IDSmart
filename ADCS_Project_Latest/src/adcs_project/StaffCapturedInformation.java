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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class StaffCapturedInformation extends JFrame {
    public JPanel staffidPane;
     private JPanel ImagePane;
     private BufferedImage img=null;
      private  BufferedImage sign=null;
      private BufferedImage finger=null;
      public boolean checkcapure=false;
      private String StaffNum[]=BiometricMain.getDB_Biodata();
      private  BufferedImage display_Captured[]=BiometricMain.getCaptured_Biodata();
      private  File pixpath ;
      private  File sigpath;
      private  File finpath;
      private String staff_id;
      Boolean Iswritten=false;
      private String InfoLogged="";
      private BiometricSet1 bs=null;
      Reconsoft data=new Reconsoft();
     // Reconsoft2 dat2=new Reconsoft2();
      //private String staff_id="";
      
    StaffCapturedInformation(){
        
         setIconImage(new ImageIcon(getClass().getResource("see.jpg")).getImage()); 
      
        if(ImageCapturing.isWebcamFirst)
        {
            staff_id=StaffNum[2];
             img=display_Captured[0];
            sign=display_Captured[1];
            finger=display_Captured[2];
             System.out.println("The pix at webcam is stored on index 0:"); 
             System.out.println("The sig at is stored on index 1");
            // System.out.println("The finger print 1 link is:"+finger);
             //Test1373887906467.jpg
        }
        else if(!ImageCapturing.isWebcamFirst)
        {
            staff_id=StaffNum[2];
             img=display_Captured[1];
             sign=display_Captured[0];
            finger=display_Captured[2];
             System.out.println("The pix at webcam is stored on index 0:"); 
             System.out.println("The sig at is stored on index 1");
         // System.out.println("The finger print 2 link is:"+finger);
        }
        else
        {
            //img="img_1376906652231.jpg";
            //sign="sig_1226180967083.jpg";
            //finger="img_1377100513559.jpg";
            JOptionPane.showMessageDialog(null, "Biometric Information not Captured");
        }
        bs=data.RetriveImage(staff_id);
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

    
   public void BioInform(){
        
       
       String staff_id=StaffNum[2];
       if(StaffNum[2]==null)
       {
           StaffNum[2]=AdminLogin.student_staffid;
       }
       //File imagepath = new File("");
       
       
       
     System.out.println(this.CheckNewRegistration());
      
       /*********************************************
      // fill i the background image for display
     **********************************************/ 
        // final ImageIcon icon = new ImageIcon(getClass().getResource("/ADCS_PROJECT/mbg2.png"));
         ImagePane = new JPanel();//{			
           // protected void paintComponent(Graphics g){			
            //g.drawImage(icon.getImage(), 0,0, null);
            //super.paintComponent(g);
           
            
      // }	
      //};
      ImagePane.setLayout(null);
        
      ImagePane.setOpaque(false);
        
         
               JLabel conf = new JLabel("STAFF BIOMETRIC SUMMARY INFORMATION" );
	           conf.setBounds(40,30,450, 30 );
                conf.setForeground(Color.BLUE);
                conf.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(conf);
                 
                 JLabel id = new JLabel("Staff_id:" );
	           id.setBounds( 50,90,100, 20 );
                id.setForeground(Color.BLUE);
                id.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(id);
                 
                 JLabel id2 = new JLabel(StaffNum[2]);
		 id2.setBounds( 120, 90, 100, 20 );
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
         // pixpath = new File(imagepath.getAbsolutePath()+"\\src\\adcs_project\\std_pix\\"+img);
            
              // ImageIcon icon3 = new ImageIcon(getClass().getResource("/adcs_project/std_pix/"+img));  
                 
                        JLabel label = new JLabel();
                        label.setBounds( 120,125,270,240 );
                        if(img==null){
                            img=bs.getBufferedPix();
                        }
                        label.setIcon(new ImageIcon(img));
                       //label.setIcon(icon3);
                        ImagePane.add(label);
                 
                 
                      
                 JLabel signa = new JLabel("SIGNATURE:" );
		 signa.setBounds( 20,440, 150, 20 );
                signa.setForeground(Color.BLUE);
                signa.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(signa);
                 
                 
   // sigpath = new File(imagepath.getAbsolutePath()+"\\src\\adcs_project\\std_sig\\"+sign);
                
     // ImageIcon sigicon = new ImageIcon(getClass().getResource("/ADCS_PROJECT/std_sig/"+sign)); 
          //"sig_1373987334932.jpg"
                 JLabel sig= new JLabel();
		 sig.setBounds( 150,420, 200,80 );
                  if(sign==null){
                            sign=bs.getBufferedSig();
                        }
                  sig.setIcon(new ImageIcon(sign));
                // sig.setIcon(sigicon);
		 ImagePane.add(sig);
                 
                 
                
                 JLabel fingerLabel = new JLabel("FINGERPRINT:" );
		 fingerLabel.setBounds( 20,530, 150, 20 );
                fingerLabel.setForeground(Color.BLUE);
                fingerLabel.setFont(new Font("Verdana",Font.BOLD,14));
		 ImagePane.add(fingerLabel);
                 
         //finpath = new File(imagepath.getAbsolutePath()+"\\src\\adcs_project\\finger_img\\"+finger);
          
        //   ImageIcon finicon = new ImageIcon(imagepath.getAbsolutePath()+"\\src\\adcs_project\\finger_img\\"+finger); 
             
                 
      // ImageIcon finicon = new ImageIcon(getClass().getResource("/adcs_project/finger_img/"+finger));  
                 JLabel fin= new JLabel( );
		 fin.setBounds( 150, 480,200,100 );
                 if(finger==null){
                            finger=bs.getBufferedFingerPrint();
                        }
                 fin.setIcon(new ImageIcon(finger));
                // fin.setIcon(finicon);
		 ImagePane.add(fin);
                 
                 //sig_1373555799433.jpg printtest.bmp
                 
                 JButton subB= new JButton("Complete All Capturing");
		 subB.setBounds( 50,580, 220, 20 );
                subB.setForeground(Color.BLUE);
                subB.setFont(new Font("Verdana",Font.BOLD,12));
                 subB.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                   WriteRegToSmartCard(StaffNum[2]);
          //Reconsoft rs=new Reconsoft();
         // TestConnection test = new TestConnection();
         
          
         // rs.AddStudentBiometricInfor(StaffNum[2],img, sign,finger);
          //rs.SaveClientBiometricInfor(StaffNum[2], pixpath,sigpath,finpath);
         // rs.SaveClientBiometricInfor("3333", pixpath,sigpath,finpath);
          
        // test.SaveClientBiometricInfor(StaffNum[2], img, sign,finger);
          if(Iswritten)
              {
         //test.SaveClientBiometricInfor(StaffNum[2],convertPixtobyte(img), convertPixtobyte(sign),convertPixtobyte(finger));
      // System.out.println(finger);
       //System.out.println(pixpath);
       //System.out.println(sigpath);
     //  System.out.println("Information was sent to database successfully");
          InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "COMPLETED THE OVERALL REGISTRATION  OF BASIC AND BIOMETRIC INFORMATION  OF STAFF WITH STAFF ID:"+StaffNum[2]);
               AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
       JOptionPane.showMessageDialog(null, "Please Close the page OR click Ok to Print your ID");
            Printablestaffidcard();
            data.UpdateUserStatus("staff_registration", "staffNum", "Completed", StaffNum[2]);
           // dat2.UpdateUserStatus("staff_registration", "staffNum", "Completed", StaffNum[2]);

            InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO PRINT THE SMART IDCARD IMMEDIATELY AFTER COMPLETING THE REGISTRATION OF STUDENT WITH MATRIC. NO: "+StaffNum[2]);
       AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
          }
          else if(!Iswritten)
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
                
            
          //BiometricMain.stci.setVisible(false);
           BiometricMain.stci.addWindowListener(new java.awt.event.WindowAdapter() {
            
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
           //BiometricMain.capvis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        }
    });
		// ImagePane.add(exitB);
      
                 
                 
       // this.BioInform();
       staffidPane = new JPanel();
        staffidPane.setLayout(new BorderLayout());
       // getContentPane().add(staffidPane);
        
         staffidPane.add(ImagePane,BorderLayout.CENTER );
         JFrame staff = new JFrame();
         staff.add(staffidPane);
         staff.setSize(500,650);
         staff.setLocationRelativeTo(null);
        staff.setVisible(true); 
        staff.setResizable(false);
        
       //  this.setSize(450,500);
                  
                 
                 
    } 
   private void formWindowClosing(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
       // JOptionPane.showMessageDialog(null,"Thank you for Closing me\n\n     Bye!!!");
        
    }
   
   
  
   
    public byte [] convertPixtobyte(BufferedImage m)
   {
        
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
       String id =StaffNum[2];

               //"555";
               //StaffNum[2];
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
       if(img==null || img.equals(""))
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
   
   
   
   
   public void MyCaptureCaller()
   {
        if(this.CheckNewRegistration()==false)
            {
                JOptionPane.showMessageDialog(null, "Please Register New Staff");
                System.out.println(this.CheckNewRegistration());
                return;
            }else
                 {
            
                     this.BioInform();
       staffidPane = new JPanel();
        staffidPane.setLayout(new BorderLayout());
       // getContentPane().add(staffidPane);
        
         staffidPane.add(ImagePane,BorderLayout.CENTER );
        // this.setSize(450,500);
           //StaffCapturedInformation  st= new StaffCapturedInformation();
             // st.setResizable(false);
              //st.setLocationRelativeTo(null);
              //st.setVisible(true);
                 }
        
        }
   


  /*   public static void main(String[] args) {
        // TODO code application logic here
  StaffCapturedInformation sta = new StaffCapturedInformation();
  sta.BioInform();
        //staff.setResizable(false);
     // staff.setLocationRelativeTo(null);
       // staff.setVisible(true); 
        
         
     }*/
    
   
   
   

   
 public void Printablestaffidcard()
 {
        staffIdcardPrint  b = new staffIdcardPrint (staff_id);
        b.setResizable(false);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.setResizable(false);
        ScalePrintableFormat test = new ScalePrintableFormat();
        test.printCard(b.staffidPane);  
       
 }  
   
   
   
   
    
}