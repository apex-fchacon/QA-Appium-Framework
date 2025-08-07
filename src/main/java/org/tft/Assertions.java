package org.tft;

import com.aspose.ocr.*;
//import org.junit.Assert;
import org.tft.utils.Utils;

import java.util.ArrayList;

public class Assertions {
    //Create api instance
    AsposeOCR api = new AsposeOCR();
    public Assertions imageHasText (String textToFind, String imageName){
        // ExStart:1
        // The path to the documents directory.
        String dataDir = Utils.getSharedDataDir(Assertions.class);

        // The image path
        String imagePath1 = dataDir + imageName ;

        // Detect if image has text
        boolean result = api.ImageHasText(imagePath1, textToFind);
        System.out.println("Image Has Text: "+ textToFind + " : " + result);
        return this;
    }

    public boolean compareImages(String img1, String img2){

        // ExStart:1
        // The path to the documents directory.
        String dataDir = Utils.getSharedDataDir(Assertions.class);

        // The image path
        String imagePath1 = dataDir + img1 +  ".png";
        String imagePath2 = dataDir + img2 + ".png";

        // Compare image against image
        boolean isEqual = api.CompareImageTexts(imagePath1, imagePath2);
//        System.out.println("Images are equal: " + isEqual);

        return isEqual;
    }
    public String getText(String imgName){
        // Initialize Aspose.OCR recognition API
        RecognitionSettings recognitionSettings = new RecognitionSettings();
        // Add image to the recognition batch
        OcrInput source = new OcrInput(InputType.SingleImage);
        source.add("src/main/resources/screenshots/"+imgName+".png");
        // Specify recognition language
//        RecognitionSettings recognitionSettings = new RecognitionSettings();
        recognitionSettings.setLanguage(Language.Eng);
//        recognitionSettings.setUpscaleSmallFont(true);
        // Extract text from image
        ArrayList<RecognitionResult> results = null;
        try {
            results = api.Recognize(source, recognitionSettings);
        } catch (AsposeOCRException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("Recognition result:\n" + results.get(0).recognitionText);
        return results.get(0).recognitionText;
    }
}
