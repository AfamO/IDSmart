/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Admin
 */

import SecuGen.FDxSDKPro.jni.*;
import java.awt.Color;
import java.awt.Graphics2D;
//import SecuGen.FDxSDKPro.jni
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Calendar;
import javax.swing.JFrame;

public class AfamFP extends JFrame {
private static long IsError=99;
private byte [] img_buffer;
private int img_width,img_height;
static JSGFPLib jsplib;
byte[] imageBuffer;
byte[] imageBuffer2;
byte[] templateBuffer;
byte[] templateBuffer2;
int[] new_quality;
static BufferedImage updatecapbuffImg=null;
private String tempvalue; 
private String InfoLogged="";
Reconsoft data=new Reconsoft();


    /**
     * Creates new form AfamFP
     */
    public AfamFP() {
        initComponents();
        imgLB.setVisible(false);
        closeB.setVisible(false);
        this.InitializeingerPrintDevice();
        
       /* if(checkforDll("jnisgfplib")==true){
              JOptionPane.showMessageDialog(null, "Dll or other configuration file(s) is/are missing in your path", "CONFIGURATION ERROR", 0);
              System.out.println("dll is missing");
              return;
          }
              else
              {
                   
              }
              */
        
    }
    
    
    
    public final void InitializeingerPrintDevice() {
        
        
        
        jsplib =new JSGFPLib();
        SGDeviceInfoParam device_info;
        device_info = new SGDeviceInfoParam() ;
        IsError  = jsplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
        //IsError = jsplib.InitEx(260,300,500);
        System.out.println("The Int is:"+IsError);
        if(IsError==0){
            System.out.println("Initialization Successful.The Error code is:"+IsError);
            IsError=jsplib.OpenDevice(SGFDxDeviceName.SG_DEV_AUTO);
            //IsError=jsplib.Close();
            if(IsError==0){
                
            System.out.println("Initialization OF DEVICE Successful.The Error code is:"+IsError);
           IsError= jsplib.GetDeviceInfo(device_info);
           if(IsError==SGFDxErrorCode.SGFDX_ERROR_NONE){
            System.out.println("DEVICE info was gotten.The Error code is:"+SGFDxErrorCode.SGFDX_ERROR_NONE);
            img_width=device_info.imageWidth;
            img_height=device_info.imageHeight;
            status.setText(" DEVICE is READY \n");
            System.out.println("Device id:"+device_info.deviceID);
            System.out.println("Device FWVERSION: "+device_info.FWVersion);
            System.out.println("Device IMAGE HEIGHT: "+img_height);
            System.out.println("Device IMAGE WIDTH: "+img_width);
            /*
            IsError=jsplib.Close();
             if(IsError==0){
                 System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
             }
             else
             {
                 System.out.println("DEVICE OBJECT WAS NOT CLOSED.The Error code is:"+IsError);
             }
             */
           }
           
             else
        {
             System.out.println("DEVICE info wasn't available-thus failed.The Error code is:"+IsError);
        }
            
            }
            else
        {
             System.out.println("DEVICE Initialization Failed.The Error code is:"+IsError);
        }
           // System.out.println("Device Name:"+jsplib.GetDeviceInfo(null));
        }
        else
        {
             System.out.println("Initialization Failed.The Error code is:"+IsError);
             javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Secugen\nDriver is installed properly and that \nthe device is PROPERLY CONNECTED\nThank you");
             return;
        }
       
        // jsplib.CreateTemplate(null, bytes, bytes1);
        //jsplib.GetDeviceInfo(null);
    
    }
    public static boolean IsDeviceConnected(){
        boolean Isconnected=false;
        jsplib =new JSGFPLib();
        SGDeviceInfoParam device_info;
        device_info = new SGDeviceInfoParam() ;
        IsError  = jsplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
        //IsError = jsplib.InitEx(260,300,500);
        System.out.println("The Int is:"+IsError);
        if(IsError==0){
            System.out.println("Initialization Successful.The Error code is:"+IsError);
            Isconnected=true;
        }
        return Isconnected;
        
    }
    private boolean checkforDll(String dll)
    {
        String name; 
        boolean IsDllMissing=false;
        
        try{
          System.loadLibrary(dll);
           
               System.out.println("dll is  not missing");
           
           
        }
        catch(java.lang.UnsatisfiedLinkError ex)
        {
            IsDllMissing=true;
            System.out.println("dll is missing");
        }
        return IsDllMissing;
    }
     public byte [] convertPixtobyte(BufferedImage m)
   {
      // byte [] pixbyte= new byte[5000];
       //int byteread=0;
      // ByteArrayOutputStream pixout = new ByteArrayOutputStream();
       
     try{
       ByteArrayOutputStream pixout = new ByteArrayOutputStream();
    ImageIO.write(m,"png", pixout);
    byte [] pixbyte=pixout.toByteArray();
  //ByteArrayInputStream  pix =new ByteArrayInputStream(pixout.toByteArray(),0,pixout.size()); 
  return pixbyte;
       }catch(IOException n){
           n.printStackTrace();
           return null;
       }
       
   }
    public void CaptureFingerPrint()
    {
        if(IsError==0){
            //img_buffer=new byte[img_width,img_height];
            img_buffer=new byte[1000];
            long time_out=10000;
            long quality=95;
            File f=new File("");
           
             //long new_quality=95
               new_quality = new int[1];;
            BufferedImage bm=new BufferedImage(img_width,img_height,BufferedImage.TYPE_BYTE_GRAY);
             imageBuffer = ((java.awt.image.DataBufferByte) bm.getRaster().getDataBuffer()).getData();
            if(jsplib.GetImageEx(imageBuffer, time_out, 0, quality)==SGFDxErrorCode.SGFDX_ERROR_NONE)
            {
                IsError=SGFDxErrorCode.SGFDX_ERROR_NONE;
                SGFingerInfo sgif=new SGFingerInfo();
                System.out.println("Image Byte Captured.The Error code is:"+IsError);
                System.out.println("finger imp type :"+sgif.ImpressionType);
                System.out.println("finger img quality :"+sgif.ImageQuality);
                System.out.println("finger NUMBER :"+sgif.FingerNumber);
                status.setText("Image Captured\n");
                jsplib.SetBrightness(90);
                IsError=jsplib.GetImageQuality(img_width, img_height, imageBuffer, new_quality);
                status.setText(status.getText()+" \n"+" \nImage Quality is: "+new_quality[0]+"\n");
                //
               // capimg.setIcon(new ImageIcon(bm));
                
               
  /******************************************************
  //reduce the image size and re-draw it
  * ****************************************************/
  Image pix=bm.getScaledInstance(100, 50, 0);
  BufferedImage buffImg = new BufferedImage(pix.getWidth(null),pix.getHeight(null), BufferedImage.TYPE_INT_RGB);
  Graphics2D g = buffImg.createGraphics();
  g.drawImage(pix, 0, 0, 100, 50, null);
   g.dispose();
   
  // File mypath=new File(f.getAbsoluteFile()+"\\src\\adcs_project\\finger_img\\fp_"+Calendar.getInstance().getTimeInMillis()+".jpg");
   if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("update_biometric_data")){
                    updatecapbuffImg=buffImg;
                    BiometricUpdate.finL.setIcon(new ImageIcon(updatecapbuffImg));
                    //System.out.println("Got update fing: "+updatecapbuffImg.toString());
                    imgLB.setVisible(true);
                    capimg.setIcon(new ImageIcon(bm));
                     JOptionPane.showMessageDialog(null, "Fingerprint Information Captured Successfuly");
                    this.setVisible(false);
                }
   else
   {
   
        if(BiometricMain.getCaptured_Biodata().length != 0)
                    {
                       // BiometricMain.setDB_Biodata(mypath.getName(), 3);
                        BiometricMain.setCaptured_Biodata(buffImg,2);
                        data.UpdateBiometricImageInfor(4,"finger_img",AdminLogin.student_staffid,convertPixtobyte(buffImg));
                      //  dat2.UpdateBiometricImageInfor(4,"finger_img",AdminLogin.student_staffid,convertPixtobyte(buffImg));
                     
                        ImageCapturing.isWebcamFirst=false;
                    }
                    
                 try{
                    // ImageIO.write(buffImg, "jpg", mypath);
                    // System.out.println("Image saved in:"+mypath);
                     //display_Captured[3]
                     imgLB.setVisible(true);
                     capimg.setIcon(new ImageIcon(bm));
                 }
                 catch(Exception io){
                     io.printStackTrace();
                 }
                
                 JOptionPane.showMessageDialog(null, "Fingerprint Information Captured Successfuly");
                 String who=" "; 
              
                 if(BiometricMain.getWhoIsRegistering().equals("staff"))
                 {
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
                     //JOptionPane.showMessageDialog(null, "recall that my id is:"+"SP 333");
                    sbrs.setLocationRelativeTo(null);
                    sbrs.setVisible(true);
                     
                    
                if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
   
       who="STAFF";
        data.UpdateUserStatus("staff_registration", "staffNum", "5c", AdminLogin.student_staffid);
      //  dat2.UpdateUserStatus("staff_registration", "staffNum", "5c", AdminLogin.student_staffid);
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED FINGERPRINT OF "+who+"'S -that is still registering- WITH ID :"+AdminLogin.student_staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
  
                    this.setVisible(false);
                    //StaffCapturedInformation  st= new StaffCapturedInformation();
              //st.BioInform();
             // BiometricMain.setStaffObj(st);
              
                 }else if(BiometricMain.getWhoIsRegistering().equals("student"))
                 {
                     who="STUDENT";
       data.UpdateUserStatus("student_registration", "client_id", "5c", AdminLogin.student_staffid);
     //  dat2.UpdateUserStatus("student_registration", "client_id", "5c", AdminLogin.student_staffid);
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED FINGERPRINT OF "+who+"'S -that is still registering- WITH MATR. NO:"+AdminLogin.student_staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
                    String id=" ";
               if(BiometricMain.getmyuserid()[3]==null);
               {
                  id =BiometricMain.getDB_Biodata()[2];
               }
               if(BiometricMain.getmyuserid()[3]!=null)
               {
                   id=BiometricMain.getmyuserid()[3];
               }
                    
                    StudentBasicRegSummary sbrs=new  StudentBasicRegSummary(id);
                    // JOptionPane.showMessageDialog(null, "recall that my id is:"+id);
                    sbrs.setLocationRelativeTo(null);
                    sbrs.setVisible(true);
                    this.setVisible(false);
             //StudentCapturedInformation stundent_confirm= new StudentCapturedInformation();
            // stundent_confirm.AddStudentsBiometricInfo();
              //BiometricMain.setStudObj(stundent_confirm);
                     
                 }
                //this.SaveCapturedFingerPrintTemplate();
                
               // IsError=jsplib.Close();
            // if(IsError==0){
               //  System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
            // }
            // else
            // {
              //   System.out.println("DEVICE OBJECT WAS NOT CLOSED.The Error code is:"+IsError);
            // }
   }
            }
            else
            {
                 System.out.println("Image Byte was not Captured.The Error code is:"+IsError);
                 javax.swing.JOptionPane.showMessageDialog(this, "Cannot capture your Fingerprint Image.\nPlease Make Sure that your FINGER is PROPERLY PLACED on the Fingerprint reader. \nThank you.","UNAVAILABLE FINGER ON THE READER ",2);
            }
            
                
        }
    }
     /*
    public void CaptureFingerPrint2()
    {
        if(IsError==0){
            //img_buffer=new byte[img_width,img_height];
            img_buffer=new byte[1000];
            long time_out=10000;
            long quality=95;
             //long new_quality=95
               new_quality = new int[1];;
            BufferedImage bm=new BufferedImage(img_width,img_height,BufferedImage.TYPE_BYTE_GRAY);
             imageBuffer2 = ((java.awt.image.DataBufferByte) bm.getRaster().getDataBuffer()).getData();
            if(jsplib.GetImageEx(imageBuffer2, time_out, 0, quality)==SGFDxErrorCode.SGFDX_ERROR_NONE)
            {
                IsError=SGFDxErrorCode.SGFDX_ERROR_NONE;
                SGFingerInfo sgif=new SGFingerInfo();
                System.out.println("Image Byte2 Captured.The Error code is:"+IsError);
                System.out.println("finger imp type :"+sgif.ImpressionType);
                System.out.println("finger img quality :"+sgif.ImageQuality);
                System.out.println("finger NUMBER :"+sgif.FingerNumber);
                status.setText("Image2 Byte Captured\n");
                jsplib.SetBrightness(90);
                IsError=jsplib.GetImageQuality(img_width, img_height, imageBuffer2, new_quality);
                status.setText(status.getText()+"\nImage2 Quality is: "+new_quality[0]+"\n");
                //
                capimg2.setIcon(new ImageIcon(bm));
                this.SaveCapturedFingerPrintTemplate2();
                /*
                IsError=jsplib.Close();
             if(IsError==0){
                 System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
             }
             else
             {
                 System.out.println("DEVICE OBJECT WAS NOT CLOSED.The Error code is:"+IsError);
             }
             
            }
            else
            {
                 System.out.println("Image Byte2 was not Captured.The Error code is:"+IsError);
            }
            
                
        }
    }
   
    public void SaveCapturedFingerPrintTemplate()
    {
        //cREATE TEMPLATE
        if(IsError==0)
        {
           
                 System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
                 IsError=jsplib.GetImage(imageBuffer);
        if(IsError==SGFDxErrorCode.SGFDX_ERROR_NONE)
        {
            System.out.println("fp image WAS gotten.The Error code is:"+IsError);
            IsError=jsplib.SetTemplateFormat( SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400);
            if(IsError==0)
        {
            templateBuffer=new byte[400];
            
            SGFingerInfo sgif=new SGFingerInfo();
            sgif.FingerNumber=SGFingerPosition.SG_FINGPOS_LI;
            sgif.ImageQuality=new_quality[0];
            sgif.ViewNumber=1;
            sgif.ImpressionType=SGImpressionType.SG_IMPTYPE_LP;
            System.out.println("finger impression type :"+sgif.ImpressionType);
            System.out.println("finger img quality :"+sgif.ImageQuality);
            System.out.println("finger NUMBER :"+sgif.FingerNumber);
            System.out.println("VIEW NUMBER :"+sgif.ViewNumber);
            System.out.println("img byte toString() :"+templateBuffer.toString());
            System.out.println("finger NUMBER :"+ SGFingerPosition.SG_FINGPOS_LI);
            IsError=jsplib.CreateTemplate(sgif, imageBuffer, templateBuffer);
            if(IsError==SGFDxErrorCode.SGFDX_ERROR_NONE)
        {
            
           // new MySerialize().SerializeMe(templateBuffer);
          tempvalue=new TestBase64Coder().EncodeBytetoString(templateBuffer);
            System.out.println("fp template WAS created.The Error code is: "+IsError);
        }
                 else
                 {
                     System.out.println("fp template WAS NOT created.The Error code is: "+IsError+" The buffer value is:"+templateBuffer[0]);
                 }
            
            
            System.out.println("fp template FORMAT WAS gotten.The Error code is:"+IsError);
        }
            else
            {
                System.out.println("fp template FORMAT WAS NOT gotten.The Error code is:"+IsError);
            }
            
        }
         else
         {
                     System.out.println("fp image WAS not gotten.The Error code is:"+IsError);
         }
               // int max_tempsize[]=new int[400];
               //IsError=jsplib.GetMaxTemplateSize(max_tempsize);
              
                //if(IsError==0)
        
            //System.out.println("max_temp size image WAS gotten.The Error code is:"+IsError+"Ttempsize is:"+max_tempsize[1]);
            
            
          //SGFingerPosition sfp=  new SGFingerPosition();
        }
                
            
             
       // }
        else
             {
                 System.out.println("DEVICE OBJECT WAS NOT CLOSED.The Error code is:"+IsError);
             } 
        
    }
    public void SaveCapturedFingerPrintTemplate2()
    {
        //cREATE TEMPLATE
        if(IsError==0)
        {
           
                 System.out.println("DEVICE OBJECT WAS not CLOSED.The Error code is:"+IsError);
                 IsError=jsplib.GetImage(imageBuffer2);
        if(IsError==SGFDxErrorCode.SGFDX_ERROR_NONE)
        {
            System.out.println("fp image WAS gotten.The Error code is:"+IsError);
            IsError=jsplib.SetTemplateFormat( SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400);
            if(IsError==0)
        {
            templateBuffer2=new byte[400];
            SGFingerInfo sgif=new SGFingerInfo();
            sgif.FingerNumber=SGFingerPosition.SG_FINGPOS_LI;
            sgif.ImageQuality=new_quality[0];
            sgif.ViewNumber=1;
            sgif.ImpressionType=SGImpressionType.SG_IMPTYPE_LP;
            System.out.println("finger2 impression type :"+sgif.ImpressionType);
            System.out.println("finger2 img quality :"+sgif.ImageQuality);
            System.out.println("finger2 NUMBER :"+sgif.FingerNumber);
            System.out.println("VIEW NUMBER2 :"+sgif.ViewNumber);
            System.out.println("temp img2 byte toString() :"+templateBuffer2.toString());
            System.out.println("finger NUMBER2 :"+ SGFingerPosition.SG_FINGPOS_LI);
            IsError=jsplib.CreateTemplate(sgif, imageBuffer2, templateBuffer2);
            if(IsError==SGFDxErrorCode.SGFDX_ERROR_NONE)
        {
            System.out.println("fp template2 WAS created.The Error code is: "+IsError);
        }
                 else
                 {
                     System.out.println("fp template2 WAS NOT created.The Error code is: "+IsError+" The buffer value is:"+templateBuffer2[0]);
                 }
            
            
            System.out.println("fp template2 FORMAT WAS gotten.The Error code is:"+IsError);
        }
            else
            {
                System.out.println("fp template2 FORMAT WAS NOT gotten.The Error code is:"+IsError);
            }
            
        }
         else
         {
                     System.out.println("fp image2 WAS not gotten.The Error code is:"+IsError);
         }
               // int max_tempsize[]=new int[400];
               //IsError=jsplib.GetMaxTemplateSize(max_tempsize);
              
                //if(IsError==0)
        
            //System.out.println("max_temp size image WAS gotten.The Error code is:"+IsError+"Ttempsize is:"+max_tempsize[1]);
            
            
          //SGFingerPosition sfp=  new SGFingerPosition();
        }
                
            
             
       // }
        else
             {
                 System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
             } 
        
    }
   
    public void VerifyFingerPrint(byte[] imageBuffer1,byte[] imageBuffer2)
    {
        boolean []matched=new boolean[1];
        if (IsError == SGFDxErrorCode.SGFDX_ERROR_NONE)
         {
             
            
               templateBuffer2=new TestBase64Coder().DecodeStringtoByte(tempvalue);
               if(templateBuffer==null){
                   
                   JOptionPane.showMessageDialog(this,"TEMPLATE BUFFER is NULL");
                   return;
               }
               
               else if (templateBuffer2==null)
               {
                    JOptionPane.showMessageDialog(this,"TEMPLATE BUFFER2 is NULL");
                    return;
               }
               else
               {
                    JOptionPane.showMessageDialog(this,"none of the TEMPLATE BUFFER is NULL");
                    
               }
               System.out.println("The String Form of the Debuffered String is:"+new TestBase64Coder().EncodeBytetoString(templateBuffer2));
               this.status.setText( "Deserialze="+templateBuffer2);
               IsError = jsplib.MatchTemplate(templateBuffer, templateBuffer2, 4, matched);
                 if (IsError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
                 if (matched[0]) {
                         this.status.setText( "Verification Successful");
                     }
                 else {
                         this.status.setText( "Verification Fail");
                     }
             }
                 else {
                 this.status.setText( "Verification Match Attempt Fail - MatchTemplate() Error : " + IsError);
             }
                 
             }                            
         
         else
        {
             this.status.setText( "Verification Attempt 1 Fail - Error in the Application: " + IsError);        
        }
           
    }
    */
    
    public void CloseFingerPrintDevice()
    {
        if(IsError==0){
        IsError=jsplib.Close();
        if(IsError==0)
        {
           
                 System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
            
             
        }
        else
             {
                 System.out.println("DEVICE OBJECT WAS NOT CLOSED.The Error code is:"+IsError);
             } 
        }
    }
    public static BufferedImage getBufferedImage()
     {
         return updatecapbuffImg;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        verimg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        imgLB = new javax.swing.JLabel();
        capimg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        closeB = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("DEVICE NAME:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SECUGEN  FINGERPRINT DEVICE");

        imgLB.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        imgLB.setText(" CAPTURED FINGERPRINT IMAGE");

        jButton1.setText("CAPTURE FINGERPRINT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        closeB.setText("Close");
        closeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jButton1)
                .addGap(37, 37, 37)
                .addComponent(closeB, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(closeB))
                .addContainerGap())
        );

        status.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        status.setText("STATUS TEXT");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("STATUS MESSAGE:");

        javax.swing.GroupLayout verimgLayout = new javax.swing.GroupLayout(verimg);
        verimg.setLayout(verimgLayout);
        verimgLayout.setHorizontalGroup(
            verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(verimgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(verimgLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(verimgLayout.createSequentialGroup()
                        .addGroup(verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(verimgLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(verimgLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(capimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(verimgLayout.createSequentialGroup()
                                        .addGroup(verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(imgLB)
                                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(132, 132, 132))))
        );
        verimgLayout.setVerticalGroup(
            verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, verimgLayout.createSequentialGroup()
                .addGroup(verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(verimgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(imgLB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capimg, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(verimg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(verimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.CloseFingerPrintDevice();
         if(!this.IsDeviceConnected())
            {
                System.out.println("Initialization Failed.The Secugen Device/Driver is not found:");
             javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Secugen\nDriver is still installed properly and that \nthe device is still PROPERLY CONNECTED\nThank you.");
             //return;
            }
         else
         {
             this.InitializeingerPrintDevice();
              CaptureFingerPrint();
         }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void closeBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBActionPerformed
        // TODO add your handling code here:
        CloseFingerPrintDevice();
        this.setVisible(false);
        //System.exit(1);
    }//GEN-LAST:event_closeBActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       // JOptionPane.showMessageDialog(this, "Thank you for Closing me\n\n     Bye!!!");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(this, "Thank you for Closing me\n\n     Bye!!!");
        
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel capimg;
    private javax.swing.JButton closeB;
    private javax.swing.JLabel imgLB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel status;
    private javax.swing.JPanel verimg;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        new AfamFP().setVisible(true);
        /*
        int img_width,img_height;
        
        JSGFPLib jsplib=new JSGFPLib();
        SGDeviceInfoParam device_info;
        device_info = new SGDeviceInfoParam() ;
       IsError  = jsplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
        //IsError = jsplib.InitEx(260,300,500);
        System.out.println("The Int is:"+IsError);
        if(IsError==0){
            System.out.println("Initialization Successful.The Error code is:"+IsError);
            IsError=jsplib.OpenDevice(0);
            //IsError=jsplib.Close();
            if(IsError==0){
                
            System.out.println("Initialization OF DEVICE Successful.The Error code is:"+IsError);
           IsError= jsplib.GetDeviceInfo(device_info);
           if(IsError==SGFDxErrorCode.SGFDX_ERROR_NONE){
            System.out.println("DEVICE info was gotten.The Error code is:"+SGFDxErrorCode.SGFDX_ERROR_NONE);
            img_width=device_info.imageWidth;
            img_height=device_info.imageHeight;
            System.out.println("Device id:"+device_info.deviceID);
            System.out.println("Device FWVERSION: "+device_info.FWVersion);
            System.out.println("Device IMAGE HEIGHT: "+img_height);
            System.out.println("Device IMAGE WIDTH: "+img_width);
            IsError=jsplib.Close();
             if(IsError==0){
                 System.out.println("DEVICE OBJECT WAS CLOSED.The Error code is:"+IsError);
             }
             else
             {
                 System.out.println("DEVICE OBJECT WAS NOT CLOSED.The Error code is:"+IsError);
             }
           }
             else
        {
             System.out.println("DEVICE info wasn't available-thus failed.The Error code is:"+IsError);
        }
            
            }
            else
        {
             System.out.println("DEVICE Initialization Failed.The Error code is:"+IsError);
        }
           // System.out.println("Device Name:"+jsplib.GetDeviceInfo(null));
        }
        else
        {
             System.out.println("Initialization Failed.The Error code is:"+IsError);
        }
       
        // jsplib.CreateTemplate(null, bytes, bytes1);
        //jsplib.GetDeviceInfo(null);
        */
    
}
 
}
