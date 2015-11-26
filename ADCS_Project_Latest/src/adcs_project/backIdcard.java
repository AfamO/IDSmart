/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class backIdcard  extends JFrame{
    public JPanel staffidPane;
     JPanel me;
    private JPanel ImagePane;
   
  Reconsoft rs_back=new Reconsoft();
  private BiometricSet1  back;
     
   public  backIdcard(){
        this.BackInformation();
       staffidPane = new JPanel();
        staffidPane.setLayout(new BorderLayout());
       // staffidPane.setLayout(null);
        getContentPane().add(staffidPane);
        
         staffidPane.add(ImagePane,BorderLayout.CENTER );
         this.setSize(605,365);
         
        
    }
    
    
  
   public void BackInformation(){
         
         back=rs_back.QueryBackID_CardFrame();
         BufferedImage back_pix=back.getBack_pix();
        // final ImageIcon icon = new ImageIcon(getClass().getResource("/adcs_project/backimg4.png"));
          final ImageIcon icon = new ImageIcon(back_pix);
         ImagePane = new JPanel(){			
            protected void paintComponent(Graphics g){			
            g.drawImage(icon.getImage(), 0,0, null);
            super.paintComponent(g);
            //super.paintComponent(g);
            
        }	
        };
        ImagePane.setLayout(null);
        
        ImagePane.setOpaque(false);
        
        
       
         
        
                // ImageIcon log = new ImageIcon(getClass().getResource("/adcs_project/log3.png"));
                 
                 JLabel t5= new JLabel();
		 t5.setBounds(220,30,110,100);
                 //t5.setIcon(log);
		//ImagePane.add(t5);
                 
                 
                JLabel T1= new JLabel("The identity card is an official document and relates" );
		 T1.setBounds(100,120,460,20 );
                T1.setForeground(Color.BLACK);
                T1.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T1);
         
         JLabel T2= new JLabel("only to the Person described." );
		 T2.setBounds(60,140,300,20 );
                T2.setForeground(Color.BLACK);
                T2.setFont(new Font("Arial",Font.BOLD,16));
		 //ImagePane.add(T2);
         JLabel T3= new JLabel("It is the property of Federal University of Petroleum" );
		 T3.setBounds(60,160,460,20 );
                T3.setForeground(Color.BLACK);
                T3.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T3);
         
         
          JLabel T4= new JLabel("Resources,Effurun Nigeria." );
		 T4.setBounds(60,180,300,20 );
                T4.setForeground(Color.BLACK);
                T4.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T4);
         
         JLabel T6= new JLabel("Impersonation of the authorized holder,");
		 T6.setBounds(60,200,460,20 );
                T6.setForeground(Color.BLACK);
                T6.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T6);
         
         JLabel T7= new JLabel("alteration, destruction and transfer of the card ");
		 T7.setBounds(60,220,460,20 );
                T7.setForeground(Color.BLACK);
                T7.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T7);
         
                 
                 JLabel T14= new JLabel("to person are penal offences." );
		 T14.setBounds(60,240,300,20 );
                T14.setForeground(Color.BLACK);
                T14.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T14);
         
                 
         
                 JLabel T8= new JLabel("The loss/recovery of this card should be");
		 T8.setBounds(60,260,460,20 );
                T8.setForeground(Color.BLACK);
                T8.setFont(new Font("Arial",Font.BOLD,16));
		 //ImagePane.add(T8);
                 
                 JLabel T12= new JLabel("reported to the office of" );
		 T12.setBounds(60,280,300,20 );
                T12.setForeground(Color.BLACK);
                T12.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T12);
         
         
                 JLabel T9= new JLabel("The Chief Security officer of the University or the nearest");
		 T9.setBounds(60,300,460,20 );
                T9.setForeground(Color.BLACK);
                T9.setFont(new Font("Arial",Font.BOLD,16));
		// ImagePane.add(T9);
         
         JLabel T13= new JLabel("Police Station." );
		 T13.setBounds(60,320,300,20 );
                T13.setForeground(Color.BLACK);
                T13.setFont(new Font("Arial",Font.BOLD,16));
		 //ImagePane.add(T13);
         
         
         
                       
       
                
                
   }             
    
    public static void main(String[] args) {
        // TODO code application logic here
     backIdcard staff = new backIdcard ();
       staff.setResizable(false);
       staff.setLocationRelativeTo(null);
       staff.setVisible(true); 
       ScaleBackPrintable test = new ScaleBackPrintable();
       // test.printCard(staff.staffidPane);
    }

    
}
