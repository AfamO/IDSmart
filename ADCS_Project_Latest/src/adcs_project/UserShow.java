/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Charles
 */


public class UserShow  extends JInternalFrame{
   private boolean firsttime = true;
   private Container c;
   private JScrollPane jspane;
   private userModel qtbl;
   private JTable jtbl;
  // private String dept;
   //private String sec;
   
    /** Creates a new instance of NameResult */
    public UserShow() {
        //this.dept= dept1; 
         initComponents2();
    }
    
   
   
   private void initComponents2() {

		
      //  Set up GUI environment
	if ( firsttime ) {
 	  c = getContentPane();
          c.setLayout( new BorderLayout() );
         c.setBackground (Color.white);
        // c.setFont("Verdana",Font.BOLD,14);
         //c.setForeground(Color.blue);
	   qtbl = new userModel();
        qtbl.setVector();
 	 jtbl = new JTable( qtbl );
         jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         jtbl.setForeground(Color.BLUE);
         
	TableColumn t1col = jtbl.getColumnModel().getColumn(0);
        TableColumn t2col = jtbl.getColumnModel().getColumn(1);
        t1col.setPreferredWidth(50);
        t2col.setPreferredWidth(150);
       
       
	jspane = new JScrollPane( jtbl );
	c.add( jspane, BorderLayout.CENTER );
         	firsttime = false;
	setBounds (20,0,1000,600);
	//setResizable(true);
	setClosable(true);
	//setMaximizable(true);
	//setIconifiable(true);
	setTitle("FUPRE SMART ID CAPTURING SUMMARY");
	
	}else{
	   qtbl.setVector();
           qtbl.fire();
	   TableColumn tcol = jtbl.getColumnModel().getColumn(0);
	   tcol.setPreferredWidth(125);
      	}
	setVisible(true);

    }
   
   
  
}