/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

import javax.swing.JOptionPane;

import javax.swing.JFrame;

/**
 *
 * @author Charles
 */
public class EditStaffdata extends JFrame {

    /**
     * Creates new form EditStaffdata1
     */
   private String reg6;
    
    BiometricSet1  stabio,staffbioid,staffNextkinid;
   // private String  getsaffids []= new String [4];
    private String info_id;

    private InformationChecker check = new  InformationChecker(); 
    
    public EditStaffdata(String m4) {
        initComponents();
         this.reg6=m4;
         Reconsoft st = new Reconsoft();
       stabio=st.QueryAllStaffInformation(reg6);
       //staffbioid=st.QueryStaffBioID(reg6);
      
       
       sunTF.setText(stabio.getStaffSurname());
       Fname.setText(stabio.getFirstName());
       midNameTF.setText(stabio.getMiddleName());
       desTF.setText(stabio.getDesign());

       departTF.setText(stabio.getDepart());
       bloodTF.setText(stabio.getBloodgrp());
       staffNumTF.setText(stabio.getStaffNum());
      titleTF.setText(stabio.getTitle());
      
     
        addTA.setText(stabio.getAddress());
        foneTF.setText(stabio.getFone());
        sexTF.setText(stabio.getSex());
        dateTF.setText(stabio.getBdate());
        
        stateTF.setText(stabio.getState());
        lgaTF.setText(stabio.getLga());
        
        info_id=stabio.getSelectedStaffid();
                     // System.out.println("The Staff id is "+stabio.getSelectedStaffid());
                      //System.out.println("The Staff id is "+staffbioid.getSelectedStaffbioid());
                    //  System.out.println("The Staff id is "+staffNextkinid.getSelectedStaffkinid());

    }

    
    
    
    
   public void ProcessStaffInformation(){
        
       
        String surname2= this.CheckSurName(sunTF.getText());
        String title=titleTF.getText();
        String fname=this.CheckFirstName(Fname.getText());
        String mid= this.CheckMiddleName(midNameTF.getText());
        String staffnum=this.CheckStaffNum(staffNumTF.getText());
        String depart=this.Checkdepartment(departTF.getText());
        String design=this.CheckDesignation(desTF.getText());
        String group=bloodTF.getText();
        String fon=this.CheckPhoneNum(foneTF.getText());
        String ad=this.CheckAddress(addTA.getText());
        String bdate=dateTF.getText();
        String lga=lgaTF.getText();
        String stat=stateTF.getText();
        String sex=sexTF.getText();
       
        
           Reconsoft data = new Reconsoft();
           Reconsoft dat2 = new Reconsoft();
        
       
        
        
         
         if(surname2==null ||fname==null||mid==null||fon==null||title==null||depart==null||staffnum==null||group==null||design==null)
         {
          JOptionPane.showMessageDialog(null, "Empty Registration is not Allowed for this Staff");   
         }
         else
         {
           data.UpdateAllEditableStaffInformation(info_id,title,surname2,fname,mid,design,depart,group, fon,ad, bdate,lga,stat,sex,staffnum);
           dat2.UpdateAllEditableStaffInformation(info_id,title,surname2,fname,mid,design,depart,group, fon,ad, bdate,lga,stat,sex,staffnum); 
          
           JOptionPane.showMessageDialog(null, "Information updated successfully"); 
           NextCaller(reg6,staffnum);
           
         }
    } 
    
    
    
   
   public void NextCaller(String no,String n)
   {
       this.setVisible(false);
        EditStaffNextofKin bis = new EditStaffNextofKin(no,n);
        bis.setLocationRelativeTo(null);
        bis.setVisible(true);
        bis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
   }
   
   
   
    public void ClearInformation(){
        sunTF.setText("");
        Fname.setText("");
        midNameTF.setText("");
        staffNumTF.setText(null);
        departTF.setText(null);
        desTF.setText(null);
        addTA.setText(null);
        foneTF.setText(null);
        sexTF.setText(null);
        dateTF.setText(null);
        bloodTF.setText(null);
        stateTF.setText(null);
        lgaTF.setText(null);
       
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
         
     if(check.checkMidname(mname)){
         
        
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

        ContentPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Fname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        midNameTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        staffNumTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        submitB1 = new javax.swing.JButton();
        desTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        foneTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        sunTF = new javax.swing.JTextField();
        departTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        titleTF = new javax.swing.JTextField();
        tiL = new javax.swing.JLabel();
        sexTF = new javax.swing.JTextField();
        dateTF = new javax.swing.JTextField();
        bloodTF = new javax.swing.JTextField();
        stateTF = new javax.swing.JTextField();
        lgaTF = new javax.swing.JTextField();
        TLabe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("First Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Middle Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Staff NO:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Department:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Sex:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("State:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("LGA:");

        submitB1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        submitB1.setText("Next >>");
        submitB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitB1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Residential Address:");

        addTA.setColumns(20);
        addTA.setRows(5);
        jScrollPane1.setViewportView(addTA);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Phone Number:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Blood Group:");

        sunTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sunTFActionPerformed(evt);
            }
        });
        sunTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sunTFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sunTFKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Designation:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Date of Birth:");

        tiL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiL.setText("Title:");

        javax.swing.GroupLayout ContentPaneLayout = new javax.swing.GroupLayout(ContentPane);
        ContentPane.setLayout(ContentPaneLayout);
        ContentPaneLayout.setHorizontalGroup(
            ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(submitB1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
            .addGroup(ContentPaneLayout.createSequentialGroup()
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiL, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sexTF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(midNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Fname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(staffNumTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(departTF, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(foneTF, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(desTF, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(dateTF))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sunTF, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(titleTF)))
                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stateTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bloodTF)
                                    .addComponent(lgaTF))))))
                .addGap(41, 41, 41))
        );
        ContentPaneLayout.setVerticalGroup(
            ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPaneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tiL)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sunTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(midNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(staffNumTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(159, 159, 159))
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addComponent(sexTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(bloodTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(stateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(lgaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)))
                .addComponent(submitB1)
                .addContainerGap())
        );

        TLabe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TLabe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TLabe.setText("Staff Basic Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(ContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TLabe)
                .addGap(18, 18, 18)
                .addComponent(ContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitB1ActionPerformed
        // TODO add your handling code here:

        this.ProcessStaffInformation();

    }//GEN-LAST:event_submitB1ActionPerformed

    private void sunTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sunTFActionPerformed
        // TODO add your handling code here:
        // this.CheckingStaff();
    }//GEN-LAST:event_sunTFActionPerformed

    private void sunTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sunTFKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_sunTFKeyPressed

    private void sunTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sunTFKeyReleased
        // TODO add your handling code here:
        //this.CheckingStaff();
    }//GEN-LAST:event_sunTFKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContentPane;
    private javax.swing.JTextField Fname;
    private javax.swing.JLabel TLabe;
    private javax.swing.JTextArea addTA;
    private javax.swing.JTextField bloodTF;
    private javax.swing.JTextField dateTF;
    private javax.swing.JTextField departTF;
    private javax.swing.JTextField desTF;
    private javax.swing.JTextField foneTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lgaTF;
    private javax.swing.JTextField midNameTF;
    private javax.swing.JTextField sexTF;
    private javax.swing.JTextField staffNumTF;
    private javax.swing.JTextField stateTF;
    private javax.swing.JButton submitB1;
    private javax.swing.JTextField sunTF;
    private javax.swing.JLabel tiL;
    private javax.swing.JTextField titleTF;
    // End of variables declaration//GEN-END:variables
}
