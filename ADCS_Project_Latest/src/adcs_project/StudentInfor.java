/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Charles
 */
public class StudentInfor extends javax.swing.JDialog {
    private String bioinfo[]=BiometricMain.getDB_Biodata();
    private InformationChecker check = new  InformationChecker();
    private boolean IsecondTime=false;
    private Reconsoft rs=new Reconsoft();
    private String InfoLogged="";
     private  BiometricSet1  bio;
    
    private String sex,bdate;

    /**
     * Creates new form StudentInfor
     */
    public StudentInfor() {
        
        initComponents();
        lgaCB.setEnabled(false);
        DeActivateStaffEntryComponents();
    }
    
     public StudentInfor(BiometricSet1  stabio,boolean Issctime) {
        
        initComponents();
       // lgaCB.setEnabled(false);
        //titleTF.setVisible(false);
       // titleTF.setText(stabio);
        this.bio=stabio;
        this.IsecondTime=Issctime;
        String session[],titledName[],bdate[];
        String sname;
        sname = "";
        session = this.BuildItems(sessCB);
        //titleCB.g
        
        bdate=stabio.getBdate().split("/");
        sname=stabio.getStudentSurname();
        ///if(titledName.length==1)
        //{
           // titledName=stabio.getStaffSurname().split(" ");
           
        //}
        
        //JOptionPane.showMessageDialog(null, "The value of splitted title  is: "+titledName[0]);
        
        
        fnTF.setText(sname);
        otherTF.setText(stabio.getStudentOname());
       
        mnameTF.setText(stabio.getStudMiddlename());
        matTF.setText(stabio.getMatric());
        addTA.setText(stabio.getAddress());
        
        foneTF.setText(stabio.getFone());
        //stabio.getBdate();
        
        switch (stabio.getSex()) {
            case "Male":
                mRB.setSelected(true);
                break;
            case "Female":
                fRB.setSelected(true);
                break;
        }
        addTA.setText(stabio.getAddress());
        
        //String bg1=(String)bg1[]
      
        String grp[] = this.BuildItems(groupCB);
        String state[] = this.BuildItems(stateCB);
        String d[] = this.BuildItems(dayCB);
        String m[] = this.BuildItems(monthCB);
        String y[] = this.BuildItems(yearCB);
        String col[] = this.BuildItems(colCB);
        String dpt[] = this.BuildItems(departCB);
         String lev[] = this.BuildItems(levCB);
        
        
        groupCB.setSelectedIndex(this.SearchObjectsPosition(grp, stabio.getBloodgrp()));
        stateCB.setSelectedIndex(this.SearchObjectsPosition(state, stabio.getState()));
        dayCB.setSelectedIndex(this.SearchObjectsPosition(d,bdate[0]));
        monthCB.setSelectedIndex(this.SearchObjectsPosition(m, bdate[1]));
        yearCB.setSelectedIndex(this.SearchObjectsPosition(y, bdate[2]));
        sessCB.setSelectedIndex(this.SearchObjectsPosition(session, stabio.getSess()));
        colCB.setSelectedIndex(this.SearchObjectsPosition(col, stabio.getFac()));
        departCB.setSelectedIndex(this.SearchObjectsPosition(dpt, stabio.getDepart()));
        levCB.setSelectedIndex(this.SearchObjectsPosition(lev, stabio.getLevel()));
        
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
    private void DeActivateStaffEntryComponents()
    {   //method for begins
       
        fnTF.setEditable(false);
        mnameTF.setEditable(false);
        otherTF.setEditable(false);
       // matTF.setEditable(false);
      
        departCB.setEnabled(false);
       // fRB.resetKeyboardActions();
        stateCB.setEnabled(false);
        lgaCB.setEnabled(false);
        
        colCB.setEnabled(false);
        foneTF.setEditable(false);
        groupCB.setEnabled(false);
        levCB.setEnabled(false);
        sessCB.setEnabled(false);
        dayCB.setEnabled(false);
        monthCB.setEnabled(false);
        yearCB.setEnabled(false);
        addTA.setEditable(false);
        submitB1.setVisible(false);
        
    }   //method brace ends 
    
    
    
    //activate the staff entry components
   public void ActivateStaffEntryComponents()
    {   //method for begins
       
        fnTF.setEditable(true);
        mnameTF.setEditable(true);
        otherTF.setEditable(true);
       // matTF.setEditable(false);
      
        departCB.setEnabled(false);
       // fRB.resetKeyboardActions();
        stateCB.setEnabled(true);
        lgaCB.setEnabled(false);
        
        colCB.setEnabled(true);
        foneTF.setEditable(true);
        groupCB.setEnabled(true);
        levCB.setEnabled(true);
        sessCB.setEnabled(true);
        dayCB.setEnabled(true);
        monthCB.setEnabled(true);
        yearCB.setEnabled(true);
        addTA.setEditable(true);
        checkB.setVisible(false);
        submitB1.setVisible(true);
        
    }   //method brace ends
    
    
public void CheckingStudent()
    {
    Reconsoft dat = new Reconsoft(); 
   // Reconsoft2 dat2 = new Reconsoft2(); 
    
    String reg_no=this.CheckMatricNum(matTF.getText());
    
    if(reg_no==null || reg_no.equals(""))
    {
      //JOptionPane.showMessageDialog(null, "Student Matric Number Should Not be Empty");    
    }else
    {
     if(dat.CheckExistingStudent(reg_no))
    {
     this.ActivateStaffEntryComponents();

    }else
    {
        if(dat.CheckExistingNextofKin(reg_no))
        {
            //call next of kin form if next is not existing in database
            JOptionPane.showMessageDialog(null,"Student's Basic Information already Exist"
                    + "\n please you will be move to Student's Next of Kin Form");
           
          this.NextItem();
          this.setVisible(false);
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else
        {if(dat.CheckExistingBiometric(reg_no))
        {
            BiometricMain.setWhoIsRegistering("student");
            JOptionPane.showMessageDialog(null,"Student's Basic  and Next of Kin Information already Exist"
                    + "\n please you will be move to Student's data Capturing");
             this.setVisible(false);
             this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             ImageCapturing b = new ImageCapturing();
      
          
        }else
        {
         JOptionPane.showMessageDialog(null,"Student's Complete Information already Exist"
                     + "\n Thanks for your registration");   
          DeActivateStaffEntryComponents();
        }   
        }
    }   
  }
    
 }
    
    
    
    public void AddStudentsBasicInfo()
    {
        Reconsoft rs=new Reconsoft();
       // Reconsoft2 dat2 = new Reconsoft2();
        
        String fname=this.CheckSurName(fnTF.getText());
        String mname=this.CheckMiddleName(mnameTF.getText());
        String oname=this.CheckFirstName(otherTF.getText());
        String reg_no=this.CheckMatricNum(matTF.getText());
        String fac=this.CheckCollege((String)colCB.getSelectedItem());
        String dept=this.Checkdepartment((String)departCB.getSelectedItem());
        String fone=this.CheckPhoneNum(foneTF.getText());
        String address=this.CheckAddress(addTA.getText());
        String group=this.CheckBloodGroup((String)groupCB.getSelectedItem()); 
        String d=(String)dayCB.getSelectedItem();
        String m=(String)monthCB.getSelectedItem();
        String y=(String)yearCB.getSelectedItem();
        bdate=d+"/"+m+"/"+y;
        //String male=mRB.getText();
        //String female=fRB.getText();
        if(mRB.isSelected()){
             sex=mRB.getText();
             //data.AddStaffInformationTodb(infor,ma);
         }else if(fRB.isSelected()){
           sex=fRB.getText();
           // data.AddStaffInformationTodb(infor,fe);
          }
        String lev=this.CheckLevel((String)levCB.getSelectedItem());
        String sess=this.CheckSession((String)sessCB.getSelectedItem());
        String state=(String)stateCB.getSelectedItem();
        String lga=(String)lgaCB.getSelectedItem();
        
        if(fname==null ||mname==null ||reg_no==null ||dept==null ||mname==null ||fac==null ||levCB.getSelectedIndex()==0 ||sessCB.getSelectedIndex()==0 ||group==null||address==null||address.equals(" ") ||stateCB.getSelectedIndex()==0 ||lgaCB.getSelectedIndex()==0 )
        {
           JOptionPane.showMessageDialog(null, "Empty Registration is not Allowed: One of the field is empty"); 
        }else
        {
            if(rs.CheckExistingStudent(reg_no))
             {
               bioinfo[2]=reg_no;
               rs.AddStudentsBasicInfor(reg_no, fname, mname, oname,sex, fac, dept, lev, sess,address,fone,group,state,lga,bdate);
               //dat2.AddStudentsBasicInfor(reg_no, fname, mname, oname,sex, fac, dept, lev, sess,address,fone,group,state,lga,bdate);
          
               BiometricMain.setWhoIsRegistering("student");
               BiometricMain.Setuserid(reg_no, 3);
               rs.UpdateUserStatus("student_registration", "client_id", "1c", reg_no);
               //dat2.UpdateUserStatus("student_registration", "client_id", "1c", reg_no);
               javax.swing.JOptionPane.showMessageDialog(this,"Student Information sent Successful");
               InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED BASIC INFORMATION OF STUDENT-that is still registering- WITH MATR. NO:"+reg_no);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
               this.ClearInputs();
               this.NextItem();
               }
              else
              {
               
               
            
             if(rs.CheckExistingBiometric(reg_no))
                  {
                    bioinfo[2]=reg_no;
                 BiometricMain.setWhoIsRegistering("student");
              JOptionPane.showMessageDialog(null,"Student Basic Information already Exist"
                     + "\n please Capture this Student's biometric information");
                  }else{
                    JOptionPane.showMessageDialog(null,"Student Complete Information already Exist"
                     + "\n Thanks for your registration");
              
                  }
                }
        
         }
    }
    
    public void MoveStudentsBasicInfoForward()
    {
       // Reconsoft rs=new Reconsoft();
        
        String fname=this.CheckSurName(fnTF.getText());
        String mname=this.CheckMiddleName(mnameTF.getText());
        String oname=this.CheckFirstName(otherTF.getText());
        String reg_no=this.CheckMatricNum(matTF.getText());
        String fac=this.CheckCollege((String)colCB.getSelectedItem());
        String dept=this.Checkdepartment((String)departCB.getSelectedItem());
        String fone=this.CheckPhoneNum(foneTF.getText());
        String address=this.CheckAddress(addTA.getText());
        String group=this.CheckBloodGroup((String)groupCB.getSelectedItem()); 
        String d=(String)dayCB.getSelectedItem();
        String m=(String)monthCB.getSelectedItem();
        String y=(String)yearCB.getSelectedItem();
        bdate=d+"/"+m+"/"+y;
        //String male=mRB.getText();
        //String female=fRB.getText();
        if(mRB.isSelected()){
             sex=mRB.getText();
             //data.AddStaffInformationTodb(infor,ma);
         }else if(fRB.isSelected()){
           sex=fRB.getText();
           // data.AddStaffInformationTodb(infor,fe);
          }
        String lev=this.CheckLevel((String)levCB.getSelectedItem());
        String sess=this.CheckSession((String)sessCB.getSelectedItem());
        String state=(String)stateCB.getSelectedItem();
        String lga=(String)lgaCB.getSelectedItem();
        
        if(fname==null ||mname==null ||reg_no==null ||dept==null ||mname==null ||fac==null ||levCB.getSelectedIndex()==0 ||sessCB.getSelectedIndex()==0 ||group==null||stateCB.getSelectedIndex()==0 ||lgaCB.getSelectedIndex()==0 )
        {
           JOptionPane.showMessageDialog(null, "Empty Registration is not Allowed"); 
        }else
        {
           
               bioinfo[2]=reg_no;
               BiometricSet1 infor= new  BiometricSet1(reg_no,"", fname, mname, oname,sex, fac, dept, lev, sess,address,fone,group,state,lga,bdate);
               infor.SetBdate(bdate);
               infor.Sex(sex);
             
              // rs.AddStudentsBasicInfor(reg_no, fname, mname, oname,sex, fac, dept, lev, sess,address,fone,group,state,lga,bdate);
               BiometricMain.setWhoIsRegistering("student");
               BiometricMain.SetBiometricSet1Obj(infor);
               InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO MODIFY THE BASIC INFORMATION OF STUDENT-that is still registering- WITH MATR. NO:"+reg_no);
               AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
              
                //JOptionPane.showMessageDialog(null, "My SurName is"+ BiometricMain.getBiometricSet1Obj().getStudentSurname()); 
               this.ClearInputs();
               
               //this.NextItem();
               
              
        
         }
    }
    
    
    public void ClearInputs()
    {
        fnTF.setText("");
        mnameTF.setText("");
        otherTF.setText("");
        matTF.setText("");
        //departTF.setText("");//mRB.setText(null);
        departCB.setSelectedIndex(0);
        fRB.resetKeyboardActions();
        stateCB.setSelectedIndex(0);
        lgaCB.setSelectedIndex(0);
        //facTF.setText("");
        colCB.setSelectedIndex(0);
        foneTF.setText("");
        groupCB.setSelectedIndex(0);
        levCB.setSelectedIndex(0);
        sessCB.setSelectedIndex(0);
        dayCB.setSelectedIndex(0);
        monthCB.setSelectedIndex(0);
        yearCB.setSelectedIndex(0);
        addTA.setText("");
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
    
    
    
    
    public void ParameterInfor(){
      if(mRB.isSelected()){
         fRB.setEnabled(false);
      }else {
            mRB.setEnabled(true);
            fRB.setEnabled(true);
        if(fRB.isSelected()){
          mRB.setEnabled(false);
            fRB.setEnabled(true);
        }else{
            mRB.setEnabled(true);
            fRB.setEnabled(true);
        }
      } 
    }
    
    
    
    
    
     public void NextItem()
    {
       this.setVisible(false);
       BiometricMain.setWhoIsRegistering("student");
       AddStudentKin kin = new AddStudentKin();
       kin.setLocationRelativeTo(null);
       kin.setVisible(true);
     //kin.setResizable(false);
       kin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        
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
     
         
         
         
       private String CheckSession(String sess)
    {
        
      if(sessCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Student Session Not Selected"); 
        return null;
      }else{
          
            return sess;
      } 
      
    }     
         
         
         
         
       private String CheckLevel(String lev)
    {
        
      if(levCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Student Level Not Selected"); 
        return null;
      }else{
          
            return lev;
      } 
      
    }     
         
         
 
 
   private String CheckCollege(String col)
    {
        
      if(colCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "College Not Selected"); 
        return null;
      }else{
          
            return col;
      } 
      
    }  
        
   
   private String Checkdepartment(String depart)
    {
        
      if(colCB.getSelectedIndex()==0) 
      {
       JOptionPane.showMessageDialog(null, "Department Not Selected"); 
        return null;
      }else{
          
            return depart;
      } 
      
    }  
   
   
   
          private String CheckPhoneNum(String fone)
    {
        
      if(!fone.equals("") || fone != null ) 
      {
       check.checkGsm(fone);
       return fone;
       }else
      {
          JOptionPane.showMessageDialog(null, "Invalid Character in Phone Number Field,\n"
                  + " Phone Number format is 08067707555"); 
       return null;
      } 
      
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
     public void ActivateDepart_CB()
    {
        if(colCB.getSelectedIndex()==0)
        {
            departCB.setEnabled(false);
        }else
        {
            String coll = (String)colCB.getSelectedItem();
            
            departCB.setModel(new javax.swing.DefaultComboBoxModel(new Reconsoft().queryListofDeparts(coll)));
            departCB.setEnabled(true);
              
            
        }
    } 
    
    
    
    
    
    
    
    
    
    
    
    
   /*  public static void main(String[] args) {
        // TODO code application logic here
     StudentInfor student = new StudentInfor();
        //staff.setResizable(false);
       student .setLocationRelativeTo(null);
        student .setVisible(true); 
         //PrintSmartIdCard.printComponent(staff.staf);
      // TestPrinting test = new TestPrinting();
        ScalePrintableFormat test = new ScalePrintableFormat();
      // test.printCardImage(staff.staffidPane);
      // test.JPanelImageConverter(staff.staffidPane);
      //test.printCard(student.idcardPan);
    }
    */
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ContentPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fnTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        otherTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        matTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fRB = new javax.swing.JRadioButton();
        mRB = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        stateCB = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        lgaCB = new javax.swing.JComboBox();
        submitB1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        mnameTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        addTA = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        levCB = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        sessCB = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        foneTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dayCB = new javax.swing.JComboBox();
        monthCB = new javax.swing.JComboBox();
        yearCB = new javax.swing.JComboBox();
        colCB = new javax.swing.JComboBox();
        departCB = new javax.swing.JComboBox();
        groupCB = new javax.swing.JComboBox();
        checkB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Basic Information");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Surname:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Firstname:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Matric NO:");

        matTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                matTFMouseExited(evt);
            }
        });
        matTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                matTFFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Department:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("College:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Sex:");

        fRB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fRB.setText("Female");
        fRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fRBActionPerformed(evt);
            }
        });

        mRB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mRB.setSelected(true);
        mRB.setText("Male");
        mRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRBActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("State:");

        stateCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stateCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", " ", "Abia state", "Anambra state", "Akwa Ibom state", "Adamawa state", "Bauchi state", "Bayelsa state", "Benue state", "Borno state", "Cross River state", "Delta state", "Ebonyi state", "Enugu state", "Edo state", "Ekiti state", "Gombe state", "Imo state", "Jigawa state", "Kaduna state", "Kano state", "Katsina state", "Kebbi state", "Kogi state", "Kwara state", "Lagos state", "Nasarawa state", "Niger state", "Ogun state", "Ondo state", "Osun state", "Oyo state", "Plateau state", "Rivers state", "Sokoto state", "Taraba state", "Yobe state", "Zamfara state", "FCT" }));
        stateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateCBActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("LGA:");

        lgaCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lgaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "2010/2011", "2011/2012", "2012/2013", "2013/2014", "2014/2015" }));

        submitB1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        submitB1.setText("Next >>");
        submitB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitB1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("MiddleName:");

        addTA.setColumns(20);
        addTA.setRows(5);
        jScrollPane1.setViewportView(addTA);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Address:");

        levCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        levCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "100L", "200L", "300L", "400L", "500L", "600L", "700L" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Phone Number:");

        sessCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sessCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "2009/2010", "2010/2011", "2011/2012", "2012/2013", "2013/2014", "2014/2015", "2015/2016", "2016/2017", "2017/2018", "2018/2019", "2019/2020", "2020/2021", "2021/2022", "2022/2023", "2023/2024", "2024/2025", "2025/2026", "2026/2027", "2027/2028", "2028/2029", "2029/2030", "2030/2031", "2031/2032", "2032/2033", "2033/2034", "2034/2035", "2035/2036", "2036/2037", "2037/2038", "2038/2039", "2039/2040", "2040/2041", "2041/2042", "2042/2043", "2043/2044", "2044/2045", "2045/2046", "2046/2047", "2047/2048", "2048/2049", "2049/2050", "2050/2051" }));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Session:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Blood Group:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Level:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Date of Birth:");

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

        colCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        colCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "SCIENCE", "TECHNOLOGY" }));
        colCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colCBActionPerformed(evt);
            }
        });

        departCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        departCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", "COMPUTER SCIENCE", "CHEMICAL ENGR", "CHEMISTRY", "GEOLOGY", "PHYSICS", "ENVIROMENTAL SCI", "MARINE ENGR", "MECHANICAL ENGR", "PETROLEUM ENGR", " " }));

        groupCB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        groupCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose-one", " ", "A", "B", "AB", "O+", "A-", "B-", "AB-", "O-" }));

        checkB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        checkB.setText("Check");
        checkB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ContentPaneLayout = new javax.swing.GroupLayout(ContentPane);
        ContentPane.setLayout(ContentPaneLayout);
        ContentPaneLayout.setHorizontalGroup(
            ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPaneLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ContentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(departCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lgaCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stateCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sessCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(levCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mnameTF)
                                    .addComponent(matTF)
                                    .addComponent(foneTF)
                                    .addComponent(fnTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(otherTF)
                                    .addComponent(groupCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(ContentPaneLayout.createSequentialGroup()
                                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(mRB)
                                                .addGap(26, 26, 26)
                                                .addComponent(fRB))
                                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                                .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22)
                                                .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(ContentPaneLayout.createSequentialGroup()
                                                .addComponent(checkB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(submitB1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 16, Short.MAX_VALUE))))))
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(colCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(37, 37, 37))
        );
        ContentPaneLayout.setVerticalGroup(
            ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fnTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(otherTF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(20, 20, 20)))
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(departCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContentPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(groupCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(levCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sessCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContentPaneLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fRB, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mRB, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stateCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lgaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(ContentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitB1)
                    .addComponent(checkB))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(ContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitB1ActionPerformed
        // TODO add your handling code here:
        if(IsecondTime)
        {
            this.MoveStudentsBasicInfoForward();
          AddStudentKin as= new  AddStudentKin(StudentBasicRegSummary.getBiometricObj(),true);
          as.setLocationRelativeTo(null);
          as.setVisible(true);
          
         this.setVisible(false);
          
        }
        else
        {
             this.AddStudentsBasicInfo();
        }
     
      
                 
        
        
        
    }//GEN-LAST:event_submitB1ActionPerformed

    private void stateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateCBActionPerformed
        // TODO add your handling code here:
        ActivateLga();
    }//GEN-LAST:event_stateCBActionPerformed

    private void mRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRBActionPerformed
        // TODO add your handling code here:
        this.ParameterInfor();
    }//GEN-LAST:event_mRBActionPerformed

    private void fRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fRBActionPerformed
        // TODO add your handling code here:
        this.ParameterInfor();
    }//GEN-LAST:event_fRBActionPerformed

    private void monthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthCBActionPerformed

    private void yearCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearCBActionPerformed

    private void matTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_matTFMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_matTFMouseExited

    private void checkBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBActionPerformed
        // TODO add your handling code here:
        CheckingStudent();
    }//GEN-LAST:event_checkBActionPerformed

    private void colCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colCBActionPerformed
        // TODO add your handling code here:
        ActivateDepart_CB();
    }//GEN-LAST:event_colCBActionPerformed

    private void matTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_matTFFocusLost
        // TODO add your handling code here:
         CheckingStudent();
    }//GEN-LAST:event_matTFFocusLost

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContentPane;
    private javax.swing.JTextArea addTA;
    private javax.swing.JButton checkB;
    private javax.swing.JComboBox colCB;
    private javax.swing.JComboBox dayCB;
    private javax.swing.JComboBox departCB;
    private javax.swing.JRadioButton fRB;
    private javax.swing.JTextField fnTF;
    private javax.swing.JTextField foneTF;
    private javax.swing.JComboBox groupCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox levCB;
    private javax.swing.JComboBox lgaCB;
    private javax.swing.JRadioButton mRB;
    private javax.swing.JTextField matTF;
    private javax.swing.JTextField mnameTF;
    private javax.swing.JComboBox monthCB;
    private javax.swing.JTextField otherTF;
    private javax.swing.JComboBox sessCB;
    private javax.swing.JComboBox stateCB;
    private javax.swing.JButton submitB1;
    private javax.swing.JComboBox yearCB;
    // End of variables declaration//GEN-END:variables
}
