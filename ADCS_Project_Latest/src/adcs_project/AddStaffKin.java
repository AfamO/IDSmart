/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author graphics
 */

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class AddStaffKin extends javax.swing.JDialog {
    
 private InformationChecker check = new  InformationChecker();

 private String StaffNum[]=BiometricMain.getDB_Biodata();
 private Reconsoft rs=new Reconsoft();
 //private Reconsoft2 dat2=new Reconsoft2();
 private  BiometricSet1  bio;
 private boolean IssecondTime=false;
 private String InfoLogged="";
 private String title="";
    public AddStaffKin() {
       // super(parent, modal);
        initComponents();
       titleTF.setVisible(false);
       OtitleL.setVisible(false);
    }
    public AddStaffKin(BiometricSet1 bs,boolean isSecTime) {
       // super(parent, modal);
        initComponents();
       titleTF.setVisible(false);
       OtitleL.setVisible(false);
        this.IssecondTime=isSecTime;
        
        bio=bs;
        bio=rs.QueryStaffNextofKinInformation(bs.getStaffNum());
         
          String  titles[] = this.BuildItems(titleCB);
         if(BiometricMain.getStaffkin_Biodata()[1]!=null){
             String knisbiodata[]=  BiometricMain.getStaffkin_Biodata();
               
              /*
                 bio.StaffNum(knisbiodata[0]);
                 bio.SetKinSname(knisbiodata[1]);
                 bio.SetKinFname(knisbiodata[2]);
                 bio.SetFone(knisbiodata[3]);
                 bio.SetKinaddress(knisbiodata[4]);
                 bio.SetRelation(knisbiodata[5]);
                 */
             String titledName[]=knisbiodata[1].split(" ");
             titleCB.setSelectedIndex(this.SearchObjectsPosition(titles,titledName[0]));
              //kinfone.setText(knisbiodata[3]);
              //kinrela.setText(knisbiodata[5]);
              //kinNameL.setText(knisbiodata[1]+" "+knisbiodata[2]);
              //kinadd.setText(knisbiodata[4]);
              surTF.setText(titledName[1]);
              fnameTF.setText(knisbiodata[2]);
              foneTF.setText(knisbiodata[3]);
              addTA.setText(knisbiodata[4]);
              reTF.setText(knisbiodata[5]);
         }
         else
         {
            
      String titledName[]=bs.getKinSname().split(" ");
      titleCB.setSelectedIndex(this.SearchObjectsPosition(titles,titledName[0]));
      surTF.setText(titledName[1]);
      fnameTF.setText(bs.getKinFname());
      foneTF.setText(bs.getPhone());
      addTA.setText(bs.getKinAddress());
      reTF.setText(bs.getRelation());
         }
      
      
       //titleTF.setVisible(false); 
    }

    
    public final int SearchObjectsPosition(Object[] grp,String item){
        int pos=0;
        for(int i=0;i<grp.length;i++)
        {
            if(grp[i].equals(item))
            {
                pos=i;
                break;
            }
        }
        return pos;
    }
    public final String[] BuildItems(JComboBox list)
    {
        int n =list.getItemCount();
        String[] vals=new String[n];
        for(int i=0;i<list.getItemCount();i++)
        {
            vals[i]=(String)list.getItemAt(i);
        }
        return vals;
    }
    
    public void SubmitInformation()
    {
        //String title=this.CheckTitle((String)titleCB.getSelectedItem());
        if(titleCB.getSelectedItem().equals("others"))
        {
           title=titleTF.getText();
                  // this.CheckTFtitle(titleTF.getText());
           
        }else{
           title=this.CheckTitle((String)titleCB.getSelectedItem());
             //title=titleTF.getText();
            
            }
        String sname2=this.CheckSurName(surTF.getText());
        String sname=title+" "+sname2;
        String fname=this.CheckFirstName(fnameTF.getText());
        String fone=this.CheckPhoneNum(foneTF.getText());
        String add =this.CheckAddress(addTA.getText().trim());
        String rela=this.CheckRelation(reTF.getText());
        String staffid =StaffNum[2];
        
        
         if(sname==null ||fname==null||fone==null||title==null||add==null||rela==null||titleCB.getSelectedIndex()==0)
         {
          JOptionPane.showMessageDialog(null, "Empty Next of kin Registration is not Allowed for this Staff");   
         }
         else
         {
        Reconsoft data = new Reconsoft();
        
        BiometricMain.setWhoIsRegistering("staff");
        data.AddStaffNextKinInfor(staffid, sname, fname, fone, add, rela);
       // dat2.AddStaffNextKinInfor(staffid, sname, fname, fone, add, rela);
        data.UpdateUserStatus("staff_registration", "staffNum", "2c", staffid);
       // dat2.UpdateUserStatus("staff_registration", "staffNum", "2c", staffid);
        
        JOptionPane.showMessageDialog(null, "Staff Information Submitted Successfully, Move to Capturing ");
        InfoLogged=AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED THE BASIC INFORMATION OF STAFF'S NEXT OF KIN-that is still registering- WITH STAFF ID:"+staffid);
        AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
        AdminLogin.setStudent_StaffId(staffid);
         this.ClearInfor();
         this.NextItem();
        
         }
        
    }
    public void MoveStaffnextofkinInformationForward()
    {
        if(titleCB.getSelectedItem().equals("others"))
        {
           title=titleTF.getText();
                  // this.CheckTFtitle(titleTF.getText());
           
        }else{
           title=this.CheckTitle((String)titleCB.getSelectedItem());
             //title=titleTF.getText();
            
            }

       // title=this.CheckTitle((String)titleCB.getSelectedItem());
        String sname2=this.CheckSurName(surTF.getText());
        String sname=title+" "+sname2;
        String fname=this.CheckFirstName(fnameTF.getText());
        String fone=this.CheckPhoneNum(foneTF.getText());
        String add =this.CheckAddress(addTA.getText());
        String rela=this.CheckRelation(reTF.getText());
        String staffid =StaffNum[2];
        
        
         if(sname==null ||fname==null||fone==null||title==null||add==null||rela==null||titleCB.getSelectedIndex()==0)
         {
          JOptionPane.showMessageDialog(null, "Empty Next of kin Registration is not Allowed for this Staff");   
         }
         else
         {
       // Reconsoft data = new Reconsoft();
        BiometricMain.setWhoIsRegistering("staff");
        //data.AddStaffNextKinInfor(staffid, sname, fname, fone, add, rela);
        //JOptionPane.showMessageDialog(null, "Staff Information Submitted Successfully, Move to Capturing ");
        BiometricMain.setStaffkin_Biodata(staffid, 0);
         BiometricMain.setStaffkin_Biodata(sname, 1);
          BiometricMain.setStaffkin_Biodata(fname, 2);
           BiometricMain.setStaffkin_Biodata(fone, 3);
            BiometricMain.setStaffkin_Biodata(add, 4);
            BiometricMain.setStaffkin_Biodata(rela, 5);
         this.ClearInfor();
         //BiometricMain.SetBiometricSet1Obj(bio);
            String id=" ";
               if(BiometricMain.getmyuserid()[3]==null);
               {
                  id =BiometricMain.getDB_Biodata()[2];
               }
               if(BiometricMain.getmyuserid()[3]!=null)
               {
                   id=BiometricMain.getmyuserid()[3];
               }
               
               StaffBasicRegSummary sbrs=new  StaffBasicRegSummary(id);
               //JOptionPane.showMessageDialog(null, "recall that my id is:"+id);
               
               sbrs.setLocationRelativeTo(null);
               InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO MODIFY BASIC INFORMATION OF STAFF'S NEXT OF KIN-that is still registering- WITH STAFF ID:"+staffid);
               AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
               AdminLogin.setStudent_StaffId(staffid);
                sbrs.setVisible(true);
                this.setVisible(false);
         //this.NextItem();
        
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
            
          OtitleL.setVisible(true);
          titleTF.setVisible(true);
          titleL.setVisible(false);
          titleCB.setVisible(false);
        }else{
           titleTF.setVisible(false);
           OtitleL.setVisible(false);
          titleL.setVisible(true);
          titleCB.setVisible(true); 
        }
    } 
    
    
    
    
    
    
    
     public void NextItem()
    {
        this.setVisible(false);
        BiometricMain.setWhoIsRegistering("staff");
        if(false)
            {
                //ImageCapturing.IsNotPlayerAvailable()
                JOptionPane.showMessageDialog(null, "Cannot find or configure any available Webcam Device.\nPlease make all necessary connections or/and try again.\nThank you.");
                return;
            }
            else
            {
                 ImageCapturing b = new ImageCapturing();
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
        titleL = new javax.swing.JLabel();
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
        OtitleL = new javax.swing.JLabel();
        titleTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TLabe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TLabe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TLabe.setText("Staff Basic Information");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fill Next of Kin Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Firstname:");

        surTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surTFActionPerformed(evt);
            }
        });

        titleL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titleL.setText("Title:");

        titleCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titleCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Mr.", "Miss", "Master", "Mrs.", "Dr.", "Prof.", "Chief.", "Dr(Mrs)", "Dr(Miss)", "Prof(Mrs)", "Barr.", "Engr.", "Engr(Mrs)", "Chief(Dr)", "Rt Col.", "Arch", "Rev.", "Rev. Fr.", "Rev. Fr.Dr", "Rev. Sr.", "others" }));
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
        jButton1.setText("Next >>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        OtitleL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OtitleL.setText("Title:");

        titleTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(OtitleL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addComponent(surTF)
                        .addComponent(fnameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(foneTF)
                        .addComponent(reTF))
                    .addComponent(titleCB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OtitleL))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleL))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(titleCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(surTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
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
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void titleCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleCBActionPerformed
        // TODO add your handling code here:
        this.ActivateOtherTitle();
    }//GEN-LAST:event_titleCBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
        if(IssecondTime){
            
            this.MoveStaffnextofkinInformationForward();
        }
        else{
            this.SubmitInformation();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void titleTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTFActionPerformed

    private void surTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_surTFActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel OtitleL;
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
    private javax.swing.JComboBox titleCB;
    private javax.swing.JLabel titleL;
    private javax.swing.JTextField titleTF;
    // End of variables declaration//GEN-END:variables
public static void main(String[] args) {
        // TODO code application logic here
       new AddStaffKin().setVisible(true);
    }

}
