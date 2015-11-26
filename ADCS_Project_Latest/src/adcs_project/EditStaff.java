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


public class EditStaff extends javax.swing.JDialog {

    private String reg6;
    /**
     * Creates new form EditStaff
     */
    BiometricSet1  stabio;
    private InformationChecker check = new  InformationChecker();
     private String InfoLogged="";
    
    public EditStaff(String m3) {
       // super(parent, modal);
        this.reg6=m3;
        initComponents();
        Reconsoft st = new Reconsoft();
       stabio=st.QueryAllStaffInformation(reg6);
       surTF.setText(stabio.getStaffSurname());
       otherTF.setText(stabio.getFirstName());
       midTF.setText(stabio.getMiddleName());
       desTF.setText(stabio.getDesign());

       departTF.setText(stabio.getDepart());
       groupTF.setText(stabio.getBloodgrp());
       staffnumTF.setText(stabio.getStaffNum());
      // staffnumTF.setEditable(false);
        
    }

    
    
    
    public void ProcessStaffInformation(){
        
       
        String surname=this.CheckSurName(surTF.getText());
        String fname=this.CheckFirstName(otherTF.getText());
        String mid= this.CheckMiddleName(midTF.getText());
        String staffid=this.CheckStaffNum(staffnumTF.getText());
        String depart=this.Checkdepartment(departTF.getText());
        String group=this.CheckBloodG(groupTF.getText());
        String design=this.CheckDesignation(desTF.getText());
        String id =stabio.getSelectedStaffid();
        
        
           
        
       
        
         
         
         if(surname==null ||fname==null||mid==null||depart==null||staffid==null||group==null||design==null)
         {
          JOptionPane.showMessageDialog(null, "Empty information is not Allowed for this Staff");   
         }
         else
         {
             
             Reconsoft data = new Reconsoft(); 
           //  Reconsoft2 dat2 = new Reconsoft2();
             data.StaffInformation(id,surname, fname,mid, design, depart, group, staffid);
            // dat2.StaffInformation(id,surname, fname,mid, design, depart, group, staffid);
               JOptionPane.showMessageDialog(null, "Staff Information Updated Successfully");
               InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "UPDATED STAFF'S INFORMATION WITH ID :"+staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
               this.ClearInformation();
               
             
            
            
        
         }
    }
    
    public void ClearInformation(){
        surTF.setText("");
        otherTF.setText("");
        midTF.setText("");
        staffnumTF.setText(null);
        departTF.setText(null);
        groupTF.setText(null);
        desTF.setText(null);
        
        
    }
    
    

    
    private String CheckSurName(String name)
    {
        
      if(name.equals("") || name == null ) 
      {
        JOptionPane.showMessageDialog(null, "Surname should not be Empty"); 
        return null;
      }else if(check.checkSurname(name)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in surname Field, Enter letter A-Z"); 
        return null;
      } 
       return name;
    }
    
    
    
    
     private String CheckFirstName(String fname)
    {
        
      if(fname.equals("") || fname == null ) 
      {
        JOptionPane.showMessageDialog(null, "First Name should not be Empty"); 
        return null;
      }else if(check.checkSurname(fname)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in first name Field, Enter letter A-Z"); 
        return null;
      } 
       return fname;
    }
    
     
     
      private String CheckMiddleName(String mname)
    {
        
      if(mname.equals("") || mname == null ) 
      {
        JOptionPane.showMessageDialog(null, " Middle Name should not be Empty"); 
        return null;
      }else if(check.checkMidname(mname)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in middle name Field, Enter letter A-Z"); 
        return null;
      } 
       return mname;
    }
     
     
      
       private String Checkdepartment(String depart)
    {
        
      if(depart.equals("") || depart == null ) 
      {
        JOptionPane.showMessageDialog(null, " Department should not be Empty"); 
        return null;
      }else if(check.checkDepart(depart)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Department Field, Enter letter A-Z"); 
        return null;
      } 
       return depart;
    }
      
       
        private String CheckDesignation(String dessign)
    {
        
      if(dessign.equals("") || dessign == null ) 
      {
        JOptionPane.showMessageDialog(null, " Designation should not be Empty"); 
        return null;
      }else if(check.checkDepart(dessign)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Designation Field, Enter letter A-Z"); 
        return null;
      } 
       return dessign;
    }
        
       
       
        
        
        
        
        
      
         
 private String CheckBloodG(String blo)
    {
        
      if(!blo.equals("") || blo != null ) 
      {
       check.checkBloodGroup(reg6);
       return blo;
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Blood group Field, Enter letter A-Z"); 
        return null;
      } 
      
    }
        
        
      
      
       private String CheckStaffNum(String staffnum2)
    {
        
      if(staffnum2.equals("") ||staffnum2 == null ) 
      {
        JOptionPane.showMessageDialog(null, "Staff number should not be Empty"); 
        return null;
      }else if(check.checkstaffnum(staffnum2)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in staff number Field,\n"
                  + "Staff number format is two Characters followed by three digits e.g SP139"); 
        return null;
      } 
       return staffnum2;
    }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bodyPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        otherTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        desTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        departTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        groupTF = new javax.swing.JTextField();
        surTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        staffnumTF = new javax.swing.JTextField();
        saveB = new javax.swing.JButton();
        saveB1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        midTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("       STAFF BASIC INFORMATION");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SURNAME:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("First Name:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("DESIGNATION:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("DEPARTMENT:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Blood Group:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("STAFF NO:");

        saveB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveB.setText("Process");
        saveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBActionPerformed(evt);
            }
        });

        saveB1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveB1.setText("Exit");
        saveB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveB1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Middle Name:");

        javax.swing.GroupLayout bodyPaneLayout = new javax.swing.GroupLayout(bodyPane);
        bodyPane.setLayout(bodyPaneLayout);
        bodyPaneLayout.setHorizontalGroup(
            bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPaneLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(saveB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveB1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(staffnumTF)
                    .addComponent(departTF, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(otherTF)
                    .addComponent(desTF)
                    .addComponent(groupTF)
                    .addComponent(surTF)
                    .addComponent(midTF))
                .addContainerGap())
        );
        bodyPaneLayout.setVerticalGroup(
            bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surTF))
                .addGap(10, 10, 10)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(otherTF)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(midTF))
                .addGap(10, 10, 10)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(desTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(groupTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(staffnumTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveB)
                    .addComponent(saveB1))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout MainPLayout = new javax.swing.GroupLayout(MainP);
        MainP.setLayout(MainPLayout);
        MainPLayout.setHorizontalGroup(
            MainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MainPLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        MainPLayout.setVerticalGroup(
            MainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bodyPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveB1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_saveB1ActionPerformed

    private void saveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBActionPerformed
        // TODO add your handling code here:;
        ProcessStaffInformation();
        
    }//GEN-LAST:event_saveBActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainP;
    private javax.swing.JPanel bodyPane;
    private javax.swing.JTextField departTF;
    private javax.swing.JTextField desTF;
    private javax.swing.JTextField groupTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField midTF;
    private javax.swing.JTextField otherTF;
    private javax.swing.JButton saveB;
    private javax.swing.JButton saveB1;
    private javax.swing.JTextField staffnumTF;
    private javax.swing.JTextField surTF;
    // End of variables declaration//GEN-END:variables
}
