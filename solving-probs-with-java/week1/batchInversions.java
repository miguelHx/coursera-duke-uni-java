
/*
 * 
 * @author Miguel Hernandez
 * @version nill
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {
    
    public ImageResource makeInversion(ImageResource imageChoice) { //I started with the image I chose (imageChoice)
        //I made a blank image the same size(outImage)
        ImageResource outImage = new ImageResource(imageChoice.getWidth(), imageChoice.getHeight());
        //for each pixel in outimage
        for (Pixel pixel: outImage.pixels()) {
            //look at corresponding pixel in imageChoice(inPixel)
            Pixel inPixel = imageChoice.getPixel(pixel.getX(), pixel.getY());
            //compute 255 minus each color separately to get inverse
            //call each variable inverseR, inverseG, inverseB
            int inverseR = 255 - inPixel.getRed();
            int inverseG = 255 - inPixel.getGreen();
            int inverseB = 255 - inPixel.getBlue();
            //set pixel's red to inverseR
            pixel.setRed(inverseR);
            //set pixel's green to inverseG
            pixel.setGreen(inverseG);
            //set pixel's blue to inverseB
            pixel.setBlue(inverseB);
        }
        return outImage;
    }
    
    public void selectAndConvertAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource imageChoice = new ImageResource(f);
            ImageResource invertedImage = makeInversion(imageChoice);
            String fname = imageChoice.getFileName();
            String newName = "inverted-" + fname;
            invertedImage.setFileName(newName);
            invertedImage.draw();
            invertedImage.save();
        }
    }
}
