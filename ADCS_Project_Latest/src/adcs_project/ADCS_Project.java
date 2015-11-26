/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author teledom
 */
public class ADCS_Project {  
    private static Reconsoft rs=new Reconsoft();
    private  static BiometricSet1 home_bg;
      static BiometricMain card;
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        home_bg=rs.QueryHome_Logo_Card();
        if(home_bg.getCo_name()==null|| home_bg.getBack_pix()==null||home_bg.getCo_City()==null){
           
            setup_page bt = new setup_page();
             bt.setSize(500, 550);
             bt.setVisible(true);
             bt.setLocationRelativeTo(null);
             bt.setResizable(false);
             bt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        }else{
       card= new BiometricMain();
       card.setSize(1050, 720);
       card.setLocationRelativeTo(null);
       card.setResizable(false);
       card.setVisible(true);
            
        //bm=card;
        //card.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } 
        
            
    }
}
