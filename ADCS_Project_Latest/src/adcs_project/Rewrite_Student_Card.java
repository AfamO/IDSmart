/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

import javax.swing.JOptionPane;

/**
 *
 * @author fupre3
 */
public class Rewrite_Student_Card 
{
    Boolean Iswritten=false;
    
    
    public Rewrite_Student_Card ()
    {
        
    }
    
     public void WriteRegToSmartCard(String reg){
         Iswritten=false;
        AfamSmartCard jsc=new AfamSmartCard();
        
            byte id=(byte)0x02;
            byte DATA=(byte)0xF0;
            if(jsc.IsCardPresent())
            {
                if(jsc.Format_PersonalizeSmartCard()){
               //int lent=0;
               int lent=9;
              // if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
              // {
                  // lent=9;
              // }
              // else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               //{
                  // lent=6;
              // }
             //reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No to write to \nyour SmartIDCard for Personalization");
           if(!reg.equals(""))
           {
               if(reg.length()!=lent){
                  // if(lent==6){
                    //    javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Staff No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                  // }
                   //else
                   //if(lent==9)
                   //{
                       javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Matric. No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                   //}
                   
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
               
              /*  else if(jsc.IsSmartCardFormated_Personalized() && (jsc.ReadFromSmartCard(DATA, id,9).matches("0262/2009")))
                {
                    
                    
                    int lent=9;
                    // int lent=0;
              // if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
               //{
                  // lent=9;
               //}
               //else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               //{
                 //  lent=6;
              // }
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
       */
       
    //String info=jsc.ReadFromSmartCard(DATA, id);
       
    //JOptionPane.showMessageDialog(null,"The red Info is:\n"+info);
   
    }
   
}
 
     
     
  public void WriteRegToSmartCard_ForStaff(String reg){
         Iswritten=false;
        AfamSmartCard jsc=new AfamSmartCard();
        
            byte id=(byte)0x02;
            byte DATA=(byte)0xF0;
            if(jsc.IsCardPresent())
            {
                if(jsc.Format_PersonalizeSmartCard()){
               //int lent=0;
               int lent=6;
              // if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
              // {
                  // lent=9;
              // }
              // else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               //{
                  // lent=6;
              // }
             //reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No to write to \nyour SmartIDCard for Personalization");
           if(!reg.equals(""))
           {
               if(reg.length()!=lent){
                  // if(lent==6){
                    //    javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Staff No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                  // }
                   //else
                   //if(lent==9)
                   //{
                       javax.swing.JOptionPane.showMessageDialog(null, "Incorrect Matric. No.\nMake Sure that number of the characters is exactly "+lent+"\n"+"Thank you.");
                   //}
                   
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
               
              /*  else if(jsc.IsSmartCardFormated_Personalized() && (jsc.ReadFromSmartCard(DATA, id,9).matches("0262/2009")))
                {
                    
                    
                    int lent=9;
                    // int lent=0;
              // if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
               //{
                  // lent=9;
               //}
               //else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               //{
                 //  lent=6;
              // }
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
       */
       
    //String info=jsc.ReadFromSmartCard(DATA, id);
       
    //JOptionPane.showMessageDialog(null,"The red Info is:\n"+info);
   
    }
   
}    
     
     
     
}
