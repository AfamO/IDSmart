/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */


import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.logging.Logger;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.image.BufferedImage;

public class BiometricMain extends JFrame{
    private JTabbedPane tab;
    private JPanel MainPan;
    private JPanel welcompan;
    private JPanel ImagePan;
    private JPanel SignaturePan;
    private JPanel FingerPrintPan;
    private String InfoLogged="";
    private JPanel CapturePan;
    private JMenuBar bar;
    private JScrollPane jspane;
    private  String status;
    private String reg;
    private JMenuItem create,deleteid,editid,editid2,editid3,studentprint,editscard,staffprint,updatebio;
    
    private JMenuItem rewrite_student,rewrite_staff,setup_change;
    private JButton subB;
    public static Boolean IsCSOLoggedIn=false;
    static BiometricMain bm;
    private static String WhoIsRegistering="none";
   static public Logger LogerObj=null;
    private JMenu editf,viewf;
    
   
    
    private static String db_biodata[]=new String[5];
    private static String staffkin_biodata[]=new String[7];
    private static BufferedImage captured_biodata[]=new BufferedImage[3];
    
   // private static String getmyuserid;
    private static String getmyuserid[]=new String[4];
    
    private static String getmystatus[]=new String[2];
    public static SigPlusImg demo1=null;
    
    public static StaffCapturedInformation stci=null;
    public static StudentCapturedInformation stuci=null;
    private String flop;
    private static BiometricSet1 bs=null;
    private Reconsoft rs=new Reconsoft();
    private   BiometricSet1 home_bg;
    private JLabel company_name,company_city,logo;
    
    BiometricMain(){
        setTitle("IDSmart SOFTWARE");
        setIconImage(new ImageIcon(getClass().getResource("see.jpg")).getImage()); 
       this.WelcomePage();
        this.BasicInforPage();
        this.CaptureImagePage();
         AppLogger apl=new AppLogger();
         LogerObj=apl.getLogger();
        
         
        addWindowListener(new java.awt.event.WindowAdapter() {
            
           
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
               //if(JOptionPane.showConfirmDialog(null,"Are you sure that you want to exit this application?")==0){
               System.exit(1);
           
               
            }
        });
        
        
        bar= new JMenuBar();
        
        
        bar.setVisible(false);
       
        
        JMenu file=new JMenu("File");
        
       
        
         editid = new JMenuItem("Edit Student Basic Information");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(editid);
         
        editid.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          
             reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter student Matric/Reg No");
       //  PrintID b= new PrintID(reg); "2013/4444"
             if(reg.equals("")||reg==null)
             {
         JOptionPane.showMessageDialog(null, "Please Student Matric Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
             }else
             {
                  Reconsoft data=new Reconsoft();
             if(data.CheckExistingStudent(reg))
               {
            JOptionPane.showMessageDialog(null, " STUDENT DOES NOT EXIST,\nCheck your data Or Alert Administrator");
            InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO EDIT STUDENT'S BASIC INFORMATION WITH MATRIC NO :"+reg);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
        
                 } else
                    {
        EditStudentdata ba = new EditStudentdata (reg);
        ba.setResizable(false);
        ba.setLocationRelativeTo(null);
        ba.setVisible(true);   
                     }
      
              }
        }
    });
         editid2 = new JMenuItem("Edit Staff Basic Information");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(editid2);
         
        editid2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter Staff Number");
       //  PrintID b= new PrintID(reg); "2013/4444"
             if(reg.equals("")||reg==null)
             {
         JOptionPane.showMessageDialog(null, "Please Staff Number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
             }else
             {
                  Reconsoft data=new Reconsoft();
        if(data.CheckExistingStaff(reg))
        {
            JOptionPane.showMessageDialog(null, " STAFF DOES NOT EXIST,\nCheck your data Or Alert Administrator");
            InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED TO EDIT STAFF'S BASIC INFORMATION WITH ID :"+reg);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
        
        }else
           {
         EditStaffdata ba = new  EditStaffdata(reg);
        ba.setResizable(false);
        ba.setLocationRelativeTo(null);
        ba.setVisible(true);   
             }
          }
        }
    });
        //editscard
        editscard = new JMenuItem("Edit SmartCard Data");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(editscard);
         
        editscard.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e){
            //AdminLogin ad=new AdminLogin(this,bm);
            IsCSOLoggedIn=true;
            Calllogin();
 }
           
    });
        
         editid3 = new JMenuItem("Change Login details");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(editid3);
         
        editid3.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            
            reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your userid");
       
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please userid Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        Reconsoft data=new Reconsoft();
        if(data.CheckExistingUserID(reg))
        {
            JOptionPane.showMessageDialog(null, " USERID DOES NOT EXIST,\nCheck your data Or Alert Administrator");
        
        }else
        {
            String stat=data.Showmystatus(reg);
           Setuserid(reg,1);
           Setuserid(stat,2);
          Change_user cal2 = new Change_user();
          cal2.setLocationRelativeTo(null);
          cal2.setVisible(true);
          cal2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
            
        }
            
            
    }
 }
    });
        
        create = new JMenuItem("Add user");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(create);
         
        create.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          Create_user cal = new Create_user();
          cal.setLocationRelativeTo(null);
          cal.setVisible(true);
          cal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    });
        
      
        setup_change = new JMenuItem("Change setup");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(setup_change);
         
        setup_change.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
          home_bg=rs.QueryHome_Logo_Card();
        if(home_bg.getCo_name()==null|| home_bg.getBack_pix()==null||home_bg.getCo_City()==null)
        {
           
       JOptionPane.showMessageDialog(null,"Customization Information does not Exist");
       
       }else
        {
            String co_title=home_bg.getCo_name();
            String city=home_bg.getCo_City();
             setup_change bt = new setup_change(co_title,city);
             bt.setSize(500, 550);
             bt.setVisible(true);
             bt.setLocationRelativeTo(null);
             bt.setResizable(false);
             bt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        }
    });    
        
        
        
        deleteid = new JMenuItem("Delete user");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(deleteid);
         
        deleteid.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
       reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No");
       
       if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please userid Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        Reconsoft data=new Reconsoft();
        if(data.CheckExistingUserID(reg))
        {
            JOptionPane.showMessageDialog(null, " USERID DOES NOT EXIST,\nCheck your data Or Alert Administrator");
        
        }else
        {
          data.DeleteUsers(reg);  
          InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "DELETED A USER  with ID. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
        }
    }     
        }
    });
        
        
  
         rewrite_student = new JMenuItem("Re-write Student Card");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(rewrite_student);
       rewrite_student.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

    reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Reg No");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Student Matric number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        Reconsoft d = new Reconsoft();
        if(d.CheckExistingStudent(reg)){
  JOptionPane.showMessageDialog(null, "Please Student Basic Information Does not Exist in the School Database");
        }else{
               if(d.CheckExistingBiometric(reg))
                  {
        JOptionPane.showMessageDialog(null, "Please Student Information Does not Exist in the School Database\nPlease Contact Chief Security Officer For Proper Identification");
        InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "FAILED ATTEMPT WITH STUDENT with matric. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
                  
                  }
               else
               {
       new Rewrite_Student_Card ().WriteRegToSmartCard(reg); 
          InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "REWRITED A STUDENT CARD  with ID. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
          }
       }
    }
    }
    });
        
    
        rewrite_staff = new JMenuItem("Re-write Staff Card");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(rewrite_staff );
       rewrite_staff .addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

    reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Staff Number");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Staff number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        Reconsoft d = new Reconsoft();
        if(d.CheckExistingStaff(reg)){
  JOptionPane.showMessageDialog(null, "Please Staff Basic Information Does not Exist in the School Database");
        }else{
               if(d.CheckExistingBiometric(reg))
                  {
        JOptionPane.showMessageDialog(null, "Please Staff Information Does not Exist in the School Database\nPlease Contact Chief Security Officer For Proper Identification");
        InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "FAILED ATTEMPT WITH STAFF with STAFF No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
                  
                  }
               else
               {
       new Rewrite_Student_Card ().WriteRegToSmartCard_ForStaff(reg); 
          InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "REWRITED A STAFF CARD  with ID. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
          }
       }
    }
    }
    });
       
       
       
    
        
        studentprint = new JMenuItem("Print Student");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(studentprint);
       studentprint.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

    reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Reg No");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Student Matric number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        Reconsoft d = new Reconsoft();
        if(d.CheckExistingStudent(reg)){
  JOptionPane.showMessageDialog(null, "Please Student Basic Information Does not Exist in the School Database");
        }else{
               if(d.CheckExistingBiometric(reg))
                  {
        JOptionPane.showMessageDialog(null, "Please Student Information Does not Exist in the School Database\nPlease Contact Chief Security Officer For Proper Identification");
        InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "FAILED ATTEMPT TO PRINT A STUDENT with matric. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
                  
                  }
               else
               {
        PrintID  b = new PrintID  (reg);
        b.setResizable(false);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.setResizable(false);
        ScalePrintableFormat test = new ScalePrintableFormat();
        test.printCard(b.idcardPan);
      InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED 'PRINT'  STUDENT with matric. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
           }
       }
    }
    }
    });
    
       
       staffprint = new JMenuItem("Print Staff");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(staffprint);
       staffprint.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

    reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Staff Number");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Staff Number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {  
        Reconsoft d = new Reconsoft();
        if(d.CheckExistingStaff(reg)){
 JOptionPane.showMessageDialog(null, "Please Staff Basic Information Does not Exist in the School Database");
        }else{
               if(d.CheckExistingBiometric(reg))
                  {
        JOptionPane.showMessageDialog(null, "Please Staff Information Does not Exist in the School Database\nPlease Contact Chief Security Officer For Proper Identification");
                  
                  }
               else
               {
        staffIdcardPrint  b = new staffIdcardPrint (reg);
        b.setResizable(false);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.setResizable(false);
        ScalePrintableFormat test = new ScalePrintableFormat();
        test.printCard(b.staffidPane);
        InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICK 'PRINT' A  STAFF with ID :"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
  
            
               }
                   
                   
            }
     
       
     
    } 
    }
    });
        
        
        
        
     JMenuItem backprint = new JMenuItem("Print Back");
       backprint.setFont(new Font("Verdana",Font.BOLD,12));
       backprint.setForeground(Color.BLACK);
       file.add(backprint);
       backprint.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
       backIdcard back = new backIdcard ();
        //StaffCardDisplay back = new StaffCardDisplay ("SP 101");
       //StudentCardDisplay back = new StudentCardDisplay ("1615/2012");
       back.setLocationRelativeTo(null);
       back.setVisible(true);
       back.setResizable(false);
     ScaleBackPrintable test = new ScaleBackPrintable();
     test.printCard(back.staffidPane);
     InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "PRINTED BACK OF ID CARD:");
              AppLogger.LogInfo(LogerObj,InfoLogged);
      
      
        }
    });   
        
        updatebio = new JMenuItem("Modify Biometric Data");
        file.setFont(new Font("Verdana",Font.BOLD,12));
        file.setForeground(Color.BLUE);
        file.add(updatebio);
       updatebio.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

    reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Student Matric. No/Staff Number");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Student Matric/Staff Number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }
    //InformationChecker ic=new InformationChecker();
    
    /*
    if( reg.matches("SP 111")&&  ic.checkMatNo(reg) ==false){
        
            JOptionPane.showMessageDialog(null, "Invalid Character in matric number Field,\n"
                  + "Student Matric number format is 2009/2062");
    }
    else if(reg.matches("2010/121316")&& ic.checkstaffnum(reg)==false)
    {
        JOptionPane.showMessageDialog(null, "Invalid Character in staff number Field,\n"
                  + "Staff number format is two Characters followed by a space and  three digits e.g SP 139");
    }
    */
    else
    {  
        Reconsoft d = new Reconsoft();
        
               if(d.CheckExistingBiometric(reg))
                  {
        JOptionPane.showMessageDialog(null, "Please Staff/Student Information Does not Exist in the School Database\nPlease Contact Chief Security Officer For Proper Identification");
                  
                  }
               else
               {
        BiometricMain.setWhoIsRegistering("update_biometric_data");       
        BiometricUpdate  b = new BiometricUpdate(reg);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.setResizable(false);
        
        InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICK 'MODIFY BIOMETRIC DATA ' TO MODIFY STUDENT/STAFF WITH   with MATRIC NO/ID :"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
  
            
               }
                   
                   
            
     
       
     
    } 
    }
    });

        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("Verdana",Font.BOLD,12));
        exit.setForeground(Color.BLUE);
        file.add(exit);
        exit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
           System.exit(0); 
        }
    });
        
        viewf=new JMenu("View Users Tables"); 
        
        
       JMenuItem viewstu = new JMenuItem("Student");
        viewf.setFont(new Font("Verdana",Font.BOLD,12));
        viewf.setForeground(Color.BLUE);
        viewf.add(viewstu);
        viewstu.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          ShowStudent stud= new ShowStudent();
          welcompan.add(stud);
          stud.toFront();
         // stud.setLocation(100, 100);
          // stud.setl
         stud.setVisible(true);
        }
    });
        
        
     JMenuItem viewstaff = new JMenuItem("Staff");
        viewf.setFont(new Font("Verdana",Font.BOLD,12));
        viewf.setForeground(Color.BLUE);
        viewf.add(viewstaff);
        viewstaff.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
          showStaff sta2= new showStaff();
          welcompan.add(sta2);
          sta2.toFront();
         // stud.setLocation(100, 100);
          // stud.setl
         sta2.setVisible(true);  
        }
    });  
        
        
        JMenuItem viewusers = new JMenuItem("users");
        viewf.setFont(new Font("Verdana",Font.BOLD,12));
        viewf.setForeground(Color.BLUE);
        viewf.add(viewusers);
        viewusers.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          UserShow user2= new UserShow();
          welcompan.add(user2);
          user2.toFront();
         user2.setVisible(true); 
        }
    });  
        
        JMenu newreg=new JMenu("New Biometric Capture");
        JMenuItem addinfor = new JMenuItem("Capture Student");
        newreg.setFont(new Font("Verdana",Font.BOLD,12));
       newreg.setForeground(Color.BLUE);
       newreg.add(addinfor );
       addinfor .addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        StudentInfor b= new StudentInfor();
        b.setResizable(false);
        //b.setLocation(100, 100);
        b.setLocationRelativeTo(null);
        b.setVisible(true);     
        }
    });
       
       
       
       
        JMenuItem addstaff = new JMenuItem("Capture Staff");
        
       newreg.add(addstaff);
       addstaff.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        AddStaffInfor bis= new AddStaffInfor();
        bis.setResizable(false);
        bis.setLocationRelativeTo(null);
        bis.setVisible(true);     
        }
    });
       
       
       JMenu bioinfo=new JMenu("Confirm BioMetric Info");
       JMenuItem save = new JMenuItem("Confirm Student Biometric Information");
       JMenuItem sta = new JMenuItem("Confirm Staff Biometric Information");
       
       bioinfo.setFont(new Font("Verdana",Font.BOLD,12));
        bioinfo.setForeground(Color.BLUE);
        bioinfo.add(save);
        bioinfo.add(sta);
       save .addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //new BiometricSummary().setVisible(true);
            
            StudentCapturedInformation stundent_confirm=null;
            
             if(new StudentCapturedInformation().CheckNewRegistration()==true)
            {
              JOptionPane.showMessageDialog(null, "Student Matric Number should not be Empty \nPlease Register New Student");  
            }else if(new StudentCapturedInformation().CheckwebcamImage()==true)
            {
                 JOptionPane.showMessageDialog(null, "Student Image has not been Captured");
            }
            else if(new StudentCapturedInformation().CheckSignatureImage()==true)
            {
                 JOptionPane.showMessageDialog(null, " Student Signature has been not Captured");
            }else if(new StudentCapturedInformation().CheckfingerImage()==true)
            {
                 JOptionPane.showMessageDialog(null, "Student fingerprint has not been Captured");
            }else
                 {
            
              stundent_confirm= new StudentCapturedInformation();
             stundent_confirm.AddStudentsBiometricInfo();
              BiometricMain.setStudObj(stundent_confirm);
              
              //st.setResizable(false);
              //st.setLocationRelativeTo(null);
              //stundent_confirm.setVisible(true);
                 }
            
        }
           
    });
       
       
       sta.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            StaffCapturedInformation st=null;
            
             if(new StaffCapturedInformation().CheckNewRegistration()==true)
            {
              JOptionPane.showMessageDialog(null, "Staff ID should not be Empty \nPlease Register New Staff");  
            }else if(new StaffCapturedInformation().CheckwebcamImage()==true)
            {
                 JOptionPane.showMessageDialog(null, "Staff Image has not been Captured");
            }
            else if(new StaffCapturedInformation().CheckSignatureImage()==true)
            {
                 JOptionPane.showMessageDialog(null, " Staff Signature has been not Captured");
            }else if(new StaffCapturedInformation().CheckfingerImage()==true)
            {
                 JOptionPane.showMessageDialog(null, "Staff fingerprint has not been Captured");
            }else
                 {
            
              st= new StaffCapturedInformation();
              st.BioInform();
              BiometricMain.setStaffObj(st);
              //st.setResizable(false);
              //st.setLocationRelativeTo(null);
              //st.setVisible(true);
                 }
        }
            
    });
       
       JMenu Photo=new JMenu("Resume Data Capture");
        JMenuItem addstudent = new JMenuItem("Resume Student Capture");
        JMenuItem addstaffcap = new JMenuItem("Resume Staff Capture");
        Photo.setFont(new Font("Verdana",Font.BOLD,12));
      Photo.setForeground(Color.BLUE);
       Photo.add(addstudent);
       Photo.add(addstaffcap);
      addstudent.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
            
           reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Reg No");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Student Matric number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        
    Reconsoft d = new Reconsoft();
        if(d.CheckExistingStudent(reg))
         {
    JOptionPane.showMessageDialog(null, "Student does not exist\n try again");
         }else{
             status= rs.RetrieveItem("student_registration", "client_id", reg, "status");
               if( !status.equalsIgnoreCase("completed") )
                  {
                    BiometricMain.setDB_Biodata(reg,2);
                    
                    setWhoIsRegistering("student");
                    AdminLogin.setStudent_StaffId(reg);
                   
                    if(status.equalsIgnoreCase("0c"))
                    {
                         JOptionPane.showMessageDialog(null, "This student has not started  registration process");
                    }
                    else if (status.equalsIgnoreCase("1c"))
                    {
                        BiometricMain.setWhoIsRegistering("student");
                        AddStudentKin kin = new AddStudentKin();
                        kin.setLocationRelativeTo(null);
                        kin.setVisible(true);
     //kin.setResizable(false);
                        kin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                    else if (status.equalsIgnoreCase("2c"))
                    {
                            ImageCapturing b = new ImageCapturing();
                    }
                    else if (status.equalsIgnoreCase("3c"))
                    {
                            SignaturePage();
                    }
                    else if (status.equalsIgnoreCase("4c"))
                    {
                         FingerPrint();
                    }
                    else if (status.equalsIgnoreCase("5c"))
                    {
                         StudentBasicRegSummary sbrs=new  StudentBasicRegSummary(reg);
                    // JOptionPane.showMessageDialog(null, "recall that my id is:"+id);
                    sbrs.setLocationRelativeTo(null);
                    sbrs.setVisible(true);
                    //this.setVisible(false);
                    }
                     else if (status.equalsIgnoreCase("6c"))
                    {
                        StudentCapturedInformation stundent_confirm= new StudentCapturedInformation();
                        stundent_confirm.AddStudentsBiometricInfo();
                         BiometricMain.setStudObj(stundent_confirm);
                         
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null, "Student registration is already completed");
                    }
                
               //String g= ;
              InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "RESUMED CAPTURING OF STUDENT with matric. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
                  }else{
           JOptionPane.showMessageDialog(null, "Student registration is already completed"); 
            InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "FAILED TO RESUME CAPTURING OF STUDENT matric. No:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
               }
        }  
            
            
    }        
           /* if(false)
            {
                //ImageCapturing.IsNotPlayerAvailable()
                JOptionPane.showMessageDialog(null, "Cannot find or configure any available Webcam Device.\nPlease make all necessary connections or/and try again.\nThank you.");
                return;
            }
            else
            {
                 ImageCapturing b = new ImageCapturing();
            }*/
            
          
       // }
    }});
       
      
      
      addstaffcap.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
            
           reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Staff No");
       //  PrintID b= new PrintID(reg); "2013/4444"
    if(reg.equals("")||reg==null)
    {
      JOptionPane.showMessageDialog(null, "Please Staff Number Should not be Empty.\nThank you", "EMPTY INPUT", 2);   
          
    }else
    {
        
    Reconsoft d = new Reconsoft();
        if(d.CheckExistingStaff(reg))
         {
        JOptionPane.showMessageDialog(null, "Staff does not exist\n try again");
         }else{
             status= rs.RetrieveItem("staff_registration", "staffNum", reg, "status");
                //JOptionPane.showMessageDialog(null, "Staff STATUS IS="+status);
              if((!status.equalsIgnoreCase("completed")) )
                  {
                    BiometricMain.setDB_Biodata(reg,2);
                    setWhoIsRegistering("staff");
                    AdminLogin.setStudent_StaffId(reg);
               
                
                //JOptionPane.showMessageDialog(null, "Staff STATUS IS="+status);
                    if(status.equalsIgnoreCase("0c"))
                    {
                         JOptionPane.showMessageDialog(null, "This student has not started  registration process");
                    }
                    else if (status.equalsIgnoreCase("1c"))
                    {
                         BiometricMain.setWhoIsRegistering("staff");
                         AddStaffKin kin = new AddStaffKin();
                         kin.setLocationRelativeTo(null);
                         kin.setVisible(true);
                         kin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     
                       
                    }
                    else if (status.equalsIgnoreCase("2c"))
                    {
                            ImageCapturing b = new ImageCapturing();
                    }
                    else if (status.equalsIgnoreCase("3c"))
                    {
                            SignaturePage();
                    }
                    else if (status.equalsIgnoreCase("4c"))
                    {
                         FingerPrint();
                    }
                    else if (status.equalsIgnoreCase("5c"))
                    {
                         StaffBasicRegSummary sbrs=new  StaffBasicRegSummary(reg);
                    // JOptionPane.showMessageDialog(null, "recall that my id is:"+id);
                    sbrs.setLocationRelativeTo(null);
                    sbrs.setVisible(true);
                    //this.setVisible(false);
                    }
                     else if (status.equalsIgnoreCase("6c"))
                    {
                        StaffCapturedInformation  st= new StaffCapturedInformation();
                        st.BioInform();
                        BiometricMain.setStaffObj(st);
                         
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null, "Staff registration is already completed");
                    }
                InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "RESUMED CAPTURING OF STAFF with ID:"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
                  }else{
           JOptionPane.showMessageDialog(null, "Staff registration is ALREADY completed"); 
            InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "FAILED TO RESUME CAPTURING OF STAFF with ID :"+reg);
              AppLogger.LogInfo(LogerObj,InfoLogged);
               }
        }  
            
            
    }        
          
    }}); 
      
      
      
      
      
      
      
      
      
      
      
       JMenu sign=new JMenu("Capture Signature");
        JMenuItem addsign = new JMenuItem("Signature");
       sign.setFont(new Font("Verdana",Font.BOLD,12));
       sign.setForeground(Color.BLUE);
       sign.add(addsign );
       addsign .addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
         if(checkforDll("SigUsb")==true){
              JOptionPane.showMessageDialog(bm, "Dll or other configuration file(s) is/are missing in your path", "CONFIGURATION ERROR", 0);
              System.out.println("dll is missing");
              return;
          }
              else
              {
                   System.out.println("dll is not missing");
                   SigPlusImg demo = new SigPlusImg();
            demo.setSize(800,200);
            demo.setLocationRelativeTo(null);
            demo.setVisible(true);
            demo.setResizable(false);
            demo.setBackground(Color.lightGray);
            demo1=demo;
              }
        
            
            
        }
    });
       
       JMenu finger=new JMenu("Capture FingerPrint");
        JMenuItem addfinger = new JMenuItem("FingerPrint");
       finger.setFont(new Font("Verdana",Font.BOLD,12));
       finger.setForeground(Color.BLUE);
       finger.add(addfinger );
       addfinger.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        
            if(checkforDll("jnisgfplib")==true){
              JOptionPane.showMessageDialog(null, "Dll or other configuration file(s) is/are missing in your path", "CONFIGURATION ERROR", 0);
              //System.out.println("dll is missing");
              return;
          }
            else if(!AfamFP.IsDeviceConnected())
            {
                System.out.println("Initialization Failed.The Secugen Device/Driver is not found:");
             javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Secugen\nDriver is installed properly and that \nthe device is PROPERLY CONNECTED\nThank you");
             return;
            }
              else
              {
                 AfamFP check= new AfamFP();
                 //check.InitializeingerPrintDevice();
                 check.setLocationRelativeTo(null);
                 //check.setSize(450, 500);
                  check.setVisible(true);
                  check.setResizable(false);
                  // this.InitializeingerPrintDevice();
              }
        
    }
    
    
            
        
    });
       
       
         //staffIdcardPrint
       
       JMenu print=new JMenu("Process Smart Card");
       
       JMenuItem writesmc = new JMenuItem("Format/Write To SmartCard");
       print.setFont(new Font("Verdana",Font.BOLD,12));
       print.setForeground(Color.BLUE);
       //print.add(writesmc );
       writesmc .addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

  // WriteRegToSmartCard();
        }
    });
       
       JMenuItem addprintstaff = new JMenuItem("Read_Print Staff ID from SmartCard");
       print.setFont(new Font("Verdana",Font.BOLD,12));
       print.setForeground(Color.BLUE);
       print.add(addprintstaff);
       addprintstaff.addActionListener(new ActionListener(){
           
        public void actionPerformed(ActionEvent e){
          String info="";
          StudentCardDisplay staff_student=null;
         StaffCardDisplay staff_student2= null;
            reg=ReadFromSmartCard();
            info=reg;
              info=info.substring(6,9);
    if(info.endsWith("0")||info.endsWith("1")||info.endsWith("2")||info.endsWith("3")||info.endsWith("4")||info.endsWith("5")||info.endsWith("6")||info.endsWith("7")||info.endsWith("8")||info.endsWith("9")||info.endsWith("0"))
    {
        //JOptionPane.showMessageDialog(null,"The INFO RED IS student reg  is "+reg);
        if(!new Reconsoft().CheckIfItemExist("client_biometric_infor", "client_id", reg))
            {
                 javax.swing.JOptionPane.showMessageDialog(null, "UKNOWN STUDENT'S MATRIC NO\n\nWe can't find it in our database"+"\n    BYE!!!.");
                 javax.swing.JOptionPane.showMessageDialog(null, "Please take your SmartCard"+"\n    Thank You!!!.");
                //System.exit(1);
            }
        else
        {
        
       StudentCardDisplay staff_student1= new StudentCardDisplay(reg);
        staff_student1.setLocationRelativeTo(null);
        staff_student1.setResizable(false);
        staff_student1.setVisible(true);
       staff_student=staff_student1;
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "RED SMARTCARD INFO FOR A STUDENT WITH matri. no:"+reg);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
        }
    }
    else
    {
        
         reg=reg.substring(0, 6);
         
        //JOptionPane.showMessageDialog(null,"The INFO RED IS   :"+reg);
          if(!new Reconsoft().CheckIfItemExist("client_biometric_infor", "client_id", reg))
            {
                 javax.swing.JOptionPane.showMessageDialog(null, "UKNOWN STAFF ID\n\nWe can't find it in our database"+"\n    BYE!!!.");
                 javax.swing.JOptionPane.showMessageDialog(null, "Please take your SmartCard"+"\n    Thank You!!!.");
                //System.exit(1);
            }
          else
          {
        StaffCardDisplay staff_student1= new StaffCardDisplay(reg);
        staff_student1.setLocationRelativeTo(null);
        staff_student1.setResizable(false);
        staff_student1.setVisible(true);
        staff_student2=staff_student1;
        InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "RED SMARTCARD INFO FOR A STAFF WITH ID :"+reg);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
          }
        // ScalePrintableFormat test = new ScalePrintableFormat();
        //test.printCard(staff_student1.staffidPane);
        
       
    }
            
          if(reg.equalsIgnoreCase("NOT REGISTERED") ){
                return;
            }
            else if(reg.equalsIgnoreCase("none"))
            {
                javax.swing.JOptionPane.showMessageDialog(null, "UKNOWN STAFF/MATRIC \n"+"BYE!!!.");
                //System.exit(1);
            }
           
            
            else
            {
                
           
        
        if(staff_student==null && staff_student2==null){
             //javax.swing.JOptionPane.showMessageDialog(null, "UKNOWN STAFF/MATRIC ID.Please Contact Administrator\n"+"BYE!!!.");
            // System.exit(1);
            }
        else if(staff_student==null){
            staff_student2.setVisible(true);
        }
        else
        {
            staff_student.setVisible(true);
        }
        //staff.setResizable(false);
       // staff.setLocationRelativeTo(null);
        
         }
        //JFramePrintTest staff= new JFramePrintTest(reg);
        //staff.setResizable(false);
       // staff.setLocationRelativeTo(null);
       // staff.setVisible(true); 
            
        // JFramePrintTest staff= new JFramePrintTest();
        //staff.setResizable(false);
       //staff.setLocationRelativeTo(null);
       // staff.setVisible(true); 
         //PrintSmartIdCard.printComponent(staff.staf);
       //TestPrinting test = new TestPrinting();
       //test.printCard(staff.staffidPane);
      //test.printCard();
       // try{
        //test.printComponentToFile(staff.staffidPane, true);
        //test.printComponent(staff.staffidPane, true);
         //test.printComponentToFile(staff.staffidPane,true);
           
            
        //}
        //catch (PrinterException exp) {
          System.out.println("PRINTING ERRROR HAS OCCURRED");
          //  exp.printStackTrace();
          // }
        }
    });
       JMenu logout=new JMenu("Logout");
        JMenuItem lgout = new JMenuItem("LogOut");
       logout.setFont(new Font("Verdana",Font.BOLD,12));
       logout.setForeground(Color.BLUE);
       logout.add(lgout );
       lgout.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(JOptionPane.showConfirmDialog(null,"Are you sure that you want to log out?")==0){
                bar.setVisible(false);
                subB.setEnabled(true);
                InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CLICKED 'LOGOUT' FROM THE APPLICATION");
              AppLogger.LogInfo(LogerObj,InfoLogged);
            }
            else
            {
                
            }
            
    
        }
    });
        
       bar.add(file);
       bar.add(newreg);
        bar.add(viewf);
       //bar.add(staff);
      
       //bar.add(sign);
       //bar.add(finger);
       //bar.add(bioinfo);
       bar.add(print);
       bar.add(Photo);
       bar.add(logout);
      
      
        
        
         
        
        
        MainPan = new JPanel();
        MainPan.setLayout(new BorderLayout());
        getContentPane().add(MainPan);
        
        tab = new JTabbedPane();
        
		tab.addTab("Activated",new ImageIcon(getClass().getResource("/adcs_project/see.jpg")), welcompan );
		
                tab.setForeground(Color.BLUE);
                tab.setBackground(Color.WHITE);
               MainPan.add(bar,BorderLayout.NORTH);
                
		MainPan.add( tab, BorderLayout.CENTER );
                //MainPan.add(subB,BorderLayout.CENTER);
                
        
    }
    public static void setWhoIsRegistering(String val)
    {
        BiometricMain.WhoIsRegistering=val;
    }
    
    public static String getWhoIsRegistering()
    {
       return BiometricMain.WhoIsRegistering;
    }

    public static void setStaffObj(StaffCapturedInformation val)
    {
        BiometricMain.stci=val;
    }
    
    
     
    
    
    
    /**
     *
     * @return
     */
    public static StaffCapturedInformation getStaffObj()
    {
        return BiometricMain.stci;
    }
     public static void setStudObj(StudentCapturedInformation val)
    {
        BiometricMain.stuci=val;
    }
    /**
     *
     * @return
     */
    public static StudentCapturedInformation getStudObj()
    {
        return BiometricMain.stuci;
    }
    //StaffCapturedInformation
     public void WriteRegToSmartCard(){
        AfamSmartCard jsc=new AfamSmartCard();
        
            byte id=(byte)0x02;
            byte DATA=(byte)0xF0;
            if(jsc.IsCardPresent())
            {
                if(jsc.Format_PersonalizeSmartCard()){
               int lent=0;
               if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
               {
                   lent=9;
               }
               else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               {
                   lent=6;
               }
             reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No to write to \nyour SmartIDCard for Personalization");
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
                   jsc.WriteToSmartCard(DATA, id,reg);
                   
                   JOptionPane.showMessageDialog(null,"Your Information was successfully written to ACS ACOS SMARTCARD \n"+"Thank you");
               }
               
           }
           else
           {
               javax.swing.JOptionPane.showMessageDialog(null, "You have NOT entered anything\n"+"Thank you.");
               return;
           }
                  
            }
                else if( jsc.IsSmartCardFormated_Personalized() && (!jsc.ReadFromSmartCard(DATA, id,9).matches("SP 139") ||!jsc.ReadFromSmartCard(DATA, id,9).matches("0262/2009")))
                {
                    
                    
                    int lent=0;
               if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
               {
                   lent=9;
               }
               else if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("staff"))
               {
                   lent=6;
               }
             reg=javax.swing.JOptionPane.showInputDialog(null, "Please enter your Matric/Staff No to write to \nyour SmartIDCard for Personalization");
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
                   jsc.WriteToSmartCard(DATA, id,reg);
                   JOptionPane.showMessageDialog(null,"Your Information was successfully written to ACS ACOS SMARTCARD \n"+"Thank you");
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
       
       
    //String info=jsc.ReadFromSmartCard(DATA, id);
       
    //JOptionPane.showMessageDialog(null,"The red Info is:\n"+info);
    }
   public String ReadFromSmartCard(){
        AfamSmartCard jsc=new AfamSmartCard();
         String info="none";
       byte id=(byte)0x02;
       byte DATA=(byte)0xF0;
       byte id1=(byte)0x09;
       byte DATA1=(byte)0xA0;
       if(jsc.IsCardPresent())
       {
           
           if(jsc.IsSmartCardFormated_Personalized())
           {
               info=jsc.ReadFromSmartCard(DATA1, id1,32);
               
               if(!info.equals("donsimon999_@afamokonkwo#healing"))
               {
                   info="none";
                   if(jsc.disconnectSmartCard())
              {
                  //JOptionPane.showMessageDialog(bm,"Authentication Successfull!.\n Please take your SmartCard from the Reader.\nIt has been disconnected since your transaction has been COMPLETED.\n     Thank you\n     Bye!!!!!","Authentication Successful",2);
              }
              else{
                   JOptionPane.showMessageDialog(null,"Error disconnecting your card.\nPlease contact the Administrator ");
              }
                   JOptionPane.showMessageDialog(bm,"Authentication Failure!.\n Please take your SmartCard from the Reader.IT WAS TAMPERED WITH BY A CRIMINAL\nIt has been disconnected.\n     Thank you\n     Bye!!!!!","Authentication Failure",2);
               }
               else if(info.equals("donsimon999_@afamokonkwo#healing"))
               {
                    AfamSmartCard jsc1=new AfamSmartCard();
                   info=jsc1.ReadFromSmartCard(DATA, id,9);
              if(jsc.disconnectSmartCard())
              {
                 // JOptionPane.showMessageDialog(bm,"Authentication Successfull!.\n Please take your SmartCard from the Reader.\nIt has been disconnected since your transaction has been COMPLETED.\n     Thank you\n     Bye!!!!!","Authentication Successful",2);
              }
              else{
                   JOptionPane.showMessageDialog(null,"Error disconnecting your card.\nPlease contact the Administrator ");
              }
              
                   
               }
                   
              
                
               
                //return;
           }
           else
           {
                JOptionPane.showMessageDialog(null,"This SmartCard has NOT BEEN  REGISTERED/CONFIGURED.\nPlease Insert AND REGISTER a new one TO PRINT YOUR ID CARD\nThank you\nBye!!!!!");
                info="NOT REGISTERED";
               // System.exit(1);
           }
       
    
       
    //JOptionPane.showMessageDialog(null,"The red Info is:\n"+info);
       }
       else
       {
           JOptionPane.showMessageDialog(this,"SmartCard is Absent:\nPlease Make sure that your SmartCard Driver or/and Reader is PROPERLY \nCONNECTED to your system and that there is a smartcard inside it\nThank you.","SmartCard Initialization Problem",3);
           //System.exit(1);
           //return;
       }
    
    return info;
    }
    
   
   
  public static void SignaturePage(){
         if(checkforDll("SigUsb")==true){
              JOptionPane.showMessageDialog(bm, "Dll or other configuration file(s) is/are missing in your path", "CONFIGURATION ERROR", 0);
              System.out.println("dll is missing");
              return;
          }
              else
              {
                 
              System.out.println("dll is not missing");
              
              //if(SigPlusImg.IsSignaturePadConnected())
              //{
               SigPlusImg  demo = new SigPlusImg();
               demo.setSize(800,200);
               demo.setLocationRelativeTo(null);
               demo.setVisible(true);
               demo.setBackground(Color.lightGray);
               demo.addWindowFocusListener(new java.awt.event.WindowAdapter() {
            
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
                //player.stop();
                //player.close();
            }
        });
               demo1=demo;
              
              //}
              //else
              //{
                  //javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Signature pad \nDriver is installed properly and that \nthe device is PROPERLY CONNECTED\nThank you");
              //}
              }
         
        
    }
  
  public static void formWindowClosing(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
       // JOptionPane.showMessageDialog(null,"Thank you for Closing me\n\n     Bye!!!");
      //demo1.setVisible(false);
      
        
    }   
   
   
   
    private static boolean checkforDll(String dll)
    {
        String name; 
        boolean IsDllMissing=false;
        
        try{
          System.loadLibrary(dll);
           
               System.out.println("dll is missing");
           
           
        }
        catch(java.lang.UnsatisfiedLinkError ex)
        {
            IsDllMissing=true;
            System.out.println("dll is missing");
        }
        return IsDllMissing;
    }
    
    
    public void WelcomePage(){
        
     
          home_bg=rs.QueryHome_Logo_Card();
         final ImageIcon icon = new ImageIcon(getClass().getResource("/adcs_project/home.png"));
         welcompan = new JPanel(){			
            protected void paintComponent(Graphics g){			
            g.drawImage(icon.getImage(), 0,0, null);
            super.paintComponent(g);
            //super.paintComponent(g);
            
        }	
        };
        welcompan.setLayout(null);
        
        welcompan.setOpaque(false);
        
        
        company_name=new JLabel(home_bg.getCo_name().toUpperCase());
        company_name.setBounds(0,10,1050, 40);
        company_name.setForeground(new java.awt.Color(0, 85, 134));
        company_name.setFont(new java.awt.Font("Segoe UI Symbol",1, 34));
        company_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       // company_name.setForeground(Color.BLUE);
        //company_name.setFont(new Font("Verdana",Font.BOLD,36));
        
        String city =home_bg.getCo_City();
     
        company_city=new JLabel(city.toUpperCase());
        company_city.setBounds(350,50,500, 40);
        company_city.setForeground(new Color(0, 85, 134));
        company_city.setFont(new Font("Segoe UI Symbol",1, 34));
        company_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        
        
        logo=new JLabel(new ImageIcon(home_bg.getBack_pix()));
        logo.setBounds(350,150,350,300);
        
        subB= new JButton("LOGIN");
		 subB.setBounds( 30,550, 80, 20 );
                 subB.setForeground(Color.BLUE);
                 subB.setFont(new Font("Verdana",Font.BOLD,12));
                 subB.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e)
              {
                 Calllogin(); 
                  
              }
                 });
               


    welcompan.add(company_name);  
    welcompan.add(company_city); 
    welcompan.add(logo); 
    welcompan.add(subB);    
    }
    
    
    
    
    
    
    
    
    public void  EnablePage(String id,String status)
    {
        
        if(status.equals("super Admin"))
        {
            this.flop=id;
         subB.setEnabled(false);
         bar.setVisible(true); 
          create.setVisible(true);
          editid.setVisible(true);
          editid2.setVisible(true);
          editid3.setVisible(true);
          deleteid.setVisible(true);
          staffprint.setVisible(true);
          editscard.setVisible(true);
          studentprint.setVisible(true);
          updatebio.setVisible(true);
           viewf.setVisible(true);
         // editf.setVisible(true);
          
          
        }else{
          this.flop=id;
         subB.setEnabled(false);
         bar.setVisible(true); 
          create.setVisible(false);
          editid3.setVisible(false);
           editid.setVisible(false);
          editid2.setVisible(false);
          deleteid.setVisible(false);
          staffprint.setVisible(false);
          studentprint.setVisible(false);
          updatebio.setVisible(false);
          editscard.setVisible(false);
          viewf.setVisible(false);
         
        }
        
    }





 public  void Calllogin(){
        new AdminLogin(this,this).setVisible(true);
        
    }
    public void BasicInforPage(){
        
    }
    
    public void CaptureImagePage(){
        
    }
    
    public static void  FingerPrint()
    {
       if(checkforDll("jnisgfplib")==true){
              JOptionPane.showMessageDialog(null, "Dll or other configuration file(s) is/are missing in your path", "CONFIGURATION ERROR", 0);
              //System.out.println("dll is missing");
              return;
          }
            while(!AfamFP.IsDeviceConnected())
            {
                System.out.println("Initialization Failed.The Secugen Device/Driver is not found:");
             javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Secugen\nDriver is installed properly and that \nthe device is PROPERLY CONNECTED\nThank you");
             //return;
            }
              
              
                 AfamFP check= new AfamFP();
                 //check.InitializeingerPrintDevice();
                 check.setLocationRelativeTo(null);
                 //check.setSize(450, 500);
                  check.setVisible(true);
                  check.setResizable(false);
                  // this.InitializeingerPrintDevice();
              
          
    }
    
    public static void setDB_Biodata(String val, int i)
    {
        BiometricMain.db_biodata[i]=val;
    }
    
    public static String[] getDB_Biodata()
    {
       return BiometricMain.db_biodata;
    }
     public static void setStaffkin_Biodata(String val, int i)
    {
        BiometricMain.staffkin_biodata[i]=val;
    }
    
    public static String[] getStaffkin_Biodata()
    {
       return BiometricMain.staffkin_biodata;
    }
    
    
  
     public static void setCaptured_Biodata(BufferedImage val, int i)
    {
        BiometricMain.captured_biodata[i]=val;
    }
    
    public static BufferedImage[] getCaptured_Biodata()
    {
       return BiometricMain.captured_biodata;
    }
    
    
    
   public static void Setuserid(String myid,int y)
   {
     BiometricMain.getmyuserid[y]=myid;  
   }
    
    
   public static String [] getmyuserid()
   {
     return BiometricMain.getmyuserid;  
   }
    
    
    
   public static void Setstatus(String myid,int y)
   {
     BiometricMain.getmystatus[y]=myid;  
   }
    
    
   public static String [] getmyclientstatus()
   {
     return BiometricMain.getmystatus;  
   }
   
    
    public static void SetBiometricSet1Obj(BiometricSet1 bs)
   {
     BiometricMain.bs=bs;  
   }
    
    
   public static BiometricSet1 getBiometricSet1Obj()
   {
     return BiometricMain.bs;  
   }
   
   
   
   /* 
    public static void main(String [] args)
    {
        new BiometricMain().setVisible(true);
    }
    */
}
