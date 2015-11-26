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
import java.awt.image.BufferedImage;
import javax.swing.*;
public class staffIdcardPrint  extends JFrame{
    public JPanel staffidPane;
     JPanel me;
    private JPanel ImagePane;
    private String staffid="";
    private String StaffNum[]=BiometricMain.getDB_Biodata();
   // private String staff_id=StaffNum[2];
   
     private Reconsoft rs=new Reconsoft();
     private  BiometricSet1  bio;
     private BiometricSet1  front_id;
     
    staffIdcardPrint(String id){
        this.staffid=id;
        this.BackInformation();
       staffidPane = new JPanel();
        staffidPane.setLayout(new BorderLayout());
       // staffidPane.setLayout(null);
        getContentPane().add(staffidPane);
        
         staffidPane.add(ImagePane,BorderLayout.CENTER );
        // this.setSize(515,345);
         // this.setSize(635,360);
         //989 by 629
         this.setSize(1050,670);
         
        
    }
    
    
  
   public void BackInformation(){
         
         front_id=rs.QueryFrontID_CardFrame();
         BufferedImage id_bg=front_id.getFront_pix();
        // final ImageIcon icon = new ImageIcon(getClass().getResource("/ADCS_PROJECT/staffimg9.png"));
         final ImageIcon icon = new ImageIcon(id_bg);
         ImagePane = new JPanel(){			
            protected void paintComponent(Graphics g){			
            g.drawImage(icon.getImage(), 0,0, null);
            super.paintComponent(g);
            //super.paintComponent(g);
            
        }	
        };
        ImagePane.setLayout(null);
        
        ImagePane.setOpaque(false);
        
        
       // String staff_id="55555";
                //"55555";
                //StaffNum[2]; 121AAAAAAAAA
         //bio=rs.QueryStaffInformation(this.staffid);
          bio=rs.QueryStaffDatabaseInformation(this.staffid);
         
         JLabel T1= new JLabel("FEDERAL UNIVERSITY OF PETROLEUM RESOURCES" );
		 T1.setBounds(140,9,550,40 );
                T1.setForeground(Color.WHITE);
                T1.setFont(new Font("Arial",Font.BOLD,18));
		// ImagePane.add(T1);
         
         JLabel T2= new JLabel("EFFURUN,NIGERIA." );
		 T2.setBounds(270,35,200,40 );
                T2.setForeground(Color.WHITE);
                T2.setFont(new Font("Arial",Font.BOLD,18));
		 //ImagePane.add(T2);
         JLabel T3= new JLabel("www.fupre.edu.ng" );
		 T3.setBounds(280,55,280,40 );
                T3.setForeground(Color.WHITE);
                T3.setFont(new Font("Arial",Font.BOLD,18));
		// ImagePane.add(T3);
         
         
         JLabel T4= new JLabel("STAFF IDENTITY CARD" );
		 T4.setBounds(180,100,300,20 );
                T4.setForeground(Color.BLACK);
                T4.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T4);
         
         
                // ImageIcon log = new ImageIcon(getClass().getResource("/ADCS_PROJECT/log3.png"));
                 
                 JLabel t5= new JLabel();
		 t5.setBounds(2,2,105,90);
                 //t5.setIcon(log);
		//ImagePane.add(t5);
                 
                 
                 
         JLabel staffnum= new JLabel("STAFF NO: " );
		staffnum.setBounds(730,220, 350,40 );
                staffnum.setForeground(Color.black);
                staffnum.setFont(new Font("Arial",Font.BOLD,32));
		 ImagePane.add(staffnum);
                 
                 
                 JLabel staffnum2= new JLabel(bio.getStaffNum());
		 staffnum2.setBounds(900,220, 200, 40 );
                //staffnum2.setForeground(new java.awt.Color(206, 156, 7));
                  staffnum2.setForeground(Color.black);
                staffnum2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,32));
		 ImagePane.add(staffnum2);
                 
         
         
                 String me[]=bio.getFirstName().split(" ");
                 
                 JLabel tit = new JLabel("Title:" );
		tit.setBounds(290,260,300,40);
                tit.setForeground(Color.black);
                tit.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePane.add(tit);
                 
                 //JLabel tit2 = new JLabel(me[0]+" "+me[1]+" "+me[2]);
                 JLabel tit2 = new JLabel(bio.getTitle());
		tit2.setBounds(490,260,650, 40 );
                tit2.setForeground(new java.awt.Color(0, 51, 153));
                tit2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePane.add(tit2);
                 
                 
                 
         
         
        
               JLabel surname = new JLabel("Surname:" );
		surname.setBounds(290,320,300,40);
                surname.setForeground(Color.black);
                surname.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePane.add( surname );
                 
                 JLabel surname2 = new JLabel(bio.getFirstName());
		surname2.setBounds(490,320,650, 40 );
                surname2.setForeground(new java.awt.Color(0, 51, 153));
                surname2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePane.add( surname2);
                 
                 JLabel othername = new JLabel("Other Names:" );
		othername.setBounds(290,380,400, 40);
                othername.setForeground(Color.black);
                othername.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePane.add(othername);
                 
                JLabel othername2 = new JLabel(bio.getStaffSurname()+" "+bio.getMiddleName());
		othername2.setBounds(490,380, 600, 40 );
                othername2.setForeground(new java.awt.Color(0, 51, 153));
                othername2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePane.add( othername2);
                 
                 
                 JLabel fac= new JLabel("Designation: " );
		fac.setBounds(290,440, 350, 40 );
                fac.setForeground(Color.black);
                fac.setFont(new Font("Arial",Font.BOLD,28));
		ImagePane.add(fac);
                 
                 JLabel fac2= new JLabel(bio.getDesign());
		 fac2.setBounds(490,440, 600, 40 );
                fac2.setForeground(new java.awt.Color(0, 51, 153));
                fac2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		ImagePane.add( fac2);
                
                JLabel depart= new JLabel("Department: " );
		 depart.setBounds(290,500,300, 40 );
                depart.setForeground(Color.black);
                depart.setFont(new Font("Arial",Font.BOLD,28));
		ImagePane.add(depart);
                 
                 JLabel depart2= new JLabel(bio.getDepart());
		 depart2.setBounds(490,500,600, 40);
                depart2.setForeground(new java.awt.Color(0, 51, 153));
                depart2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		ImagePane.add(depart2);
                
                
                JLabel bloodT= new JLabel("Blood Group:");
		 bloodT.setBounds(290,560,350,40);
                 bloodT.setForeground(Color.black);
                 bloodT.setFont(new Font("Arial",Font.BOLD,28));
	         ImagePane.add( bloodT);
                
                
                
               JLabel bloodT2= new JLabel(bio.getBloodgrp());
		 bloodT2.setBounds(490,560,450, 40 );
                 bloodT2.setForeground(new java.awt.Color(0, 51, 153));
                 bloodT2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		ImagePane.add( bloodT2);
               
               //ImageIcon icon3 = new ImageIcon(getClass().getResource("/adcs_project/std_pix/"+bio.getPix())); 
               
                 // Fill in the path to an existing image   img_1376769778820
                // BufferedImage sigmage=ScaleStaffPix(bio.getBufferedPix());
                 BufferedImage sigmage=bio.getBufferedPix();
                        JLabel label = new JLabel();
                        label.setBounds(05,270,270,240 );
                     // label.setIcon(icon3);
                      label.setIcon(new ImageIcon(sigmage));
                      ImagePane.add(label);
                 
                 
 // ImageIcon sigIcon = new ImageIcon(getClass().getResource("/ADCS_PROJECT/std_sig/"+bio.getSig()));
                 
                 JLabel sig= new JLabel();
		 sig.setBounds(70,520,200,70 );
                 sig.setBackground(Color.GRAY);
               //  sig.setIcon(sigIcon);
                 sig.setIcon(new ImageIcon(bio.getBufferedSig()));
		ImagePane.add(sig);
                 
                 
                 
                 
    } 
  
   
   
  public  static BufferedImage ScaleStaffPix( BufferedImage org)
   {
       
  Image pix=org.getScaledInstance(270,240,Image.SCALE_SMOOTH);
   BufferedImage buffImg = new BufferedImage(pix.getWidth(null),pix.getHeight(null), BufferedImage.TYPE_INT_RGB);
   Graphics2D g = buffImg.createGraphics();
  g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
  g.drawImage(org, 0, 0,270,240, null);
  g.dispose();
  return buffImg;
   }
    
  
   
 public static void main(String[] args) {
        // TODO code application logic here"SP 121"
     //staffIdcardPrint staff = new staffIdcardPrint ("SP 121");
     staffIdcardPrint staff = new staffIdcardPrint ("SP 121");
        //staff.setResizable(false);
      staff.setLocationRelativeTo(null);
        staff.setVisible(true); 
        
         //Reconsoft data = new Reconsoft();
        
       // data.AddStaffNextKinInfor("SP 111","Prof.Chukwuka","Charles","08055332211","progressive Estate Ikorodu","sister");
         //PrintSmartIdCard.printComponent(staff.staf);
      // TestPrinting test = new TestPrinting();
       // ScalePrintableFormat test = new ScalePrintableFormat();
      // test.printCardImage(staff.staffidPane);
       //test.JPanelImageConverter(staff.staffidPane);
  //  test.printCard(staff.staffidPane);
    }
    

    
}
