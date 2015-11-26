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
public class ScalePrintableFormat {
    
     private static java.util.List<String> types = Arrays.asList( ImageIO.getWriterFileSuffixes() );
     private static   BufferedImage printableimage;
     // private static  File rootpath = new File("");
      //  private static File finalimg2= new File(rootpath.getAbsolutePath()+"\\src\\adcs_project1\\printtest.jpg");
     
  
     
     
     
     
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
                          //  File rootpath = new File("");
                String idname="sn435"+Calendar.getInstance().getTimeInMillis();
           //File filepath =new File(rootpath.getAbsolutePath()+"\\print_img\\"+idname+".png");
             
             // ScalePrintableFormat.writeImage(image,idname+".jpg");
                //write a copy of the Jpanel image to folder to use later.
              // ImageIO.write(image,"png", filepath);
                 //StoreConvertedBufferedToImage(image);
              
              //  ImageIO.write(image,"jpg", new File(idname+".jpg")); 
               
      // send the buffered image to a directory
       //File finalimg = new File(rootpath.getAbsolutePath()+"\\print_img\\"+idname+".png");
       // File finalimg= new File("C://Finger_raw_img//"+idname+".jpg");
              printableimage = ImageIO.read(StoreConvertedBufferedToImage(image));
              // printableimage = ImageIO.read(new File(idname+".jpg"));
              
              
               saveGridImage(printableimage);
           System.out.println(printableimage.getWidth() + "x" + printableimage.getHeight());
           
           //ImageScale(printableimage);
           
               } 
            catch (IOException ex) 
            {
            ex.printStackTrace();
            } 
                        
          //ScalePrintableFormat.printCard();
        System.out.println("Buffered Image Created Succesfully");
                     
                       
     return   printableimage;
 }       
 
 
     
  
 /* public static BufferedImage ImageScale( BufferedImage b )
  {
     BufferedImage buffImg=b;
      BufferedImage st=null ;
     File rootpath = new File("");
     String idname="scaled"+Calendar.getInstance().getTimeInMillis();
      try
      {
           File pathimg = new File(rootpath.getAbsolutePath()+"\\print_img\\new"+idname+".png");
         Image scaled = buffImg.getScaledInstance(243,154, Image.SCALE_SMOOTH);
          buffImg = new BufferedImage(scaled.getWidth(null),scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
        
   Graphics2D g = buffImg.createGraphics();
//g.drawImage(img, null, null);
//g.setComposite(AlphaComposite.Src);
   g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      g.drawImage(scaled, 0, 0,243,154, null);
       g.dispose();
        ImageIO.write(buffImg, "png",pathimg);
        
        st=ImageIO.read(pathimg); 
       saveGridImage(st,pathimg);
       
          
      }catch(IOException r){
          r.printStackTrace();
      }
      
      return st;
  }
  
  */
  
  
  
  
  
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
 
 
 
/* public static void writeImage(BufferedImage image, String fileName)
		throws IOException
	{
		if (fileName == null) return;

		int offset = fileName.lastIndexOf( "." );

		if (offset == -1)
		{
			String message = "file suffix was not specified";
			throw new IOException( message );
		}

		String type = fileName.substring(offset + 1);

		if (types.contains(type))
		{
			//File rootpath = new File("");
                ImageIO.write(image, type, new File(fileName));
                       // "C://Finger_raw_img//"+fileName));
                      //new File(rootpath.getAbsolutePath()+"\\src\\adcs_project1\\print_img\\"+fileName)
		}
		else
		{
			String message = "unknown writer file suffix (" + type + ")";
			throw new IOException( message );
		}
	}*/

 
 
 
 
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
                        
                  // String idname="finalimg"+Calendar.getInstance().getTimeInMillis();
          // File filepath =new File(rootpath.getAbsolutePath()+"\\print_img\\"+idname+".png");
            
                   
			Graphics2D g2d = (Graphics2D)pg;
                             g2d.translate((int) pf.getImageableX(),(int) pf.getImageableY());
                              
                             g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                            //  Image scaled = fin2.getScaledInstance(243,154, Image.SCALE_DEFAULT);
                      // BufferedImage fin3 = new BufferedImage(scaled.getWidth(null),scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
                           //g2d = fin3.createGraphics();
                             g2d.drawImage(but, 0, 0, 243,154, null);
                               
                //Image scaled = but.getScaledInstance(243,153, Image.SCALE_DEFAULT);
                // BufferedImage  buffImg = new BufferedImage(scaled.getWidth(null),scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
                 
                 //try{
                  // saveGridImage(buffImg,filepath); 
                  // g2d = buffImg.createGraphics();
                //g2d.drawImage(scaled , 0, 0, null);
                //g2d.drawImage(scaled, 0, 0, 243,153, null);
                //ImageIO.write(buffImg, "png",filepath);
               // }catch(IOException r){
                 //   r.printStackTrace();
               // }
                 
               
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
double dotsPerMilli = 1.0 * 600 / 10 / 2.54;

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
 
 
 /*public BufferedImage getScaledInstance(BufferedImage img,
                                           int targetWidth,
                                           int targetHeight,
                                           Object hint,
                                           boolean higherQuality)
    {
        int type = (img.getTransparency() == Transparency.OPAQUE) ?
            BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage)img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }
        
        do {
            //if (higherQuality &amp;&amp; w &gt; targetWidth) {
                w /= 2;
                if (w== targetWidth) {
                    w = targetWidth;
                }
           // }

           // if (higherQuality &amp;&amp; h &gt; targetHeight) {
                h /= 2;
                //if (h &lt; targetHeight) {
                    h = targetHeight;
               // }
            //}

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }
 */
 
    
}
