/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class SigPlusImg1 extends Frame implements Runnable
   {
    static SigPlusImg sg;
   SigPlus  sigObj = null;
   Thread   eventThread;
   BufferedImage sigImage=null;
   Button saveJpgButton;
   static int bauds=115200;
   private static final String _NO_DEVICE_FOUND = "  No Device Found";
    static CommPortIdentifier portId;
     private final static Formatter _formatter = new Formatter(System.out);

   static Enumeration<CommPortIdentifier> portList;
   Button clearButton;
   static Container c;
   private static boolean IsCleared=false;
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

	public SigPlusImg1()
		{
               
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
                startButton.setEnabled(false);
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
                System.out.println("The Tablet Comport is:"+sigObj.getTabletComPort());
			    sigObj.setTabletState(1);
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
                            IsCleared=true;
		   }
	  });

	  saveJpgButton.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e){
String fname;
                   try {

                   sigObj.setTabletState(0);
                   sigObj.setImageJustifyMode(5);
                   sigObj.setImagePenWidth(1);
                   sigObj.setImageXSize(150);
                   sigObj.setImageYSize(70);
                   sigImage  = sigObj.sigImage();
                   int w = sigImage.getWidth(null);
                   int h = sigImage.getHeight(null);
                   int[] pixels = new int[(w * h) * 2];
                   //File f=new File("");
                   //System.out.println("The absolute path is "+f.getAbsolutePath());
                   //System.out.println("The Width is "+w+" while the Height is "+h);
                   //System.out.println("The Parent path is "+f.getParent());
                   //sigImage.setRGB(0, 0, 0, 0, pixels, 0, 0);
                   //(0, 0, 0, 0, pixels, 0, 0);
                  // File sigf=new File(f.getAbsolutePath()+"\\src\\adcs_project\\std_sig\\sig_"+Calendar.getInstance().getTimeInMillis()+".jpg");
                   
                   //FileOutputStream fos = new FileOutputStream(sigf);
                   
                   //fname=Calendar.getInstance().getTimeInMillis()+".jpg";
                   //JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
                  // System.out.println("The File Name  is "+sigf.getName());
                   //System.out.println("The Canonical Path  is "+sigf.getCanonicalPath());
                  // System.out.println("The Parent path is "+sigf.getParent());
                   if(BiometricMain.getCaptured_Biodata().length==0 || !ImageCapturing.isWebcamFirst)
                    {
                       // BiometricMain.setDB_Biodata(sigf.getName(), 0);
                         BiometricMain.setCaptured_Biodata(sigImage,0);
                        ImageCapturing.isWebcamFirst=false;
                    }
                    else
                    {
                       // BiometricMain.setDB_Biodata(sigf.getName(), 1);
                        BiometricMain.setCaptured_Biodata(sigImage,1);
                        ImageCapturing.isWebcamFirst=true;
                    }
           
                  // jpeg.encode(sigImage);
                   //ImageIO.write(sigImage, "jpg", sigf);
                   //fos.close();
                JOptionPane.showMessageDialog(sg, "Your signature was successfully saved", "SUCCESSFULLY SAVED", 2);
                saveJpgButton.setEnabled(false);
                clearButton.setEnabled(false);
                sigObj.clearTablet();
                sigObj.setTabletState(1);
                IsCleared=true;
                sigObj.setTabletState(0);
                 BiometricMain.FingerPrint();
                BiometricMain.demo1.setVisible(false);
               

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

				public void handleNewTabletData( SigPlusEvent0 evt )
					{
                                            //saveJpgButton.setEnabled(true);
					}

				public void handleKeyPadData( SigPlusEvent0 evt )
					{
                                            //saveJpgButton.setEnabled(true);
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
		catch ( IOException | ClassNotFoundException e )
			{
                           System.out.println("The Error cause is: "+e.getMessage());
                           //e.printStackTrace();
			return;
			}

		}


                public void run()
	        {
                 
	        try
		   {
		   while ( true )
			{
			Thread.sleep(100);
                        if(sigObj.getNumberOfStrokes()==0 || sigObj.numberOfTabletPoints()==0)
                                            
                    {
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
                         System.out.println("Number of Tablet Points "+ sigObj.numberOfTabletPoints());
                         System.out.println("Number of Strokes is: "+sigObj.getNumberOfStrokes());
                         //System.out.println("I just GOT my IMAGE: THE HEIGHT IS :"+sigObj.sigImage().getHeight(null)+" while the width is"+sigObj.sigImage().getWidth(null));
                    }
                    
                        
			}
		   }
	        catch (InterruptedException e)
		   {
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
                private static Enumeration<CommPortIdentifier> getCleanPortIdentifiers()
    {
        return CommPortIdentifier.getPortIdentifiers();
    }
                
                public static java.util. List getAllModemPorts()
    {
        //java.util.Enumeration portList;
       java.util. List<CommPortIdentifier> portLst=new ArrayList<CommPortIdentifier>();
        System.out.println("\nSearching for Devices...");
        portList = getCleanPortIdentifiers();
        int i=0;
        try{
            SerialPort serialPort = null;
            portId=CommPortIdentifier.getPortIdentifier("HID1");
            System.out.println("\nHID1 Device FOUND...");
                        serialPort = (SerialPort)portId.open("TeledomSMSLibCommTester", 1971);
                        serialPort.setFlowControlMode(org.smslib.helper.SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds, org.smslib.helper.SerialPort.DATABITS_8, org.smslib.helper.SerialPort.STOPBITS_1, org.smslib.helper.SerialPort.PARITY_NONE);
                        //inStream = serialPort.getInputStream();
                       // outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);
        }
        catch(javax.comm.NoSuchPortException | javax.comm.PortInUseException | javax.comm.UnsupportedCommOperationException ex)
        {
            
            System.out.println("\n can't open HID1 Device because of..."+ex.getMessage());
            ex.printStackTrace();
            
        }
        while (portList.hasMoreElements())
        {
            i++;
            portId = portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_PARALLEL)
            {
                _formatter.format("%nFound port: %-5s%n", portId.getName());
                //for (int i = 0; i < bauds.length; i++)
                //{
                    SerialPort serialPort = null;
                    _formatter.format("       Trying at %6d...", bauds);
                    try
                    {
                        InputStream inStream;
                        OutputStream outStream;
                        int c;
                        String response;
                        portId=CommPortIdentifier.getPortIdentifier("HIDI");
                        serialPort = (SerialPort)portId.open("TeledomSMSLibCommTester", 1971);
                        serialPort.setFlowControlMode(org.smslib.helper.SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds, org.smslib.helper.SerialPort.DATABITS_8, org.smslib.helper.SerialPort.STOPBITS_1, org.smslib.helper.SerialPort.PARITY_NONE);
                        inStream = serialPort.getInputStream();
                        outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);
                        c = inStream.read();
                        while (c != -1)
                            c = inStream.read();
                        outStream.write('A');
                        outStream.write('T');
                        outStream.write('\r');
                        
                        Thread.sleep(1000);
                        response = "";
                        StringBuilder sb = new StringBuilder();
                        c = inStream.read();
                        while (c != -1)
                        {
                            sb.append((char) c);
                            c = inStream.read();
                        }
                        response = sb.toString();
                        if (response.indexOf("OK") >= 0)
                        {
                            try
                            {
                                System.out.print("  Getting Info...");
                                outStream.write('A');
                                outStream.write('T');
                                outStream.write('+');
                                outStream.write('C');
                                outStream.write('G');
                                outStream.write('M');
                                outStream.write('M');
                                outStream.write('\r');
                                response = "";
                                c = inStream.read();
                                while (c != -1)
                                {
                                    response += (char) c;
                                    c = inStream.read();
                                }
                                portLst.add(portId);
                                System.out.println("\nThe port of the device is "+portId.getName() +"\n" );
                                //System.out.println("\nOriginal Response is: " +response+"\n");
                                System.out.println(" Found: " + response.replaceAll("\\s+OK\\s+", "").replaceAll("\n", ""));
                                
                                
                                
                                
                            }
                            catch (Exception e)
                            {
                                System.out.println(_NO_DEVICE_FOUND);
                            }
                        }
                        else
                        {
                            System.out.println(_NO_DEVICE_FOUND);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.print(_NO_DEVICE_FOUND);
                        Throwable cause = e;
                        while (cause.getCause() != null)
                        {
                            cause = cause.getCause();
                        }
                        System.out.println(" (" + cause.getMessage() + ")");
                    }
                    finally
                    {
                        if (serialPort != null)
                        {
                            serialPort.close();
                        }
                    }
                //}
            }
        }
        if(portLst.isEmpty())
        {
            //IsNotModemPortsAvailable=true;
            javax.swing.JOptionPane.showMessageDialog(null, "There are no comm/modem ports installed in your system\nOr remove all the devices using your ports,");
            System.exit(1);
        }
         
        System.out.println("\nTest complete.");
         return portLst;
    }
    */
                
            }


