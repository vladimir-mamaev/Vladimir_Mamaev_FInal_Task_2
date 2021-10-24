package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RussianPage extends BasePage {
    @FindBy(xpath = "//p[contains(text(), 'часто используемый в печати и вэб-дизайне')]")
    private WebElement firstParagraph;

    public WebElement getFirstParagraph() {
        return firstParagraph;
    }

    public RussianPage(WebDriver driver) {
        super(driver);
    }

}
