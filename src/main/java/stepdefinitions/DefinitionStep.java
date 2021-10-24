package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.*;


public class DefinitionStep {
    private static final long DEFAULT_TIMEOUT = 30;
    private static final int MAX_NUMBER_OF_PARAGRAPH = 3;
    private static final int MIN_NUMBER_OF_PARAGRAPH = 2;
    private static final int BEGIN_INDEX_SUBSTRING = 0;
    private static final int END_INDEX_SUBSTRING = 11;
    WebDriver driver;
    HomePage homePage;
    PageFactoryManager pageFactoryManager;
    RussianPage russianPage;
    GenerateResultPage generateResultPage;
    RadioButtonElement radioButtonElement;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @Given("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User clicks 'Русский' link")
    public void clickOnTheLink() {
        homePage.clickOnRussianLink();
    }

    @Then("User checks that the first paragraph, contains the {string} word")
    public void checkFirstParagraphContainsTheKeyword(final String keyword) {
        russianPage = pageFactoryManager.getRussianPage();
        russianPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, russianPage.getFirstParagraph());
        assertTrue(russianPage.getFirstParagraph().getText().contains(keyword));
    }

    @When("User clicks 'Generate Lorem Ipsum' button")
    public void clickOnGenerateLoremIpsum() {
        homePage.clickOnGenerateButton();
    }

    @Then("User checks that the first paragraph starts with {string}")
    public void checkFirstParagraphStartsWithSubString(final String subString) {
        generateResultPage = pageFactoryManager.getGenerateResultPage();
        generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(generateResultPage.getFirstParagraph().getText().startsWith(subString));
    }

    @And("User clicks {string}")
    public void clickOnBytes(final String bytesWord) {
        radioButtonElement = pageFactoryManager.getRadioButtonElement();
        radioButtonElement.setValue(bytesWord);
    }

    @And("User enters {string} into the number field")
    public void enterNumber(final String amount) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.enterAmount(amount);
    }

    @Then("User checks that amount of generation elements are {int}")
    public void checkGenerationElementsWithValidData(final int amount) {
        generateResultPage = pageFactoryManager.getGenerateResultPage();
        generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        long actualResult = generateResultPage.getFirstParagraph().getText().
                chars().filter(c -> c == ' ').count();
        assertEquals(amount, actualResult + 1);

    }

    @Then("User checks that with invalid data amount of generation elements are {int}")
    public void checkGenerationElementsWithInvalidData(final int outputNumber) {
        generateResultPage = pageFactoryManager.getGenerateResultPage();
        generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        long actualResult = generateResultPage.getFirstParagraph().getText().
                chars().filter(c -> c == ' ').count();
        assertEquals(outputNumber, actualResult + 1);

    }

    @Then("User checks that amount of generation bytes are {int}")
    public void checkGenerationBytes(final int amount) {
        generateResultPage = pageFactoryManager.getGenerateResultPage();
        generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        int actualResult = generateResultPage.getFirstParagraph().getText().length();
        assertEquals(amount, actualResult);
    }

    @Then("User checks that with invalid data amount of generation bytes are {int}")
    public void checkGenerationBytesWithInvalidData(final int outputNumber) {
        generateResultPage = pageFactoryManager.getGenerateResultPage();
        generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        int actualResult = generateResultPage.getFirstParagraph().getText().length();
        assertEquals(outputNumber, actualResult);
    }

    @When("User disables 'start with Lorem Ipsum' checkbox")
    public void disableStartWithLoremIpsumCheckbox() {
        homePage.clickOnStartWithLoremCheckbox();
    }


    @Then("User checks that the first paragraph does not start with {string}")
    public void checkResultNoLongerStartsWithLorem(final String subString) {
        generateResultPage = pageFactoryManager.getGenerateResultPage();
        generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertNotEquals(generateResultPage.getFirstParagraph().getText().
                substring(BEGIN_INDEX_SUBSTRING, END_INDEX_SUBSTRING), subString);

    }

    @Then("User checks the generation {int} times and gets the average number of paragraphs containing the word {string}")
    public void checkTheNumberOfParagraphs(int arg, final String word) {

        double actualNumberParagraphs = 0;
        for (int i = 0; i < arg; i++) {
            generateResultPage = pageFactoryManager.getGenerateResultPage();
            generateResultPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
            actualNumberParagraphs = actualNumberParagraphs + generateResultPage.getListOfParagraphs().stream().filter(y -> y.getText().contains(word)).count();
            generateResultPage.refreshPage();


        }

        assertEquals(actualNumberParagraphs / arg < MAX_NUMBER_OF_PARAGRAPH, actualNumberParagraphs / arg >= MIN_NUMBER_OF_PARAGRAPH);
    }
    
    @After
    public void tearDown() {
        driver.close();
    }


}