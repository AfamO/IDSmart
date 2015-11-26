/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

import javax.swing.JOptionPane;

/**
 *
 * @author Charles
 */
public class EditStaffNextofKin extends javax.swing.JDialog {

    private String reg6,newkin3;
    private String surname,title,kinid,bioid;
    private InformationChecker check = new  InformationChecker();
   // private String StaffNum[]=BiometricMain.getDB_Biodata();
    BiometricSet1  staffbioid,staffNextkinid;
    public EditStaffNextofKin(String kin,String newkin) {
        //super(parent, modal);
         initComponents();
         titleTF.setVisible(false); 
        tiL.setVisible(false); 
        this.reg6=kin;
        this.newkin3=newkin;
        Reconsoft st = new Reconsoft();
       staffNextkinid=st.QueryStaffNextofKinInformation(reg6);
        staffbioid=st.QueryStaffBioID(reg6);
       String n1 []=staffNextkinid.getKinSname().split(" ");
       surTF.setText(n1[1]);
       fnameTF.setText(staffNextkinid.getKinFname());
       foneTF.setText(staffNextkinid.getPhone());
       addTA.setText(staffNextkinid.getKinAddress());
       reTF.setText(staffNextkinid.getRelation());
       kinid=staffNextkinid.getSelectedStaffkinid();
         
         bioid=staffbioid.getSelectedStaffbioid();
         //System.out.println("The Staff id  kin is "+ kinid);
         //System.out.println("The Staff id  bio is "+bioid);
    }

    
    
    
    public void ProcessStaffIformationUpdate()
    {
     String surname2=this.CheckSurName(surTF.getText());
        //String sname=title+" "+sname2;
         if(titleCB.getSelectedItem().equals("others"))
        {
           title=this.CheckTFtitle(titleTF.getText());
            surname=title+" "+surname2;
        }else{
            title=this.CheckTitle((String)titleCB.getSelectedItem());
            surname=title+" "+surname2;
            }
        String fname=this.CheckFirstName(fnameTF.getText());
        String fone=this.CheckPhoneNum(foneTF.getText());
        String add =this.CheckAddress(addTA.getText().trim());
        String rela=this.CheckRelation(reTF.getText()); 
        //String staffid =StaffNum[2];
        
         if(surname==null ||fname==null||fone==null||title==null||add==null||rela==null||titleCB.getSelectedIndex()==0)
         {
          JOptionPane.showMessageDialog(null, "Empty Next of kin Registration is not Allowed for this Staff");   
         }
         else
         {
        Reconsoft data = new Reconsoft();
        Reconsoft dat2 = new Reconsoft();
        data.UpdateEditableStaffNextOfKinsInformation(kinid,newkin3,surname, fname, fone, add, rela);
        data.UpdateEditable_Staff_Bio_Id(bioid, newkin3);
        
         dat2.UpdateEditableStaffNextOfKinsInformation(kinid,newkin3,surname, fname, fone, add, rela);
         dat2.UpdateEditable_Staff_Bio_Id(bioid, newkin3);
        
        JOptionPane.showMessageDialog(null, "Staff Information updated Successfully ");
        
         this.ClearInfor();
        
        
         }
        
        
        
    }
    
    
    
    
    public void ClearInfor()
    {
        titleCB.setSelectedIndex(0);
        surTF.setText(null);
        fnameTF.setText(null);
        foneTF.setText(null);
        addTA.setText(null);
        reTF.setText(null);
        
    }
    
    
    
 
    
   public void ActivateOtherTitle()
    {
        if(titleCB.getSelectedItem().equals("others"))
        {
          titleTF.setVisible(true);
          tiL.setVisible(true); 
          titleCB.setVisible(false);
          tiL2.setVisible(false);
        }else{
          // titleTF.setVisible(false);
          // tiL.setVisible(false);
          titleCB.setVisible(true); 
          tiL2.setVisible(true);
        }
    } 
    
    
    
    
    private String CheckPhoneNum(String fone)
    {
        
      if(!fone.equals("") || fone != null ) 
      {
       check.checkGsm(fone);
       return fone;
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Phone Number Field,\n"
                  + " Phone Number format is 08067707555"); 
        return null;
      } 
    }
    
      
      
      
      private String CheckAddress(String address)
    {
        
      if(address.equals("") || address == null ) 
      {
        JOptionPane.showMessageDialog(null, " Residential Address should not be Empty"); 
        return null;
      }else if(check.checkAddress(address)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Residential Address Field, Enter letter A-Z,0-9, etc"); 
        return null;
      } 
       return address;
    }
       
      
      
      
      
         private String CheckRelation(String rela)
    {
        
      if(rela.equals("") || rela== null ) 
      {
        JOptionPane.showMessageDialog(null, " Residential Address should not be Empty"); 
        return null;
      }else{
          //JOptionPane.showMessageDialog(null, "Invalid Character in Residential Address Field, Enter letter A-Z,0-9, etc"); 
        //return null;
          return rela;
      } 
       
    }
       
      
      
      
      
      
      
       private String CheckTitle(String ti)
    {
        
      if(titleCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Title Not Selected"); 
        return null;
      }else{
          
            return ti;
      } 
      
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
      
     
     
    private String CheckTFtitle(String title)
    {
        
      if(title.equals("") || title == null ) 
      {
        JOptionPane.showMessageDialog(null, "Title should not be Empty"); 
        return null;
      }else if(check.checkTitle(title)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Title Field, Enter letter A-Z"); 
        return null;
      } 
       return title;
    }  
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TLabe = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        surTF = new javax.swing.JTextField();
        tiL2 = new javax.swing.JLabel();
        titleCB = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        foneTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fnameTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        reTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        titleTF = new javax.swing.JTextField();
        tiL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TLabe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TLabe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TLabe.setText("Staff Basic Information");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fill Next of Kin Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Firstname:");

        tiL2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiL2.setText("Title:");

        titleCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titleCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Mr.", "Mrs.", "Dr.", "Prof.", "Chief.", "Dr(Mrs)", "Dr(Miss)", "Prof(Mrs)", "Barr.", "Engr.", "Engr(Mrs)", "Chief(Dr)", "Rt Col.", "Arch", "Rev.", "others" }));
        titleCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleCBActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Phone Number:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Surname:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Address:");

        addTA.setColumns(20);
        addTA.setRows(5);
        jScrollPane1.setViewportView(addTA);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Relationship:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("update information");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tiL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiL.setText("Title:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tiL2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tiL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(surTF)
                    .addComponent(fnameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(foneTF)
                    .addComponent(reTF)
                    .addComponent(titleCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleTF, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(107, 107, 107))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tiL2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tiL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(reTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void titleCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleCBActionPerformed
        // TODO add your handling code here:
        this.ActivateOtherTitle();
    }//GEN-LAST:event_titleCBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        ProcessStaffIformationUpdate();
       

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TLabe;
    private javax.swing.JTextArea addTA;
    private javax.swing.JTextField fnameTF;
    private javax.swing.JTextField foneTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField reTF;
    private javax.swing.JTextField surTF;
    private javax.swing.JLabel tiL;
    private javax.swing.JLabel tiL2;
    private javax.swing.JComboBox titleCB;
    private javax.swing.JTextField titleTF;
    // End of variables declaration//GEN-END:variables
}
