/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adcs_project;

/**
 *
 * @author Charles
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import com.topaz.sigplus.*;
import gnu.io.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
//import javax.comm.CommPortIdentifier;
//import javax.comm.SerialPort;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.image.BufferedImage;
import java.util.*;

public class SigPlusImg extends Frame implements Runnable
   {
    static SigPlusImg sg;
static   SigPlus  sigObj = null;
   Thread   eventThread;
   BufferedImage sigImage=null;
   static BufferedImage updatecapbuffImg=null;
   Button saveJpgButton;
    Reconsoft data=new Reconsoft();
   // Reconsoft2 dat2=new Reconsoft2();
   private String InfoLogged="";
   static Enumeration<CommPortIdentifier> portList;
   Button clearButton;
   static Container c;
   private static boolean IsCleared=false;
   private static boolean IsSignaturePadConnected=false;
   public static boolean  IsDllMissing=false;
   //public static Boolean isWebcamFirst=false;

   public static void main( String Args[] )
	  {
              /*
              if(checkforDll()==true){
              JOptionPane.showMessageDialog(sg, "Dll or other configuration file(s) is/are missing in your path", "CONFIGURATION ERROR", 0);
              System.out.println("dll is missing");
              return;
          }
              else
              {
                   System.out.println("dll is not missing");
              }
              */
               //while(!SigPlusImg.IsSignaturePadConnected())
            //{
                //System.out.println("Initialization Failed.The Signature pad  is not CONNECTED/Driver is not found:");
            // javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Signature pad \nDriver is installed properly and that \nthe device is PROPERLY CONNECTED\nThank you");
             //return;
            //}
              
               SigPlusImg demo = new SigPlusImg();
               demo.setSize(800,200);
               demo.setLocationRelativeTo(null);
               demo.setVisible(true);
               demo.setBackground(Color.lightGray);
               
          
	  //getAllModemPorts();
          
        //c= demo.getContentPane();
       //;
        //System.out.println("The mouse postion is:"+ c.getMousePosition());
	  }

	public SigPlusImg()
                
		{
                    initConnection();
             try
			{
             ClassLoader cl = (com.topaz.sigplus.SigPlus.class).getClassLoader();
	  		 sigObj = (SigPlus)Beans.instantiate( cl, "com.topaz.sigplus.SigPlus" );
                         //sigObj = (SigPlus)Beans.instantiate( cl, "com.topaz.sigplus.SigPlus" );
                         
                         
                        
                //setConstraints(sigObj, gbl, gc, 0, 1,
		//GridBagConstraints.REMAINDER, 1, 1, 1,
		//GridBagConstraints.CENTER,
		//GridBagConstraints.BOTH, 5, 0, 5, 0);
                
                
                        sigObj.setTabletModel("SignatureGem1X5");
                         sigObj.setTabletComPort("HID1");
                       
                         sigObj.setTabletState(1);
                
                        }
              catch ( ClassNotFoundException e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
			return;
			}
                catch ( IOException  e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
			return;
			}
               
               
              
              /*
                 SigPlusImg check= new SigPlusImg();
                 //check.InitializeingerPrintDevice();
                 check.setLocationRelativeTo(null);
                 //check.setSize(450, 500);
                  check.setVisible(true);
                  check.setResizable(false);
                  */
                
             //if(sigObj.getTabletState()!=1)
             //{
              
              //javax.swing.JOptionPane.showMessageDialog(null, "Please Make Sure that your Signature pad \nDriver is installed properly and that \nthe device is PROPERLY CONNECTED\nThank you");
             
            // }
             //else if(sigObj.getTabletState()==1){
               
                GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(gbl);
		Panel controlPanel = new Panel();
                
		setConstraints(controlPanel, gbl, gc, 0, 0,
		GridBagConstraints.REMAINDER, 1, 0, 0,
		GridBagConstraints.CENTER,
		GridBagConstraints.NONE,0, 0, 0, 0);
		add(controlPanel, gc);



		controlPanel.add(connectionChoice);
		controlPanel.add(connectionTablet);

		Button startButton = new Button("START");
		controlPanel.add(startButton);
                //startButton.setEnabled(false);
		Button stopButton = new Button("STOP");
                 stopButton.setEnabled(false);
		controlPanel.add(stopButton);

		 clearButton = new Button("CLEAR");
		controlPanel.add(clearButton);
                clearButton.setEnabled(false);

		saveJpgButton = new Button("SAVE SIGNATURE TO JPG");
                //JOptionPane.showMessageDialog(sg, "Your signature was successfully saved", "SUCCESSFULLY SAVED", 2);
                saveJpgButton.setEnabled(false);
		controlPanel.add(saveJpgButton);

		Button okButton = new Button("Next");
		controlPanel.add(okButton);
                okButton.setEnabled(false);

		initConnection();

		try
			{
			ClassLoader cl = (com.topaz.sigplus.SigPlus.class).getClassLoader();
	  		 sigObj = (SigPlus)Beans.instantiate( cl, "com.topaz.sigplus.SigPlus" );
                         
                        
                setConstraints(sigObj, gbl, gc, 0, 1,
		GridBagConstraints.REMAINDER, 1, 1, 1,
		GridBagConstraints.CENTER,
		GridBagConstraints.BOTH, 5, 0, 5, 0);
		add(sigObj, gc);
		sigObj.setSize(100,100);
                sigObj.clearTablet();
		setTitle("Teledom JPG Signature Image Application" );
                //sigObj.setTabletComPort("HSB");
                //sigObj.setTabletModel("SignatureGem1X5"); 


	   okButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    sigObj.setTabletState(0);
    		            //System.exit(0);
                BiometricMain.demo1.setVisible(false);
                BiometricMain.FingerPrint();
		   }
	  });
          
	   startButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    //sigObj.setTabletState(0);
                            //System.out.println("The Tablet Comport is:"+sigObj.getTabletComPort());
			    sigObj.setTabletState(1);
                            System.out.println("The signature pad has started, the comport is:"+sigObj.getTabletComPort());
		   }
	  });

	  stopButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			    sigObj.setTabletState(0);
                            
		   }
	  });

	  clearButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
                            //System.out.println(sigObj.getKeyReceipt());
			    sigObj.clearTablet();
                            sigObj.setTabletState(1);
                            clearButton.setEnabled(false);
                            saveJpgButton.setEnabled(false);
                            IsCleared=true;
		   }
	  });

	  saveJpgButton.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e){
String fname;
                   try {

                   sigObj.setTabletState(0);
                   sigObj.setImageJustifyMode(5);
                   sigObj.setImagePenWidth(3);
                   sigObj.setImageXSize(150);
                   sigObj.setImageYSize(70);
                   //sigObj.setImageTransparentMode(true);
                   sigImage  = sigObj.sigImage();
                   int w = sigImage.getWidth(null);
                   int h = sigImage.getHeight(null);
                   int[] pixels = new int[(w * h) * 2];
                   
                   if(JOptionPane.showConfirmDialog(null,"Are you sure that this is exactly the\n your signature and that you want to save it?")==0)
                   {
                        saveJpgButton.setEnabled(false);
                        if(BiometricMain.getWhoIsRegistering()!=null && BiometricMain.getWhoIsRegistering().equalsIgnoreCase("update_biometric_data")){
                    updatecapbuffImg=sigImage;
                    BiometricUpdate.sigL.setIcon(new ImageIcon(updatecapbuffImg));
                    System.out.println("Got update sig: "+updatecapbuffImg.toString());
                    JOptionPane.showMessageDialog(sg, "Your signature was successfully saved", "SUCCESSFULLY SAVED", 2);
                    
                    BiometricMain.demo1.setVisible(false);
                     //showFrame.setVisible(false);
   //BiometricMain.demo1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
                    
                }
                        else{
                        
                   if(BiometricMain.getCaptured_Biodata().length==0 || !ImageCapturing.isWebcamFirst)
                    {
                       // BiometricMain.setDB_Biodata(sigf.getName(), 0);
                          BiometricMain.setCaptured_Biodata(sigImage,0);
                          data.UpdateBiometricImageInfor(3,"sign_img",AdminLogin.student_staffid,convertPixtobyte(sigImage));
                         // dat2.UpdateBiometricImageInfor(3,"sign_img",AdminLogin.student_staffid,convertPixtobyte(sigImage));
                      
                        ImageCapturing.isWebcamFirst=false;
                    }
                    else
                    {
                       // BiometricMain.setDB_Biodata(sigf.getName(), 1);
                        BiometricMain.setCaptured_Biodata(sigImage,1);
                        data.UpdateBiometricImageInfor(3,"sign_img",AdminLogin.student_staffid,convertPixtobyte(sigImage));
                       // dat2.UpdateBiometricImageInfor(3,"sign_img",AdminLogin.student_staffid,convertPixtobyte(sigImage));
                       
                        ImageCapturing.isWebcamFirst=true;
                    }
           
                  // jpeg.encode(sigImage);
                   //ImageIO.write(sigImage, "jpg", sigf);
                   //fos.close();
                JOptionPane.showMessageDialog(sg, "Your signature was successfully saved", "SUCCESSFULLY SAVED", 2);
                
                clearButton.setEnabled(false);
                sigObj.clearTablet();
                sigObj.setTabletState(1);
                IsCleared=true;
                sigObj.setTabletState(0);
                String who=" ";
               
                if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
   {
       who="STUDENT";
       data.UpdateUserStatus("student_registration", "client_id", "4c", AdminLogin.student_staffid);
      // dat2.UpdateUserStatus("student_registration", "client_id", "4c", AdminLogin.student_staffid);
      
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED SIGNATURE OF "+who+"'S -that is still registering- WITH MATR. NO:"+AdminLogin.student_staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
   }
   else
   {
       who="STAFF";
       data.UpdateUserStatus("staff_registration", "staffNum", "4c", AdminLogin.student_staffid);
      // dat2.UpdateUserStatus("staff_registration", "staffNum", "4c", AdminLogin.student_staffid);
      
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED SIGNATURE OF "+who+"'S -that is still registering- WITH ID :"+AdminLogin.student_staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
              
   }
                 BiometricMain.FingerPrint();
                 BiometricMain.demo1.setVisible(false);
                        }
                
               

                   }
                   else{
                       
                   }
                   }
                catch (Throwable th) {
                        th.printStackTrace();
                }


              }
	  });


	 connectionTablet.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent e){

                        if(connectionTablet.getSelectedItem() != "SignatureGemLCD4X3"){
                           sigObj.setTabletModel(connectionTablet.getSelectedItem());
                        }
                        else{
                           sigObj.setTabletModel("SignatureGemLCD4X3New"); //properly set up LCD4X3
                        }

		  }
	  });


	 connectionChoice.addItemListener(new ItemListener(){
		  public void itemStateChanged(ItemEvent e){

                        if(connectionChoice.getSelectedItem() != "HSB"){
  	                   sigObj.setTabletComPort(connectionChoice.getSelectedItem());
                        }
                        else{
                           sigObj.setTabletComPort("HID1"); //properly set up HSB tablet
                        }

		  }
	  });

			addWindowListener(new WindowAdapter()
				{
				public void windowClosing( WindowEvent we )
					{
					sigObj.setTabletState( 0 );
					//System.exit( 0 );
                                        BiometricMain.demo1.setVisible(false);
					}

				public void windowClosed( WindowEvent we )
					{
					System.exit( 0 );
					}
				} );

			sigObj.addSigPlusListener( new SigPlusListener()
				{
				public void handleTabletTimerEvent( SigPlusEvent0 evt )
					{
                                            // if(sigImage!=null)
                                            
                       // {
                            
                        //}
					}

                                    @Override
				public void handleNewTabletData( SigPlusEvent0 evt )
					{
                                            saveJpgButton.setEnabled(true);
					}

                                    @Override
				public void handleKeyPadData( SigPlusEvent0 evt )
					{
                                            saveJpgButton.setEnabled(true);
					}
				} );


			show();
                        
                        
                         sigObj.setTabletModel("SignatureGem1X5");
                         sigObj.setTabletComPort("HID1");
                       
                         sigObj.setTabletState(1);
                       
                        
                        
                        System.out.println("SigObj tablet state is "+ sigObj.getTabletState());
                        System.out.println("SigObj Serial No is "+ sigObj.getSerialNumber());
                        System.out.println("SigObj started here automatically ");
                        connectionChoice.setEnabled(false);
                        connectionTablet.setEnabled(false);

			eventThread = new Thread(this);
			eventThread.start();
                        
			}
		catch ( IOException  e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
			return;
			}
                catch ( ClassNotFoundException e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
			return;
			}
                /*
                this.setSize(800,200);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setResizable(false);
            this.setBackground(Color.lightGray);
            */
            
           // } 
		}


                public void run()
	        {
                 
	        try
		   {
		   while (true)
			{
			Thread.sleep(100);
                        if(sigObj.getNumberOfStrokes()==0 || sigObj.numberOfTabletPoints()==0)
                                            
                    {
                        /*
                        System.out.println("NO IMAGE YET :");
                         System.out.println(" Serial No is "+ sigObj.getSerialNumber());
                         System.out.println(" SigPlus Version is "+ sigObj.getSigPlusVersion());
                         System.out.println(" SigPlus Name is "+ sigObj.getName());
                         System.out.println(" SigPlus Model No is "+ sigObj.getModelNumber());
                         System.out.println(" Baudrate is "+ sigObj.getTabletBaudRate());
                         System.out.println(" Number of Tablet Points "+ sigObj.numberOfTabletPoints());
                          System.out.println(" TabletComPort is:"+ sigObj.getTabletComPort());
                          System.out.println(" SigReceipt is:"+ sigObj.getSigReceipt());
                          System.out.println(" AsciiReceipt is:"+ sigObj.getSigReceiptAscii());
                          System.out.println(" TabletComPort is:"+ sigObj.getTabletComPort());
                          System.out.println(" TabletResolution is:"+ sigObj.getTabletResolution());
                          */
                        
                    }
                        else if(IsCleared==true)
                        {
                            saveJpgButton.setEnabled(false);
                            IsCleared=false;
                        }
                     else 
                    {
                         saveJpgButton.setEnabled(true);
                         clearButton.setEnabled(true);
                         //System.out.println("Number of Tablet Points "+ sigObj.numberOfTabletPoints());
                         //System.out.println("Number of Strokes is: "+sigObj.getNumberOfStrokes());
                         //System.out.println("I just GOT my IMAGE: THE HEIGHT IS :"+sigObj.sigImage().getHeight(null)+" while the width is"+sigObj.sigImage().getWidth(null));
                    }
                    
                        
			}
		   }
	        catch (InterruptedException e)
		   {
                       System.out.println("The Interruption is from: "+e.getMessage()+"ln Cause is:");
                       e.printStackTrace();
		   }
	        }

                TextField txtPath = new TextField("C:\\test.sig", 30);

                Choice connectionChoice = new Choice();   protected String[] connections =
	        {
           "HSB"
	        };
               // connectionChoice.setEnabled(false);


                Choice connectionTablet = new Choice();   protected String[] tablets =
	        {
               "SignatureGem1X5"
              };
                
/*
 * "SignatureGem4X5",
      		   "SignatureGemLCD",
       		   "SignatureGemLCD4X3",
      		   "ClipGem",
      		   "ClipGemLGL",
                   * ,
		   "COM1",
		   "COM2",
		   "COM3",
		   "COM4",
           "USB",
		   "HSB",
 */
                private void initConnection()
	        {
		   for(int i = 0; i < connections.length; i++)
		   {
			connectionChoice.add(connections[i]);
		   }

		   for(int i = 0; i < tablets.length; i++)
		   {
			connectionTablet.add(tablets[i]);
		   }

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
                 public static BufferedImage getBufferedImage()
     {
         return updatecapbuffImg;
     }

                //Convenience method for GridBagLayout
	        private void setConstraints(
		Component comp,
		GridBagLayout gbl,
	    	GridBagConstraints gc,
	    	int gridx,
	    	int gridy,
	    	int gridwidth,
	    	int gridheight,
	    	int weightx,
	    	int weighty,
	    	int anchor,
	    	int fill,
	    	int top,
	    	int left,
	    	int bottom,
	    	int right)
	    	{
			gc.gridx = gridx;
			gc.gridy = gridy;
			gc.gridwidth = gridwidth;
			gc.gridheight = gridheight;
			gc.weightx = weightx;
			gc.weighty = weighty;
			gc.anchor = anchor;
			gc.fill = fill;
			gc.insets = new Insets(top, left, bottom, right);
			gbl.setConstraints(comp, gc);
	    	}
               /*
                public static boolean IsSignaturePadConnected(){
                    {
                   // initConnection();
             try
			{
             ClassLoader cl = (com.topaz.sigplus.SigPlus.class).getClassLoader();
	  		 sigObj = (SigPlus)Beans.instantiate( cl, "com.topaz.sigplus.SigPlus" );
                         
                        
                //setConstraints(sigObj, gbl, gc, 0, 1,
		//GridBagConstraints.REMAINDER, 1, 1, 1,
		//GridBagConstraints.CENTER,
		//GridBagConstraints.BOTH, 5, 0, 5, 0);
                
                
                        sigObj.setTabletModel("SignatureGem1X5");
                         sigObj.setTabletComPort("HID1");
                       
                         sigObj.setTabletState(1);
                         //IsSignaturePadConnected=true;
                
                        }
              catch ( ClassNotFoundException e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
                           IsSignaturePadConnected=false;
                           sigObj.setTabletState(0);
                            //sigObj.clearTablet();
			//return;
			}
                catch ( IOException  e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
                           IsSignaturePadConnected=false;
                           sigObj.setTabletState(0);
                            //sigObj.clearTablet();
			//return;
			}
                     if(sigObj.getTabletState()!=1)
             {
                 IsSignaturePadConnected=false;
                  sigObj.setTabletState(0);
                  //sigObj.clearTablet();
               // System.out.println(sigObj.getTabletState()+" "+sigObj.getTabletComPort()+" "+sigObj.getTabletComTest());
    //JOptionPane.showMessageDialog(null, "Please Make Sure that your Signature pad device is PROPERLY CONNECTED\n"+"Thank you");
           
             // JOptionPane.showMessageDialog(null, "Please Make Sure that your Signature pad device is PROPERLY CONNECTED\n Before Click OK or You have to Resume Data Capturing Again \n"+"Thank you");
             //CheckpadPlugin();
              //SigPlusImg demo = new SigPlusImg(); 
             // BiometricMain.demo1=demo;
             //return;    
             }
                     else
                     {
                         IsSignaturePadConnected=true;
                     }
                      return IsSignaturePadConnected;
                     
                }
               
                
            }
            */
}

