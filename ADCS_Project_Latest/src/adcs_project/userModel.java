/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */

import javax.swing.table.*;
import java.util.*;

public class userModel extends AbstractTableModel 
{
    private Vector totalrows;
    private String dept;
    private String sec;
    private Reconsoft mr;
    
    public userModel () {
        //this.dept = dept1; 
        totalrows = new Vector ();
    }
    
     String [ ] heading = {"S/N","Application users","Access Level"};
         
    
     
	public String getColumnName (int i) {
                     return heading [i];
        }

	public int getColumnCount () {
		     return heading.length;
        }

	public int getRowCount ( ) {

		return totalrows.size ( );
	}

	public Object getValueAt (int row, int col) {

		return ((String[ ])totalrows.elementAt (row)) [col];
	}

	public boolean isCellEditable (int row, int col) {
		
		return false;
	}
	
	public void fire () {
            fireTableChanged(null);
	}

	public void setVector ( ) {
		Reconsoft pro = new Reconsoft();
		//get all the rows and store in totolrows
              pro.queryOperatorsRegistrationList();
                
               totalrows = pro.getRows();
               
              
        }
    
}
