/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Tele
 */

import javax.smartcardio.*;
import java.util.*;
import javax.swing.JOptionPane;
public class ExSmartCardIO {
private static Card card1=null;
private  CardTerminal terminal; private static String s;
    
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


public static void main(String[] args) {

try
{
// Show the list of available terminals
// On Windows see HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Cryptography\Calais\Readers
TerminalFactory factory = TerminalFactory.getDefault();
List<CardTerminal> terminals = factory.terminals().list();
System.out.println("Terminals: " + terminals);

// Get the first terminal in the list
CardTerminal terminal = terminals.get(0);

// Establish a connection with the card using
// "T=0", "T=1", "T=CL" or "*"
Card card = terminal.connect("*");
System.out.println("Card: " + card);

// Get ATR
//byte[] baATR = card.getATR().getBytes();
//System.out.println("ATR: " + ExSmartCardIO.ConvertByteToString(baATR));

// Select Card Manager
// - Establish channel to exchange APDU
// - Send SELECT Command APDU
// - Show Response APDU
CardChannel channel = card.getBasicChannel();

//SELECT Command
// See GlobalPlatform Card Specification (e.g. 2.2, section 11.9)
// CLA: 00
// INS: A4
// P1: 04 i.e. b3 is set to 1, means select by name
// P2: 00 i.e. first or only occurence
// Lc: 08 i.e. length of AID see below
// Data: A0 00 00 00 03 00 00 00
// AID of the card manager,
// in the future should change to A0 00 00 01 51 00 00

byte[] baCommandAPDU = {(byte) 0x41, (byte) 0xA43, (byte) 0x4f,(byte)0x20};
//System.out.println("APDU >>>: " + ExSmartCardIO.toString(baCommandAPDU));

System.out.println("APDU >>>: " + ExSmartCardIO.ConvertByteToString(baCommandAPDU));

ResponseAPDU r = channel.transmit(new CommandAPDU(baCommandAPDU));
//System.out.println("APDU <<<: " + ExSmartCardIO.toString(r.getBytes()));

System.out.println("APDU <<<: " + ExSmartCardIO.ConvertByteToString(r.getBytes()));

 System.out.println("The HexString of the ByteArray of  ACOSTEST is: " + "ACOSTEST".getBytes());


 
}
catch(Exception ex) {
ex.printStackTrace();
}finally{
    try{
        // Disconnect
        // true: reset the card after disconnecting card.
       card1.disconnect(true);
       }
     catch(CardException ce){
    System.out.println("COULDN'T DISCONNECT CARD is:"+ce.getMessage());
            ce.printStackTrace();
         }
    
}
}


public static String ConvertByteToString(byte a[])
    {
        String val="";
       // byte []b="12345ACOSTEST".getBytes();
        for(int j=0;j<a.length;j++)
        {
           char c=(char)a[j];
           val+=c;
           System.out.println("When the byte at "+j+" is ="+a[j]+".The converted char is="+c);
        }
        System.out.println("CONVERTED string IS " + val);
        return val;
    }


/*
public static String toString(byte[] bytes)
{

final String hexChars = "0123456789ABCDEF";
StringBuffer sbTmp = new StringBuffer();
char[] cTmp = new char[2];

for (int i = 0; i <>> 4; & 0x0F)
{
cTmp[1] = hexChars.charAt(bytes[i] & 0x0F);
sbTmp.append(cTmp);
}

return sbTmp.toString();
}

*/



public void Test_Conversion(){
    String example = "This is an example";
		    byte[] bytes = example.getBytes();
 
		    System.out.println("Text : " + example);
		    System.out.println("Text [Byte Format] : " + bytes);
		    System.out.println("Text [Byte Format] : " + bytes.toString());
 
		     s= new String(bytes);
		    System.out.println("Text Decryted : " + s);
                    // Code to convert byte array to string:
byte[] by_original = {0,1,-2,3,-4,-5,6};
String str1 = new String(by_original);
System.out.println("str1 >> "+str1);

// Code to convert string to byte array:
byte[] by_new = str1.getBytes();
for(int i=0;i<by_new.length;i++) 
System.out.println("by1["+i+"] >> "+str1);
       
}
/*
public void Test_Card() throws CardException
{
    
       // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        //CardTerminals.State.
        CardTerminal terminal = terminals.get(0);
  
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
    
}
*/
} 
