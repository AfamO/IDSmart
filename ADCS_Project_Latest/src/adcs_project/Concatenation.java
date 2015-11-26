package adcs_project;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import javacard.*;

/**
 *
 * @author Admin
 */
public class Concatenation {
    private static byte[] globalSECDATA = { (byte)0x41, (byte)0x43, (byte)0x4f, (byte)0x53,(byte)0x54, (byte)0x45,(byte)0x53, (byte)0x54};
   private static byte[] globalSECDATA2 = { (byte)0x20, (byte)0x07,(byte)0x00,(byte)0x08,(byte)0x41,(byte)0x43,(byte)0x4F,(byte)0x53,(byte)0x54,(byte)0x45,(byte)0x53,(byte)0x54};
     private static byte[] globalSECDATA3  = { (byte)0x31, (byte)0x32, (byte)0x33, (byte)0x34};
      private static byte[] globalSECDATA4  = {(byte) 0xF0,(byte)0xFF,(byte) 0x41, (byte)0x09,(byte) 0x43, (byte) 0x4f, (byte) 0x53, (byte) 0x03, (byte) 0x21, (byte) 0x36, (byte) 0x25};
    //41 43 4F 53 54 45 53 54
     
    public static byte[] concatenate(String[] strs) {
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<strs.length; i++) {
            sb.append(strs[i]);
            if (i != strs.length-1) {
                sb.append("*.*"); //concatenate by this splitter
            }
        }
        return sb.toString().getBytes();
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
    String bytArrayToHex(byte[] a) {
   StringBuilder sb = new StringBuilder();
   for(byte b: a)
      sb.append(String.format("%02x", b&0xff));
   return sb.toString();
}

    public static void main(String[] args) {
        String[] input = {"This is","a serialization problem;","string concatenation will do as well","in some cases.","ACOSTEST IS HERE."};
        byte[] byteArray = concatenate(input);
        String output = split("ACOSTEST 123".getBytes());
        System.out.println("tHE RESULT BEFORE IS:"+"ACOSTEST 123".getBytes());
        System.out.println("tHE RESULT IS:"+output);
       // byte
        
        String val="";
        byte []b="12345ACOSTEST".getBytes();
        for(int j=0;j<globalSECDATA4.length;j++)
        {
           char c=(char)globalSECDATA4[j];
           val+=c;
           System.out.println("When the byte at "+j+" is ="+globalSECDATA4[j]+".The converted char is="+c);
        }
        System.out.println("CONVERTED string IS " + val);
        
       // for (String str: output) {
            //System.out.println(str);
       // }
    }
}
