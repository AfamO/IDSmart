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
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.*;
public class PrintID extends JFrame{
    public JPanel idcardPan;
    private JPanel ImagePan;
    private String reg;
     Reconsoft rs=new Reconsoft();
     BiometricSet1 bs;
    
    PrintID(String reg){
        this.reg=reg;
        
        this.BackImage();
        
        
         idcardPan = new JPanel();
        idcardPan.setLayout(new BorderLayout());
        getContentPane().add(idcardPan);
         idcardPan.add(ImagePan,BorderLayout.CENTER);
         //this.setSize(630,1020);
        // this.setSize(700,400);
        // this.setSize(635,380);
         this.setSize(1055,680);
        
        
    }
    
    
    public void BackImage(){ 
        //final ImageIcon icon = new ImageIcon(getClass().getResource("/adcs_project/studentimg3.png"));
        
         final ImageIcon icon = new ImageIcon(getClass().getResource("/adcs_project/studentimg5.png"));
         
         ImagePan = new JPanel(){			
            protected void paintComponent(Graphics g){			
            g.drawImage(icon.getImage(), 0,0, null);
            
            super.paintComponent(g);
            //super.paintComponent(g);
            
        }	
        };
        ImagePan.setLayout(null);
        
        ImagePan.setOpaque(false);
        if(reg==null || reg.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please Enter something.\nThank you", "EMPTY INPUT", 2);
            //return;
             //FUPRE09/2012
            //"FUPRE08/2011"
            
        }
        else
        {
           // bs=rs.QueryStudentsInfo(reg);
            bs=rs.QueryStudentsDatabaseInfo(reg);
            
            
            
            
            JLabel st1= new JLabel("FEDERAL UNIVERSITY OF PETROLEUM RESOURCES" );
		 st1.setBounds(130,10,460,20 );
                st1.setForeground(Color.WHITE);
                st1.setFont(new Font("Arial",Font.BOLD,14));
		// ImagePan.add(st1);
         
         JLabel st2= new JLabel("EFFURUN,NIGERIA." );
		 st2.setBounds(220,25,200,20 );
                st2.setForeground(Color.WHITE);
                st2.setFont(new Font("Arial",Font.BOLD,14));
		// ImagePan.add(st2);
         JLabel st3= new JLabel("www.fupre.edu.ng" );
		 st3.setBounds(230,40,280,20 );
                st3.setForeground(Color.WHITE);
                st3.setFont(new Font("Arial",Font.BOLD,14));
		// ImagePan.add(st3);
         
         
         JLabel st4= new JLabel("STUDENT IDENTITY CARD" );
		 st4.setBounds(320,155,500,40 );
                st4.setForeground(Color.BLACK);
                st4.setFont(new Font("Arial",Font.BOLD,28));
		 //ImagePan.add(st4);
         
         
                // ImageIcon log = new ImageIcon(getClass().getResource("/ADCS_PROJECT/log3.png"));
                 
                 //JLabel st5= new JLabel();
		// st5.setBounds(0,0,110,98);
                // st5.setIcon(log);
		//ImagePan.add(st5);
                 
                 JLabel studentnum= new JLabel("Matric No: " );
		 studentnum.setBounds(700,215, 300,40 );
                studentnum.setForeground(Color.black);
                studentnum.setFont(new Font("Arial",Font.BOLD,32));
		 ImagePan.add(studentnum);
                 
                 
                 JLabel studentnum2= new JLabel(reg);
		 studentnum2.setBounds(860,215, 200, 40 );
                  studentnum2.setForeground(Color.black);
                studentnum2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,32));
		 ImagePan.add(studentnum2);
                 
         
            //System.out.println("The Student's Surname is "+bs.getStudentSurname());
              // System.out.println("The Student's Pix is "+bs.getPix());
              
            
            
                 
                 JLabel surname = new JLabel("Surname:" );
		surname.setBounds(300,290,400,40);
                surname.setForeground(Color.black);
                surname.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePan.add( surname );
            
               JLabel surname2 = new JLabel(bs.getStudentSurname() );
                surname2.setBounds( 500,290, 450, 40 );
                surname2.setForeground(Color.BLACK);
                surname2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePan.add( surname2 );

                 JLabel othername = new JLabel("Other Names:" );
		 othername.setBounds(300,350, 300, 40);
                othername.setForeground(Color.black);
                othername.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePan.add( othername);
                
                 
                 JLabel othername2 = new JLabel(bs.getStudentOname()+" "+bs.getStudMiddlename());
		 othername2.setBounds( 500,350,600, 40 );
                othername2.setForeground(Color.BLACK);
                othername2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePan.add(othername2);

                 JLabel fac= new JLabel("College: ");
		 fac.setBounds(300,410, 300, 40 );
                 fac.setForeground(Color.BLACK);
                 fac.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePan.add(fac);

                 JLabel fac2= new JLabel(bs.getFac());
		 fac2.setBounds( 500,410,600, 40 );
                fac2.setForeground(Color.BLACK);
                fac2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePan.add(fac2);

                 JLabel depart= new JLabel("Department: " );
		 depart.setBounds(300, 470, 400, 40 );
                depart.setForeground(Color.BLACK);
                depart.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePan.add(depart);

                 JLabel depart2= new JLabel(bs.getDepart() );
		 depart2.setBounds( 500,470,600, 40 );
                depart2.setForeground(Color.BLACK);
                depart2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePan.add(depart2);

                 JLabel level= new JLabel("Sess(Level): " );
		 level.setBounds(300,530, 350, 40 );
                level.setForeground(Color.BLACK);
                level.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePan.add(level);

                 JLabel level2= new JLabel(bs.getSess()+"("+bs.getLevel()+")");
		 level2.setBounds( 500,530,600, 40 );
                level2.setForeground(Color.BLACK);
                level2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePan.add(level2);


                 JLabel group= new JLabel("Blood Group:" );
		 group.setBounds(300,590, 400, 40 );
                group.setForeground(Color.BLACK);
                group.setFont(new Font("Arial",Font.BOLD,28));
		 ImagePan.add(group);

               JLabel group2= new JLabel(bs.getBloodgrp());
		 group2.setBounds( 500,590, 300, 40 );
                group2.setForeground(Color.BLACK);
                group2.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,38));
		 ImagePan.add(group2);
      
                 
                 
            // ImageIcon icon3 = new ImageIcon(getClass().getResource("/adcs_project/std_pix/"+bs.getPix()));
                                                   // /adcs_project/std_sig/"+sig
                                                   // "/adcs_project/std_pix/"+pix
             //  Fill in the path to an existing image
                     BufferedImage sigmage=bs.getBufferedPix();

                      JLabel label = new JLabel();
                      label.setBounds(05,270,270,240 );
                      //label.setBounds(05,270,270,260 );
                     // label.setIcon(icon3);
                      label.setIcon(new ImageIcon(sigmage));
                      ImagePan.add(label);
                      
             // ImageIcon sigIcon = new ImageIcon(getClass().getResource("/adcs_project/std_sig/"+bs.getSig()));
                 
                 JLabel sig= new JLabel();
		 sig.setBounds( 100, 550, 200,70 );
                // sig.setIcon(sigIcon);
                 sig.setIcon(new ImageIcon(bs.getBufferedSig()));
		ImagePan.add(sig);
       
                 
                 
    }

        }
       
    
    
  public static void main(String[] args) {
        // TODO code application logic here
      //2012/1212
     PrintID staff = new PrintID ("0825/2011");
        //staff.setResizable(false);
       staff.setLocationRelativeTo(null);
        staff.setVisible(true); 
        ScalePrintableFormat test = new ScalePrintableFormat();
       test.printCard(staff.idcardPan);
    }
    
       
   
    
    
    
    
}
