package net.sourceforge.tess4j;

import java.awt.Graphics2D; 
import net.sourceforge.tess4j.*; 
import java.awt.Image; 
import java.awt.image.*; 
import java.io.*; 
  
import javax.imageio.ImageIO; 
  
@SuppressWarnings("unused")
public class ScanedImage { 
  
    public static void
    processImg(BufferedImage ipimage, 
               float scaleFactor, 
               float offset) 
        throws IOException, TesseractException 
    { 
        // Making an empty image buffer 
        // to store image later 
        // ipimage is an image buffer 
        // of input image 
    	
    	 int w = ipimage.getWidth(null);
    	    int h = ipimage.getHeight(null);
    	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    	    
    	    
        BufferedImage opimage 
            = new BufferedImage(w, 
                                h, 
                                BufferedImage.TYPE_INT_ARGB); 
  
        // creating a 2D platform 
        // on the buffer image 
        // for drawing the new image 
        Graphics2D graphic 
            = opimage.createGraphics(); 
  
        // drawing new image starting from 0 0 
        // of size 1050 x 1024 (zoomed images) 
        // null is the ImageObserver class object 
        graphic.drawImage(ipimage, 0, 0, 
                          1050, 1024, null); 
        graphic.dispose(); 
  
        // rescale OP object 
        // for gray scaling images 
        RescaleOp rescale 
            = new RescaleOp(scaleFactor, offset, null); 
  
        int target = 0;
		float fade = 0;
		// performing scaling 
        // and writing on a .png file 
         offset = target * (1.0f - fade);
        float[] scales = { fade, fade, fade, 1.0f };
        float[] offsets = { offset, offset, offset, 0.0f };
        RescaleOp rop = new RescaleOp(scales, offsets, null);

        BufferedImage fopimage = rop.filter(opimage, null); 
        ImageIO 
            .write(fopimage, 
                   "jpg", 
                   new File("/Users/apple/Downloads/Tess4J/test/resources/test-data/output.png")); 
  
        // Instantiating the Tesseract class 
        // which is used to perform OCR 
        Tesseract it = new Tesseract(); 
  
        it.setDatapath("Users/apple/eclipse-workspace/Tess4J"); 
  
        // doing OCR on the image 
        // and storing result in string str 
        String str = it.doOCR(fopimage); 
        System.out.println(str); 
    } 
  
    public static void main(String args[]) throws Exception 
    { 
        File f 
            = new File( 
            		("/Users/apple/Downloads/Tess4J/test/resources/test-data/eurotext_unlv.png"));
        BufferedImage ipimage = ImageIO.read(f); 
  
        // getting RGB content of the whole image file 
        double d 
            = ipimage 
                  .getRGB(ipimage.getTileWidth() / 2, 
                          ipimage.getTileHeight() / 2); 
  
        // comparing the values 
        // and setting new scaling values 
        // that are later on used by RescaleOP 
        if (d >= -1.4211511E7 && d < -7254228) { 
            processImg(ipimage, 3f, -10f); 
        } 
        else if (d >= -7254228 && d < -2171170) {  
            processImg(ipimage, 1.455f, -47f); 
        } 
        else if (d >= -2171170 && d < -1907998) { 
            processImg(ipimage, 1.35f, -10f); 
        } 
        else if (d >= -1907998 && d < -257) { 
            processImg(ipimage, 1.19f, 0.5f); 
        } 
        else if (d >= -257 && d < -1) { 
            processImg(ipimage, 1f, 0.5f); 
        } 
        else if (d >= -1 && d < 2) { 
            processImg(ipimage, 1f, 0.35f); 
        } 
    } 
} 