package appiumtests.tests;

import jdk.jfr.Description;
import org.junit.Test;

public class Tests extends Base {
    @Test
    @Description("In this test login ")
    public void Tutorial(){
        loginPage.loginWith("Riot");
        loginPage.loginAs("fersh474").withPassword("Avengers3..");
        loginPage.logIn();
        menuPage.play()
                .wihGameMode("tutorial");
        inGamePage.champ1board()
                .buyChamp()
                .champ2board();
    }

//    @Test
//    @Description("In this test we move a champion into the board")
//    public void Tutorial(){
//        menuPage.play()
//                .wihGameMode("tutorial");
//        inGamePage.champ1board()
//                .buyChamp()
//                .champ2board();
//    }

//    @Test
//    @Description("In this test we start an online game")
//    public void staringOnlineGame(){
//        menuPage.play()
//                .wihGameMode("normal")
//                .startGame()
//                .acceptGame();
//    }

}