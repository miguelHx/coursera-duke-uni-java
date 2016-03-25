
/**
 * 
 * 
 * @author Miguel Hernandez 
 * @version nill
 */

import edu.duke.*;
import java.io.*;



public class batchgrayscale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource grayImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        for (Pixel pixel: grayImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return grayImage;
    }
    
    public void SelectConvertandSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource imagechoice = new ImageResource(f); //makes a new image from the selected files "f"
            ImageResource grayImage = makeGray(imagechoice);
            String fname = imagechoice.getFileName();
            String newName = "gray-" + fname;
            grayImage.setFileName(newName);
            grayImage.draw();
            grayImage.save();
        }
    }
}
