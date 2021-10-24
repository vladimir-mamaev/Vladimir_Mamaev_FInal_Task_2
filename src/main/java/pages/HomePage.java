package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Pyccкий']")
    private WebElement russianLink;
    @FindBy(xpath = "//input[@value='Generate Lorem Ipsum']")
    private WebElement generateButton;
    @FindBy(xpath = "//input[@name='amount']")
    private WebElement amountInput;
    @FindBy(xpath = "//label[@for='start']")
    private WebElement startWithLoremCheckbox;

    public void clickOnStartWithLoremCheckbox() {
        startWithLoremCheckbox.click();
    }

    public void enterAmount(final String amount) {
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void clickOnGenerateButton() {
        generateButton.click();
    }

    public void clickOnRussianLink() {
        russianLink.click();
    }

    public void openHomePage(String url) {
        driver.get(url);
    }
}



