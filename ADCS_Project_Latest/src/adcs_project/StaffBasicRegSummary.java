/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Charles
 */
public class StaffBasicRegSummary extends javax.swing.JDialog {

   private Reconsoft rs=new Reconsoft();
   //private Reconsoft2 dat2=new Reconsoft2();
   private  BiometricSet1  bio;
   private  static BiometricSet1  bio1;
   private  static BiometricSet1  bio2;
   private String staffid="";
    private String InfoLogged="";

    public StaffBasicRegSummary(String id) {
       // super(parent, modal);
        this.staffid=id;
        initComponents();
         
         bio1=rs.QueryStaffNextofKinInformation(staffid);
        bio2= rs.RetriveImage(staffid);
         String surname="";String titledName[];
         if(BiometricMain.getBiometricSet1Obj()==null)
         {
             bio=rs.QueryAllStaffInformation(staffid);
             //titledName=bio.getFirstName().split(" ");
            // surname=titledName[0]+" "+titledName[1];
         kinfone.setText(bio1.getPhone());
         kinrela.setText(bio1.getRelation());
         kinNameL.setText(bio1.getKinSname()+" "+bio1.getKinFname());
         kinadd.setText(bio1.getKinAddress());
         //bio1.set
              
         }
         else
         {
              bio=BiometricMain.getBiometricSet1Obj();
             // surname=bio.getStaffSurname();
              String knisbiodata[]=  BiometricMain.getStaffkin_Biodata();
               
              /*
                 bio.StaffNum(knisbiodata[0]);
                 bio.SetKinSname(knisbiodata[1]);
                 bio.SetKinFname(knisbiodata[2]);
                 bio.SetFone(knisbiodata[3]);
                 bio.SetKinaddress(knisbiodata[4]);
                 bio.SetRelation(knisbiodata[5]);
                 */
              kinfone.setText(knisbiodata[3]);
              kinrela.setText(knisbiodata[5]);
              kinNameL.setText(knisbiodata[1]+" "+knisbiodata[2]);
              kinadd.setText(knisbiodata[4]);
               
         }
         
         staff_idL.setText(bio.getStaffNum());
         snameL.setText(bio.getStaffSurname());
         titleTF.setText(bio.getTitle());
         otherL.setText(bio.getFirstName()+" "+bio.getMiddleName());
         desL.setText(bio.getDesign());
         departL.setText(bio.getDepart());
         imageL.setBounds(02, 50, 250, 280);
         if(BiometricMain.getCaptured_Biodata()[1]!=null)
         {
              imageL.setIcon(new ImageIcon(BiometricMain.getCaptured_Biodata()[1]));
         }
         else
         {
             imageL.setIcon(new ImageIcon( bio2.getBufferedPix()));
         }
         
        
         foneL.setText(bio.getFone());
         bdateL.setText(bio.getBdate());
         addL.setText(bio.getAddress());
         stateL.setText(bio.getState());
         lgaL.setText(bio.getLga());
         groupL.setText(bio.getBloodgrp());
         sexL.setText(bio.getSex());
         
          
         
         
         
    }
     public StaffBasicRegSummary() {
       // super(parent, modal);
        //this.staffid=id;
        initComponents();
         
    }
     public static BiometricSet1 getBiometricObj()
     {
         return StaffBasicRegSummary.bio1;
         
     }

    
    
    
    
    public void CallComments()
    {
        
        AddstaffComments st= new AddstaffComments();
        st.setLocationRelativeTo(null);
        st.setResizable(false);
        st.setVisible(true);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPane = new javax.swing.JPanel();
        TLabe = new javax.swing.JLabel();
        bioPane = new javax.swing.JPanel();
        imageL = new javax.swing.JLabel();
        staff_idL = new javax.swing.JLabel();
        snameL = new javax.swing.JLabel();
        otherL = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        desL = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        departL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        foneL = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bdateL = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        addL = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        groupL = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sexL = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        stateL = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lgaL = new javax.swing.JLabel();
        staff_idL1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        titleTF = new javax.swing.JLabel();
        kinPane = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        kinNameL = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kinfone = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        kinrela = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        kinadd = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        TLabe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TLabe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TLabe.setText("CONFIRMS AND EDIT OR SAVE STAFF BASIC BIO-DATA");

        bioPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Staff Basic Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        imageL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adcs_project/std_pix/206img.jpg"))); // NOI18N

        staff_idL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        staff_idL.setForeground(new java.awt.Color(0, 0, 204));

        snameL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        otherL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Title");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Othernames:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Designation:");

        desL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Department:");

        departL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Phone Number:");

        foneL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Date of Birth:");

        bdateL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Address:");

        addL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addL.setText("6A Sule Abuka Street Off Opebi Ikeja Lagos State.");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Blood Group:");

        groupL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        groupL.setText("AB+");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Sex:");

        sexL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("State:");

        stateL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stateL.setText("Anambra State");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("LGA:");

        lgaL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lgaL.setText("Ayamelum");

        staff_idL1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        staff_idL1.setForeground(new java.awt.Color(0, 0, 204));
        staff_idL1.setText("STAFF NO: ");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Surname:");

        titleTF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout bioPaneLayout = new javax.swing.GroupLayout(bioPane);
        bioPane.setLayout(bioPaneLayout);
        bioPaneLayout.setHorizontalGroup(
            bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bioPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bioPaneLayout.createSequentialGroup()
                        .addComponent(imageL)
                        .addGap(18, 18, 18)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bioPaneLayout.createSequentialGroup()
                                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bioPaneLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(desL, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(departL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(foneL, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bdateL, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64))))
                            .addGroup(bioPaneLayout.createSequentialGroup()
                                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createSequentialGroup()
                                        .addComponent(staff_idL1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(staff_idL, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(121, 121, 121))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(otherL, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(bioPaneLayout.createSequentialGroup()
                                                .addComponent(snameL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createSequentialGroup()
                                                .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70))))))))
                    .addGroup(bioPaneLayout.createSequentialGroup()
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bioPaneLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lgaL, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bioPaneLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupL, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sexL, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stateL, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addL, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        bioPaneLayout.setVerticalGroup(
            bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bioPaneLayout.createSequentialGroup()
                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bioPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imageL))
                    .addGroup(bioPaneLayout.createSequentialGroup()
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bioPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(bioPaneLayout.createSequentialGroup()
                                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(staff_idL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(staff_idL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addComponent(titleTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(snameL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(otherL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(desL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foneL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bdateL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sexL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(bioPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lgaL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        kinPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Next of Kin Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Name:");

        kinNameL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Phone Number:");

        kinfone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Relationship:");

        kinrela.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Address:");

        kinadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout kinPaneLayout = new javax.swing.GroupLayout(kinPane);
        kinPane.setLayout(kinPaneLayout);
        kinPaneLayout.setHorizontalGroup(
            kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kinPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kinPaneLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(kinrela, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kinPaneLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kinfone, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kinPaneLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kinNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kinPaneLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kinadd, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kinPaneLayout.setVerticalGroup(
            kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kinPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kinNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(kinfone, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(kinrela, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kinPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(kinadd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        backButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        backButton.setText("<<Go Back and Modify");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Save Permanently");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kinPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPaneLayout.createSequentialGroup()
                        .addComponent(bioPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(415, 415, 415))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bioPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(kinPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(backButton))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        //Reconsoft rs=new Reconsoft
       
     AddStaffInfor as=   new AddStaffInfor(bio,true);
              as.setLocationRelativeTo(null);
              as.setVisible(true);
         this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        BiometricSet1 bs;
        backButton.setEnabled(false);
        if(JOptionPane.showConfirmDialog(null,"Are you sure that you want to PERMANENTLY SAVE your basic data.\nBe warned! that you CANNOT REVERSE or EDIT any of these \ndata by yourself once saved to database and smartcard?\nPlease are you sure that you have CROSSCHECKED all your basic data?")==0){
               
             if(BiometricMain.getBiometricSet1Obj()==null){
                   //JOptionPane.showMessageDialog(this, "The BiometricSet obj is NULL");
                   
                   //return;
                 bs =bio;
               }
               else
               {
                   bs=BiometricMain.getBiometricSet1Obj();
               }
                 rs.UpdateAllStaffInformation(bs);
                // dat2.UpdateAllStaffInformation(bs);
               String knisbiodata[]=  BiometricMain.getStaffkin_Biodata();
               //BiometricMain.setStaffkin_Biodata(staffid, 0);
               /*
         BiometricMain.setStaffkin_Biodata(sname, 1);
          BiometricMain.setStaffkin_Biodata(fname, 2);
           BiometricMain.setStaffkin_Biodata(fone, 3);
            BiometricMain.setStaffkin_Biodata(add, 4);
            BiometricMain.setStaffkin_Biodata(rela, 5);
            */
                 bio.StaffNum(knisbiodata[0]);
                 bio.SetKinSname(knisbiodata[1]);
                 bio.SetKinFname(knisbiodata[2]);
                 bio.SetFone(knisbiodata[3]);
                 bio.SetKinaddress(knisbiodata[4]);
                 bio.SetRelation(knisbiodata[5]);
                 rs.UpdateStaffNextOfKinsInformation(bio);
               //  dat2.UpdateStaffNextOfKinsInformation(bio);
                 rs.UpdateUserStatus("staff_registration", "staffNum", "6c", AdminLogin.student_staffid);
                // dat2.UpdateUserStatus("staff_registration", "staffNum", "6c", AdminLogin.student_staffid);
                 StaffCapturedInformation  st= new StaffCapturedInformation();
                 InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "COMPLETED THE REGISTRATION OF THE BASIC INFORMATION OF STAFF-that is still registering- WITH STAFF ID:"+staffid);
               AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
                 st.BioInform();
                 BiometricMain.setStaffObj(st);
                 this.setVisible(false);
                
            }
            else
            {
               
                 backButton.setEnabled(true);
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TLabe;
    private javax.swing.JLabel addL;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel bdateL;
    private javax.swing.JPanel bioPane;
    private javax.swing.JLabel departL;
    private javax.swing.JLabel desL;
    private javax.swing.JLabel foneL;
    private javax.swing.JLabel groupL;
    private javax.swing.JLabel imageL;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel kinNameL;
    private javax.swing.JPanel kinPane;
    private javax.swing.JLabel kinadd;
    private javax.swing.JLabel kinfone;
    private javax.swing.JLabel kinrela;
    private javax.swing.JLabel lgaL;
    private javax.swing.JPanel mainPane;
    private javax.swing.JLabel otherL;
    private javax.swing.JLabel sexL;
    private javax.swing.JLabel snameL;
    private javax.swing.JLabel staff_idL;
    private javax.swing.JLabel staff_idL1;
    private javax.swing.JLabel stateL;
    private javax.swing.JLabel titleTF;
    // End of variables declaration//GEN-END:variables
public static void main(String args[])
{
    StaffBasicRegSummary sbs=new StaffBasicRegSummary("MP 006");
    sbs.setVisible(true);
}

}

