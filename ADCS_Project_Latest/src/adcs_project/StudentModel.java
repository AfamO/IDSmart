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

public class StudentModel extends AbstractTableModel 
{
    private Vector totalrows;
    private String dept;
    private String sec;
    private Reconsoft mr;
    
    public StudentModel () {
        //this.dept = dept1; 
        totalrows = new Vector ();
    }
    
     String [ ] heading = {"S/N","Surname","Othernames","Matric Number","Faculty","Department","level","session","phoneno"," Registration Status"};
         
     //String [ ] heading = { "firstname","middlename","othernames","Matric Number","Faculty","Department","gender","level","session","address","phoneno","b_group","state","lga","bdate","Registration Status"};
     
     
	public String getColumnName (int i) {
                     return heading [i];
        }

	public int getColumnCount ( ) {
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
              pro.queryStudentRegistrationList();
                
               totalrows = pro.getRows();
               
              
        }
    
}
