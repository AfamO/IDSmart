/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Tele
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class setup_change extends JFrame {

    private JPanel Main_pan;
    private String path="";
    private File log_file=null;
    private File front_file=null;
    private File back_file=null;
    private File f1,f2,f3;
    private InformationChecker check = new  InformationChecker();
    
    public setup_change(String name,String city) {
        
        initComponents();
        setTitle("IDSmart Setup ");
        co_nameTF.setText(name);
        co_city.setText(city);
     setIconImage(new ImageIcon(getClass().getResource("see.jpg")).getImage());
    }

    
    
      private void Load_Logo()
    {
      JFileChooser chooser=new JFileChooser();
      chooser.addChoosableFileFilter(new ImageFileFilter());
       int returnval=chooser.showOpenDialog(null);
      if(returnval==JFileChooser.APPROVE_OPTION)
	{
	       log_file=chooser.getSelectedFile();
		path=log_file.getPath();
		ImageIcon icon=new ImageIcon(path);
		//labell.setIcon(icon);
                 f1=new File(path);
		//f16.setText(path);
                System.out.println("Logo:"+path);
		//repaint();
	} 
    }
    
    
    private void Load_Front()
    {
     JFileChooser chooser=new JFileChooser();
      chooser.addChoosableFileFilter(new ImageFileFilter());
       int returnval=chooser.showOpenDialog(null);
      if(returnval==JFileChooser.APPROVE_OPTION)
	{
	       front_file=chooser.getSelectedFile();
		path=front_file.getPath();
		ImageIcon icon=new ImageIcon(path);
		//labell.setIcon(icon);
                 f2=new File(path);
		//f16.setText(path);
                System.out.println("Front:"+path);
		//repaint();
	}    
    }
    
    
    private void Load_Back()
    {
     JFileChooser chooser=new JFileChooser();
      chooser.addChoosableFileFilter(new ImageFileFilter());
       int returnval=chooser.showOpenDialog(null);
      if(returnval==JFileChooser.APPROVE_OPTION)
	{
	       back_file=chooser.getSelectedFile();
		path=back_file.getPath();
		ImageIcon icon=new ImageIcon(path);
		//labell.setIcon(icon);
		//f16.setText(path);
                  f3=new File(path);
               // String name=f.getName();
                System.out.println("Back:"+path);
		//repaint();
	}    
    }
    
    
    public void update_change_info()
    {
      String co_name3=this.CheckCo_name(co_nameTF.getText().trim());
      String co_city2=this.CheckCo_City(co_city.getText().trim());
    //  String stat=(String)stat_com.getSelectedItem();
     // String toupper =stat.toUpperCase();
      Reconsoft dat = new Reconsoft();
      try{
          if(f1==null){
              
          JOptionPane.showMessageDialog(null, "Please Logo Information is not selected\n Click on Load Company logo, to get your logo setup \nfor further Information contact teledom Administrator");
             
          }else if(f2==null){
               JOptionPane.showMessageDialog(null, "Please Load Front ID Information is not selected\n Click on Load Front ID Framework, to get your Front ID setup \nfor further Information contact teledom Administrator");
            
          }else if(f3==null){
           JOptionPane.showMessageDialog(null, "Please Load back ID Information is not selected\n Click on Load Back ID Framework, to get your Back ID setup \nfor further Information contact teledom Administrator");   
          }else{
          dat.Update_setup_Inform(co_name3,co_city2, f1, f2, f3);     
          }
         
      }catch(Exception e){
          System.out.println(e.getMessage() +"setup page");
      }
      
    }
    
    
     private class ImageFileFilter extends javax.swing.filechooser.FileFilter{
	public boolean accept(File file)
	{
		if(file.isDirectory()) return false;
		String name=file.getName().toLowerCase();
		return(name.endsWith(".jpg")||name.endsWith(".png")||name.endsWith(".gif"));
	}
	public String getDescription()
	{
		return "Images(*.gif,*.bmp,*jpg,*.png)";
	}
	}
    
    
    
    
     private String CheckCo_name(String co_name)
    {
        
      if(co_name.equals("") || co_name == null ) 
      {
        JOptionPane.showMessageDialog(null, "Company Name should not be Empty"); 
        return null;
      }else if(check.checksetup_infor(co_name)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in Company name Field, Enter letter A-Z\ne.g Teledom"
                  + "International Ltd"); 
        return null;
      } 
       return co_name;
    }
    
     
     
    private String CheckCo_City(String co_city)
    {
        
      if(co_city.equals("") || co_city == null ) 
      {
        JOptionPane.showMessageDialog(null, "City should not be Empty"); 
        return null;
      }else if(check.checksetup_infor(co_city)){
         
        
       }else{
          JOptionPane.showMessageDialog(null, "Invalid Character in City Field, Enter letter A-Z\ne.g Lagos"
                  + ",Nigeria."); 
        return null;
      } 
       return co_city;
    }
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentp = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        co_nameTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        co_city = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        log_but = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        front_but = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        back_but = new javax.swing.JButton();
        save_but = new javax.swing.JButton();
        exBut = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(53, 109, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IDSMART CUSTOMIZATION FORM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Company Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("City:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Logo:");

        log_but.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        log_but.setText("Load Company Logo");
        log_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_butActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Front:");

        front_but.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        front_but.setText("Load Front ID Framework");
        front_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                front_butActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Back:");

        back_but.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        back_but.setText("Load Back ID Framework");
        back_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_butActionPerformed(evt);
            }
        });

        save_but.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        save_but.setText("Submit");
        save_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_butActionPerformed(evt);
            }
        });

        exBut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exBut.setText("Exit");
        exBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exButActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Note: Front ID Framework size is 1050px by 680px landscape");

        final ImageIcon icon = new ImageIcon(getClass().getResource("/adcs_project/setup.png"));
        contentp = new JPanel(){
            protected void paintComponent(Graphics g){
                g.drawImage(icon.getImage(), 0,0, null);
                super.paintComponent(g);
                //super.paintComponent(g);

            }
        };
        contentp.setLayout(null);

        contentp.setOpaque(false);

        Main_pan = new JPanel();
        Main_pan.setLayout(new BorderLayout());
        getContentPane().add(Main_pan);
        Main_pan.add(contentp, BorderLayout.CENTER );

        javax.swing.GroupLayout contentpLayout = new javax.swing.GroupLayout(contentp);
        contentp.setLayout(contentpLayout);
        contentpLayout.setHorizontalGroup(
            contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentpLayout.createSequentialGroup()
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentpLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentpLayout.createSequentialGroup()
                                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(back_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(front_but, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                    .addComponent(log_but, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(co_city)
                                    .addComponent(co_nameTF)))
                            .addGroup(contentpLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(contentpLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(contentpLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(save_but)
                .addGap(72, 72, 72)
                .addComponent(exBut, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentpLayout.setVerticalGroup(
            contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(co_nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(co_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(log_but))
                .addGap(32, 32, 32)
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(front_but))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back_but))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save_but)
                    .addComponent(exBut))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void log_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_butActionPerformed
        // TODO add your handling code here:
        Load_Logo();
    }//GEN-LAST:event_log_butActionPerformed

    private void front_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_front_butActionPerformed
        // TODO add your handling code here:
        Load_Front();
    }//GEN-LAST:event_front_butActionPerformed

    private void back_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_butActionPerformed
        // TODO add your handling code here:
        Load_Back();
    }//GEN-LAST:event_back_butActionPerformed

    private void save_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_butActionPerformed
        // TODO add your handling code here:
        update_change_info();
    }//GEN-LAST:event_save_butActionPerformed

    private void exButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exButActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exButActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_but;
    private javax.swing.JTextField co_city;
    private javax.swing.JTextField co_nameTF;
    private javax.swing.JPanel contentp;
    private javax.swing.JButton exBut;
    private javax.swing.JButton front_but;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton log_but;
    private javax.swing.JButton save_but;
    // End of variables declaration//GEN-END:variables
}