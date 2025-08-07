package appiumtests.tests;

import jdk.jfr.Description;
import org.junit.Ignore;
import org.junit.Test;

public class Tests extends Base {
    @Test
    @Description("In this WIP_Features login ")
    public void TutorialWithLogin(){
//        loginPage.loginWith("Riot");
//        loginPage.loginAs("fersh474").withPassword("Avengers3..");
//        loginPage.logIn();
        menuPage.play().wihGameMode("tutorial");
        inGamePage.champ1board()
                .buyChamp();
        validation.validateGoldIsReducted();
        inGamePage.champ2board().surrender();
    }

    @Test
    @Description("In this WIP_Features we move a champion into the board")
    public void Tutorial(){
        menuPage.play()
                .wihGameMode("tutorial");
        inGamePage.champ1board()
                .champ2board();
        validation.validatePurchasedChamp();
    }

    @Ignore
    @Test
    @Description("In this WIP_Features login ")
    public void NormalWithLogin(){
        loginPage.loginWith("Riot");
        loginPage.loginAs("fersh474").withPassword("Avengers3..");
        loginPage.logIn();
        menuPage.play().wihGameMode("Normal");
        inGamePage.champ1board()
                .buyChamp();
        validation.validateGoldIsReducted();
        inGamePage.champ2board();
    }

}