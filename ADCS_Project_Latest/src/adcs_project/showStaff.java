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
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class showStaff  extends JInternalFrame{
   private boolean firsttime = true;
   private Container c;
   private JScrollPane jspane;
   private StaffModel qtbl;
   private JTable jtbl;
  // private String dept;
   //private String sec;
   
    /** Creates a new instance of NameResult */
    public showStaff() {
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
	   qtbl = new StaffModel();
        qtbl.setVector();
 	 jtbl = new JTable( qtbl );
         jtbl.setFont( new Font("Verdana",Font.BOLD,14));
         jtbl.setForeground(Color.BLUE);
	TableColumn t1col = jtbl.getColumnModel().getColumn(0);
        TableColumn t2col = jtbl.getColumnModel().getColumn(1);
        TableColumn t3col = jtbl.getColumnModel().getColumn(2);
        TableColumn t4col = jtbl.getColumnModel().getColumn(3);
        TableColumn t5col = jtbl.getColumnModel().getColumn(6);
	t1col.setPreferredWidth(15);
        t2col.setPreferredWidth(100);
        t3col.setPreferredWidth(150);
        t4col.setPreferredWidth(40);
        t5col.setPreferredWidth(20);
       
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