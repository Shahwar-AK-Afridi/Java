
/**
 * Write a description of greyImages here.
 * 
 * @author (Shahwar Afridi) 
 * @version (Assignment 1: Batch Grayscale)
 */
import edu.duke.*;
import java.io.*;

public class GreyImages {
    
    
    public ImageResource makeGrey(ImageResource imageColor){
    
        ImageResource imageGrey = new ImageResource(imageColor.getWidth() , imageColor.getHeight());  //it creates an empty image whose pixels are initially black.
        
        for(Pixel p: imageColor.pixels()){
            
            Pixel greyPixel = imageGrey.getPixel(p.getX(), p.getY());
            int averagePixel = (p.getRed() + p.getBlue() + p.getGreen())/3;
                        
            greyPixel.setRed(averagePixel);
            greyPixel.setGreen(averagePixel);
            greyPixel.setBlue(averagePixel);
    

        }
        //imageGrey.draw();             Prints "Grey" Images
        return imageGrey;
    }
    
    
    public void testSaveGrey(){
    
        ImageResource originalImage = new ImageResource("butterfly.jfif");
        makeGrey(originalImage);
    
    
    }
    
    public void selectMultipleFiles(){
    
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()){
        
            ImageResource image = new ImageResource(f);
            ImageResource greyImage = makeGrey(image);
            
            String currentFileName = image.getFileName();
            greyImage.setFileName("gray-"+currentFileName);
            greyImage.save();
            
        }
    
    }
    
    public void testSelectMultipleFiles(){
    
        selectMultipleFiles();
    
    }
}
