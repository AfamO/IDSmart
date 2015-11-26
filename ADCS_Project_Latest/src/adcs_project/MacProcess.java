/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author fupre1
 */
import java.io.*;

public class MacProcess {
public MacProcess() {
}
public static void main(String[] args) {
MacProcess macProcess1 = new MacProcess();
macProcess1.pro();
}
public void pro(){
String s = null;
StringBuffer sb = new StringBuffer() ;
try {
Process p = Runtime.getRuntime().exec("ipconfig /all");
BufferedReader stdInput = new BufferedReader(new
InputStreamReader(p.getInputStream()));
while ((s = stdInput.readLine()) != null) {
sb.append(s);
}
int i = sb.indexOf("Physical Address. . . . . . . . . :");
i+=36;
String ss = sb.substring(i,i+17);
System.out.println("Mac Address : " + ss);
}
catch (IOException e) {
e.printStackTrace();
}
}
}