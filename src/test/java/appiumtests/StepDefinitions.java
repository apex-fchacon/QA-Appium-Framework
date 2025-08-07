package appiumtests;

import appiumtests.pages.InGame;
import appiumtests.pages.Login;
import appiumtests.pages.Menu;
import appiumtests.pages.Validations;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.tft.Assertions;
import org.tft.conections.StartConnection;
import org.tft.utils.JsonHelper;

import java.io.FileReader;

public class StepDefinitions {
    Login loginPage = new Login();
    Menu menuPage = new Menu();
    InGame inGamePage = new InGame();
    Validations validation = new Validations();
    JsonHelper jsonHelper = new JsonHelper();
    Assertions asserts = new Assertions();

    @Given("the user login {string} account")
    public void the_user_login_s_account(String loginType) {
        loginPage.loginWith(loginType);
    }
    @When("the user enters a {string}")
    public void the_user_enters_a(String arg0) {
        String path = System.getProperty("user.dir")+"/src/main/resources/Accounts.json";
        String username = jsonHelper.getStringValule(path, "ValidCredentials", "ferChacon-Account", "username");
        String password = jsonHelper.getStringValule(path, "ValidCredentials", "ferChacon-Account", "password");
        System.out.println("username " + username + "password " + password);
        loginPage.loginAs(username).withPassword(password );
    }
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.logIn();
    }
    @When("the user clicks the play button")
    public void the_user_clicks_the_play_button() {
        menuPage.play();
    }
    @When("the user select {string} as game mode")
    public void the_user_enters_s_as_game_mode(String gamemode) {
        menuPage.wihGameMode(gamemode);
    }
//    @When("the user select {string} as game mode")
//    public void the_user_enters_s_as_game_mode(String gamemode) {
//        menuPage.wihGameMode(gamemode);
//    }
    @When("the user move champion 1 to the board")
    public void the_user_move_champion_n_to_the_board() {
        inGamePage.champ1board();
    }
    @And("the user buy a new champion")
    public void theUserBuyANewChampion() {
        inGamePage.buyChamp();
    }

    @And("the validate the gold reduction")
    public void the_validate_the_gold_reduction() {
        validation.validateGoldIsReducted();
    }

    @When("the user move champion 2 to the board")
    public void the_user_move_champion_n2_to_the_board() {
        inGamePage.champ2board();
    }

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user quits the game")
    public void theUserQuitsTheGame() {
        inGamePage.surrender();
    }
}
