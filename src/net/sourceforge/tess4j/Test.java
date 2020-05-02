package net.sourceforge.tess4j;

import java.io.File; 

import net.sourceforge.tess4j.Tesseract; 
import net.sourceforge.tess4j.TesseractException; 
  
public class Test { 
    public static void main(String[] args) 
    { 
        Tesseract tesseract = new Tesseract(); 
        try { 
  
            tesseract.setDatapath("/Users/apple/Downloads/Tess4J/test/resources/test-data/eurotext.png"); 
  
            // the path of your tess data folder 
            // inside the extracted file 
            String text 
                = tesseract.doOCR(new File("image.jpg")); 
  
            // path of your image file 
            System.out.print(text); 
        } 
        catch (TesseractException e) { 
            e.printStackTrace(); 
        } 
    } 
}