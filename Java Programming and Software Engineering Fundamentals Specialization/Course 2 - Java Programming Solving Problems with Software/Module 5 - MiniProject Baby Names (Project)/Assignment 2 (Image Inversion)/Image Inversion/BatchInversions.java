
/**
 * Write a description of ImageInversion here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Assignment 2: Image Inversion)
 */
import edu.duke.*;
import java.io.*;

public class BatchInversions{
    
    public ImageResource makeInversion(ImageResource original){
    
        ImageResource negativeImage = new ImageResource(original.getWidth(),original.getHeight());
        int maxPixel = 255;
        for(Pixel p: original.pixels()){
        
            int redPixel = maxPixel - p.getRed();
            int greenPixel = maxPixel - p.getGreen();
            int bluePixel = maxPixel - p.getBlue();
            
            Pixel negativePixel = new Pixel(p);
            
            negativePixel.setRed(redPixel);
            negativePixel.setGreen(greenPixel);
            negativePixel.setBlue(bluePixel);
             
            negativeImage.setPixel(p.getX(), p.getY(), negativePixel);
        }
        //negativeImage.draw();
        return negativeImage;
    }

    public void testMakeNegativeImage(){
        
        ImageResource negativeImage = new ImageResource("butterfly.jfif");   
        makeInversion(negativeImage).draw();
    }
    
    public void selectAndConvert(){
    
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
        
            ImageResource originalImage = new ImageResource(f);
            ImageResource negativeImage = makeInversion(originalImage);
            negativeImage.draw();
            
            negativeImage.setFileName("inverted-"+originalImage.getFileName());
            negativeImage.save();
        }
    
    }
    
    public void testSelectAndConvert(){
    
        selectAndConvert();
    }
}
