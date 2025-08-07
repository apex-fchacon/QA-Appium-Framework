package org.tft.conections;

import com.aspose.ocr.*;
import org.tft.utils.Utils;


public class Actions{

    public static void main(String[] args) {
        // ExStart:1
        // The path to the documents directory.
        String dataDir = Utils.getSharedDataDir(Actions.class);

        // The image path
        String imagePath1 = dataDir + "img.png";
        String imagePath2 = dataDir + "img1.png";

        //Create api instance
        AsposeOCR api = new AsposeOCR();

        String textToFind = "Kha";

        // Detect if image has text
        boolean result = api.ImageHasText(imagePath1, textToFind);
        System.out.println("Image Has Text: "+ textToFind + " : " + result);

        // Compare two images by texts
        boolean isEqual = api.CompareImageTexts(imagePath1, imagePath2);
        System.out.println("Images are equal: " + isEqual);

        // Compare two images by texts
        float diff = api.ImageTextDiff(imagePath1, imagePath2);
        System.out.println("Difference: " + diff);
        // ExEnd:1

    }

    public void imageHasText(){

    }

}
