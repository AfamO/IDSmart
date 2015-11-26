/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */



import javax.media.CaptureDeviceInfo;
//import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FormatControl;
import javax.swing.*;
import java.awt.event.*;
import javax.media.util.BufferToImage;
import java.awt.image.BufferedImage;
//import java.util.Date;
import javax.media.control.FrameGrabbingControl;
import javax.imageio.ImageIO;
import javax.media.format.*;
import java.awt.*;
import javax.media.Buffer;
import java.io.*;
import java.util.Calendar;
import javax.media.CannotRealizeException;
import javax.media.NoPlayerException;

//import javax.media.control.


public class ImageCapturing  {

    CaptureDeviceInfo cam;
	MediaLocator locator;
	static Player player;
	FormatControl formatControl;
        Reconsoft data=new Reconsoft();
       // Reconsoft2 dat2=new Reconsoft2();

    private JFrame showFrame;
    private  JFrame camFrame;
    private String InfoLogged="";
    private JPanel butP;
    //File f=new File("");
    //private  File   ftest=new File(f.getAbsolutePath()+"\\src\\adcs_project\\std_pix\\img_"+Calendar.getInstance().getTimeInMillis()+".jpg");
    public static Boolean isWebcamFirst=false;
     BufferedImage capbuffImg=null;
     static BufferedImage updatecapbuffImg=null;
   // private  Image img;
   
    
	public ImageCapturing (){
          
                                    
		try{
                    //Manager.createPlayer(null)
                    
                     // System.out.println("Data source not found"+ cam.getLocator().getProtocol());
               player = Manager.createRealizedPlayer(new MediaLocator("vfw://0"));

				if(player != null){

				//player.getState();	
                                System.out.println("The state of the player is "+player.getState());
                                    // Starting the player
					player.start();
                                        Thread.sleep(2000);
                                        System.out.println("The state of the player after starting is "+player.getState());
                                          // Creating a Frame to display Video
					 camFrame = new JFrame();
                                       // camFrame.setSize(100,150);
                                         butP = new JPanel();
					camFrame.setTitle("FUPRE webcam Capturing Tool");
					camFrame.setLayout(new BorderLayout());

					if(player.getVisualComponent() !=null)
                                        {
					camFrame.add(player.getVisualComponent(), BorderLayout.CENTER);
                                        //System.out.println("webcam added");
                                        }else
                                        {
                                            JOptionPane.showMessageDialog(null, "webcam Intialization Problem\nContact Software Developers");
                                        }
                                        
                                        
                                        
                                        
                                       JButton close = new JButton("Stop and Exit");
                   // close.setBounds(20, 180, 100, 20);
                // CamButtonPane.add(close);
                   // CamButtonPane.setSize(200, 100);

                butP.add(close);
            //close.setVisible(true);

            close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    // stop and close the player which closes the video data source
                    player.stop();
                    player.close();
      // dispose of the frame and close the application
                  //  System.exit(0);
                    camFrame.setVisible(false);
            }
        });

        JButton takeB = new JButton("Take A Snapshot");

               butP.add(takeB);
         takeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    
         // Grab a frame from the capture device
FrameGrabbingControl frameGrabber =
(FrameGrabbingControl)player.getControl("javax.media.control.FrameGrabbingControl");
 Buffer buf = frameGrabber.grabFrame();

// Convert frame to an buffered image so it can be processed and saved
Image img = (new BufferToImage((VideoFormat)buf.getFormat()).createImage(buf));

Image pix=img.getScaledInstance(270,240,Image.SCALE_SMOOTH);
 capbuffImg = new BufferedImage(pix.getWidth(null),pix.getHeight(null), BufferedImage.TYPE_INT_RGB);
Graphics2D g = capbuffImg.createGraphics();
//g.drawImage(img, null, null);
//g.setComposite(AlphaComposite.Src);
g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//g.drawImage(img, 0, 0,320,240, null);
g.drawImage(img, 0, 0, null);
g.dispose();

//ImageIO.write(buffImg, "jpg",ftest );

//read the image from the saved path
//BufferedImage myImage = ImageIO.read(ftest);


JPanel ImagePane = new JPanel();
JPanel ImgbutP = new JPanel();
JLabel label = new JLabel();
label.setIcon(new ImageIcon(capbuffImg));
ImagePane.add(label);
  
  
showFrame = new JFrame();
showFrame.setTitle("FUPRE webcam Software");
showFrame.setLayout(new BorderLayout());
showFrame.add(ImagePane,BorderLayout.CENTER);
showFrame.setLocationRelativeTo(null);
showFrame.pack();
showFrame.setSize(320,300);
showFrame.setVisible(true);
showFrame.setResizable(false);


System.out.println("Passport has been taken");


JButton again = new JButton("Retake Image");
 ImgbutP.add(again);
 again.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
            showFrame.setVisible(false);
           showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
            }
        });

 JButton save = new JButton("Save and capture Signature");
  ImgbutP.add(save);
 save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {

               // try{
                   
    
//BufferedImage myImage = ImageIO.read(ftest);
//String myimageName=ftest.getName();
   
    
    //File rootpath = new File("");
   //File pixpath = new File(rootpath.getAbsolutePath()+"\\src\\adcs_project\\std_pix\\"+myimageName);
                if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("update_biometric_data")){
                    updatecapbuffImg=capbuffImg;
                    BiometricUpdate.pixL.setIcon(new ImageIcon(updatecapbuffImg));
                    //System.out.println("Got update pix: "+updatecapbuffImg.toString());
                    showFrame.setVisible(false);
   showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   player.stop();
   player.close();
   camFrame.setVisible(false);
                }
                else{
   if(BiometricMain.getCaptured_Biodata().length==0)
{
   // BiometricMain.setDB_Biodata(pixpath.getName(), 0);
    BiometricMain.setCaptured_Biodata(capbuffImg,0);
    if( data.CheckIfItemExist("client_biometric_infor", "client_id", AdminLogin.student_staffid))
    {
        
    }
    else
    {   
            data.SaveClientBiometricInfor(AdminLogin.student_staffid,convertPixtobyte(capbuffImg), convertPixtobyte(capbuffImg),convertPixtobyte(capbuffImg));
          //  dat2.SaveClientBiometricInfor(AdminLogin.student_staffid,convertPixtobyte(capbuffImg), convertPixtobyte(capbuffImg),convertPixtobyte(capbuffImg));

    }
     //
    data.UpdateBiometricImageInfor(2,"picture_img",AdminLogin.student_staffid,convertPixtobyte(capbuffImg));
   // dat2.UpdateBiometricImageInfor(2,"picture_img",AdminLogin.student_staffid,convertPixtobyte(capbuffImg));
   
    
    isWebcamFirst=true;
}
else
{
   // BiometricMain.setDB_Biodata(pixpath.getName(), 1);
   BiometricMain.setCaptured_Biodata(capbuffImg,1);
    if( data.CheckIfItemExist("client_biometric_infor", "client_id", AdminLogin.student_staffid))
    {
        
    }
    else
    {   
            data.SaveClientBiometricInfor(AdminLogin.student_staffid,convertPixtobyte(capbuffImg), convertPixtobyte(capbuffImg),convertPixtobyte(capbuffImg));
            
           // dat2.SaveClientBiometricInfor(AdminLogin.student_staffid,convertPixtobyte(capbuffImg), convertPixtobyte(capbuffImg),convertPixtobyte(capbuffImg));

    }
   data.UpdateBiometricImageInfor(2,"picture_img",AdminLogin.student_staffid,convertPixtobyte(capbuffImg));
   //dat2.UpdateBiometricImageInfor(2,"picture_img",AdminLogin.student_staffid,convertPixtobyte(capbuffImg));
 
    isWebcamFirst=false;
}

   //ImageIO.write(myImage, "jpg",pixpath);
   /*
   showFrame.setVisible(false);
   showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   player.stop();
   player.close();
   */
   
  // System.out.println("Image successfully transfered");
   JOptionPane.showMessageDialog(null, "Passport has been successfully captured");
   String who="";
   if(BiometricMain.getWhoIsRegistering().equalsIgnoreCase("student"))
   {
       who="STUDENT";
       data.UpdateUserStatus("student_registration", "client_id", "3c", AdminLogin.student_staffid);
      // dat2.UpdateUserStatus("student_registration", "client_id", "3c", AdminLogin.student_staffid);
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED PICTURE OF "+who+"'S -that is still registering- WITH MATR. NO:"+AdminLogin.student_staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
   }
   else
   {
       who="STAFF";
       data.UpdateUserStatus("staff_registration", "staffNum", "3c", AdminLogin.student_staffid);
     //  dat2.UpdateUserStatus("staff_registration", "staffNum", "3c", AdminLogin.student_staffid);
       InfoLogged=  AppLogger.InfoLogged(AdminLogin.getBiodata[1], AdminLogin.getBiodata[2], "CAPTURED PICTURE OF "+who+"'S -that is still registering- WITH ID :"+AdminLogin.student_staffid);
              AppLogger.LogInfo(BiometricMain.LogerObj,InfoLogged);
              
   }
    showFrame.setVisible(false);
   showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
   player.stop();
   player.close();

   
    camFrame.setVisible(false);
    
    BiometricMain.SignaturePage();
                }
                //}catch(IOException s){
             //  System.out.println("Error Saving Image Contact Software Developer"+s.getMessage());
               // }    
            }
        });


  showFrame.add(ImgbutP,BorderLayout.SOUTH);

       }
               catch(Exception s){
                    System.out.println(s+"error from wecam");

                }

            }
        });

              camFrame.add(butP,BorderLayout.SOUTH);
            camFrame.setLocationRelativeTo(null);
                  camFrame.setResizable(false);
					camFrame.pack();
                                       // camFrame.setSize(640,480);
					camFrame.setVisible(true);  
                                        
                                        
                                        
				}else{
                                    JOptionPane.showMessageDialog(null, "Use Internal webcam or cantact software developer");
                                }
                              
                               camFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
                player.stop();
                player.close();
            }
        });
                            //camFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           // System.out.println("The deFAULT CLOSE OPERATION value is:"+camFrame.getDefaultCloseOperation());
                            

			}
                
                catch(NoPlayerException npe)
                {
                    //player.stop();
                  //  player.close();
                    //JOptionPane.showMessageDialog(null, "Cannot find any available  device");
                    
                }
                
        catch(Exception e){
            e.printStackTrace();
	System.out.println("Please Check webcam if not started");
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
        
        
        
        
        
      /*  
        public static boolean IsNotPlayerAvailable (){
          boolean Isplayer=false;
           // Creating a Frame to display Video
					

		try{
                    
               player = Manager.createRealizedPlayer(new MediaLocator("vfw://0"));
         

				if(player != null){

					// Starting the player
					//player.start();
                                    Isplayer=true;

					
					//camFrame.add(player.getVisualComponent(), BorderLayout.CENTER);
                   
				}

			}
                catch(NoPlayerException npe)
                {
                    Isplayer=true;
                    System.out.println("Player IS NOT AVAILABLE. THUS NOPLAYEREXCEPTION, SINCE IT HAS NOT BEEN STARTED");
                    
                
                    
                }
        catch(IOException | CannotRealizeException e){
            e.printStackTrace();
	System.out.println("Please Check webcam if not started");
		}
                
                finally{
                    // player.close();
                    if(player!=null)
                    {
                        player.stop();
                        player.close();
                        
                    }
                    else
                    {
                        System.out.println("Player IS STILL NULL, SINCE IT HAS NOT BEEN STARTED");
                    }
                    
                }
                return Isplayer;
        }
*/
             
     private void formWindowClosing(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
       // JOptionPane.showMessageDialog(null,"Thank you for Closing me\n\n     Bye!!!");
        
    } 
     public static BufferedImage getBufferedImage()
     {
         return updatecapbuffImg;
     }

    public static void main(String[] args) {
        // TODO code application logic here
       new ImageCapturing ();
    }

}

