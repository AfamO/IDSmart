/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Calendar;
import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.util.Iterator;
public class ScaleBackPrintable{
    
      private static   BufferedImage printableimage;
    
  
     
     
     
     
  public static BufferedImage JPanelImageConverter( final JComponent com )
 {
    Dimension d = com.getSize();
                        if (! com.isDisplayable())
		        {
			

			if (d.width == 0 || d.height == 0)
			{
				d = com.getPreferredSize();
				com.setSize( d );
			}

			layoutComponent(com);
		      }

                  Rectangle region = new Rectangle(0, 0, d.width, d.height);
		  BufferedImage image = new BufferedImage(region.width, region.height, BufferedImage.TYPE_INT_RGB);
                
                
		 Graphics2D g2d = image.createGraphics();

		//  Paint a background for non-opaque components,
		//  otherwise the background will be black

		if (! com.isOpaque())
		{
			g2d.setColor( com.getBackground() );
			g2d.fillRect(region.x, region.y, region.width, region.height);
		}

		g2d.translate(-region.x, -region.y);
		com.paint( g2d );
		g2d.dispose();
                        
	
                        try {
                         
              printableimage = ImageIO.read(StoreConvertedBufferedToImage(image));
              
              
               saveGridImage(printableimage);
           System.out.println(printableimage.getWidth() + "x" + printableimage.getHeight());
           
           
               } 
            catch (IOException ex) 
            {
            ex.printStackTrace();
            } 
                        
          //ScalePrintableFormat.printCard();
        System.out.println("Buffered Image Created Succesfully");
                     
                       
     return   printableimage;
 }       
 
 
    
  
  
 static void layoutComponent(Component component)
	{
		synchronized (component.getTreeLock())
		{
			component.doLayout();

    	    if (component instanceof Container)
        	{
            	for (Component child : ((Container)component).getComponents())
	            {
    	            layoutComponent(child);
        	    }
	        }
    	}
                
   }
 

 
 
 
 
  public  void printCard( final JComponent pan) {
   // final  File rootpath = new File("");
       final BufferedImage but=ScalePrintableFormat.JPanelImageConverter(pan);
       //final BufferedImage fin2=ImageScale(but);

	PrinterJob printjob = PrinterJob.getPrinterJob();
	printjob.setJobName(" FUPRE SMART ID CARD ");
        
        

	Printable printable = new Printable() {

		public int print(Graphics pg, PageFormat pf, int index) {
                    
                    
                    
			if (index > 0) {
                            System.out.println("No Such Page exist"+index);
				return Printable.NO_SUCH_PAGE;
                                 
			}
                        
                  
                   
			Graphics2D g2d = (Graphics2D)pg;
                             g2d.translate((int) pf.getImageableX(),(int) pf.getImageableY());
                              
                             g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                             g2d.drawImage(but, 0, 0, 243,154, null);
                             g2d.dispose();
                
       System.out.println("Information Found wait while it Print"+index);
			return Printable.PAGE_EXISTS;
		}
	};

	Paper paper = new Paper();
	
	paper.setSize(243, 154);
        
       // paper.setSize(8.3 * 320, 11.7 * 320);
        
       //paper.setImageableArea(0, 0, 153, 243);

	PageFormat format = new PageFormat();
	format.setPaper(paper);
	format.setOrientation(PageFormat.LANDSCAPE);

	printjob.setPrintable(printable, format);
	if (printjob.printDialog() == false)
		return;

	try {
		printjob.print();
	} catch (PrinterException ex) {
		System.out.println("NO PAGE FOUND." + ex);

	}
}
 

 private static void saveGridImage(BufferedImage gridimage) throws IOException {
     // output.delete();

final String formatName = "png";

for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(formatName); iw.hasNext();) {
   ImageWriter writer = iw.next();
   ImageWriteParam writeParam = writer.getDefaultWriteParam();
   ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
   IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
   if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
      continue;
   }

   setDPI(metadata);

  // final ImageOutputStream stream = ImageIO.createImageOutputStream(output);
   try {
     // writer.setOutput(stream);
     // writer.write(metadata, new IIOImage(gridimage, null, metadata), writeParam);
   } finally {
     // stream.close();
   }
   break;
}
 }

 private static void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

// for PMG, it's dots per millimeter
double dotsPerMilli = 1.0 * 250 / 10 / 2.54;

IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
horiz.setAttribute("value", Double.toString(dotsPerMilli));

IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
vert.setAttribute("value", Double.toString(dotsPerMilli));

IIOMetadataNode dim = new IIOMetadataNode("Dimension");
dim.appendChild(horiz);
dim.appendChild(vert);

IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
root.appendChild(dim);

metadata.mergeTree("javax_imageio_1.0", root);
 } 
  
  
  

 
 public static InputStream StoreConvertedBufferedToImage(BufferedImage mm2)
 {
   
      try{
       ByteArrayOutputStream pixout = new ByteArrayOutputStream();
      ImageIO.write(mm2,"png", pixout);
   // byte [] pixbyte=pixout.toByteArray();
  InputStream  printstream =new ByteArrayInputStream(pixout.toByteArray()); 
  return printstream;
       }catch(IOException n){
           n.printStackTrace();
           return null;
       }
 }
 
 
 
    
}
