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
public class EditStudent extends javax.swing.JDialog  {

    /**
     * Creates new form EditStudent
     */
    private String reg2;
    
    BiometricSet1  stabio;
    private InformationChecker check = new  InformationChecker();
    private String InfoLogged="";
    
    public EditStudent(String m) {
        this.reg2=m;
      Reconsoft st = new Reconsoft();
        initComponents();
       stabio= st.updateStudentsIDcard(reg2);
        surTF.setText(stabio.getStudentSurname());
        otherTF.setText(stabio.getStudentOname());
        midTF.setText(stabio.getStudMiddlename());
        colTF.setText(stabio.getFac());
        departTF.setText(stabio.getDepart());
        levTF.setText(stabio.getLevel());
        sessTF.setText(stabio.getSess());
        groupTF.setText(stabio.getBloodgrp());
        maTF.setText(reg2);
        //maTF.setEditable(false);
    }

    
   
    
    
    
    
    
    
     public void updateStudentsBasicInfo()
    {
       // Reconsoft rs=new Reconsoft();
        
        String fname=this.CheckSurName(surTF.getText());
        String mname=this.CheckMiddleName(midTF.getText());
        String oname=this.CheckFirstName(otherTF.getText());
        String reg_no=this.CheckMatricNum(maTF.getText());
        String dept=this.Checkdepartment(departTF.getText());
        String fac=this.CheckCollege(colTF.getText());
        String lev=levTF.getText();
        String sess=sessTF.getText();
        String group=this.CheckBloodG(groupTF.getText()); 
        String id =stabio.getSelectedStudentid();
        
        
        
        if(fname==null ||mname==null ||reg_no==null ||dept==null ||mname==null ||fac==null ||lev==null ||sess==null ||group==null)
        {
           JOptionPane.showMessageDialog(null, "Empty information is not Allowed"); 
        }else
        {
              Reconsoft data = new Reconsoft(); 
             // Reconsoft2 dat2 = new Reconsoft2(); 
              data.StudentInformation(id,fname, mname, oname, fac, dept, lev, sess, group,reg_no);
              //dat2.StudentInformation(id,fname, mname, oname, fac, dept, lev, sess, group,reg_no);
            
            
            javax.swing.JOptionPane.showMessageDialog(this,"Student Information updated Successfully");
            InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "UPDATED STUDENT'S INFORMATION WITH MATRIC NO :"+reg_no);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
               this.ClearInputs();
      
              
               
        
         }
    }
    
    public void ClearInputs()
    {
        surTF.setText("");
        midTF.setText("");
        otherTF.setText("");
        sessTF.setText("");
        departTF.setText("");//mRB.setText(null);
        colTF.setText(null);
        levTF.setText("");
        groupTF.setText(null);
        maTF.setText(null);
        
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
    
    
    private String CheckBloodG(String blo)
    {
        
      if(!blo.equals("") || blo != null ) 
      {
       check.checkBloodGroup(reg2);
       return blo;
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Blood group Field, Enter letter A-Z"); 
        return null;
      } 
      
    }
       
        
        
        
     
         
         
 private String CheckCollege(String coll)
    {
        
      if(coll.equals("") || coll == null ) 
      {
        JOptionPane.showMessageDialog(null, "College should not be Empty"); 
        return null;
      }else if(check.checkDepart(coll)){
         
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in College Field, Enter letter A-Z"); 
        return null;
      } 
      return coll;
    }
        
         
      
      
      
       private String CheckMatricNum(String matric)
    {
        
      if(matric.equals("") ||matric == null ) 
      {
        JOptionPane.showMessageDialog(null, "Matric number should not be Empty"); 
        return null;
      }else if(check.checkMatNo(matric)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in matric number Field,\n"
                  + "Student Matric number format is 2009/2062"); 
        return null;
      } 
       return matric;
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
        colTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        departTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        groupTF = new javax.swing.JTextField();
        levTF = new javax.swing.JTextField();
        surTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        maTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        midTF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        sessTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("       STUDENT BASIC INFORMATION");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SURNAME:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Firstname:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("COLLEGE:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("DEPARTMENT:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Level:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Blood Group:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Matric NO:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("MiddleName:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Session:");

        javax.swing.GroupLayout bodyPaneLayout = new javax.swing.GroupLayout(bodyPane);
        bodyPane.setLayout(bodyPaneLayout);
        bodyPaneLayout.setHorizontalGroup(
            bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPaneLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(sessTF))
                    .addGroup(bodyPaneLayout.createSequentialGroup()
                        .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maTF, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(otherTF)
                            .addComponent(colTF)
                            .addComponent(groupTF)
                            .addComponent(levTF)
                            .addComponent(surTF)
                            .addComponent(midTF)
                            .addComponent(departTF, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(midTF))
                .addGap(13, 13, 13)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(colTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(levTF))
                .addGap(18, 18, 18)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPaneLayout.createSequentialGroup()
                        .addComponent(sessTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(groupTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(bodyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(19, 19, 19))
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Process");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPLayout = new javax.swing.GroupLayout(MainP);
        MainP.setLayout(MainPLayout);
        MainPLayout.setHorizontalGroup(
            MainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MainPLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(MainPLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(83, 83, 83))
        );
        MainPLayout.setVerticalGroup(
            MainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bodyPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(MainPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(MainP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updateStudentsBasicInfo();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainP;
    private javax.swing.JPanel bodyPane;
    private javax.swing.JTextField colTF;
    private javax.swing.JTextField departTF;
    private javax.swing.JTextField groupTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField levTF;
    private javax.swing.JTextField maTF;
    private javax.swing.JTextField midTF;
    private javax.swing.JTextField otherTF;
    private javax.swing.JTextField sessTF;
    private javax.swing.JTextField surTF;
    // End of variables declaration//GEN-END:variables
}

