/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;


import javax.swing.*;

/**
 *
 * @author Charles
 */
public class AddStaffInfor extends JFrame {

    /**
     * Creates new form AddStaffInfor
     */
     private String StaffNum[]=BiometricMain.getDB_Biodata();
     private InformationChecker check = new  InformationChecker();
     private String sex,bdate,surname,title;
     private boolean IsecondTime=false;
     private String InfoLogged="";
     private Reconsoft rs=new Reconsoft();
     private  BiometricSet1  bio;
     
    public AddStaffInfor() {
        
    setIconImage(new ImageIcon(getClass().getResource("see.jpg")).getImage()); 
        initComponents();
        lgaCB.setEnabled(false);
        DeActivateStaffEntryComponents();
        tiL.setVisible(false);
        titleTF.setVisible(false);
        
 }
    public AddStaffInfor(BiometricSet1  stabio,boolean Issctime) {
        
        initComponents();
       // lgaCB.setEnabled(false);
        //titleTF.setVisible(false);
       // titleTF.setText(stabio);
        this.bio=stabio;
        this.IsecondTime=Issctime;
        String titles[],titledName[],bdate[];
        String sname;
        sname = "";
        titles = this.BuildItems(titleCB);
        //titleCB.g
       // titledName = stabio.getStaffSurname().split(" ");
        bdate=stabio.getBdate().split("/");
        //sname=stabio.getStaffSurname();
        ///if(titledName.length==1)
        //{
           // titledName=stabio.getStaffSurname().split(" ");
            //sname=titledName[1];
        //}
        
        //JOptionPane.showMessageDialog(null, "The value of db title  is: "+stabio.getTitle());
        
        titleCB.setSelectedIndex(this.SearchObjectsPosition(titles, stabio.getTitle()));
        sunTF.setText(stabio.getStaffSurname());
        Fname.setText(stabio.getFirstName());
       
        midNameTF.setText(stabio.getMiddleName());
        staffNumTF.setText(stabio.getStaffNum());
        departTF.setText(stabio.getDepart());
        foneTF.setText(stabio.getFone());
        //stabio.getBdate();
        desTF.setText(stabio.getDesign());
        switch (stabio.getSex()) {
            case "Male":
                maRB.setSelected(true);
                break;
            case "Female":
                feRB.setSelected(true);
                break;
        }
        addTA.setText(stabio.getAddress());
        
        //String bg1=(String)bg1[]
      
        String grp[] = this.BuildItems(groupCB);
        String state[] = this.BuildItems(stateCB);
        String d[] = this.BuildItems(dayCB);
        String m[] = this.BuildItems(monthCB);
        String y[] = this.BuildItems(yearCB);
        
        
        groupCB.setSelectedIndex(this.SearchObjectsPosition(grp, stabio.getBloodgrp()));
        stateCB.setSelectedIndex(this.SearchObjectsPosition(state, stabio.getState()));
        dayCB.setSelectedIndex(this.SearchObjectsPosition(d,bdate[0]));
        monthCB.setSelectedIndex(this.SearchObjectsPosition(m, bdate[1]));
        yearCB.setSelectedIndex(this.SearchObjectsPosition(y, bdate[2]));
        
       // String sta = (String)stateCB.getSelectedItem();
            
            lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofLga(stabio.getState()) ));
            lgaCB.setEnabled(true);
        String lga[] = this.BuildItems(lgaCB);
        lgaCB.setSelectedIndex(this.SearchObjectsPosition(lga, stabio.getLga()));
        
       
        
        
        
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
    

    
    
    
    public void ActivateLga()
    {
        if(stateCB.getSelectedIndex()==0)
        {
            lgaCB.setEnabled(false);
        }else
        {
            String sta = (String)stateCB.getSelectedItem();
            
            lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofLga(sta) ));
            lgaCB.setEnabled(true);
            //System.out.println(new Reconsoft().CountLga(sta));
            
            
        }
    }
    
    public void ActivateOtherTitle()
    {
        if(titleCB.getSelectedItem().equals("others"))
        {
          titleTF.setVisible(true);
           tiL.setVisible(true);
            ti2L.setVisible(false);
          titleCB.setVisible(false);
        }else{
           titleTF.setVisible(false);
           tiL.setVisible(false);
          titleCB.setVisible(true); 
          ti2L.setVisible(true);
        }
    }
    
    
     public void ParameterInfor(){
      if(maRB.isSelected()){
         feRB.setEnabled(false);
      }else {
             maRB.setEnabled(true);
            feRB.setEnabled(true);
        if(feRB.isSelected()){
          maRB.setEnabled(false);
            feRB.setEnabled(true);
        }else{
            maRB.setEnabled(true);
            feRB.setEnabled(true);
        }
      } 
    }
      private void DeActivateStaffEntryComponents()
    {   //method for begins
       
        sunTF.setEditable(false);
        Fname.setEditable(false);
        midNameTF.setEditable(false);
       // staffNumTF.setText(null);
        departTF.setEditable(false);
        desTF.setEditable(false);
         addTA.setEnabled(false);
        foneTF.setEditable(false);
        groupCB.setEnabled(false);
        stateCB.setEnabled(false);
        lgaCB.setEnabled(false);
        titleCB.setEnabled(false);
        dayCB.setEnabled(false);
        monthCB.setEnabled(false);
        yearCB.setEnabled(false);
        submitB1.setVisible(false);
        
    }   //m
     public void ActivateStaffEntryComponents()
    {   //method for begins
       
        sunTF.setEditable(true);
        Fname.setEditable(true);
        midNameTF.setEditable(true);
       // staffNumTF.setText(null);
        departTF.setEditable(true);
        desTF.setEditable(true);
         addTA.setEnabled(true);
        foneTF.setEditable(true);
        groupCB.setEnabled(true);
        stateCB.setEnabled(true);
        
        lgaCB.setEnabled(false);
        
        titleCB.setEnabled(true);
        dayCB.setEnabled(true);
        monthCB.setEnabled(true);
        yearCB.setEnabled(true);
        submitB1.setVisible(true);
       // checkB.setVisible(false);
    }   //method brace ends
     
     
     public void CheckingStaff()
    {
    Reconsoft dat = new Reconsoft(); 
 //   Reconsoft2 dat2 = new Reconsoft2(); 
   
    String staffid=this.CheckStaffNum(staffNumTF.getText());
    
    if(staffid==null || staffid.equals(""))
    {
      //JOptionPane.showMessageDialog(null, "Staff Number Should Not be Empty");    
    }else
    {
    if(dat.CheckExistingStaff(staffid))
    {
     this.ActivateStaffEntryComponents();

    }else
    {
        if(dat.CheckExistingNextofKin(staffid))
        {
            //call next of kin form if next is not existing in database
            JOptionPane.showMessageDialog(null,"Staff's Basic Information already Exist"
                    + "\n please you will be move to Staff's Next of Kin Form");
           
          this.NextItem();
          this.setVisible(false);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else
        {if(dat.CheckExistingBiometric(staffid))
        {
            BiometricMain.setWhoIsRegistering("staff");
            JOptionPane.showMessageDialog(null,"Staff's Basic  and Next of Kin Information already Exist"
                    + "\n please you will be move to Staff's data Capturing");
             this.setVisible(false);
             this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             ImageCapturing b = new ImageCapturing();
      
          
        }else
        {
         JOptionPane.showMessageDialog(null,"This Staff's Complete Information already Exist"
                     + "\n Thanks for your registration");   
         DeActivateStaffEntryComponents();
        }   
        }
    }
  }   
    
    
    
    } 
     
     
    public void AddStaffInformation(){
        
       
        surname= this.CheckSurName(sunTF.getText());
         if(titleCB.getSelectedItem().equals("others"))
        {
           title=titleTF.getText();
                  //this.CheckTFtitle(titleTF.getText());
            //surname=title+" "+surname2;
        }else{
           title=this.CheckTitle((String)titleCB.getSelectedItem());
             //title=titleTF.getText();
            //surname=title+" "+surname2;
            }
        String fname=this.CheckFirstName(Fname.getText());
        String mid= this.CheckMiddleName(midNameTF.getText());
        String staffid=this.CheckStaffNum(staffNumTF.getText());
        String depart=this.Checkdepartment(departTF.getText());
        String design=this.CheckDesignation(desTF.getText());
        String fon=this.CheckPhoneNum(foneTF.getText());
        String address=this.CheckAddress(addTA.getText());
        // String group=(String)groupCB.getSelectedItem();
        String d=this.CheckDay((String)dayCB.getSelectedItem());
        String m=this.CheckMonth((String)monthCB.getSelectedItem());
        String y=this.CheckYear((String)yearCB.getSelectedItem());
         bdate=d+"/"+m+"/"+y;
        String group=this.CheckBloodGroup((String)groupCB.getSelectedItem());
        String stat=(String)stateCB.getSelectedItem();
        String lga=(String)lgaCB.getSelectedItem();
        String status="1c";
        
           Reconsoft data = new Reconsoft();
        // Reconsoft2 dat2 = new Reconsoft2(); 
       
        
         if(maRB.isSelected()){
             sex=maRB.getText();
             //data.AddStaffInformationTodb(infor,ma);
         }else if(feRB.isSelected()){
           sex=feRB.getText();
           // data.AddStaffInformationTodb(infor,fe);
          }
         
         if(surname==null ||fname==null||mid==null||fon==null||title==null||depart==null||staffid==null||group==null||design==null)
         {
          JOptionPane.showMessageDialog(null, "Empty Registration is not Allowed for this Staff");   
         }
         else
         {
              if(data.CheckExistingStaff(staffid))
                {
              BiometricSet1  infor = new BiometricSet1(fname,title,surname,mid,address,fon,depart,lga,stat,staffid,group,design);
              data.AddStaffInformationTodb(infor,sex,bdate,status);
              //dat2.AddStaffInformationTodb(infor,sex,bdate,status);
               StaffNum[2]=staffid;
               BiometricMain.setWhoIsRegistering("staff");
               BiometricMain.Setuserid(StaffNum[2], 3);
               JOptionPane.showMessageDialog(null, "Information Submitted Successfully ");
               InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED BASIC INFORMATION OF A STAFF-that is still registering- WITH STAFF ID:"+staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
               this.ClearInformation();
                this.NextItem();
               
                }
              else
              { 
                  if(data.CheckExistingBiometric(staffid))
                  {
                    StaffNum[2]=staffid;
                  BiometricMain.setWhoIsRegistering("staff");
             JOptionPane.showMessageDialog(null,"Staff Basic Information already Exist"
                     + "\n please Capture another Staff's  information");
               
                  }else{
                    JOptionPane.showMessageDialog(null,"Staff Complete Information already Exist"
                     + "\n Thanks for your registration");
              
                  }
                  
            
              }
        
         }
    }
    
    public void MoveStaffInformationForward(){
        
       
         surname= this.CheckSurName(sunTF.getText());
        if(titleCB.getSelectedItem().equals("others"))
        {
           title=titleTF.getText();
                  // this.CheckTFtitle(titleTF.getText());
            
        }else{
            title=this.CheckTitle((String)titleCB.getSelectedItem());
             //title=titleTF.getText();
           
            }

        //String surname=surname2;
        String fname=this.CheckFirstName(Fname.getText());
        String mid= this.CheckMiddleName(midNameTF.getText());
        String staffid=this.CheckStaffNum(staffNumTF.getText());
        String depart=this.Checkdepartment(departTF.getText());
        String design=this.CheckDesignation(desTF.getText());
        String fon=this.CheckPhoneNum(foneTF.getText());
        String address=this.CheckAddress(addTA.getText());
        // String group=(String)groupCB.getSelectedItem();
        String d=this.CheckDay((String)dayCB.getSelectedItem());
        String m=this.CheckMonth((String)monthCB.getSelectedItem());
        String y=this.CheckYear((String)yearCB.getSelectedItem());
         bdate=d+"/"+m+"/"+y;
        String group=this.CheckBloodGroup((String)groupCB.getSelectedItem());
        String stat=(String)stateCB.getSelectedItem();
        String lga=(String)lgaCB.getSelectedItem();
        
        
          // Reconsoft data = new Reconsoft();
        
       
        
         if(maRB.isSelected()){
             sex=maRB.getText();
             //data.AddStaffInformationTodb(infor,ma);
         }else if(feRB.isSelected()){
           sex=feRB.getText();
           // data.AddStaffInformationTodb(infor,fe);
          }
         
         if(surname==null ||fname==null||mid==null||fon==null||title==null||depart==null||staffid==null||group==null||design==null)
         {
          JOptionPane.showMessageDialog(null, "Empty Registration is not Allowed for this Staff");   
         }
         else
         {
              
              BiometricSet1  infor = new BiometricSet1(fname,title,surname,mid,address,fon,depart,lga,stat,staffid,group,design);
             //data.AddStaffInformationTodb(infor,sex,bdate);
             infor.Sex(sex);
             infor.SetBdate(bdate);
               StaffNum[2]=staffid;
               BiometricMain.setWhoIsRegistering("staff");
               InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO MODIFY THE BASIC INFORMATION OF A STAFF-that is still registering- WITH STAFF ID:"+staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
               this.ClearInformation();
               BiometricMain.SetBiometricSet1Obj(infor);
               
               
              
                //this.NextItem();
               
                }
             
        
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
        groupCB.setSelectedIndex(0);
        stateCB.setSelectedIndex(0);
        lgaCB.setSelectedIndex(0);
        titleCB.setSelectedIndex(0);
        dayCB.setSelectedIndex(0);
        monthCB.setSelectedIndex(0);
        yearCB.setSelectedIndex(0);
    }
    
    
    
    public void NextItem()
    {
        this.setVisible(false);
        BiometricMain.setWhoIsRegistering("staff");
        AddStaffKin kin = new AddStaffKin();
       kin.setLocationRelativeTo(null);
       kin.setVisible(true);
     //kin.setResizable(false);
       kin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        
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
     
       
         private String CheckDay(String da)
    {
        
      if(dayCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Day Not Selected"); 
        return null;
      }else{
          
            return da;
      } 
      
    }  
         
         
           private String CheckMonth(String mon)
    {
        
      if(monthCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Month Not Selected"); 
        return null;
      }else{
          
            return mon;
      } 
      
    }  
     
           
      private String CheckYear(String yrs)
    {
        
      if(yearCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "year Not Selected"); 
        return null;
      }else{
          
            return yrs;
      } 
      
    }         
           
           
        
        
       private String CheckBloodGroup(String group)
    {
        
      if(groupCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Blood Group Not Selected"); 
        return null;
      }else{
          
            return group;
      } 
      
    }  
         
         
 private String CheckFaculty(String fac)
    {
        
      if(!fac.equals("") || fac != null ) 
      {
       check.checkDepart(fac);
       return fac;
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Faculty Field, Enter letter A-Z"); 
        return null;
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
      
      
      
       private String CheckStaffNum(String staffnum2)
    {
        
      if(staffnum2.equals("") ||staffnum2 == null ) 
      {
        JOptionPane.showMessageDialog(null, "Staff number should not be Empty"); 
        return null;
      }else if(check.checkstaffnum(staffnum2)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in staff number Field,\n"
                  + "Staff number format is two Characters followed by a space and  three digits e.g SP 139"); 
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

        jTextField1 = new javax.swing.JTextField();
        MainPan = new javax.swing.JPanel();
        TLabe = new javax.swing.JLabel();
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
        feRB = new javax.swing.JRadioButton();
        maRB = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        stateCB = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        lgaCB = new javax.swing.JComboBox();
        submitB1 = new javax.swing.JButton();
        ti2L = new javax.swing.JLabel();
        desTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        foneTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        groupCB = new javax.swing.JComboBox();
        sunTF = new javax.swing.JTextField();
        departTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        titleCB = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        dayCB = new javax.swing.JComboBox();
        monthCB = new javax.swing.JComboBox();
        yearCB = new javax.swing.JComboBox();
        tiL = new javax.swing.JLabel();
        titleTF = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TLabe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TLabe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TLabe.setText("Staff Basic Information");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("First Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Middle Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Staff NO:");

        staffNumTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                staffNumTFFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Department:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Sex:");

        feRB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        feRB.setText("Female");
        feRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feRBActionPerformed(evt);
            }
        });

        maRB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        maRB.setText("Male");
        maRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maRBActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("State:");

        stateCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stateCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", " ", "Abia state", "Anambra state", "Akwa Ibom state", "Adamawa state", "Bauchi state", "Bayelsa state", "Benue state", "Borno state", "Cross River state", "Delta state", "Ebonyi state", "Enugu state", "Edo state", "Ekiti state", "Gombe state", "Imo state", "Jigawa state", "Kaduna state", "Kano state", "Katsina state", "Kebbi state", "Kogi state", "Kwara state", "Lagos state", "Nasarawa state", "Niger state", "Ogun state", "Ondo state", "Osun state", "Oyo state", "Plateau state", "Rivers state", "Sokoto state", "Taraba state", "Yobe state", "Zamfara state", "FCT", " " }));
        stateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateCBActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("LGA:");

        lgaCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Ikorodu", "Enugu-North", "Asba", "Onitsha-North", "Ikeja" }));

        submitB1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        submitB1.setText("Next >>");
        submitB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitB1ActionPerformed(evt);
            }
        });

        ti2L.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ti2L.setText("Title:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Residential Address:");

        addTA.setColumns(20);
        addTA.setRows(5);
        jScrollPane1.setViewportView(addTA);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Phone Number:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Blood Group:");

        groupCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        groupCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", " ", "A", "B", "AB", "O+", "A-", "A+", "B+", "B-", "AB-", "O-" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Designation:");

        titleCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titleCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "Mr.", "Mrs.", "Miss.", "Dr.", "Prof.", "Chief.", "Dr(Mrs)", "Dr(Miss)", "Prof(Mrs)", "Barr.", "Engr.", "Engr(Mrs)", "Chief(Dr)", "Rt Col.", "Arch", "Rev.", "Rev. Fr.", "Rev. Fr.Dr", "Rev. Sr.", "Lt Col (rtd)", "others" }));
        titleCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleCBActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Date of Birth:");

        dayCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dayCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        monthCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        monthCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        monthCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthCBActionPerformed(evt);
            }
        });

        yearCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        yearCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", " " }));
        yearCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearCBActionPerformed(evt);
            }
        });

        tiL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiL.setText("Title:");

        javax.swing.GroupLayout ContentPaneLayout = new javax.swing.GroupLayout(ContentPane);
        ContentPane.setLayout(ContentPaneLayout);
        ContentPaneLayout.setHorizontalGroup(
            ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(midNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(groupCB, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stateCB, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addComponent(sunTF)
                            .addComponent(titleCB, 0, 178, Short.MAX_VALUE)
                            .addComponent(titleTF)))
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
                        .addGap(18, 18, 18)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ContentPaneLayout.createSequentialGroup()
                                        .addComponent(maRB)
                                        .addGap(48, 48, 48)
                                        .addComponent(feRB))
                                    .addGroup(ContentPaneLayout.createSequentialGroup()
                                        .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Fname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(staffNumTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(departTF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(desTF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(submitB1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
            .addGroup(ContentPaneLayout.createSequentialGroup()
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ti2L, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tiL, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ContentPaneLayout.setVerticalGroup(
            ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPaneLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tiL)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ti2L)
                    .addComponent(titleCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maRB)
                            .addComponent(feRB))
                        .addGap(18, 18, 18)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(groupCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stateCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(25, 25, 25)))
                .addComponent(submitB1)
                .addContainerGap())
        );

        javax.swing.GroupLayout MainPanLayout = new javax.swing.GroupLayout(MainPan);
        MainPan.setLayout(MainPanLayout);
        MainPanLayout.setHorizontalGroup(
            MainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanLayout.createSequentialGroup()
                .addGroup(MainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(ContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(TLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        MainPanLayout.setVerticalGroup(
            MainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanLayout.createSequentialGroup()
                .addComponent(TLabe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(ContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitB1ActionPerformed
        // TODO add your handling code here:
        if(IsecondTime)
        {
            this.MoveStaffInformationForward();
          AddStaffKin as= new  AddStaffKin(StaffBasicRegSummary.getBiometricObj(),true);
          as.setLocationRelativeTo(null);
          as.setVisible(true);
          this.setVisible(false);
        }
        else
        {
             this.AddStaffInformation();
        }
     
      
    }//GEN-LAST:event_submitB1ActionPerformed

    private void maRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maRBActionPerformed
        // TODO add your handling code here:
        this.ParameterInfor();
    }//GEN-LAST:event_maRBActionPerformed

    private void feRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feRBActionPerformed
        // TODO add your handling code here:
        this.ParameterInfor();
    }//GEN-LAST:event_feRBActionPerformed

    private void stateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateCBActionPerformed
        // TODO add your handling code here:
        ActivateLga();
    }//GEN-LAST:event_stateCBActionPerformed

    private void monthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthCBActionPerformed

    private void yearCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearCBActionPerformed

    private void titleCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleCBActionPerformed
        // TODO add your handling code here:
        ActivateOtherTitle();
    }//GEN-LAST:event_titleCBActionPerformed

    private void staffNumTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staffNumTFFocusLost
        // TODO add your handling code here:
        this.CheckingStaff();
    }//GEN-LAST:event_staffNumTFFocusLost

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContentPane;
    private javax.swing.JTextField Fname;
    private javax.swing.JPanel MainPan;
    private javax.swing.JLabel TLabe;
    private javax.swing.JTextArea addTA;
    private javax.swing.JComboBox dayCB;
    private javax.swing.JTextField departTF;
    private javax.swing.JTextField desTF;
    private javax.swing.JRadioButton feRB;
    private javax.swing.JTextField foneTF;
    private javax.swing.JComboBox groupCB;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox lgaCB;
    private javax.swing.JRadioButton maRB;
    private javax.swing.JTextField midNameTF;
    private javax.swing.JComboBox monthCB;
    private javax.swing.JTextField staffNumTF;
    private javax.swing.JComboBox stateCB;
    private javax.swing.JButton submitB1;
    private javax.swing.JTextField sunTF;
    private javax.swing.JLabel ti2L;
    private javax.swing.JLabel tiL;
    private javax.swing.JComboBox titleCB;
    private javax.swing.JTextField titleTF;
    private javax.swing.JComboBox yearCB;
    // End of variables declaration//GEN-END:variables
}
