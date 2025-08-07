package appiumtests.pages;

import io.qameta.allure.Step;
import org.tft.Assertions;

import static org.junit.Assert.fail;

public class Validations {
    Assertions asserts = new Assertions();

    @Step("Validate gold is reducted")
    public Validations validateGoldIsReducted(){
        String goldAfterBuy = asserts.getText("goldAfterBuy").trim();
        String goldBeforeBuy = asserts.getText("goldBeforeBuy").trim();
        boolean textResult = (Integer.parseInt(goldAfterBuy.trim()) < Integer.parseInt(goldBeforeBuy.trim())) ? true : false;
        boolean result = asserts.compareImages("goldBeforeBuy", "goldAfterBuy");
        if (textResult && !result){
            System.out.println("Gold was reduced");
        }else fail("The gold was not reduced");
        return this;
    }

    @Step("Validate Purchased Champ")
    public Validations validatePurchasedChamp(){
        boolean result = asserts.compareImages("goldBeforeBuy", "goldAfterBuy");
        if (result){
            System.out.println("Champ was Purchased");
        }else fail("The champ was not Purchased");
        return this;
    }
}
