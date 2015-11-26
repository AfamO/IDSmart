package adcs_project;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
import javax.smartcardio.*;
import java.util.*;
import javax.swing.*;
import javax.smartcardio.CardNotPresentException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import opencard.core
//import opencard.
//import com.sun.javacard.javax.smartcard.rmiclient.*;
import java.nio.charset.Charset;
public final class JSmartCard {
    private static byte CLA;
    private static byte f;
   // private static byte INS;
    final static byte   CLASS  = (byte) 0x80; 
    final static byte   INS  = (byte) 0xA4; 
    final static byte   INS2  = (byte) 0xB2; 
    //0xA4
    private static byte P1;
    private static byte P2;
    private static byte Lc;
    final static byte   LC  = (byte) 0x31;
    static byte   DATA  = (byte) 0xF0;
    final  static byte   DATA2  = (byte) 0x42;
            //(byte) 0xFF;
    private static byte[] globalPin = { (byte)0x31, (byte)0x32, (byte)0x33, (byte)0x34};
    private static byte[] globalSECDATA = { (byte)0x41, (byte)0x43, (byte)0x4F, (byte)0x53,(byte)0x54, (byte)0x45,(byte)0x53, (byte)0x54};
    final static byte bTAG_INS_VERIFY_KEY = (byte) 0x30;
    private static byte []dat;  //41 43 4F 53 54 45 53 54
    private static byte data;
    //private static byte Le= (byte)41 43 4F 53 03 01 16 20;
    private static String pin="1234";
     private static String infoTOwrite="ACOSTEST";
     private static String anserTOwrite="";
     private static String WRITEcommand="80D2010002";
     private static ResponseAPDU r=null;
     private Card card1=null;
     CardTerminal terminal;
      //byte [ ] comAPDUBytes31 = new byte[100,200];
      byte [ ] comAPDUBytes3 = new byte []{CLASS, INS2, 0x01, 0x00, 01,DATA2};
    private static String s;
   
   public JSmartCard(){
       this.ConnectToSmartCard();
       
   }
   public static String byteArrayToHexString(byte[] b){
    String result = "";
    for (int i = 0; i < b.length; i++) {
        result +=
                Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
    }
    return result;
    }
   public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    } 
    private static byte getByteData(String byte1Str) throws Exception
    {
        byte data_LC;
        byte byte1 =  Byte.parseByte(byte1Str );
        //byte byte2 =  Byte.parseByte(byte2Str);
        data_LC = byte1;
        //data_LC[1] = byte2;
      
        return data_LC;
    }
    public static String split(byte[] bytes) {
        String entire = new String(bytes);
        return entire;
                //split("\\*\\.\\*");
    }
    public String ConvertByteToString(byte a[])
    {
        String val="";
        byte []b="12345ACOSTEST".getBytes();
        for(int j=0;j<a.length;j++)
        {
           char c=(char)a[j];
           val+=c;
           System.out.println("When the byte at "+j+" is ="+a[j]+".The converted char is="+c);
        }
        System.out.println("CONVERTED string IS " + val);
        return val;
    }
    public static String htos(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String tmp = Integer.toHexString(((int) bytes[i]) & 0xFF);
            while (tmp.length() < 2) {
                tmp = "0" + tmp;
            }
            if (i != bytes.length - 1) {
                sb.append(tmp).append(" ");
            } else {
                sb.append(tmp);
            }
            if(((i+1)%17) == 0)
            {
                sb.append("\n");
                sb.append("        ");
            }

        }
        return sb.toString().toUpperCase();
    }
    public void ConnectToSmartCard(){
    try{
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        //CardTerminals.State.
        CardTerminal terminal = terminals.get(0);
        
        
        if(terminal.isCardPresent()){
             JOptionPane.showMessageDialog(null,"Card is Present");
             this.terminal=terminal;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Card is absent.Connnect it in 5 secs\nWaiting...");
            terminal.waitForCardPresent(5000);
            JOptionPane.showMessageDialog(null,"Time is Up!\nBye!!!");
            return;
        }
        // establish a connection with the card
        Card card = terminal.connect("*");
        this.card1=card;
    }
    catch(CardException ce){
        
    }
}
    
    public Card getConnection()
    {
        return card1;
    }
   
    public void PersonalizeCard(){
        
    }
    public void IssueICCode(){
        
    }
    public boolean AuthenticateSmartPin()
    {
        return false;
    }
    public  boolean IsCardPresent(){
        boolean Ispresent=false;
        if(card1!=null && terminal!=null){
            try{
                if(terminal.isCardPresent())
                {
                     Ispresent= true;
                }
                else
                {
                    Ispresent= false;
                }
            }
            catch(CardException ce)
            {
                
            }
            
               
        }
        else
        {
            JOptionPane.showMessageDialog(null,"SmartCard Initialization Problem\nBye!!!");
        }
        return Ispresent;
        
    }
    public void CreateFiles(){
        
    }
     private Boolean SelectFileFromSmartCard(byte file,byte id){
       boolean IsFileSelected=false; 
         try{
            if( this.terminal!=null && this.terminal.isCardPresent())
            {
                CardChannel channel = card1.getBasicChannel();
                byte [ ] SelectFilecomAPDUBytes = new byte []{CLASS,INS, 0x00, 0x00, 02,file, id};
                r=channel.transmit(new CommandAPDU(SelectFilecomAPDUBytes));
                if(Integer.toHexString(r.getSW()).equals("9000") ||Integer.toHexString(r.getSW()).equals("9102")){
                    System.out.println("FILE selection WAS SUCCESSFUL:Status is"+Integer.toHexString(r.getSW()));
                    IsFileSelected=true;
                }
                else
                {
                    System.out.println("FILE selection WAS nOt SUCCESSFUL:Status is"+Integer.toHexString(r.getSW()));
                    IsFileSelected=false;
                }
                
                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Card is absent.Please insert your smartCard\n to connnect it in 5 secs\nWaiting...");
            }
         }
         catch(CardException ce){
             
         }
        return IsFileSelected;
    }
    public String ReadFromSmartCard(byte file,byte id){
        String infoRed="none";
         try{
            if( this.terminal!=null && this.terminal.isCardPresent())
            {
                if(this.SelectFileFromSmartCard(file, id)){
                CardChannel channel = card1.getBasicChannel();
              byte[] val= infoTOwrite.getBytes();
           //new CommandAPDU(0x80,0xD2,0x01,0x00,val)
           r= channel.transmit(new CommandAPDU(0x80,0xB2,0x01,0x00,12));
          //channel.transmit((Buffer)apdu, apdu);
         System.out.println("The response2 to string is: " + r.toString());
          System.out.println("The response2 to Sting() DATA ISSS: " + r.getData().toString());
          byte dat[]=r.getData();
        System.out.println("The response2 DATA ISSS: " + r.getData());
        System.out.println("The RESPONSE2 FOR GETDATA() IS "+new Concatenation().ConvertByteToString(r.getData()));
        System.out.println("The RESPONSE2 FOR GETBYTE() IS "+new Concatenation().ConvertByteToString(r.getBytes()));
        //Integer.toHexString(javaCard.getStatusWords()).toUpperCase()
        System.out.println("The SW1 is: " + Integer.toHexString(r.getSW1()));
        System.out.println("The SW2 is: " + Integer.toHexString(r.getSW2()));
        System.out.println("The tHE WhoLE SW is: " + Integer.toHexString(r.getSW()));
          //r = channel.transmit(new CommandAPDU(new byte[]{CLASS,(byte)0xA4,(byte)0x00,(byte)0x00,(byte)0x02,0,(byte)0x02,0}));
         if(Integer.toHexString(r.getSW()).equals("9000") ||Integer.toHexString(r.getSW()).equals("9102"))
         {
              infoRed= new Concatenation().ConvertByteToString(r.getData());

             System.out.println("OPERATION ON THE FILE WAS SUCCESSFUL" );
         }
         else
         {
             System.out.println("OPERATION ON THE FILE WAS NOT SUCCESSFUL" );
         }
     }
                else
                {
                     System.out.println("FILE selection WAS nOt SUCCESSFUL:Thus cannot read from it"); 
                }
            }
               else
            {
                JOptionPane.showMessageDialog(null,"Card is absent.Please insert your smartCard\n to connnect it in 5 secs\nWaiting...");
            }
                
                
            
            
         }
         catch(CardException ce){
            System.out.println("The Error cause is:"+ce.getMessage());
            ce.printStackTrace();
         }
         finally{
             try{
                 this.card1.disconnect(true);
             }
             catch(CardException ce){
            System.out.println("COULDN'T DISCONNECT CARD is:"+ce.getMessage());
            ce.printStackTrace();
         }
         }
        return infoRed;
    }
    public boolean WriteToSmartCard(byte file,byte id,String data){
        Boolean IsWriiten=false;
         try{
            if( this.terminal!=null && this.terminal.isCardPresent())
            {
                if(this.SelectFileFromSmartCard(file, id)){
                   CardChannel channel = card1.getBasicChannel();
              byte[] val= data.getBytes();
           //new CommandAPDU(0x80,0xD2,0x01,0x00,val)
           r= channel.transmit(new CommandAPDU(0x80,0xD2,0x01,0x00,val));
          //channel.transmit((Buffer)apdu, apdu);
         System.out.println("The response2 to string is: " + r.toString());
          System.out.println("The response2 to Sting() DATA ISSS: " + r.getData().toString());
          byte dat[]=r.getData();
        System.out.println("The response2 DATA ISSS: " + r.getData());
        System.out.println("The RESPONSE2 FOR GETDATA() IS "+new Concatenation().ConvertByteToString(r.getData()));
        System.out.println("The RESPONSE2 FOR GETBYTE() IS "+new Concatenation().ConvertByteToString(r.getBytes()));
        //Integer.toHexString(javaCard.getStatusWords()).toUpperCase()
        System.out.println("The SW1 is: " + Integer.toHexString(r.getSW1()));
        System.out.println("The SW2 is: " + Integer.toHexString(r.getSW2()));
        System.out.println("The tHE WhoLE SW is: " + Integer.toHexString(r.getSW()));
          //r = channel.transmit(new CommandAPDU(new byte[]{CLASS,(byte)0xA4,(byte)0x00,(byte)0x00,(byte)0x02,0,(byte)0x02,0}));
         if(Integer.toHexString(r.getSW()).equals("9000") ||Integer.toHexString(r.getSW()).equals("9102"))
         {
             IsWriiten=true;

             System.out.println("OPERATION ON THE FILE WAS SUCCESSFUL" );
         }
         else
         {
             IsWriiten=false;
             System.out.println("OPERATION ON THE FILE WAS NOT SUCCESSFUL" );
         }
     }
                else
                {
                   System.out.println("FILE selection WAS nOt SUCCESSFUL:Thus cannot write to it"); 
                }
                }
                
               else
            {
                JOptionPane.showMessageDialog(null,"Card is absent.Please insert your smartCard\n to connnect it in 5 secs\nWaiting...");
            }
                
                
            
            
         }
         catch(CardException ce){
            System.out.println("The Error cause is:"+ce.getMessage());
            ce.printStackTrace();
         }
         finally{
             try{
                 this.card1.disconnect(true);
             }
             catch(CardException ce){
            System.out.println("COULDN'T DISCONNECT CARD is:"+ce.getMessage());
            ce.printStackTrace();
         }
         }
        
        return IsWriiten;
    }
    public boolean disconnectSmartCard(){
        return false;
    }

   public static void main(String args[]) throws CardException
   {
       
       String example = "This is an example";
		    byte[] bytes = example.getBytes();
 
		    System.out.println("Text : " + example);
		    System.out.println("Text [Byte Format] : " + bytes);
		    System.out.println("Text [Byte Format] : " + bytes.toString());
 
		     s= new String(bytes);
		    System.out.println("Text Decryted : " + s);
                    // Code to convert byte arr to str:
byte[] by_original = {0,1,-2,3,-4,-5,6};
String str1 = new String(by_original);
System.out.println("str1 >> "+str1);

// Code to convert str to byte arr:
byte[] by_new = str1.getBytes();
for(int i=0;i<by_new.length;i++) 
System.out.println("by1["+i+"] >> "+str1);
       
       // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        //CardTerminals.State.
        CardTerminal terminal = terminals.get(0);
        /*
        if(terminal.isCardPresent()){
             JOptionPane.showMessageDialog(null,"Card is Present");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Card is absent.Connnect it in 5 secs\nWaiting...");
            terminal.waitForCardPresent(5000);
            JOptionPane.showMessageDialog(null,"Time is Up!\nBye!!!");
            return;
        }
        */
        // establish a connection with the card
        Card card = terminal.connect("*");
        
        
        System.out.println("card: " + card);
        try{
            String decoded = new String(card.getATR().getBytes(),  Charset.defaultCharset());
            System.out.println("The CARD ATR IS "+new Concatenation().ConvertByteToString(card.getATR().getBytes()));
        System.out.println("The conVERSsion IS "+decoded);
            System.out.println("The convsesion is "+new String(new byte[]{-109}, "Cp1252"));
            String output = split(card.getATR().getBytes());
        System.out.println("tHE RESULT BEFORE IS:"+card.getATR().getBytes());
        System.out.println("tHE RESULT AFTER IS:"+output);
            //System.out.println("The STRING IS: "+new String(new byte[]{ (byte)0x00 }));
            System.out.println("The STRING IS: "+new String(card.getATR().getBytes()));
            System.out.println("The STRING from char IS: ");
            char ch = (char)0x31;
System.out.println(ch);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        System.out.println("The ATR is: " + card.getATR().getBytes().toString());
        s = new String(card.getATR().getBytes());
		    System.out.println("Text Decryted : " + s);
        String entry = new String(card.getATR().getBytes());
        entry = entry.trim();
        System.out.println(entry);
        CardChannel channel = card.getBasicChannel();
        //0x01
        dat=pin.getBytes();
      System.out.println("The HexString of the ByteArray OF' ' IS:" + byteArrayToHexString(" ".getBytes()));//ACOSTEST=41 43 4F 53 54 45 53 54
      System.out.println("The HexString of the ByteArray of  ACOSTEST is: " + byteArrayToHexString("ACOSTEST".getBytes()));
      System.out.println("The HexString of the ByteArray of  ACOSTEST is: " + "ACOSTEST".getBytes());
      System.out.println("The HexString TO ByteArray of THE  41 43 4F 53 03 01 16 20 IS  " + hexStringToByteArray("ACOSTEST"));
        data=globalSECDATA[0];
        //data+=dat[0];
         System.out.println("The length of the pin is: " + globalSECDATA.length);
         
         System.out.println("The Initial data is: " + globalSECDATA[0]);
        for(int i=0;i<globalSECDATA.length;i++)
        {
            data+=globalSECDATA[i];
            System.out.println("The  data at "+i+" is: " + globalSECDATA[i]);
        }
        System.out.println("The Final data is: " + data);
        System.out.println("The Length of the Data  is: " + pin.getBytes().length);
        //DATA=byteArrayToHexString("ACOSTEST".getB);
        //DATA=
       
        byte [ ] SelectFilecomAPDUBytes = new byte []{CLASS,INS, 0x00, 0x00, 02,DATA, 02};
        //byte [ ] comAPDUBytes = new byte [ ]{CLASS, INS, 0x00, 0x00, 0x01,data,0x00};
        String va=byteArrayToHexString("ACOSTEST".getBytes());
        //va="ACOSTEST".
        System.out.println("THE  BYTE to STING IS : " +va);
       // DATA=Byte.valueOf(va);
       
        
       System.out.println("THE TRANSMITTED BYTE IS : " +SelectFilecomAPDUBytes[5]);
       /*
        try {
            
           r= channel.transmit(new CommandAPDU(SelectFilecomAPDUBytes));
         
          //r = channel.transmit(new CommandAPDU(new byte[]{CLASS,(byte)0xA4,(byte)0x00,(byte)0x00,(byte)0x02,0,(byte)0x02,0}));
        } catch (CardException ex) {
            Logger.getLogger(JSmartCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(Integer.toHexString(r.getSW()).equals("9000") ||Integer.toHexString(r.getSW()).equals("9102")){
           String data_to_write=byteArrayToHexString(infoTOwrite.getBytes());
           
           System.out.println("The Hex String to write is: "+data_to_write);
         WRITEcommand=WRITEcommand.concat(data_to_write);
           System.out.println("The Hex command to write is: "+WRITEcommand);
            byte apdu []= hexStringToByteArray(WRITEcommand);
            System.out.println("The FINAL COMMAND TO WRITE ISSSS: "+htos(apdu));
            System.out.println("The response is: "+Integer.toHexString(r.getSW())+" AND is OKAY");
             byte [ ] ReadcomAPDUBytes2 = new byte []{CLASS, INS2, 0x01, 0x00, 013};
             ResponseAPDU r2=null;
        try {//new CommandAPDU(0x00, 0xD0, 0x00, 0x00, data, 0x00);
           byte[] val= infoTOwrite.getBytes();
           //new CommandAPDU(0x80,0xD2,0x01,0x00,val)
           r2= channel.transmit(new CommandAPDU(0x80,0xB2,0x01,0x00,13));
          //channel.transmit((Buffer)apdu, apdu);
           String entry1 = new String(r2.getBytes());
           entry1 = entry.trim();
         System.out.println("response2 converted is:"+entry1);
         System.out.println("The response2 to string is: " + r2.toString());
          System.out.println("The response2 to Sting() DATA ISSS: " + r2.getData().toString());
          byte dat[]=r2.getData();
        System.out.println("The response2 DATA ISSS: " + r2.getData());
        System.out.println("The RESPONSE2 FOR GETDATA() IS "+new Concatenation().ConvertByteToString(r2.getData()));
        System.out.println("The RESPONSE2 FOR GETBYTE() IS "+new Concatenation().ConvertByteToString(r2.getBytes()));
        //Integer.toHexString(javaCard.getStatusWords()).toUpperCase()
        System.out.println("The SW1 is: " + Integer.toHexString(r2.getSW1()));
        System.out.println("The SW2 is: " + Integer.toHexString(r2.getSW2()));
        System.out.println("The tHE WhoLE SW is: " + Integer.toHexString(r2.getSW()));
          //r = channel.transmit(new CommandAPDU(new byte[]{CLASS,(byte)0xA4,(byte)0x00,(byte)0x00,(byte)0x02,0,(byte)0x02,0}));
         if(Integer.toHexString(r2.getSW()).equals("9000") ||Integer.toHexString(r2.getSW()).equals("9102"))
         {
             System.out.println("OPERATION ON THE FILE WAS SUCCESSFUL" );
         }
         else
         {
             System.out.println("OPERATION ON THE FILE WAS NOT SUCCESSFUL" );
         }
        } catch (CardException ex) {
            Logger.getLogger(JSmartCard.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        }
        else
        {
             System.out.println("The response1 is: "+Integer.toHexString(r.getSW())+" AND is noT OKAY");
             
        }
        byte [] val=r.getBytes();
       
        System.out.println("The response is: ");
        for(int i=0;i<val.length;i++)
        {
            System.out.print((char)val[i]);
            System.out.println();
        }
        System.out.println("The response1 is: " + r.toString());
        System.out.println("The response1 DATA ISSS: " + r.getData());
        System.out.println("The RESPONSE1 DATA ISSS "+new Concatenation().ConvertByteToString(r.getData()));
        s = new String(r.getBytes());
		    System.out.println("Text Decryted : " + s);
        String entry1 = new String(r.getBytes());
        entry1 = entry.trim();
        System.out.println("response1 converted is:"+entry1);
        //Integer.toHexString(javaCard.getStatusWords()).toUpperCase()
        System.out.println("The SW1 is: " + Integer.toHexString(r.getSW1()));
        System.out.println("The SW2 is: " + Integer.toHexString(r.getSW2()));
        System.out.println("The tHE WhoLE SW is: " + Integer.toHexString(r.getSW()));
        
        // disconnect
        
        card.disconnect(false);
        */
       JSmartCard jsc=new JSmartCard();
       byte id=(byte)0x02;
       //jsc.WriteToSmartCard(DATA, id,"FUPRE09/2012");
    String info=jsc.ReadFromSmartCard(DATA, id);
    JOptionPane.showMessageDialog(null,"The red Info is:\n"+info);
       
   }
   
   
}
